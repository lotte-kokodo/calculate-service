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
        LocalDateTime expectDay = calculateService.getExpectDay();
        return Response.success(expectDay);
    }

    @GetMapping("/{id}/expectMoney")
    public Response expectMoney(@PathVariable("id") Long id) {
        Long expectMoney = calculateService.getExpectMoney(id);
        log.info("date in expectMoney");
        return Response.success(expectMoney);
    }

    @PostMapping("/{id}/calculateList")
    public Response calculateList(@RequestBody CalculateSearchCondition calculateSearchCondition, Pageable pageable) {
        Page<CalculateDto> calculateList = calculateService.getCalculateList(calculateSearchCondition,pageable);
        return Response.success(calculateList);
    }

    @GetMapping("/{sellerId}/calculateModal/{calculateId}")
    public Response calculateModal(@PathVariable Long sellerId, @PathVariable Long calculateId){
        log.info("sellerId {}", sellerId);
        log.info("calculateId {}", calculateId);
        CalculateModalDto calculateModal = calculateService.getCalculateModal(sellerId, calculateId);
        log.info(calculateModal.getAccountHolder());
        return Response.success(calculateModal);
    }

    @GetMapping("/{sellerId}/SellerDashBoardExpectMoney")
    public Response dashBoardCardInfo(@PathVariable Long sellerId){
        DashBoardCardSearchInfoDto dashBoardCardSearchInfoDto = calculateService.dashBoardExpectMoney(sellerId);
        return Response.success(dashBoardCardSearchInfoDto);
    }
}
