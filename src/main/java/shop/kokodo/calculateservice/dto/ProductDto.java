package shop.kokodo.calculateservice.dto;

import lombok.*;

/**
 * packageName    : shop.kokodo.calculateservice.dto
 * fileName       : ProductDto
 * author         : namhyeop
 * date           : 2022/09/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        namhyeop       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductDto {
    private Long sellerId;
}
