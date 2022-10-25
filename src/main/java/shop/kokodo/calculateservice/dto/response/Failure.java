package shop.kokodo.calculateservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : shop.kokodo.calculateservice.job
 * fileName       : OrderJobConfiguration
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * View 응답 반환을 위한 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */

@AllArgsConstructor
@Getter
public class Failure implements Result {
    private String msg;
}
