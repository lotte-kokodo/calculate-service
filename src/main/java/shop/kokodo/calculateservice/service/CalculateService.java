package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.client.SellerServiceClient;
import shop.kokodo.calculateservice.dto.*;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.exception.CalculateListNotFoundException;
import shop.kokodo.calculateservice.exception.CalculateNotFoundException;
import shop.kokodo.calculateservice.exception.SellerFinanceNotFoundException;
import shop.kokodo.calculateservice.repository.calculate.CalculateRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static shop.kokodo.calculateservice.circuitbreaker.factory.AllCircuitBreaker.createSellerCircuitBreaker;
import static shop.kokodo.calculateservice.dto.DashBoardCardSearchInfoDto.createDashBoardCardSearchInfoDto;
import static shop.kokodo.calculateservice.entity.Calculate.createCalculate;
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
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalculateService {

    private final CalculateRepository calculateRepository;
    private final SellerServiceClient sellerServiceClient;

    private final int weak = 7;

    public CostAndCommissionDto getCommission(List<CommissionPolicyDto> commissionPolicyDtoList, Integer money) {
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

    public DashBoardCardSearchInfoDto dashBoardExpectMoney(Long id) {
        LocalDateTime lateExpectDay = getExpectDay();
        LocalDateTime toDayStartTime = lateExpectDay.minusDays(7);
        LocalDateTime lastExpectDay = getExpectDay().minusDays(7);
        LocalDateTime beforeDayStartTime = lastExpectDay.minusDays(7);
        Long weakExpectMoney = calculateRepository.findWeakExpectMoney(id, toDayStartTime, lateExpectDay);
        Long lastWeakExpectMoney = calculateRepository.findWeakExpectMoney(id, beforeDayStartTime, lastExpectDay);

        if (weakExpectMoney == null){
            weakExpectMoney = 0L;
        }

        if (lastWeakExpectMoney == null){
            lastWeakExpectMoney = 0L;
        }

        String percentInfo = "";
        if(weakExpectMoney > lastWeakExpectMoney){
            percentInfo += "^";
        }else{
            percentInfo += "v";
        }
        Long diffValue = Math.abs(weakExpectMoney - lastWeakExpectMoney);
        long total = weakExpectMoney + lastWeakExpectMoney;
        long weakExpectMoneyPer = (long)((double)weakExpectMoney / total * 100.0);
        long lastWeakExpectMoneyPer = (long)((double)lastWeakExpectMoney / total * 100.0);
        long percentDiff = Math.abs(weakExpectMoneyPer - lastWeakExpectMoneyPer);

        percentInfo += diffValue + " ("+String.valueOf(percentDiff) + "%"+")";
        return createDashBoardCardSearchInfoDto(weakExpectMoney, percentInfo);
    }

    public LocalDateTime getExpectDay() {

        LocalDateTime friDay = LocalDateTime.of(2022, 9, 30, 12, 0);
        LocalDateTime now = LocalDateTime.now();

        while (now.compareTo(friDay) > 0) {
            friDay = friDay.plusDays(weak);
        }

        return friDay;
    }

    public Page<CalculateDto> getCalculateList(CalculateSearchCondition calculateSearchCondition, Pageable pageable) {

        if (calculateSearchCondition.getProvideStatus().getKey() == "ALL") {
            calculateSearchCondition.setProvideStatus(null);
        }

        Page<CalculateDto> calculateDto = calculateRepository.searchCalculate(calculateSearchCondition, pageable);

        if(calculateDto == null){
            throw new CalculateNotFoundException("calculateService - 정산 데이터를 찾을 수 없습니다.");
        }

        return calculateDto;
    }

    @Transactional
    public void makeCalculate(List<Long> costList, List<Commission> commissionList) {
        int totalCount = costList.size();
        List<Calculate> calculates = new ArrayList<>();
        for(int i = 0; i < totalCount; i++){
            System.out.println(commissionList.get(i));
            System.out.println(costList.get(i));
            Calculate calculate = createCalculate(commissionList.get(i), costList.get(i));
            calculates.add(calculate);
        }
        calculateRepository.saveAll(calculates);
    }

    public CalculateModalDto getCalculateModal(Long sellerId, Long calculateId){
        Calculate calculate = calculateRepository.findById(calculateId).orElseThrow(CalculateListNotFoundException::new);

        if(calculate == null){
            throw new CalculateNotFoundException("calculateService - 정산 데이터를 찾을 수 없습니다.");
        }

        CircuitBreaker sellerCircuitBreaker = createSellerCircuitBreaker();
        FinanceInfoDto sellerFinanceInfo = sellerCircuitBreaker.run(() ->sellerServiceClient.getSellerFinanceInfo(sellerId), throwable -> new FinanceInfoDto());

        if (sellerFinanceInfo == null) {
            throw new SellerFinanceNotFoundException("calculateService - Feign 통신으로부터 판매자 금융 정보를 찾을 수 없습니다.");
        }

        return CalculateModalDto.toDto(calculate, sellerFinanceInfo);
    }
}
