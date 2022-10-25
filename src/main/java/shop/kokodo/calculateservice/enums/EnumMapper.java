package shop.kokodo.calculateservice.enums;

import shop.kokodo.calculateservice.dto.EnumValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * packageName    : shop.kokodo.calculateservice.utils
 * fileName       : EnumMapper
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * EnumType의 자료를 사용하기 쉽해 존재하는 EnumMapper
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */

public class EnumMapper {

    private Map<String, List<EnumValue>> factory = new HashMap<>();

    private List<EnumValue> toEnumValues(Class<? extends EnumType> e) {
        return Arrays.stream(e.getEnumConstants())
                .map(EnumValue::new)
                .collect(Collectors.toList());
    }

    public void put(String key, Class<? extends EnumType> e) {
        factory.put(key, toEnumValues(e));
    }

    public Map<String, List<EnumValue>> getAll() {
        return factory;
    }

    public Map<String, List<EnumValue>> get(String keys) {
        return Arrays.stream(keys.split(","))
                .collect(Collectors.toMap(Function.identity(), key -> factory.get(key)));
    }
}
