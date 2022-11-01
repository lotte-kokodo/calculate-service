package shop.kokodo.calculateservice.enums.order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.kokodo.calculateservice.enums.EnumType;

/**
 * packageName    : shop.kokodo.calculateservice.enums
 * fileName       : OrderStatus
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum OrderStatus implements EnumType {

    ORDER_SUCCESS("주문/결제 완료"),
    PURCHASE_CONFIRM("구매 확정"),
    REFUND_PROCESS("환불 진행");

    private String value;

    OrderStatus(String value){
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
