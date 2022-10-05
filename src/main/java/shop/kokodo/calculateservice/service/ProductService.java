package shop.kokodo.calculateservice.service;//package shop.kokodo.calculateservice.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
//import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
//import org.springframework.stereotype.Service;
//import shop.kokodo.calculateservice.client.ProductServiceClient;
//import shop.kokodo.calculateservice.dto.response.Response;
//import shop.kokodo.calculateservice.dto.response.Result;
//import shop.kokodo.calculateservice.dto.response.Success;
//
///**
// * packageName    : shop.kokodo.calculateservice.service
// * fileName       : ProductService
// * author         : namhyeop
// * date           : 2022/09/30
// * description    : 통합 테스트시 주석해제
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2022/09/30        namhyeop       최초 생성
// */
//@Service
//@RequiredArgsConstructor
//public class ProductService {
//
//    private final OrderProductRepository orderProductRepository;
//    private final ProductServiceClient productServiceClient;
//    private final CircuitBreakerFactory circuitBreakerFactory;
//
//    public Long getSellerId(Long orderId) {
//        Long productIdByOrderId = orderProductRepository.findProductIdByOrderId(orderId);
//        CircuitBreaker productCircuitBreaker = circuitBreakerFactory.create("productCircuitBreaker");
//        Response productSellerIdResponse = productCircuitBreaker.run(
//                () -> productServiceClient.getProductSellerId(productIdByOrderId)
//                ,throwable -> Response.failure(500, "productServer 응답이 실패했습니다."));
//
//        Result result = productSellerIdResponse.getResult();
//        Long sellerId = (Long)((Success) result).getData();
//        return sellerId;
//    }
//}
