package shop.kokodo.calculateservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : shop.kokodo.calculateservice.job
 * fileName       : OrderJobConfiguration
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */

@AllArgsConstructor
@Getter
public class Success<T> implements Result {
    private T data;
}
