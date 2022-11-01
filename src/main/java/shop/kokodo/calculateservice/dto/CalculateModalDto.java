package shop.kokodo.calculateservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.kokodo.calculateservice.entity.Calculate;

/**
 * packageName    : shop.kokodo.calculateservice.dto
 * fileName       : CalculateModalDto
 * author         : namhyeop
 * date           : 2022/10/25
 * description    :
 * Calculate Modal View에 제공되는 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/25        namhyeop       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CalculateModalDto {

    private Long finalPaymentCost;

    private String provideStatus;

    private Long basic;

    private Long salesPromotion;

    private Long firstPaymentDelivery;

    private Long deliverySupport;

    private Long discountSupport;

    private Long mediumCompanyCostRefund;

    private Long etc;

    private Long commissionSum;

    private String bankName;

    private String accountNumber;

    private String accountHolder;

    public static CalculateModalDto toDto(Calculate calculate, FinanceInfoDto financeInfoDto){
        return new CalculateModalDto(
                calculate.getFinalPaymentCost(),
                calculate.getProvideStatus().getValue(),

                calculate.getCommission().getBasic(),
                calculate.getCommission().getSalesPromotion(),
                calculate.getCommission().getFirstPaymentDelivery(),
                calculate.getCommission().getDeliverySupport(),
                calculate.getCommission().getDiscountSupport(),
                calculate.getCommission().getMediumCompanyCostRefund(),
                calculate.getCommission().getEtc(),
                getCommissionSum(calculate),

                financeInfoDto.getBankName(),
                financeInfoDto.getAccountNumber(),
                financeInfoDto.getAccountHolder()
        );
    }

    public static Long getCommissionSum(Calculate calculate){
          return (calculate.getCommission().getBasic() + calculate.getCommission().getSalesPromotion() + calculate.getCommission().getFirstPaymentDelivery()) -
            (calculate.getCommission().getDeliverySupport() + calculate.getCommission().getDiscountSupport() + calculate.getCommission().getMediumCompanyCostRefund() + calculate.getCommission().getEtc());
    }
}
