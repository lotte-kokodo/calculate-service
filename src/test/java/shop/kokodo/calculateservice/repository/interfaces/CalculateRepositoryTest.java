package shop.kokodo.calculateservice.repository.interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.kokodo.calculateservice.TestContext;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.exception.CalculateNotFoundException;
import shop.kokodo.calculateservice.repository.calculate.CalculateRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : shop.kokodo.calculateservice.repository.interfaces
 * fileName       : CalculateRepositoryTest
 * author         : namhyeop
 * date           : 2022/10/05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/05        namhyeop       최초 생성
 */
@SpringBootTest
class CalculateRepositoryTest extends TestContext {

    @Autowired
    CalculateRepository calculateRepository;

    @DisplayName("정산 예정 금액 테스트")
    @Test
    public void checkExpectMoney() throws Exception {
        List<Calculate> findCalculate = calculateRepository.findBySellerId(1L);
        assertThat(findCalculate.get(0).getId()).isEqualTo(1L);
    }

    @DisplayName("정산 리스트를 찾을 수 없는 경우 예외 테스트")
    @Test
    public void checkFailExpectMoney() throws Exception {
        //given
        //then
        CalculateNotFoundException calculateNotFoundException = null;

        List<Calculate> calculateList = calculateRepository.findBySellerId(4L);

        if (calculateList.isEmpty()){
            calculateNotFoundException = new CalculateNotFoundException("정산 리스트를 찾을 수 없습니다.");
        }

        assertThat(calculateNotFoundException == null ? false : true).isTrue();
    }

}