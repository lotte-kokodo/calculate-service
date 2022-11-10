package shop.kokodo.calculateservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.kokodo.calculateservice.entity.ArCoupon;

import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.repository
 * fileName       : ArCouponRepository
 * author         : namhyeop
 * date           : 2022/11/02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/02        namhyeop       최초 생성
 */
public interface ArCouponRepository extends JpaRepository<ArCoupon, Long> {
    @Query("select ar from ArCoupon ar where ar.sellerId = :sellerId")
    List<ArCoupon> findCouponBySellerId(Long sellerId);
}
