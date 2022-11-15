package shop.kokodo.calculateservice.repository.commission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.kokodo.calculateservice.dto.SaleListDto;
import shop.kokodo.calculateservice.dto.SaleListSearchCondition;

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
public interface CommissionRepositoryCustom {

    Page<SaleListDto> searchSaleList(SaleListSearchCondition condition, Pageable pageable);
}
