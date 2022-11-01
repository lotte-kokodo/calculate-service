package shop.kokodo.calculateservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.client
 * fileName       : ProductServiceClient
 * author         : namhyeop
 * date           : 2022/09/30
 * description    :
 * Product MS 간 통신을 위한 FeigintClient
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        namhyeop       최초 생성
 */
@FeignClient(name = "product-service")
public interface ProductServiceClient {
    @GetMapping("/product/productSellerId")
    List<Long> getProductSellerId(@RequestParam List<Long> productId);
}
