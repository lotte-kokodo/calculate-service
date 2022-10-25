package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shop.kokodo.calculateservice.dto.SaleListDto;
import shop.kokodo.calculateservice.dto.SaleListSearchCondition;
import shop.kokodo.calculateservice.repository.commission.CommissionRepository;

import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.service
 * fileName       : CommissionService
 * author         : namhyeop
 * date           : 2022/10/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/18        namhyeop       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommissionService {

    private final CommissionRepository commissionRepository;

    public List<SaleListDto> getSaleList(SaleListSearchCondition saleListSearchCondition){
        List<SaleListDto> saleListDtos = commissionRepository.searchSaleList(saleListSearchCondition);
        log.info("saleListDtos = {}", saleListDtos);
        return saleListDtos;
    }
}
