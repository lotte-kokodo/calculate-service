package shop.kokodo.calculateservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : shop.kokodo.calculateservice.dto.response
 * fileName       : DashBoardCardSearchInfoDto
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
public class DashBoardCardSearchInfoDto {
    private Long numberInfo;
    private String changeNumberInfo;

    public static DashBoardCardSearchInfoDto createDashBoardCardSearchInfoDto(Long numberInfo, String changeNumberInfo){
        return new DashBoardCardSearchInfoDto(numberInfo, changeNumberInfo);
    }
}
