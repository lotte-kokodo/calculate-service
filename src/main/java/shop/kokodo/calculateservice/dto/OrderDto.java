package shop.kokodo.calculateservice.dto;

import lombok.*;
import shop.kokodo.calculateservice.enums.order.OrderStatus;

/**
 * packageName    : shop.kokodo.calculateservice.dto
 * fileName       : OrderDto
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
//@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    private Long userId;

    private OrderStatus orderStatus;

    private Integer totalPrice;

//    private LocalDateTime orderDate;
}
