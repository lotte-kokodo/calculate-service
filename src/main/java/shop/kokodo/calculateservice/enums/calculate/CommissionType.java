package shop.kokodo.calculateservice.enums.calculate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.kokodo.calculateservice.enums.EnumType;

/**
 * packageName    : shop.kokodo.calculateservice.enums.calculate
 * fileName       : CommissionType
 * author         : namhyeop
 * date           : 2022/09/30
 * description    :
 * CommissionType Enum Value
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        namhyeop       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum CommissionType implements EnumType {

    BASIC("0.05"),
    SALES_PROMOTION("0.08"),
    FIRST_PAYMENT_DELIVERY("0.03"),
    DELIVERY_SUPPORT("0.5"),
    DISCOUNT_SUPPORT("0.02"),
    ETC("0"),
    MEDIUM_COMPANY_COST_REFUND("0.01");

    private String value;

    CommissionType(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
