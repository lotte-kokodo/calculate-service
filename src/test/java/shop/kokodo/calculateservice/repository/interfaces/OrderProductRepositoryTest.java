package shop.kokodo.calculateservice.repository.interfaces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.entity.OrderProduct;
import shop.kokodo.calculateservice.repository.order.OrderRepository;
import shop.kokodo.calculateservice.repository.orderproduct.OrderProductRepository;

import java.util.List;

import static shop.kokodo.calculateservice.factory.entity.OrderFactory.createOrder;

/**
 * packageName    : shop.kokodo.calculateservice.repository.interfaces
 * fileName       : OrderProductRepositoryTest
 * author         : namhyeop
 * date           : 2022/10/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/15        namhyeop       최초 생성
 */
@SpringBootTest
public class OrderProductRepositoryTest {
    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    public void before() {
        orderProductRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void findProductIdByOrderId() throws Exception {
        //given
        Order order = createOrder();
        OrderProduct orderProduct1 = new OrderProduct(1L, order, 1L, 2L, 30, 21900);
        OrderProduct orderProduct2 = new OrderProduct(2L, order, 1L, 3L, 30, 18900);
        OrderProduct orderProduct3 = new OrderProduct(3L, order, 1L, 4L, 30, 20900);

        //when
        orderRepository.save(order);
        orderProductRepository.save(orderProduct1);
        orderProductRepository.save(orderProduct2);
        orderProductRepository.save(orderProduct3);

        List<Long> productIdByOrderId = orderProductRepository.findProductIdByOrderId(1L);
        //then
        Assertions.assertThat(productIdByOrderId.get(0)).isEqualTo(1L);
        Assertions.assertThat(productIdByOrderId.get(1)).isEqualTo(2L);
        Assertions.assertThat(productIdByOrderId.get(2)).isEqualTo(3L);
    }
}
