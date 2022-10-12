package shop.kokodo.calculateservice.repository.interfaces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.repository.order.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static shop.kokodo.calculateservice.factory.entity.OrderFactory.createOrder;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void saveOrder() throws Exception {
        //given
        Order order = createOrder();
        Order saveOrder = orderRepository.save(order);
        //when
        clear();
        //then
        Assertions.assertThat(saveOrder).isEqualTo(order);
    }

    void clear() {
        em.flush();
        em.clear();
    }
}