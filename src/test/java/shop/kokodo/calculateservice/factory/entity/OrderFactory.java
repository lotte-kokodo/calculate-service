package shop.kokodo.calculateservice.factory.entity;

import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.enums.calculate.CommissionType;
import shop.kokodo.calculateservice.enums.order.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.factory.entity
 * fileName       : OrderFactory
 * author         : namhyeop
 * date           : 2022/10/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/04        namhyeop       최초 생성
 */
public class OrderFactory {
    public static Order createOrder(){
        return new Order((Long) null, 1L, OrderStatus.ORDER_SUCCESS, "광나루 1-1", "띠용1", 50000L, LocalDateTime.now());
    }
}
