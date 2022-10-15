package shop.kokodo.calculateservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.kokodo.calculateservice.dto.response.Response;

import java.util.List;

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
    @GetMapping("/seller-service/productSellerId")
    Response getSellerCommissionPolicy(@RequestBody List<Long> sellerId);
}
