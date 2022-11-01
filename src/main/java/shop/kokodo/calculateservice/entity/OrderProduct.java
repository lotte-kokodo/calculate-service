package shop.kokodo.calculateservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * packageName    : shop.kokodo.calculateservice.entity
 * fileName       : OrderProduct
 * author         : namhyeop
 * date           : 2022/09/30
 * description    :
 * OrderProduct Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        namhyeop       최초 생성
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long memberId;
    private Long productId;

    private Integer qty;
    private Integer unitPrice;

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderProduct(Order order, Long memberId, Long productId, Integer qty, Integer unitPrice) {
        this.order = order;
        this.memberId = memberId;
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderProduct(Long memberId, Long productId, Integer qty, Integer unitPrice) {
        this.memberId = memberId;
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
}