package shop.kokodo.calculateservice.querydsl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.kokodo.calculateservice.TestContext;
import shop.kokodo.calculateservice.dto.CalculateDto;
import shop.kokodo.calculateservice.dto.CalculateSearchCondition;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;
import shop.kokodo.calculateservice.repository.calculate.CalculateRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : shop.kokodo.calculateservice.querydsl
 * fileName       : CalculateTest
 * author         : namhyeop
 * date           : 2022/10/06
 * description    :
 * 정산리스트를 조회하는 쿼리 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/06        namhyeop       최초 생성
 */
@SpringBootTest
public class CalculateTest extends TestContext {

    @Autowired
    CalculateRepository calculateRepository;

    @DisplayName("동적 쿼리를 통한 정산 내역 조회")
    @Test
    public void calculateSearch() throws Exception {
        CalculateSearchCondition calcuateSerachCondtion = new CalculateSearchCondition
                (1L
                        , LocalDateTime.parse("2022-10-05T15:10:10")
                        , LocalDateTime.parse("9999-10-05T15:10:10")
                        , ProvideStatus.PROVIDE_SUCCESS
                        , CalculateType.MAIN_CALCULATE
                        , null);
//        clear();

        List<CalculateDto> calculateDtos = calculateRepository.searchCalculate(calcuateSerachCondtion);
        assertThat(calculateDtos.size()).isEqualTo(1);
        //데이터 일치성 확인
        assertThat(calculateDtos.get(0).getFinalPaymentCost()).isEqualTo(10000L);
        assertThat(calculateDtos.get(0).getType()).isEqualTo(CalculateType.MAIN_CALCULATE);
    }
}
