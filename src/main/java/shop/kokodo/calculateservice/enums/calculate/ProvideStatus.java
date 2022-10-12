package shop.kokodo.calculateservice.enums.calculate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.kokodo.calculateservice.enums.EnumType;

/**
 * packageName    : shop.kokodo.calculateservice.enums.calculate
 * fileName       : PaymentStatus
 * author         : namhyeop
 * date           : 2022/09/29
 * description    :
 * ProvideStatus Enum Value
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/29        namhyeop       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum ProvideStatus implements EnumType {

    ALL("전체"),
    PROVIDE_SUCCESS("지급확정"),
    PROVIDE_SCHEDULE("지급예정"),
    PROVIDE_POSTPONE("지급보류");

    private String value;

    ProvideStatus(String value) {
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
