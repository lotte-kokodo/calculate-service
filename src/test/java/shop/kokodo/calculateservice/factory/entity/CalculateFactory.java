package shop.kokodo.calculateservice.factory.entity;

import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;
import shop.kokodo.calculateservice.enums.calculate.WithdrawalMethod;

/**
 * packageName    : shop.kokodo.calculateservice.factory.entity
 * fileName       : CalculateFactory
 * author         : namhyeop
 * date           : 2022/10/07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/07        namhyeop       최초 생성
 */
public class CalculateFactory {
    public static Calculate createCalculate() {
        return new Calculate(null, CalculateType.MAIN_CALCULATE, "100%", ProvideStatus.PROVIDE_SUCCESS, WithdrawalMethod.BASIC_WITHDRAWAL, 20000L, 1L);
    }

    public static Calculate createCalculate(Commission commission) {
        return new Calculate(commission, CalculateType.MAIN_CALCULATE, "100%", ProvideStatus.PROVIDE_SUCCESS, WithdrawalMethod.BASIC_WITHDRAWAL, 30000L, 1L);
    }

    public static Calculate createCalculate(Commission commission, Long finalPaymentCost, Long sellerId) {
        return new Calculate(commission, CalculateType.MAIN_CALCULATE, "100%", ProvideStatus.PROVIDE_SUCCESS, WithdrawalMethod.BASIC_WITHDRAWAL, finalPaymentCost, sellerId);
    }
}
