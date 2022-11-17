package shop.kokodo.calculateservice.controller;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import shop.kokodo.calculateservice.DocumentConfiguration;
import shop.kokodo.calculateservice.dto.CalculateSearchCondition;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

/**
 * packageName    : shop.kokodo.calculateservice.controller
 * fileName       : CalculateControllerTest
 * author         : namhyeop
 * date           : 2022/10/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/26        namhyeop       최초 생성
 */

class CalculateControllerTest extends DocumentConfiguration {

    @PersistenceContext
    private EntityManager em;

    @AfterEach
    public void tearDown() {
        em.unwrap(Session.class)
                .doWork(this::cleanUpTable);
    }

    private void cleanUpTable(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeUpdate("SET REFERENTIAL_INTEGRITY FALSE");

        statement.executeUpdate("TRUNCATE TABLE \"ORDERS\"");
        statement.executeUpdate("TRUNCATE TABLE ORDER_PRODUCT");

        statement.executeUpdate("SET REFERENTIAL_INTEGRITY TRUE");
    }

    @DisplayName("정산 예정날짜 조회")
    @Test
    public void calculateExpectDay() throws Exception{
        //given
        //when
        final ExtractableResponse<Response> response = RestAssured.
                given(spec).log().all()
                .filter(document("calculate-expectDay"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/calculate/expectDay")
                .then().log().all().extract();
        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("정산 예정 금액")
    @Test
    public void calculateExpectMoney() throws Exception{
        //given
        Long sellerId = 1L;
        //when
        final ExtractableResponse<Response> response = RestAssured.
                given(spec).log().all()
                .filter(document("calculate-expect-money"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/calculate/" + sellerId + "/expectMoney")
                .then().log().all().extract();
        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("정산 리스트 조회")
    @Test
    public void calculateList() throws Exception{
        //given
        Long sellerId = 1L;
        LocalDateTime startDate = LocalDateTime.parse("1999-01-01T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2099-01-01T00:00:00");
        ProvideStatus provideStatus = ProvideStatus.PROVIDE_SUCCESS;
        CalculateType calculateType = CalculateType.MAIN_CALCULATE;
        Long id = null;
        CalculateSearchCondition calculateSearchCondition = new CalculateSearchCondition(sellerId, startDate, endDate, provideStatus, calculateType, id, 0, 5);
        //when
        final ExtractableResponse<Response> response = RestAssured.
                given(spec).log().all()
                .filter(document("calculate-list"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(calculateSearchCondition)
                .when().post("/calculate/" + sellerId+ "/calculateList")
                .then().log().all().extract();
        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("정산 리스트에서 모달 요청 테스트")
    @Test
    public void calculateModal() throws Exception{
        //given
        Map<String, Long> params = new HashMap<>();
        params.put("sellerId", 1L);
        params.put("calculateId", 1L);

        //when
        final ExtractableResponse<Response> response = RestAssured.
                given(spec).log().all()
                .filter(document("calculate-modal"))
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                //when()절에서는 이제 어떠한 uri의 api를 호출할 것인지 지정, post,get,put 이 예시이다.
                .when().get("/calculate/" + params.get("sellerId") + "/calculateModal/" + params.get("calculateId"))
                //http body 로그 출력
                .then().log().all().extract();

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("Seller Dashboard 주 정산 예정 금액")
    @Test
    public void dashBoardCardInfo() throws Exception{
        //given
        Map<String, Long> params = new HashMap<>();
        params.put("sellerId", 1L);

        //when
        final ExtractableResponse<Response> response = RestAssured.
                given(spec).log().all()
                .filter(document("calculate-dashboard-card"))
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                //when()절에서는 이제 어떠한 uri의 api를 호출할 것인지 지정, post,get,put 이 예시이다.
                .when().get("/calculate/" + params.get("sellerId") + "/SellerDashBoardExpectMoney")
                //http body 로그 출력
                .then().log().all().extract();

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

//    @DisplayName("Seller Dashboard 차트 데이터")
//    @Test
//    public void annualSaleInfo() throws Exception{
//        //given
//        Map<String, Long> params = new HashMap<>();
//        params.put("sellerId", 1L);
//
//        //when
//        final ExtractableResponse<Response> response = RestAssured.
//                given(spec).log().all()
//                .filter(document("calculate-dashboard-chart-data"))
//                .body(params)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                //when()절에서는 이제 어떠한 uri의 api를 호출할 것인지 지정, post,get,put 이 예시이다.
//                .when().get("/calculate/" + params.get("sellerId") + "/annualSalesInfo")
//                //http body 로그 출력
//                .then().log().all().extract();
//
//        //then
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
//    }

}