package shop.kokodo.calculateservice.factory.entity;

import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.enums.calculate.CommissionType;

/**
 * packageName    : shop.kokodo.calculateservice.factory.entity
 * fileName       : CommissionFactory
 * author         : namhyeop
 * date           : 2022/10/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/04        namhyeop       최초 생성
 */
public class CommissionFactory {
    public static Commission createCommission(){
        return new Commission(null, 1L, Double.parseDouble(CommissionType.BASIC.getValue()), Double.parseDouble(CommissionType.SALES_PROMOTION.getValue()), Double.parseDouble(CommissionType.FIRST_PAYMENT_DELIVERY.getValue()), Double.parseDouble(CommissionType.DELIVERY_SUPPORT.getValue()), Double.parseDouble(CommissionType.DISCOUNT_SUPPORT.getValue()), Double.parseDouble(CommissionType.MEDIUM_COMPANY_COST_REFUND.getValue()), Double.parseDouble(CommissionType.ETC.getValue()));
    }
}
