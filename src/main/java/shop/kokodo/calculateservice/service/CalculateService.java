package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.dto.CalculateDto;
import shop.kokodo.calculateservice.dto.CalculateSearchCondition;
import shop.kokodo.calculateservice.dto.CommissionPolicyDto;
import shop.kokodo.calculateservice.dto.CostAndCommissionDto;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.exception.CalculateListNotFoundException;
import shop.kokodo.calculateservice.exception.CalculateNotFoundException;
import shop.kokodo.calculateservice.repository.calculate.CalculateRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static shop.kokodo.calculateservice.entity.Commission.createCommission;

/**
 * packageName    : shop.kokodo.calculateservice.service
 * fileName       : CalculateService
 * author         : namhyeop
 * date           : 2022/09/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        namhyeop       최초 생성
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalculateService {

    private final CalculateRepository calculateRepository;
    private final int weak = 7;

    public CostAndCommissionDto getCommission(List<CommissionPolicyDto> commissionPolicyDtoList, Long money) {
        List<Long> retCostList = new ArrayList<>();
        List<Commission> retCommissionList = new ArrayList<>();

        for (int i = 0; i < commissionPolicyDtoList.size(); i++) {
            CommissionPolicyDto commissionPolicyDto = commissionPolicyDtoList.get(0);

            Long basicCost = (long) (money * commissionPolicyDto.getBasic());
            Long salesPromotionCost = (long) (money * commissionPolicyDto.getSalesPromotion());
            Long firstPaymentDeliveryCost = (long) (money * commissionPolicyDto.getFirstPaymentDelivery());

            Long deliverySupportCost = (long) (money * commissionPolicyDto.getDeliverySupport());
            Long disCountSupportCost = (long) (money * commissionPolicyDto.getDiscountSupport());
            Long mediumCompanyCostRefundCost = (long) (money * commissionPolicyDto.getMediumCompanyCostRefund());
            Long etCost = (long) (money * commissionPolicyDto.getEtc());

            Long burdenCost = money - (basicCost + salesPromotionCost + firstPaymentDeliveryCost);
            Long supportCost = deliverySupportCost + disCountSupportCost + mediumCompanyCostRefundCost + etCost;

            Long finalPaymentCost = money - burdenCost + supportCost;
            Commission commission = createCommission(null, commissionPolicyDto.getSellerId(), basicCost, salesPromotionCost, firstPaymentDeliveryCost, deliverySupportCost, disCountSupportCost, mediumCompanyCostRefundCost, etCost);
            retCostList.add(finalPaymentCost);
            retCommissionList.add(commission);
        }

        return CostAndCommissionDto.builder().cost(retCostList).commissionList(retCommissionList).build();
    }

    public Long getExpectMoney(Long id) {

        List<Calculate> calculates = calculateRepository.findBySellerId(id);
        Long sum = calculates.stream().mapToLong(c -> c.getFinalPaymentCost()).sum();

        return sum;
    }

    public LocalDateTime getExpectDay() {

        LocalDateTime friDay = LocalDateTime.of(2022, 9, 30, 0, 0);
        LocalDateTime now = LocalDateTime.now();

        while (now.compareTo(friDay) > 0) {
            friDay = friDay.plusDays(weak);
        }

        return friDay;
    }

    public List<CalculateDto> getCalculateList(CalculateSearchCondition calculateSearchCondition) {

        if (calculateSearchCondition.getProvideStatus().getKey() == "ALL") {
            calculateSearchCondition.setProvideStatus(null);
        }

        List<CalculateDto> calculateDto = Optional.ofNullable(calculateRepository.searchCalculate(calculateSearchCondition)).orElseThrow(CalculateListNotFoundException::new);

        return calculateDto;
    }

    @Transactional
    public void makeCalculate(List<Long> costList, List<Commission> commissionList) {
        int totalCount = costList.size();
        List<Calculate> calculates = new ArrayList<>();
        for(int i = 0; i < totalCount; i++){
            calculates.add(Calculate.createCalculate(commissionList.get(i), costList.get(i)));
        }
        calculateRepository.saveAll(calculates);
    }
}
