package shop.kokodo.calculateservice.repository.calculate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.kokodo.calculateservice.dto.CalculateDto;
import shop.kokodo.calculateservice.dto.CalculateSearchCondition;

import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.repository.interfaces
 * fileName       : CalculateRepositoryCustom
 * author         : namhyeop
 * date           : 2022/10/06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/06        namhyeop       최초 생성
 */
public interface CalculateRepositoryCustom {

    Page<CalculateDto> searchCalculate(CalculateSearchCondition condition, Pageable pageable);

}
