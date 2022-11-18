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
@Builder
public class Commission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commission_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "commission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Calculate calculate;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private Long basic;

    @Column(nullable = false)
    private Long salesPromotion;
    @Column(nullable = false)
    private Long firstPaymentDelivery;

    @Column(nullable = false)
    private Long deliverySupport;

    @Column(nullable = false)
    private Long discountSupport;

    @Column(nullable = false)
    private Long mediumCompanyCostRefund;

    @Column(nullable = false)
    private Long etc;

    public void changeCalculate(Calculate calculate) {
        this.calculate = calculate;
    }

    public static Commission createCommission(Calculate calculate, Long sellerId, Long basic, Long salesPromotion, Long firstPaymentDelivery, Long deliverySupport, Long discountSupport, Long mediumCompanyCostRefund, Long etc) {
        return Commission.builder()
                .calculate(calculate)
                .sellerId(sellerId)
                .basic(basic)
                .salesPromotion(salesPromotion)
                .firstPaymentDelivery(firstPaymentDelivery)
                .deliverySupport(deliverySupport)
                .discountSupport(discountSupport)
                .mediumCompanyCostRefund(mediumCompanyCostRefund)
                .etc(etc)
                .build();
    }

    public Commission(Calculate calculate, Long sellerId, Long basic, Long salesPromotion, Long firstPaymentDelivery, Long deliverySupport, Long discountSupport, Long mediumCompanyCostRefund, Long etc) {
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
