package shop.kokodo.calculateservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : shop.kokodo.calculateservice.entity
 * fileName       : delivery
 * author         : namhyeop
 * date           : 2022/09/26
 * description    :
 * Delivery Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/26        namhyeop       최초 생성
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    private Long orderId;

    private Long sellerId;

    private String companyName;

    private String memo;

    private String curPosition;

    private LocalDateTime arrivalEstimatedDate;

    private String origin;

    private String arrival;
}