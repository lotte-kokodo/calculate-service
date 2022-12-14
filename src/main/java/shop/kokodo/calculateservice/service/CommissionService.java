package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.dto.SaleListDto;
import shop.kokodo.calculateservice.dto.SaleListSearchCondition;
import shop.kokodo.calculateservice.repository.commission.CommissionRepository;

import java.util.List;
import java.util.stream.Collectors;

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
@Transactional(readOnly = true)
public class CommissionService {

    private final CommissionRepository commissionRepository;

    public Page<SaleListDto> getSaleList(SaleListSearchCondition saleListSearchCondition, Pageable pageable){
        Page<SaleListDto> saleListDtos = commissionRepository.searchSaleList(saleListSearchCondition, pageable);
        setSaleListValue(saleListDtos);
        for (SaleListDto saleListDto : saleListDtos) {
            System.out.println("saleListDto = " + saleListDto);
        }
        return saleListDtos;
    }

    public void setSaleListValue(Page<SaleListDto> saleList){
        saleList.stream().forEach(saleListDto -> saleListDto.setSaleListValue(
                getSaleSum(saleListDto),
                getSaleCommission(saleListDto.getBasic(), saleListDto.getSalesPromotion(), saleListDto.getFirstPaymentDelivery(), saleListDto.getDeliverySupport(), saleListDto.getDiscountSupport(), saleListDto.getMediumCompanyCostRefund(), saleListDto.getEtc()),
                getSettlementMoney(saleListDto),
                getSum(saleListDto)
                ));
    }

    private long getSaleSum(SaleListDto saleListDto) {
        return getSum(saleListDto) + getSaleCommission(saleListDto.getBasic(), saleListDto.getSalesPromotion(), saleListDto.getFirstPaymentDelivery(), saleListDto.getDeliverySupport(), saleListDto.getDiscountSupport(), saleListDto.getMediumCompanyCostRefund(), saleListDto.getEtc());
    }

    private long getSaleCommission(Long basic, Long salesPromotion, Long firstPaymentDelivery, Long deliverySupport, Long discountSupport, Long mediumCompanyCostRefund, Long etc) {
        Long sum = basic + salesPromotion + firstPaymentDelivery - (deliverySupport + discountSupport + mediumCompanyCostRefund + etc);
        if (sum < 0L){ sum = 0L;}
        return sum;
    }

    private long getSettlementMoney(SaleListDto saleListDto) {
        return getSum(saleListDto) - getSaleCommission(saleListDto.getBasic(), saleListDto.getSalesPromotion(), saleListDto.getFirstPaymentDelivery(), saleListDto.getDeliverySupport(), saleListDto.getDiscountSupport(), saleListDto.getMediumCompanyCostRefund(), saleListDto.getEtc());
    }

    private  Long getSum(SaleListDto saleListDto) {
        return saleListDto.getFinalPaymentCost();
    }
}
