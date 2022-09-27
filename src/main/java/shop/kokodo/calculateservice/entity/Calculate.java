package shop.kokodo.calculateservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.entity
 * fileName       : calculate
 * author         : namhyeop
 * date           : 2022/09/26
 * description    :
 * Calculate Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/26        namhyeop       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calculate extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "calculate_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "commission_id")
    private Commission commission;

    private String type;

    private String supportRate;

    private String withdrawalMethod;

    private Long finalPaymentCost;

    private boolean paymentStatus;

    private LocalDateTime date;

    private LocalDateTime purchaseConfirmationDuration;
}
