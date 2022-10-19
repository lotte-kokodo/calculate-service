package shop.kokodo.calculateservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;

/**
 * packageName    : shop.kokodo.calculateservice.repository.calculate
 * fileName       : SaleListDto
 * author         : namhyeop
 * date           : 2022/10/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/18        namhyeop       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class SaleListDto {

    private Long sellerId;
    private Long basic;
    private Long salesPromotion;
    private Long firstPaymentDelivery;
    private Long deliverySupport;
    private Long discountSupport;
    private Long mediumCompanyCostRefund;
    private Long etc;

    private CalculateType calculateType;
    private Long finalPaymentCost;

    @QueryProjection
    public SaleListDto(Long sellerId, Long basic, Long salesPromotion, Long first_paymentDelivery, Long deliverySupport, Long discountSupport, Long mediumCompanyCostRefund, Long etc, CalculateType calculateType, Long finalPaymentCost) {
        this.sellerId = sellerId;
        this.basic = basic;
        this.salesPromotion = salesPromotion;
        this.firstPaymentDelivery = first_paymentDelivery;
        this.deliverySupport = deliverySupport;
        this.discountSupport = discountSupport;
        this.mediumCompanyCostRefund = mediumCompanyCostRefund;
        this.etc = etc;

        this.calculateType = calculateType;
        this.finalPaymentCost = finalPaymentCost;
    }
}
