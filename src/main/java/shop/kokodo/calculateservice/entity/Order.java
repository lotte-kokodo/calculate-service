package shop.kokodo.calculateservice.entity;

import lombok.*;
import shop.kokodo.calculateservice.enums.order.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
@Getter @Setter
@NoArgsConstructor
@Table(name = "orders")
@ToString
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

    private Integer totalPrice;

    private LocalDateTime orderDate; // 주문 일자

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Order(Long id, Long memberId, OrderStatus orderStatus, String deliveryMemberName, String deliveryMemberAddress, Integer totalPrice, LocalDateTime orderDate, List<OrderProduct> orderProducts) {
        this.id = id;
        this.memberId = memberId;
        this.orderStatus = orderStatus;
        this.deliveryMemberName = deliveryMemberName;
        this.deliveryMemberAddress = deliveryMemberAddress;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderProducts = orderProducts;
        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.setOrder(this);
        }
    }

    @Builder
    public Order(Long memberId,
                 OrderStatus orderStatus, String deliveryMemberName, String deliveryMemberAddress,
                 Integer totalPrice, LocalDateTime orderDate,
                 List<OrderProduct> orderProducts) {
        this.memberId = memberId;
        this.orderStatus = orderStatus;
        this.deliveryMemberName = deliveryMemberName;
        this.deliveryMemberAddress = deliveryMemberAddress;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        for (OrderProduct orderProduct : orderProducts) {
            this.orderProducts.add(orderProduct);
            orderProduct.setOrder(this);
        }
    }
}
