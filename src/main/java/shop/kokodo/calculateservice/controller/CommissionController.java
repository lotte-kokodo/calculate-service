package shop.kokodo.calculateservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.kokodo.calculateservice.dto.SaleListDto;
import shop.kokodo.calculateservice.dto.SaleListSearchCondition;
import shop.kokodo.calculateservice.dto.response.Response;
import shop.kokodo.calculateservice.service.CommissionService;

import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.controller
 * fileName       : CalculateController
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * Seller Offiece의 정산 View에 Data를 제공하기 위한 Controller
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/commission")
public class CommissionController {

    private final CommissionService commissionService;

    @PostMapping("/saleList")
    public Response saleList(@RequestBody SaleListSearchCondition saleListSearchCondition, Pageable pageable){
        return  Response.success(commissionService.getSaleList(saleListSearchCondition, pageable));
    }

}
