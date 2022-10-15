package shop.kokodo.calculateservice.dto;

import lombok.*;
import shop.kokodo.calculateservice.entity.Commission;

import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.dto
 * fileName       : CostAndCommissionDto
 * author         : namhyeop
 * date           : 2022/10/15
 * description    :
 * service로직에서 수수료 계산 반환시 필요한 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/15        namhyeop       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CostAndCommissionDto {
    List<Long> cost;
    List<Commission> commissionList;
}
