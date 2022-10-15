package shop.kokodo.calculateservice.dto;

import lombok.Getter;
import shop.kokodo.calculateservice.enums.EnumType;

/**
 * packageName    : shop.kokodo.calculateservice.utils
 * fileName       : EnumValue
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * Enum값을 효율적으로 사용하기 위한 EnumValue 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
@Getter
public class EnumValue {

    private String key;

    private String value;

    public EnumValue(EnumType enumType) {
        this.key = enumType.getKey();
        this.value = enumType.getValue();
    }
}
