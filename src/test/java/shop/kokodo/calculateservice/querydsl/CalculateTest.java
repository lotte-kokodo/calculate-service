package shop.kokodo.calculateservice.querydsl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.dto.CalculateDto;
import shop.kokodo.calculateservice.dto.CalculateSearchCondition;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;
import shop.kokodo.calculateservice.enums.calculate.WithdrawalMethod;
import shop.kokodo.calculateservice.repository.calculate.CalculateRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CalculateTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    CalculateRepository calculateRepository;

    @BeforeEach
    public void before(){
        calculateRepository.deleteAll();
    }

    @Test
    public void calculateSearch() throws Exception {
        //given
        Calculate calculate1 = Calculate.builder()
                .calculateType(CalculateType.MAIN_CALCULATE)
                .supportRate("100%")
                .provideStatus(ProvideStatus.PROVIDE_SUCCESS)
                .withdrawalMethod(WithdrawalMethod.REGULAR_WITHDRAWAL)
                .sellerId(1L)
                .finalPaymentCost(1000L)
                .build();
        Calculate saveCalculate1 = calculateRepository.save(calculate1);

        Calculate calculate2 = Calculate.builder()
                .calculateType(CalculateType.MAIN_CALCULATE)
                .supportRate("100%")
                .provideStatus(ProvideStatus.PROVIDE_SUCCESS)
                .withdrawalMethod(WithdrawalMethod.REGULAR_WITHDRAWAL)
                .sellerId(1L)
                .finalPaymentCost(2000L)
                .build();
        Calculate saveCalculate2 = calculateRepository.save(calculate2);

        CalculateSearchCondition calcuateSerachCondtion = new CalculateSearchCondition
                (1L
                        , LocalDateTime.parse("2022-10-05T15:10:10")
                        , LocalDateTime.parse("9999-10-05T15:10:10")
                        , ProvideStatus.PROVIDE_SUCCESS
                        , CalculateType.MAIN_CALCULATE
                        , null);
        clear();

        List<CalculateDto> calculateDtos = calculateRepository.searchCalculate(calcuateSerachCondtion);
        for (CalculateDto calculateDto : calculateDtos) {
            System.out.println("calculateDto = " + calculateDto);
        }
        assertThat(calculateDtos.size()).isEqualTo(2);
        //데이터 일치성 확인
        assertThat(calculateDtos.get(0).getFinalPaymentCost()).isEqualTo(1000L);
        assertThat(calculateDtos.get(1).getFinalPaymentCost()).isEqualTo(2000L);
    }

    public void clear() {
        em.flush();
        em.clear();
    }
}
