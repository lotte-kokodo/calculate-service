package shop.kokodo.calculateservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;

import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.dto
 * fileName       : CommissionDto
 * author         : namhyeop
 * date           : 2022/10/06
 * description    :
 * 정산 View의 정산내역을 보여주기 위한 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/06        namhyeop       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalculateDto {

    private LocalDateTime date;

    private CalculateType type;

    private String supportRate;

    private ProvideStatus provideStatus;

    private Long finalPaymentCost;

    @QueryProjection
    public CalculateDto(LocalDateTime date, CalculateType type, String supportRate, ProvideStatus provideStatus, Long finalPaymentCost) {
        this.date = date;
        this.type = type;
        this.supportRate = supportRate;
        this.provideStatus = provideStatus;
        this.finalPaymentCost = finalPaymentCost;
    }
}
