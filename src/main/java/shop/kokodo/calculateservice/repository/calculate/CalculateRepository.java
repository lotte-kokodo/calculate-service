package shop.kokodo.calculateservice.repository.calculate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.kokodo.calculateservice.entity.Calculate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName    : shop.kokodo.calculateservice.repository.interfaces
 * fileName       : CalculateRepository
 * author         : namhyeop
 * date           : 2022/10/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/04        namhyeop       최초 생성
 */
public interface CalculateRepository extends JpaRepository<Calculate, Long>, CalculateRepositoryCustom {

    List<Calculate> findBySellerId(@Param("id") Long id);

    Optional<Calculate> findById(Long id);

    @Query("select sum(c.finalPaymentCost) "
            + "from Calculate c "
            + "where c.sellerId = :sellerId and c.provideStatus ='PROVIDE_SUCCESS' and c.createdDate between :startDate and :endDate")
    Long findWeakExpectMoney(Long sellerId, @Param("startDate") LocalDateTime startDate,  @Param("endDate") LocalDateTime endDate);

    //mysql에 의존적이라 테스트 코드에서 에러 발생, QueryDsl로 대체한다.
//    @Query("select function('date_format', c.createdDate, '%Y, %m') as monthDate, sum(c.finalPaymentCost) from Calculate c where c.id = :id group by monthDate")
    @Query(value = "select date_format(created_date, '%Y-%m') as monthdate sum(final_payment_cost) from calculate group by monthdate",nativeQuery = true)
    List<Map<String, Object>> findAnnualSale2(Long id);
}

