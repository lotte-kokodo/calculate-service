package shop.kokodo.calculateservice.repository.calculate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import shop.kokodo.calculateservice.entity.Calculate;

import java.util.List;
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
}
