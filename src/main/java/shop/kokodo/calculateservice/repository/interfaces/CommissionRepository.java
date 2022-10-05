package shop.kokodo.calculateservice.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.kokodo.calculateservice.entity.Commission;

/**
 * packageName    : shop.kokodo.calculateservice.repository.interfaces
 * fileName       : CommissionRepository
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
public interface CommissionRepository extends JpaRepository<Commission, Long> {

    Commission findBySellerId(Long sellerId);
}
