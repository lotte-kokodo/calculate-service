package shop.kokodo.calculateservice.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.kokodo.calculateservice.entity.OrderProduct;

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

    @Query("select op.id from OrderProduct op"
            + " join fetch op.order o"
            + " where o.id = :orderId")
    Long findProductIdByOrderId(Long orderId);
}
