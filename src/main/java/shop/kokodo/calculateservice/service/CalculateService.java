package shop.kokodo.calculateservice.service;

import com.querydsl.core.Tuple;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static shop.kokodo.calculateservice.circuitbreaker.factory.AllCircuitBreaker.createSellerCircuitBreaker;
import static shop.kokodo.calculateservice.dto.DashBoardCardSearchInfoDto.createDashBoardCardSearchInfoDto;
import static shop.kokodo.calculateservice.entity.Calculate.createCalculate;
import static shop.kokodo.calculateservice.entity.Commission.createCommission;
import static shop.kokodo.calculateservice.exception.message.BatchErrorMessage.CALCULATE_DATA_NULL;
import static shop.kokodo.calculateservice.exception.message.BatchErrorMessage.SELLER_FEIGN_FINANCE_NULL;

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

            final Long basicCost = (long) (money * commissionPolicyDto.getBasic());
            final Long salesPromotionCost = (long) (money * commissionPolicyDto.getSalesPromotion());
            final Long firstPaymentDeliveryCost = (long) (money * commissionPolicyDto.getFirstPaymentDelivery());

            final Long deliverySupportCost = (long) (money * commissionPolicyDto.getDeliverySupport());
            final Long disCountSupportCost = (long) (money * commissionPolicyDto.getDiscountSupport());
            final Long mediumCompanyCostRefundCost = (long) (money * commissionPolicyDto.getMediumCompanyCostRefund());
            final Long etCost = (long) (money * commissionPolicyDto.getEtc());

            final Long burdenCost = money - (basicCost + salesPromotionCost + firstPaymentDeliveryCost);
            final Long supportCost = deliverySupportCost + disCountSupportCost + mediumCompanyCostRefundCost + etCost;

            final Long finalPaymentCost = money - burdenCost + supportCost;
            final Commission commission = createCommission(null, commissionPolicyDto.getSellerId(), basicCost, salesPromotionCost, firstPaymentDeliveryCost, deliverySupportCost, disCountSupportCost, mediumCompanyCostRefundCost, etCost);
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

        if (weakExpectMoney > lastWeakExpectMoney){
            percentInfo += "^";
        }else{
            percentInfo += "v";
        }

        Long diffValue = Math.abs(weakExpectMoney - lastWeakExpectMoney);
        long total = weakExpectMoney + lastWeakExpectMoney;
        long weakExpectMoneyPer = (long)((double)weakExpectMoney / total * 100.0);
        long lastWeakExpectMoneyPer = (long)((double)lastWeakExpectMoney / total * 100.0);
        long percentDiff = Math.abs(weakExpectMoneyPer - lastWeakExpectMoneyPer);

        percentInfo += diffValue + "("+String.valueOf(percentDiff) + "%" + ")";
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
            throw new CalculateNotFoundException(CALCULATE_DATA_NULL);
        }
        return calculateDto;
    }

    @Transactional
    public void makeCalculate(List<Long> costList, List<Commission> commissionList) {
        List<Calculate> calculateList = commissionList.stream().map((c) -> createCalculate(c, costList.get(commissionList.indexOf(c)))).collect(Collectors.toList());
        calculateRepository.saveAll(calculateList);
    }

    public CalculateModalDto getCalculateModal(Long sellerId, Long calculateId){
        Calculate calculate = calculateRepository.findById(calculateId).orElseThrow(CalculateListNotFoundException::new);

        if(calculate == null){
            throw new CalculateNotFoundException(CALCULATE_DATA_NULL);
        }

        CircuitBreaker sellerCircuitBreaker = createSellerCircuitBreaker();
        FinanceInfoDto sellerFinanceInfo = sellerCircuitBreaker.run(() ->sellerServiceClient.getSellerFinanceInfo(sellerId), throwable -> new FinanceInfoDto());

        if (sellerFinanceInfo == null) {
            throw new SellerFinanceNotFoundException(SELLER_FEIGN_FINANCE_NULL);
        }

        return CalculateModalDto.toDto(calculate, sellerFinanceInfo);
    }

    public AnnualSaleDto getAnnualSaleList(Long sellerId){
        LocalDateTime startDate = LocalDateTime.of(LocalDateTime.now().getYear(), 01, 01, 00,00);
        LocalDateTime endDate = LocalDateTime.of(LocalDateTime.now().getYear(), 12, 31, 00,00);
        List<Tuple> annualSale = calculateRepository.getAnnualSale(sellerId, startDate, endDate);

        List<Long> annualInfo = Arrays.asList(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);
        for(int idx = 0; idx < annualSale.size(); idx++){
            String yearInfo = annualSale.get(idx).get(0, String.class);
            int monthInfo = Integer.parseInt(yearInfo.substring(5));
            Long saleMount = annualSale.get(idx).get(1, Long.class);
            annualInfo.set(monthInfo, saleMount);
        }
        return AnnualSaleDto.createAnnualSaleDto(annualInfo);
    }
}
