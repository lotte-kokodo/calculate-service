//TODO: 통합연결, 배포시 주석해제
package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import shop.kokodo.calculateservice.client.ProductServiceClient;
import shop.kokodo.calculateservice.dto.response.Response;
import shop.kokodo.calculateservice.dto.response.Result;
import shop.kokodo.calculateservice.dto.response.Success;
import shop.kokodo.calculateservice.repository.orderproduct.OrderProductRepository;

import java.util.List;

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
public class ProductService {

    private final OrderProductRepository orderProductRepository;
    private final ProductServiceClient productServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public List<Long> getSellerId(Long orderId) {
        List<Long> productIdByOrderId = orderProductRepository.findProductIdByOrderId(orderId);
        log.info("======================process 2======================" + productIdByOrderId);
        CircuitBreaker productCircuitBreaker = circuitBreakerFactory.create("productCircuitBreaker");
        log.info("======================process 3======================" + productIdByOrderId);
        Response productSellerIdResponse = productCircuitBreaker.run(
                () -> productServiceClient.getProductSellerId(productIdByOrderId)
                ,throwable -> Response.failure(500, "productServer 응답이 실패했습니다."));
        log.info("======================process 4======================" + productIdByOrderId);
        Result result = productSellerIdResponse.getResult();
        List<Long> sellerId = (List<Long>)((Success) result).getData();
        return sellerId;
    }
}
