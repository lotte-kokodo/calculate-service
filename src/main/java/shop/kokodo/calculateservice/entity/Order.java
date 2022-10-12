package shop.kokodo.calculateservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.kokodo.calculateservice.enums.order.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.entity
 * fileName       : Order
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * Order Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {

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

    public Order(Long userId, OrderStatus orderStatus, String deliveryName, String deliveryAddress, Long totalPrice, LocalDateTime orderDate) {
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.deliveryName = deliveryName;
        this.deliveryAddress = deliveryAddress;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
}