package shop.kokodo.calculateservice.dto.ardto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.dto.ardto
 * fileName       : CouponInfo
 * author         : namhyeop
 * date           : 2022/11/02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/02        namhyeop       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponInfo {

    private Long sellerId;
    private Long productId;
    private String couponName;
    private Integer rate;
    private Long x;
    private Long y;
    private Long z;
    private Long minPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime regDate;
}
