package shop.kokodo.calculateservice.dto;

import lombok.*;

/**
 * packageName    : shop.kokodo.calculateservice.entity
 * fileName       : commission
 * author         : namhyeop
 * date           : 2022/09/26
 * description    :
 * 수수로 정책 생성을 위해 필요한 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/26        namhyeop       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class CommissionPolicyDto {

    private Long sellerId;

    private Double basic;

    private Double salesPromotion;

    private Double firstPaymentDelivery;

    private Double deliverySupport;

    private Double discountSupport;

    private Double mediumCompanyCostRefund;

    private Double etc;

}
