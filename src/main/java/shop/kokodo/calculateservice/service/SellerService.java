//TODO: 통합연결, 배포시 주석해제
package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.client.SellerServiceClient;
import shop.kokodo.calculateservice.dto.CommissionPolicyDto;
import shop.kokodo.calculateservice.exception.FeignClientFailException;

import java.util.ArrayList;
import java.util.List;

import static shop.kokodo.calculateservice.exception.message.BatchErrorMessage.PRODUCT_FEIGN_NULL;
import static shop.kokodo.calculateservice.exception.message.BatchErrorMessage.SELLER_FEIGN_NULL;

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
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SellerService {

    private final SellerServiceClient sellerServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public List<CommissionPolicyDto> searchCommissionPolicy(List<Long> sellerId){
        CircuitBreaker sellerCircuitBreaker = circuitBreakerFactory.create("sellerCircuitBreaker");
        List<CommissionPolicyDto> sellerCommissionPolicyList = sellerCircuitBreaker.run(() -> sellerServiceClient.searchCommissionPolicy(sellerId)
                , throwable -> new ArrayList<>());

        if (sellerCommissionPolicyList.isEmpty()){
            throw new FeignClientFailException(SELLER_FEIGN_NULL);
        }
        log.info("seller commission Info = {}", sellerCommissionPolicyList);
        return sellerCommissionPolicyList;
    }
}
