package shop.kokodo.calculateservice.entity;

import lombok.*;
import shop.kokodo.calculateservice.enums.order.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.entity
 * fileName       : Order
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
@Entity
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "orders")
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String deliveryName;

    private String deliveryAddress;

    private Long totalPrice;

    private LocalDateTime orderDate;

    public Order(Long id, Long userId, OrderStatus orderStatus, String deliveryName, String deliveryAddress, Long totalPrice, LocalDateTime orderDate) {
        this.id = id;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.deliveryName = deliveryName;
        this.deliveryAddress = deliveryAddress;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public Order(BaseEntityBuilder<?, ?> b, Long userId, OrderStatus orderStatus, String deliveryName, String deliveryAddress, Long totalPrice, LocalDateTime orderDate) {
        super(b);
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.deliveryName = deliveryName;
        this.deliveryAddress = deliveryAddress;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public Order() {

    }
}