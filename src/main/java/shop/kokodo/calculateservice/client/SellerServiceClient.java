package shop.kokodo.calculateservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.kokodo.calculateservice.dto.response.Response;

/**
 * packageName    : shop.kokodo.calculateservice.client
 * fileName       : SellerServiceClient
 * author         : namhyeop
 * date           : 2022/09/30
 * description    :
 * Seller MS 간 통신을 위한 FeigintClient
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        namhyeop       최초 생성
 */
@FeignClient(name = "calculate-service")
public interface SellerServiceClient {
    @GetMapping("/seller-service/{sellerId}/productSellerId")
    Response getSellerCommissionPolicy(@PathVariable Long sellerId);
}
