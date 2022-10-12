package shop.kokodo.calculateservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shop.kokodo.calculateservice.dto.CalculateDto;
import shop.kokodo.calculateservice.dto.CalculateSearchCondition;
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
public class CalculateController {

    private final CalculateService calculateService;

    @GetMapping("/calculate/{id}/expectDay")
    public Response expectDay() {
        LocalDateTime expectDay = calculateService.getExpectDay();
        System.out.println("date in expectDay");
        return Response.success(expectDay);
    }

    @GetMapping("/calculate/{id}/expectMoney")
    public Response expectMoney(@PathVariable("id") Long id) {
        Long expectMoney = calculateService.getExpectMoney(id);
        System.out.println("date in expectMoney");
        return Response.success(expectMoney);
    }

    @PostMapping("/calculate/{id}/calculateList")
    public Response calculateList(@RequestBody CalculateSearchCondition calculateSearchCondition) {
        System.out.println("calculateSearchCondition = " + calculateSearchCondition);
        List<CalculateDto> calculateList = calculateService.getCalculateList(calculateSearchCondition);
        return Response.success(calculateList);
    }
}
