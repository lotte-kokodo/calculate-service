package shop.kokodo.calculateservice.enums.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.kokodo.calculateservice.enums.EnumMapper;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;
import shop.kokodo.calculateservice.enums.calculate.WithdrawalMethod;
import shop.kokodo.calculateservice.enums.order.OrderStatus;

/**
 * packageName    : shop.kokodo.calculateservice.enums.config
 * fileName       : EnumMapperConfig
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * EnumType의 자료를 사용하기 쉽해 존재하는 EnumMapper를 등록하는 Config
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
@Configuration
public class EnumMapperConfig {
    @Bean
    public EnumMapper enumMapper() {

        EnumMapper enumMapper = new EnumMapper();

        enumMapper.put("OrderStatus", OrderStatus.class);
        enumMapper.put("CalculateType", CalculateType.class);
        enumMapper.put("ProvideStatus", ProvideStatus.class);
        enumMapper.put("WithdrawalMethodType", WithdrawalMethod.class);

        return enumMapper;
    }
}
