package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.repository.interfaces.CommissionRepository;

/**
 * packageName    : shop.kokodo.calculateservice.service
 * fileName       : CommissionService
 * author         : namhyeop
 * date           : 2022/09/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        namhyeop       최초 생성
 */
@Service
@RequiredArgsConstructor
public class CommissionService {

    private final CommissionRepository commissionRepository;

    public Commission findCommission(Long sellerId){
        Commission commission = commissionRepository.findBySellerId(sellerId);
        return commission;
    }
}
