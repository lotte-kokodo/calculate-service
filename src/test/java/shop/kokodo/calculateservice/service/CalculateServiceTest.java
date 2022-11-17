package shop.kokodo.calculateservice.service;


import com.querydsl.core.Tuple;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.repository.calculate.CalculateRepository;
import shop.kokodo.calculateservice.repository.commission.CommissionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @DisplayName("대시보드 카드 정보")
    @Test
    public void dashBoardExpectMoney(){
        LocalDateTime today = LocalDateTime.of(2022, 11, 11, 00,00);
        LocalDateTime toDayStartTime = today.minusDays(7);
        Long sellerId = 1L;

        List<Calculate> bySellerId = calculateRepository.findBySellerId(1L);
        for (Calculate calculate : bySellerId) {
            LocalDateTime createdDate = calculate.getCreatedDate();
            System.out.println("createdDate = " + createdDate);
            System.out.println("calculate = " + calculate);
        }

        Long weakExpectMoney = calculateRepository.findWeakExpectMoney(sellerId, toDayStartTime, today);
        List<Calculate> sellerIdList = calculateRepository.findBySellerId(1L);
        Long lastWeakExpectMoney = sellerIdList.get(3).getFinalPaymentCost() + sellerIdList.get(4).getFinalPaymentCost();

        if (weakExpectMoney == null){
            weakExpectMoney = 0L;
        }

        if (lastWeakExpectMoney == null){
            lastWeakExpectMoney = 0L;
        }

        System.out.println("weakExpectMoney = " + weakExpectMoney);
        System.out.println("lastWeakExpectMoney = " + lastWeakExpectMoney);

        String percentInfo = "";
        if(weakExpectMoney > lastWeakExpectMoney){
            percentInfo += "^";
        }else{
            percentInfo += "v";
        }

        Long diffValue = Math.abs(weakExpectMoney - lastWeakExpectMoney);
        long total = weakExpectMoney + lastWeakExpectMoney;
        long weakExpectMoneyPer = (long)((double) weakExpectMoney / total * 100.0);
        long lastWeakExpectMoneyPer = (long)((double)lastWeakExpectMoney / total * 100.0);
        long percentDiff = Math.abs(weakExpectMoneyPer - lastWeakExpectMoneyPer);

        percentInfo += diffValue + " ("+String.valueOf(percentDiff) + "%"+")";

        System.out.println("total = " + total);
        System.out.println("weakExpectMoneyPer = " + weakExpectMoneyPer);
        System.out.println("lastWeakExpectMoneyPer = " + lastWeakExpectMoneyPer);
        System.out.println("percentDiff = " + percentDiff);

        Assertions.assertThat(0).isEqualTo((long)weakExpectMoneyPer);
        Assertions.assertThat(100).isEqualTo((long)lastWeakExpectMoneyPer);
        Assertions.assertThat(100).isEqualTo((long)percentDiff);

        Assertions.assertThat(weakExpectMoney).isEqualTo(0);
        Assertions.assertThat(percentInfo).isEqualTo("v25000 (100%)");
    }

//    @DisplayName("년도 기준 월별 정산 정보")
//    @Test
//    public void getAnnualSaleList() {
//        Long sellerId = 1L;
//        LocalDateTime startDate = LocalDateTime.of(LocalDateTime.now().getYear(), 01, 01, 00,00);
//        LocalDateTime endDate = LocalDateTime.of(LocalDateTime.now().getYear(), 12, 31, 00,00);
//        List<Tuple> annualSale = calculateRepository.getAnnualSale(sellerId, startDate, endDate);
//        List<Long> annualInfo = Arrays.asList(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);
//        for(int idx = 0; idx < annualSale.size(); idx++){
//            String yearInfo = annualSale.get(idx).get(0, String.class);
//            int monthInfo = Integer.parseInt(yearInfo.substring(5));
//            Long saleMount = annualSale.get(idx).get(1, Long.class);
//            annualInfo.set(monthInfo, saleMount);
//        }
//        System.out.println("annualInfo = " + annualInfo);
//    }

//    @Test
//    public void beforeFetchClientCalculateTest() throws Exception {
//        commissionRepository.deleteAllInBatch();
//
////        //1.주문을 먼저 찾는다.
//        Order order = createOrder();
//
//        //(1)통합 테스트 진행시 주석 해제
////                Long sellerId = productService.getSellerId(o.getId());
//        //2.주문 아이디를 활용하여 상품 MS와 조인하여 판매자 아이디를 구한다.
//        Long sellerId = 1L;
//        //(1)통합 테스트시 주석해제
////        CommissionPolicyDto commissionPolicy = sellerService.findCommissionPolicy(sellerId);
//        //3.Seller MS와의 FeginClient를 사용하여 판매자이디를 전달하여 판매자의 수수로 정책을 획득한다.
//        CommissionPolicyDto commissionPolicy = new CommissionPolicyDto(1L, Double.parseDouble(CommissionType.BASIC.getValue()), Double.parseDouble(CommissionType.SALES_PROMOTION.getValue()), Double.parseDouble(CommissionType.FIRST_PAYMENT_DELIVERY.getValue()), Double.parseDouble(CommissionType.DELIVERY_SUPPORT.getValue()), Double.parseDouble(CommissionType.DISCOUNT_SUPPORT.getValue()), Double.parseDouble(CommissionType.MEDIUM_COMPANY_COST_REFUND.getValue()), Double.parseDouble(CommissionType.ETC.getValue()));
//        Map<String, Object> commissionAndCost = calculateService.getCommission(commissionPolicy, order.getTotalPrice());
//
//        Long cost = (Long) commissionAndCost.get("cost");
//        Commission commission = (Commission) commissionAndCost.get("commission");
//
//        Calculate calculate = Calculate.createCalculate(commission, cost);
//
//        Calculate saveCalculate = calculateService.saveCalculate(calculate);
//        Commission saveCommission = saveCalculate.getCommission();
//        System.out.println("saveCommission = " + saveCommission);
//        List<Calculate> findCalculateList = calculateRepository.findBySellerId(1L);
//        Commission findCommission = commissionRepository.findByCalculate(saveCalculate);
//        System.out.println("findCommission = " + findCommission);
//        assertThat(saveCalculate).isEqualTo(calculate);
//        assertThat(saveCalculate.getId()).isEqualTo(findCalculateList.get(0).getId());
//        System.out.println("saveCommission = " + saveCommission.getId());
//        System.out.println("findCom = " + findCommission);
////        assertThat(saveCommission.getId()).isEqualTo(findCommission.getId());
//
//        assertThat(saveCalculate).isNotEqualTo(findCalculateList);
//        assertThat(saveCommission).isNotEqualTo(findCommission);
//    }
}