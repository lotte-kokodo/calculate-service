package shop.kokodo.calculateservice.enums.calculate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.kokodo.calculateservice.enums.EnumType;

/**
 * packageName    : shop.kokodo.calculateservice.enums.calculate
 * fileName       : WidthdrawalMethodType
 * author         : namhyeop
 * date           : 2022/09/29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/29        namhyeop       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public enum WithdrawalMethod implements EnumType {

    BASIC_WITHDRAWAL("일반출금"),
    RESERVATION_WITHDRAWAL("예약출금"),
    REGULAR_WITHDRAWAL("정기출금"),
    SELLERMONEY_CHANGE("셀러머니전환");

    private String value;

    WithdrawalMethod(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}
