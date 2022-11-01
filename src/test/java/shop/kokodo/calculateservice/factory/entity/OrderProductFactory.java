package shop.kokodo.calculateservice.factory.entity;

import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.entity.OrderProduct;

/**
 * packageName    : shop.kokodo.calculateservice.factory.entity
 * fileName       : OrderProductFactory
 * author         : namhyeop
 * date           : 2022/10/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/27        namhyeop       최초 생성
 */
public class OrderProductFactory {

    public static OrderProduct createOrderProduct() {
        return new OrderProduct(null, 1L, 2L, 2, 25000);
    }
    public static OrderProduct createOrderProduct(Long id) {
        return new OrderProduct(id,null, 1L, 2L, 2, 25000);
    }
    public static OrderProduct createOrderProduct(Order order) {
        return new OrderProduct(order, 1L, 2L, 2, 25000);
    }

    public static OrderProduct createOrderProduct(Order order, Long memberId, Long productId, Integer qty, Integer unitPrice) {
        return new OrderProduct(order, memberId, productId, qty, unitPrice);
    }
}
