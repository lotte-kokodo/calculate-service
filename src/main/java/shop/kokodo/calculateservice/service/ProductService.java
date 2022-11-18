//TODO: 통합연결, 배포시 주석해제
package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.client.ProductServiceClient;
import shop.kokodo.calculateservice.exception.FeignClientFailException;
import shop.kokodo.calculateservice.repository.orderproduct.OrderProductRepository;

import java.util.ArrayList;
import java.util.List;

import static shop.kokodo.calculateservice.exception.message.BatchErrorMessage.PRODUCT_FEIGN_NULL;

/**
 * packageName    : shop.kokodo.calculateservice.service
 * fileName       : ProductService
 * author         : namhyeop
 * date           : 2022/09/30
 * description    : 통합 테스트시 주석해제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        namhyeop       최초 생성
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductService {

    private final OrderProductRepository orderProductRepository;
    private final ProductServiceClient productServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public List<Long> getSellerId(Long orderId) {

        List<Long> productIdByOrderId = orderProductRepository.findProductIdByOrderId(orderId);
        log.info("productIdByOrderId, {}", productIdByOrderId);
        CircuitBreaker productCircuitBreaker = circuitBreakerFactory.create("productCircuitBreaker");
        List<Long> sellerId = productCircuitBreaker.run(() -> productServiceClient.getProductSellerId(productIdByOrderId), throwable -> new ArrayList<>());
        log.info("Feign arrival after sellerId {}", sellerId);
        if (sellerId.isEmpty()) {
            throw new FeignClientFailException(PRODUCT_FEIGN_NULL);
        }

        return sellerId;
    }
}
