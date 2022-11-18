package shop.kokodo.calculateservice.querydsl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
                        , null
                        , 0
                        , 2
                );
//        clear();
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<CalculateDto> calculateDtos = calculateRepository.searchCalculate(calcuateSerachCondtion, pageRequest);
        assertThat(calculateDtos.getSize()).isEqualTo(2);
        //데이터 일치성 확인
        assertThat(calculateDtos.getContent()).extracting("finalPaymentCost").containsExactly(10000L,20000L);
    }
}
