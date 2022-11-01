package shop.kokodo.calculateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : shop.kokodo.calculateservice.client
 * fileName       : FinaceInfoDto
 * author         : namhyeop
 * date           : 2022/10/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/26        namhyeop       최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FinanceInfoDto {

    private String bankName;
    private String accountNumber;
    private String accountHolder;
}
