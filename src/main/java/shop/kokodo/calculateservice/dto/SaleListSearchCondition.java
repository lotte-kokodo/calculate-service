package shop.kokodo.calculateservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.dto
 * fileName       : SaleListSearchCondition
 * author         : namhyeop
 * date           : 2022/10/18
 * description    :
 * 매출내역 조회를 위한 검색 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/18        namhyeop       최초 생성
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaleListSearchCondition {

    private Long sellerId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String searchCondition;
}
