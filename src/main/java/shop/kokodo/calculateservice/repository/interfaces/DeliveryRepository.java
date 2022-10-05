package shop.kokodo.calculateservice.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.kokodo.calculateservice.entity.Delivery;

/**
 * packageName    : shop.kokodo.calculateservice.repository.interfaces
 * fileName       : DeliveryRepository
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
