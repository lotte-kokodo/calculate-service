package shop.kokodo.calculateservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

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
    private Long first_payment_delivery;
    private Long delivery_support;
    private Long medium_company_cost_refund;
    private Long etc;

    @QueryProjection
    public SaleListDto(Long sellerId, Long basic, Long salesPromotion, Long first_payment_delivery, Long delivery_support, Long medium_company_cost_refund, Long etc) {
        this.sellerId = sellerId;
        this.basic = basic;
        this.salesPromotion = salesPromotion;
        this.first_payment_delivery = first_payment_delivery;
        this.delivery_support = delivery_support;
        this.medium_company_cost_refund = medium_company_cost_refund;
        this.etc = etc;
    }
}
