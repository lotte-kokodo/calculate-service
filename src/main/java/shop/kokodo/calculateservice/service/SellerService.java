//TODO: 통합연결, 배포시 주석해제
package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import shop.kokodo.calculateservice.client.SellerServiceClient;
import shop.kokodo.calculateservice.dto.CommissionPolicyDto;
import shop.kokodo.calculateservice.dto.response.Response;
import shop.kokodo.calculateservice.dto.response.Result;
import shop.kokodo.calculateservice.entity.Commission;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.service
 * fileName       : SellerService
 * author         : namhyeop
 * date           : 2022/10/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/11        namhyeop       최초 생성
 */
@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerServiceClient sellerServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public List<CommissionPolicyDto> findCommissionPolicy(List<Long> sellerId){
        CircuitBreaker sellerCircuitBreaker = circuitBreakerFactory.create("sellerCircuitBreaker");
        List<CommissionPolicyDto> sellerCommissionPolicyList = sellerCircuitBreaker.run(() -> sellerServiceClient.getSellerCommissionPolicy(sellerId)
                , throwable -> new ArrayList<>());
        return sellerCommissionPolicyList;
    }
}
