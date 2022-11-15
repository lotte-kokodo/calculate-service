package shop.kokodo.calculateservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.dto
 * fileName       : DashBoardCardInfoDto
 * author         : namhyeop
 * date           : 2022/11/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/10        namhyeop       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DashBoardCardSearchConditionDto {
    private Long sellerId;
    private LocalDateTime today;
    private LocalDateTime beforeDay;
}
