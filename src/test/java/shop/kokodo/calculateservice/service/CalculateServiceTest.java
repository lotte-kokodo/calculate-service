package shop.kokodo.calculateservice.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.kokodo.calculateservice.dto.CommissionPolicyDto;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.enums.calculate.CommissionType;
import shop.kokodo.calculateservice.exception.CalculateNotFoundException;
import shop.kokodo.calculateservice.repository.calculate.CalculateRepository;
import shop.kokodo.calculateservice.repository.commission.CommissionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.kokodo.calculateservice.factory.entity.OrderFactory.createOrder;

/**
 * packageName    : shop.kokodo.calculateservice.service
 * fileName       : CalculateServiceTest
 * author         : namhyeop
 * date           : 2022/10/05
 * description    :
 * 정산시스템 로직 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/05        namhyeop       최초 생성
 */
@SpringBootTest
class CalculateServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    CalculateService calculateService;
    @Autowired
    CalculateRepository calculateRepository;
    @Autowired
    CommissionRepository commissionRepository;

    @BeforeEach
    public void before() {
        commissionRepository.deleteAll();
        calculateRepository.deleteAll();
    }

    @Test
    public void beforeFetchClientCalculateTest() throws Exception {
        commissionRepository.deleteAllInBatch();

//        //1.주문을 먼저 찾는다.
        Order order = createOrder();

        //(1)통합 테스트 진행시 주석 해제
//                Long sellerId = productService.getSellerId(o.getId());
        //2.주문 아이디를 활용하여 상품 MS와 조인하여 판매자 아이디를 구한다.
        Long sellerId = 1L;
        //(1)통합 테스트시 주석해제
//        CommissionPolicyDto commissionPolicy = sellerService.findCommissionPolicy(sellerId);
        //3.Seller MS와의 FeginClient를 사용하여 판매자이디를 전달하여 판매자의 수수로 정책을 획득한다.
        CommissionPolicyDto commissionPolicy = new CommissionPolicyDto(1L, Double.parseDouble(CommissionType.BASIC.getValue()), Double.parseDouble(CommissionType.SALES_PROMOTION.getValue()), Double.parseDouble(CommissionType.FIRST_PAYMENT_DELIVERY.getValue()), Double.parseDouble(CommissionType.DELIVERY_SUPPORT.getValue()), Double.parseDouble(CommissionType.DISCOUNT_SUPPORT.getValue()), Double.parseDouble(CommissionType.MEDIUM_COMPANY_COST_REFUND.getValue()), Double.parseDouble(CommissionType.ETC.getValue()));
        Map<String, Object> commissionAndCost = calculateService.getCommission(commissionPolicy, order.getTotalPrice());

        Long cost = (Long) commissionAndCost.get("cost");
        Commission commission = (Commission) commissionAndCost.get("commission");

        Calculate calculate = Calculate.createCalculate(commission, cost);

        Calculate saveCalculate = calculateService.saveCalculate(calculate);
        Commission saveCommission = saveCalculate.getCommission();
        System.out.println("saveCommission = " + saveCommission);
        List<Calculate> findCalculateList = calculateRepository.findBySellerId(1L).orElseThrow(CalculateNotFoundException::new);
        Commission findCommission = commissionRepository.findByCalculate(saveCalculate);
        System.out.println("findCommission = " + findCommission);
        assertThat(saveCalculate).isEqualTo(calculate);
        assertThat(saveCalculate.getId()).isEqualTo(findCalculateList.get(0).getId());
        System.out.println("saveCommission = " + saveCommission.getId());
        System.out.println("findCom = " + findCommission);
//        assertThat(saveCommission.getId()).isEqualTo(findCommission.getId());

        assertThat(saveCalculate).isNotEqualTo(findCalculateList);
        assertThat(saveCommission).isNotEqualTo(findCommission);
    }
}