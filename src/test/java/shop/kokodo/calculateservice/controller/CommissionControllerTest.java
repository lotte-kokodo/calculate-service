package shop.kokodo.calculateservice.controller;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import shop.kokodo.calculateservice.DocumentConfiguration;
import shop.kokodo.calculateservice.dto.SaleListSearchCondition;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

/**
 * packageName    : shop.kokodo.calculateservice.controller
 * fileName       : CommissionControllerTest
 * author         : namhyeop
 * date           : 2022/11/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/04        namhyeop       최초 생성
 */

class CommissionControllerTest extends DocumentConfiguration {

    @DisplayName("매출내역 리스트 조회")
    @Test
    public void searchSaleList() throws Exception{
        //given
        Long sellerId = 1L;
        LocalDateTime startDate = LocalDateTime.parse("1999-01-01T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2999-01-01T00:00:00");
        String searchCondition = null;
        SaleListSearchCondition saleListSearchCondition = new SaleListSearchCondition(sellerId, startDate, endDate, searchCondition);

        HashMap<String, Object> params = new HashMap<>();
        PageRequest pageRequest = PageRequest.of(0, 5);
        params.put("saleListSearchCondition", saleListSearchCondition);
        params.put("pageRequest", pageRequest);
        //when
        final ExtractableResponse<Response> response = RestAssured
                .given(spec).log().all()
                .filter(document("calculate-commission-saleList"))
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/commission/saleList?sellerId="
                        + saleListSearchCondition.getSellerId()
                        +"&startDate=2022-11-09T00:00:00&endDate=2022-11-16T12:59:59&searchCondition=&page=2&size=5")
                .then().log().all().extract();
        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}