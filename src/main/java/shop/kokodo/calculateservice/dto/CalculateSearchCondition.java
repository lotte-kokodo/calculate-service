package shop.kokodo.calculateservice.dto;

import lombok.*;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;

import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.controller
 * fileName       : CommissionChoiceDto
 * author         : namhyeop
 * date           : 2022/10/05
 * description    :
 * 정산 View에서 정산 내역을 요청할 때 전달 받는 데이터 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/05        namhyeop       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class CalculateSearchCondition {
    //    @NotNull
    private Long sellerId;
    //    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime startDate;
    //    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime endDate;
    //    @NotNull(message = "정산에 대한 제공 여부 값은 필수 입니다.")
    private ProvideStatus provideStatus;
    //    @NotNull(message = "정산 종류에 대한 제공 여부 값은 필수 입니다")
    private CalculateType calculateType;
    private Long id;
    private Integer pageNumber;
    private Integer pageSize;
}
