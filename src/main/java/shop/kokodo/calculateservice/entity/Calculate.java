package shop.kokodo.calculateservice.entity;

import lombok.*;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//@ToString
@Builder
public class Calculate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calculate_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "commission_id")
    private Commission commission;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CalculateType calculateType;

    @Column(nullable = false)
    private String supportRate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProvideStatus provideStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WithdrawalMethod withdrawalMethod;

    @Column(nullable = false)
    private Long finalPaymentCost;

    @Column(nullable = false)
    private Long sellerId;

    public void setCommission(Commission commission) {
        this.commission = commission;
        commission.changeCalculate(this);
    }

    public static Calculate createCalculate() {
       return new Calculate();
    }

    public static Calculate createCalculate(Commission commission, Long cost) {
        System.out.println("commission = " + commission);
        Calculate calculate = Calculate.builder()
                .commission(commission)
                .calculateType(CalculateType.MAIN_CALCULATE)
                .supportRate("100%")
                .withdrawalMethod(WithdrawalMethod.BASIC_WITHDRAWAL)
                .provideStatus(ProvideStatus.PROVIDE_SUCCESS)
                .sellerId(commission.getSellerId())
                .finalPaymentCost(cost)
                .build();
        calculate.setCommission(commission);
        return calculate;
    }

    public Calculate(Commission commission, CalculateType calculateType, String supportRate, ProvideStatus provideStatus, WithdrawalMethod withdrawalMethod, Long finalPaymentCost, Long sellerId) {
        setCommission(commission);
        this.commission = commission;
        this.calculateType = calculateType;
        this.supportRate = supportRate;
        this.provideStatus = provideStatus;
        this.withdrawalMethod = withdrawalMethod;
        this.finalPaymentCost = finalPaymentCost;
        this.sellerId = sellerId;
    }
}
