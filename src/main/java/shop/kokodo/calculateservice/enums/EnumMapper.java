package shop.kokodo.calculateservice.enums;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import shop.kokodo.calculateservice.dto.EnumValue;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * packageName    : shop.kokodo.calculateservice.utils
 * fileName       : EnumMapper
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
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

    //Function identity는 인자로 받아온 값의 타입을 반환한다.
    public Map<String, List<EnumValue>> get(String keys) {

//        Map<String, List<EnumValue>> result = new LinkedHashMap<>();
//        for (String key : keys.split(",")) {
//            result.put(key, factory.get(key));
//        }

        return Arrays.stream(keys.split(","))
                .collect(Collectors.toMap(Function.identity(), key -> factory.get(key)));
    }
}
