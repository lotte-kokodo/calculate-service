package shop.kokodo.calculateservice.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.repository.interfaces
 * fileName       : OrderRepository
 * author         : namhyeop
 * date           : 2022/09/29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/29        namhyeop       최초 생성
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

//    @Transactional(readOnly = true)
//    @Query("select o.orderDate " +
//            "from Order o " +
//            "where o.createdDate =:creatdDate")
//    List<String> findAllByCreatedDate(@Param("createdDate")LocalDateTime createdDate);

    @Query("select MAX(o.id) " +
            "from Order o " +
            "where o.orderDate between :startDate and :endDate")
    Long findMaxId(@Param("startDate") LocalDateTime startDate,
                   @Param("endDate") LocalDateTime endDate);

    @Query("select MIN(o.id) " +
            "from Order o " +
            "where o.orderDate between :startDate and :endDate")
    Long findMinId(@Param("startDate") LocalDateTime startDate,
                   @Param("endDate") LocalDateTime endDate);
}
