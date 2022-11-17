package shop.kokodo.calculateservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import shop.kokodo.calculateservice.dto.SaleListDto;
import shop.kokodo.calculateservice.dto.SaleListSearchCondition;
import shop.kokodo.calculateservice.dto.response.Response;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;
import shop.kokodo.calculateservice.service.CommissionService;

import java.time.LocalDateTime;
import java.util.List;

import static shop.kokodo.calculateservice.dto.SaleListSearchCondition.createSaleListCondition;

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

    @GetMapping("/saleList")
    public Response saleList(@RequestParam Long sellerId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate, @RequestParam(required=false) String provideStatus, Pageable pageable){
        log.info("sellerId {}", sellerId);
        log.info("startDate {}", startDate);
        log.info("endDate {}", endDate);
        log.info("provideStatus {}", provideStatus);
        log.info("pageable {}", pageable);
        return  Response.success(commissionService.getSaleList(createSaleListCondition(sellerId, startDate, endDate, provideStatus), pageable));
    }
//    @GetMapping("/saleList")
//    public Response saleList(@RequestParam(required = false) SaleListSearchCondition saleListSearchCondition, Pageable pageable){
//        System.out.println("saleListSearchCondition = " + saleListSearchCondition);
//        return  Response.success(commissionService.getSaleList(saleListSearchCondition, pageable));
//    }

}
