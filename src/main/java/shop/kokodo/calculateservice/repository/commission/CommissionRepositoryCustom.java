package shop.kokodo.calculateservice.repository.commission;

import shop.kokodo.calculateservice.dto.CalculateDto;
import shop.kokodo.calculateservice.dto.CalculateSearchCondition;
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

    List<SaleListDto> searchSaleList(SaleListSearchCondition condition);
}
