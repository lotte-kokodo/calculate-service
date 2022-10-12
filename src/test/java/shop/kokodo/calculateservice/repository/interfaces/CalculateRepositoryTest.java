package shop.kokodo.calculateservice.repository.interfaces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.exception.CalculateNotFoundException;
import shop.kokodo.calculateservice.repository.calculate.CalculateRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static shop.kokodo.calculateservice.factory.entity.CalculateFactory.createCalculate;

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
@Transactional
class CalculateRepositoryTest {

    @Autowired
    CalculateRepository calculateRepository;
    @PersistenceContext
    EntityManager em;

    @BeforeEach
    public void beforEach() {
        calculateRepository.deleteAll();
    }

    @Test
    public void checkExpectMoney() throws Exception {
        //given
        Calculate calculate = createCalculate();
//        //when
        calculateRepository.save(calculate);
        clear();
//        //then
        List<Calculate> findCalculate = calculateRepository.findBySellerId(1L).orElseThrow(CalculateNotFoundException::new);
        Assertions.assertThat(findCalculate.get(0).getId()).isEqualTo(1);
    }

    void clear() {
        em.flush();
        em.clear();
    }
}