package shop.kokodo.calculateservice.enums.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import shop.kokodo.calculateservice.dto.EnumValue;
import shop.kokodo.calculateservice.enums.EnumMapper;
import shop.kokodo.calculateservice.enums.EnumType;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.CommissionType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;
import shop.kokodo.calculateservice.enums.calculate.WithdrawalMethod;
import shop.kokodo.calculateservice.enums.order.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : shop.kokodo.calculateservice.enums.config
 * fileName       : EnumMapperConfigTest
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * EnumMapper 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
@SpringBootTest
class EnumMapperConfigTest {

    @Test
    public void checkEnumModelType() throws Exception {
        List<EnumType> enumTypes = new ArrayList<>();
        enumTypes.add(OrderStatus.ORDER_SUCCESS);
        enumTypes.add(OrderStatus.PURCHASE_CONFIRM);
        enumTypes.add(OrderStatus.REFUND_PROCESS);

        //when, then
        assertThat(enumTypes.get(0).getValue()).isEqualTo("주문/결제 완료");
        assertThat(enumTypes.get(1).getValue()).isEqualTo("구매 확정");
        assertThat(enumTypes.get(2).getValue()).isEqualTo("환불 진행");
    }

    @Test
    public void CalculateTypeEnumMakeValeTest() throws Exception {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("CalculateType", CalculateType.class);

        Map<String, List<EnumValue>> calculateType = enumMapper.get("CalculateType");
        List<EnumValue> calculateTypeList = calculateType.get("CalculateType");

        assertThat(calculateTypeList.get(0).getValue()).isEqualTo("최종액 정산");
        assertThat(calculateTypeList.get(1).getValue()).isEqualTo("주정산");
    }

    @Test
    public void CommissionTypeEnumMakeValeTest() throws Exception {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("CommissionType", CommissionType.class);

        Map<String, List<EnumValue>> commissionType = enumMapper.get("CommissionType");
        List<EnumValue> commissionList = commissionType.get("CommissionType");

        assertThat(commissionList.get(0).getValue()).isEqualTo("0.05");
        assertThat(commissionList.get(1).getValue()).isEqualTo("0.08");
        assertThat(commissionList.get(2).getValue()).isEqualTo("0.03");
        assertThat(commissionList.get(3).getValue()).isEqualTo("0.5");
        assertThat(commissionList.get(4).getValue()).isEqualTo("0.02");
        assertThat(commissionList.get(5).getValue()).isEqualTo("0");
    }

    @Test
    public void provideStatusEnumMakeValeTest() throws Exception {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("ProvideStatus", ProvideStatus.class);

        Map<String, List<EnumValue>> provideStatus = enumMapper.get("ProvideStatus");
        List<EnumValue> provideStatusList = provideStatus.get("ProvideStatus");

        assertThat(provideStatusList.get(0).getValue()).isEqualTo("전체");
        assertThat(provideStatusList.get(1).getValue()).isEqualTo("지급확정");
        assertThat(provideStatusList.get(2).getValue()).isEqualTo("지급예정");
        assertThat(provideStatusList.get(3).getValue()).isEqualTo("지급보류");
    }

    @Test
    public void withdrawalEnumMakeValeTest() throws Exception {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("WithdrawalMethod", WithdrawalMethod.class);

        Map<String, List<EnumValue>> WithdrawalMethod = enumMapper.get("WithdrawalMethod");
        List<EnumValue> WithdrawalMethodLIst = WithdrawalMethod.get("WithdrawalMethod");

        assertThat(WithdrawalMethodLIst.get(0).getValue()).isEqualTo("일반출금");
        assertThat(WithdrawalMethodLIst.get(1).getValue()).isEqualTo("예약출금");
        assertThat(WithdrawalMethodLIst.get(2).getValue()).isEqualTo("정기출금");
        assertThat(WithdrawalMethodLIst.get(3).getValue()).isEqualTo("셀러머니전환");
    }

    @Test
    public void OrderStatusEnumMakeValeTest() throws Exception {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("OrderStatus", OrderStatus.class);

        Map<String, List<EnumValue>> orderStatus1 = enumMapper.get("OrderStatus");
        List<EnumValue> orderStatus = orderStatus1.get("OrderStatus");

        assertThat(orderStatus.get(0).getValue()).isEqualTo("주문/결제 완료");
        assertThat(orderStatus.get(1).getValue()).isEqualTo("구매 확정");
        assertThat(orderStatus.get(2).getValue()).isEqualTo("환불 진행");
    }
}