package shop.kokodo.calculateservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import shop.kokodo.calculateservice.dto.*;
import shop.kokodo.calculateservice.dto.response.Response;
import shop.kokodo.calculateservice.service.CalculateService;

import java.time.LocalDateTime;

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
@RequestMapping("/calculate")
public class CalculateController {

    private final CalculateService calculateService;

    @GetMapping("/expectDay")
    public Response expectDay() {
        return Response.success(calculateService.getExpectDay());
    }

    @GetMapping("/{id}/expectMoney")
    public Response expectMoney(@PathVariable("id") Long id) {
        return Response.success(calculateService.getExpectMoney(id));
    }

    @PostMapping("/{id}/calculateList")
    public Response calculateList(@RequestBody CalculateSearchCondition calculateSearchCondition, Pageable pageable) {
        return Response.success(calculateService.getCalculateList(calculateSearchCondition,pageable));
    }

    @GetMapping("/{sellerId}/calculateModal/{calculateId}")
    public Response calculateModal(@PathVariable Long sellerId, @PathVariable Long calculateId){
        return Response.success(calculateService.getCalculateModal(sellerId, calculateId));
    }

    @GetMapping("/{sellerId}/SellerDashBoardExpectMoney")
    public Response dashBoardCardInfo(@PathVariable Long sellerId){
        return Response.success(calculateService.dashBoardExpectMoney(sellerId));
    }

    @GetMapping("/{sellerId}/annualSalesInfo")
    public Response annualSaleInfo(@PathVariable Long sellerId){
        return Response.success(calculateService.getAnnualSaleList(sellerId));
    }
}
