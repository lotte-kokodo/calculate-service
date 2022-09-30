package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.repository.interfaces.CalculateRepository;

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

    @Transactional
    public void saveCalculate(Calculate calculate){
        calculateRepository.save(calculate);
    }

    public Long getFinalPaymentCost(Commission commission, Long money) {

        Long basicCost = money * commission.getBasic();
        Long salesPromotionCost = money * commission.getSalesPromotion();
        Long firstPaymentDeliveryCost = money * commission.getFirstPaymentDelivery();

        Long deliverySupportCost = money * commission.getDeliverySupport();
        Long disCountSupportCost = money * commission.getDiscountSupport();
        Long mediumCompanyCostRefundCost = money * commission.getMediumCompanyCostRefund();
        Long etCost = money * commission.getEtc();

        Long burdenCost = money - (basicCost + salesPromotionCost + firstPaymentDeliveryCost);
        Long supportCost = deliverySupportCost + disCountSupportCost + mediumCompanyCostRefundCost + etCost;
        Long finalPaymentCost = money - burdenCost + supportCost;

        return finalPaymentCost;
    }
}
