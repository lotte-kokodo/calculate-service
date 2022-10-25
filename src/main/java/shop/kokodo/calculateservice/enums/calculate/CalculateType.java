package shop.kokodo.calculateservice.enums.calculate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.kokodo.calculateservice.enums.EnumType;

/**
 * packageName    : shop.kokodo.calculateservice.enums.calculate
 * fileName       : CalculateType
 * author         : namhyeop
 * date           : 2022/09/29
 * description    :
 * CalculateType Enum Value
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/29        namhyeop       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum CalculateType implements EnumType {

    FINAL_AMOUNT_CALCULATE("최종액 정산"),
    MAIN_CALCULATE("주정산");

    private String value;

    CalculateType(String value) {
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
