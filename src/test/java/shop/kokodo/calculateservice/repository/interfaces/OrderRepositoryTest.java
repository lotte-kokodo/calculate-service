//package shop.kokodo.calculateservice.repository.interfaces;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import shop.kokodo.calculateservice.TestContext;
//import shop.kokodo.calculateservice.entity.Order;
//import shop.kokodo.calculateservice.repository.order.OrderRepository;
//import shop.kokodo.calculateservice.repository.orderproduct.OrderProductRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static shop.kokodo.calculateservice.factory.entity.OrderFactory.createOrder;
//import static shop.kokodo.calculateservice.factory.entity.OrderProductFactory.createOrderProduct;
//
//@SpringBootTest
//class OrderRepositoryTest extends TestContext{
//
//    @Autowired
//    OrderRepository orderRepository;
//    @Autowired
//    OrderProductRepository orderProductRepository;
//    private Order order1;
//
//    @BeforeEach
//    public void beforeEach(){
//        order1 = orderRepository.save(createOrder(1L, List.of(createOrderProduct())));
//    }
//
//    @DisplayName("주문 저장 테스트")
//    @Test
//    public void saveOrder() throws Exception {
//        //given
//        //when
//        Optional<Order> order = orderRepository.findById(order1.getId());
//        //then
//        assertThat(order.get().getId()).isEqualTo(order1.getId());
//    }
//}