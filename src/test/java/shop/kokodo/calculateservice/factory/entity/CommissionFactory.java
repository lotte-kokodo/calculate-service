package shop.kokodo.calculateservice.factory.entity;

import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.entity.Commission;

/**
 * packageName    : shop.kokodo.calculateservice.factory.entity
 * fileName       : CommissionFactory
 * author         : namhyeop
 * date           : 2022/10/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/04        namhyeop       최초 생성
 */
public class CommissionFactory {
    public static Commission createCommission() {
        return new Commission(null, 1L, 10000L, 20000L, 30000L, 40000L, 5000L, 6000L, 7000L);
    }

    public static Commission createCommission(Calculate calculate) {
        return new Commission(calculate, 1L, 10000L, 20000L, 30000L, 40000L, 5000L, 6000L, 7000L);
    }

    public static Commission createCommission(Calculate calculate, Long sellerId) {
        return new Commission(calculate, sellerId, 10000L, 20000L, 30000L, 40000L, 5000L, 6000L, 7000L);
    }

    public static Commission createCommission(Long sellerId) {
        return new Commission(null, sellerId, 10000L, 20000L, 30000L, 40000L, 5000L, 6000L, 7000L);
    }
}
