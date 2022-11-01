package shop.kokodo.calculateservice.circuitbreaker.factory;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

/**
 * packageName    : shop.kokodo.calculateservice.circuitbreaker.factory
 * fileName       : CircuitBreakerFactory
 * author         : namhyeop
 * date           : 2022/10/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/26        namhyeop       최초 생성
 */
@Component
public class AllCircuitBreaker {

    private static CircuitBreakerFactory circuitBreakerFactory;

    public AllCircuitBreaker(CircuitBreakerFactory circuitBreakerFactory) {
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public static CircuitBreaker createSellerCircuitBreaker() {
        return circuitBreakerFactory.create("sellerCircuitBreaker");
    }
}
