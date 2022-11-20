package shop.kokodo.calculateservice.repository.orderproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.kokodo.calculateservice.entity.OrderProduct;

import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.repository.interfaces
 * fileName       : inte
 * author         : namhyeop
 * date           : 2022/09/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        namhyeop       최초 생성
 */
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @Query("select op.productId from OrderProduct op"
            + " join op.order o"
            + " where o.id = :orderId")
    List<Long> findProductIdByOrderId(Long orderId);
}
