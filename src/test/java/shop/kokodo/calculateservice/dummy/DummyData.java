package shop.kokodo.calculateservice.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.entity.OrderProduct;
import shop.kokodo.calculateservice.repository.calculate.CalculateRepository;
import shop.kokodo.calculateservice.repository.commission.CommissionRepository;
import shop.kokodo.calculateservice.repository.order.OrderRepository;
import shop.kokodo.calculateservice.repository.orderproduct.OrderProductRepository;

import java.util.ArrayList;
import java.util.List;

import static shop.kokodo.calculateservice.factory.entity.CalculateFactory.createCalculate;
import static shop.kokodo.calculateservice.factory.entity.CommissionFactory.createCommission;
import static shop.kokodo.calculateservice.factory.entity.OrderFactory.createOrder;
import static shop.kokodo.calculateservice.factory.entity.OrderProductFactory.createOrderProduct;


/**
 * packageName    : shop.kokodo.calculateservice.dummy
 * fileName       : calculateDummy
 * author         : namhyeop
 * date           : 2022/10/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/26        namhyeop       최초 생성
 */
@Component
@ActiveProfiles("test")
public class DummyData implements CommandLineRunner {

    @Autowired
    CalculateRepository calculateRepository;
    @Autowired
    CommissionRepository commissionRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderProductRepository orderProductRepository;

    public static Calculate CALCULATE1;
    public static Calculate CALCULATE2;
    public static Calculate CALCULATE3;
    public static Commission COMMISSION1;
    public static Commission COMMISSION2;
    public static Commission COMMISSION3;
    public static Order ORDER1;
    public static Order ORDER2;
    public static Order ORDER3;
    public static OrderProduct ORDERPRODUCT1;
    public static OrderProduct ORDERPRODUCT2;
    public static OrderProduct ORDERPRODUCT3;
    public static OrderProduct ORDERPRODUCT4;
    public static OrderProduct ORDERPRODUCT5;
    public static OrderProduct ORDERPRODUCT6;
    public static OrderProduct ORDERPRODUCT7;
    public static OrderProduct ORDERPRODUCT8;
    public static OrderProduct ORDERPRODUCT9;
    public List<OrderProduct> ORDERPRODUCTS1 = new ArrayList<>();
    public List<OrderProduct> ORDERPRODUCTS2 = new ArrayList<>();
    public List<OrderProduct> ORDERPRODUCTS3 = new ArrayList<>();


    @Override
    public void run(String... args) throws Exception {
        COMMISSION1 = createCommission(1L);
        COMMISSION2 = createCommission(2L);
        COMMISSION3 = createCommission(3L);

        CALCULATE1 = calculateRepository.save(createCalculate(COMMISSION1, 10000L, 1L));
        CALCULATE2 = calculateRepository.save(createCalculate(COMMISSION2, 20000L, 2L));
        CALCULATE3 = calculateRepository.save(createCalculate(COMMISSION3, 30000L, 3L));

        ORDERPRODUCT1 = createOrderProduct(null, 1L, 2L, 10, 21900);
        ORDERPRODUCT2 = createOrderProduct(null, 1L, 3L, 20, 18900);
        ORDERPRODUCT3 = createOrderProduct(null, 1L, 4L, 30, 20900);
        ORDERPRODUCTS1.addAll(List.of(ORDERPRODUCT1, ORDERPRODUCT2, ORDERPRODUCT3));

        ORDERPRODUCT4 = createOrderProduct(null, 2L, 5L, 10, 7500);
        ORDERPRODUCT5 = createOrderProduct(null, 2L, 6L, 20, 19900);
        ORDERPRODUCT6 = createOrderProduct(null, 2L, 7L, 30, 22200);
        ORDERPRODUCTS2.addAll(List.of(ORDERPRODUCT4, ORDERPRODUCT5, ORDERPRODUCT6));

        ORDERPRODUCT7 = createOrderProduct(null, 3L, 2L, 10, 35340);
        ORDERPRODUCT8 = createOrderProduct(null, 3L, 3L, 20, 15900);
        ORDERPRODUCT9 = createOrderProduct(null, 3L, 4L, 30, 14900);
        ORDERPRODUCTS3.addAll(List.of(ORDERPRODUCT7, ORDERPRODUCT8, ORDERPRODUCT9));

        ORDER1 = orderRepository.save(createOrder(1L, ORDERPRODUCTS1));
        ORDER2 = orderRepository.save(createOrder(2L, ORDERPRODUCTS2));
        ORDER3 = orderRepository.save(createOrder(3L, ORDERPRODUCTS3));
    }
}
