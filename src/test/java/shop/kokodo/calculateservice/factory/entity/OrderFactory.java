package shop.kokodo.calculateservice.factory.entity;

import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.entity.OrderProduct;
import shop.kokodo.calculateservice.enums.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

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
    public static Order createOrder() {
        return new Order(1L, OrderStatus.ORDER_SUCCESS, "광나루 1-1", "띠용1", 50000, LocalDateTime.now(), null);
    }

    public static Order createOrder(Long memberId, List<OrderProduct> orderProducts) {
        return new Order(memberId, OrderStatus.ORDER_SUCCESS, "광나루 1-1", "띠용1", 50000, LocalDateTime.now(), orderProducts);
    }

    public static Order createOrder(Long id, Long memberId, List<OrderProduct> orderProducts) {
        return new Order(id, memberId, OrderStatus.ORDER_SUCCESS, "광나루 1-1", "띠용1", 50000, LocalDateTime.now(), orderProducts);
    }
}
