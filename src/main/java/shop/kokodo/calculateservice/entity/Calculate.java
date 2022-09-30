package shop.kokodo.calculateservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;
import shop.kokodo.calculateservice.enums.calculate.WithdrawalMethod;

import javax.persistence.*;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calculate extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "calculate_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "commission_id")
    private Commission commission;

    @Enumerated(EnumType.STRING)
    private CalculateType calculateType;

    private String supportRate;

    @Enumerated(EnumType.STRING)
    private ProvideStatus provideStatus;

    @Enumerated(EnumType.STRING)
    private WithdrawalMethod withdrawalMethod;

    private Long finalPaymentCost;

    private Long sellerId;

    public static Calculate createCalculate(Commission commission, Long cost){
        return Calculate.builder()
                .commission(commission)
                .calculateType(CalculateType.MAIN_CALCULATE)
                .supportRate("100%")
                .withdrawalMethod(WithdrawalMethod.BASIC_WITHDRAWAL)
                .finalPaymentCost(cost)
                .build();
    }
}
