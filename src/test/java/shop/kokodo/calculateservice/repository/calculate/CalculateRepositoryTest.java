package shop.kokodo.calculateservice.repository.calculate;

import com.netflix.discovery.converters.Auto;
import com.querydsl.core.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import shop.kokodo.calculateservice.entity.Calculate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : shop.kokodo.calculateservice.repository.calculate
 * fileName       : CalculateRepositoryTest
 * author         : namhyeop
 * date           : 2022/11/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/11        namhyeop       최초 생성
 */
@SpringBootTest
class CalculateRepositoryTest {

    @Autowired
    CalculateRepository calculateRepository;

//    @Test
//    void findAnnualSaleByQueryDsl() {
//        Long sellerId = 1L;
//        List<Tuple> annualSale = calculateRepository.getAnnualSale(sellerId);
//        System.out.println("annualSale = " + annualSale);
//    }
}