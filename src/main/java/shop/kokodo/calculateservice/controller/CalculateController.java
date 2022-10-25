package shop.kokodo.calculateservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shop.kokodo.calculateservice.dto.CalculateDto;
import shop.kokodo.calculateservice.dto.CalculateSearchCondition;
import shop.kokodo.calculateservice.dto.SaleListSearchCondition;
import shop.kokodo.calculateservice.dto.response.Response;
import shop.kokodo.calculateservice.service.CalculateService;

import java.time.LocalDateTime;
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
@RequestMapping("/calculate")
public class CalculateController {

    private final CalculateService calculateService;

    @GetMapping("/expectDay")
    public Response expectDay() {
        LocalDateTime expectDay = calculateService.getExpectDay();
        log.info("date in expectDay");
        return Response.success(expectDay);
    }

    @GetMapping("/{id}/expectMoney")
    public Response expectMoney(@PathVariable("id") Long id) {
        Long expectMoney = calculateService.getExpectMoney(id);
        log.info("date in expectMoney");
        return Response.success(expectMoney);
    }

    @PostMapping("/{id}/calculateList")
    public Response calculateList(@RequestBody CalculateSearchCondition calculateSearchCondition) {
        List<CalculateDto> calculateList = calculateService.getCalculateList(calculateSearchCondition);
        return Response.success(calculateList);
    }
}
