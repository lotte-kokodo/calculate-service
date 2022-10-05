package shop.kokodo.calculateservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.kokodo.calculateservice.dto.response.Response;

import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.client
 * fileName       : ProductServiceClient
 * author         : namhyeop
 * date           : 2022/09/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        namhyeop       최초 생성
 */
@FeignClient(name="product-service")
public interface ProductServiceClient {
    @GetMapping("/product-service/{prodcutId}/productSellerId")
    Response getProductSellerId(@PathVariable Long productId);
}
