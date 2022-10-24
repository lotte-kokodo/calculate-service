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

    private Long memberId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String deliveryMemberName;

    private String deliveryMemberAddress;

    private Long totalPrice;

    private LocalDateTime orderDate;

    public Order(Long memberId, OrderStatus orderStatus, String deliveryMemberName, String deliveryMemberAddress, Long totalPrice, LocalDateTime orderDate) {
        this.memberId = memberId;
        this.orderStatus = orderStatus;
        this.deliveryMemberName = deliveryMemberName;
        this.deliveryMemberAddress = deliveryMemberAddress;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
}