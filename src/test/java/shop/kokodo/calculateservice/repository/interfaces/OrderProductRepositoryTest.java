package shop.kokodo.calculateservice.repository.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import shop.kokodo.calculateservice.TestContext;
import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.entity.OrderProduct;
import shop.kokodo.calculateservice.repository.order.OrderRepository;
import shop.kokodo.calculateservice.repository.orderproduct.OrderProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.kokodo.calculateservice.factory.entity.OrderFactory.createOrder;
import static shop.kokodo.calculateservice.factory.entity.OrderProductFactory.createOrderProduct;

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
public class OrderProductRepositoryTest extends TestContext {
    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    OrderRepository orderRepository;
    @PersistenceContext
    EntityManager em;

    public OrderProduct orderProduct1;
    public OrderProduct orderProduct2;
    public OrderProduct orderProduct3;
    public Order order;

    @BeforeEach
    public void beforeEach(){
        orderProduct1 = createOrderProduct();
        orderProduct2 = createOrderProduct();
        orderProduct3 = createOrderProduct();
        order = orderRepository.save(createOrder( 1L, List.of(orderProduct1, orderProduct2, orderProduct3)));

    }

    @DisplayName("주문상품 조회")
    @Test
    public void findProductIdByOrderId() throws Exception {
        //given
        //when
        List<Long> productIdByOrderId = orderProductRepository.findProductIdByOrderId(order.getId());

        //then
        assertThat(productIdByOrderId.get(0)).isEqualTo(orderProduct1.getId());
        assertThat(productIdByOrderId.get(1)).isEqualTo(orderProduct2.getId());
        assertThat(productIdByOrderId.get(2)).isEqualTo(orderProduct3.getId());
    }
}
