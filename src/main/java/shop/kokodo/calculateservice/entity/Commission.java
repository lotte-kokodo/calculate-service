package shop.kokodo.calculateservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class Commission extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commission_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "commission", fetch = FetchType.LAZY)
    private Calculate calculate;

    private Long basic;

    private Long salesPromotion;

    private Long firstPaymentDelivery;

    private Long deliverySupport;

    private Long discountSupport;

    private Long mediumCompanyCostRefund;

    private Long etc;
}
