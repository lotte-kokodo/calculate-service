package shop.kokodo.calculateservice.entity;

import lombok.*;
import shop.kokodo.calculateservice.dto.ardto.CouponInfo;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.entity
 * fileName       : ArCoupon
 * author         : namhyeop
 * date           : 2022/11/02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/02        namhyeop       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ArCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ar_coupon_id")
    private Long id;

    private Long sellerId;

    private Long productId;

    private String couponName;

    private Integer rate;

    private Long xPos;

    private Long yPos;

    private Long zPos;

    private Long minPrice;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime regDate;

    public static ArCoupon createArCoupon(CouponInfo couponInfo) {
        return ArCoupon.builder()
                .sellerId(couponInfo.getSellerId())
                .productId(couponInfo.getProductId())
                .couponName(couponInfo.getCouponName())
                .rate(couponInfo.getRate())
                .xPos(couponInfo.getX())
                .yPos(couponInfo.getY())
                .zPos(couponInfo.getZ())
                .minPrice(couponInfo.getMinPrice())
                .startDate(couponInfo.getStartDate())
                .endDate(couponInfo.getEndDate())
                .regDate(couponInfo.getRegDate())
                .build();
    }
}
