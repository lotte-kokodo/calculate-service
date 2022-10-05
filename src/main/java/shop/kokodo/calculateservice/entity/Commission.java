package shop.kokodo.calculateservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * packageName    : shop.kokodo.calculateservice.entity
 * fileName       : commission
 * author         : namhyeop
 * date           : 2022/09/26
 * description    :
 * Comission Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/26        namhyeop       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//@Builder
@ToString
public class Commission extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commission_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "commission", fetch = FetchType.LAZY)
    private Calculate calculate;

    private Long sellerId;

    private Double basic;

    private Double salesPromotion;

    private Double firstPaymentDelivery;

    private Double deliverySupport;

    private Double discountSupport;

    private Double mediumCompanyCostRefund;

    private Double etc;

    public Commission(Calculate calculate, Long sellerId, Double basic, Double salesPromotion, Double firstPaymentDelivery, Double deliverySupport, Double discountSupport, Double mediumCompanyCostRefund, Double etc) {
        this.calculate = calculate;
        this.sellerId = sellerId;
        this.basic = basic;
        this.salesPromotion = salesPromotion;
        this.firstPaymentDelivery = firstPaymentDelivery;
        this.deliverySupport = deliverySupport;
        this.discountSupport = discountSupport;
        this.mediumCompanyCostRefund = mediumCompanyCostRefund;
        this.etc = etc;
    }
}
