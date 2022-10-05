package shop.kokodo.calculateservice.repository.interfaces;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.job.CalculateJobConfiguration;
import shop.kokodo.calculateservice.tasklet.ApiEndTasklet;
import shop.kokodo.calculateservice.tasklet.ApiStartTasklet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static shop.kokodo.calculateservice.factory.entity.CommissionFactory.createCommission;

/**
 * packageName    : shop.kokodo.calculateservice.repository.interfaces
 * fileName       : CommissionRepositoryTest
 * author         : namhyeop
 * date           : 2022/10/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/04        namhyeop       최초 생성
 */

//@RunWith(SpringRunner.class)
//@SpringBatchTest
//@SpringBootTest(classes = {CalculateJobConfiguration.class, ApiStartTasklet.class, ApiEndTasklet.class, TestBatchConfig.class})
//@Transactional
@DataJpaTest
class CommissionRepositoryTest {

    @Autowired CommissionRepository commissionRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void findBySellerId() {
        Commission commission = createCommission();
        System.out.println("before commission = " + commission);
        commissionRepository.save(commission);
        System.out.println("after commission = " + commission);
        clear();
        Commission bySellerId = commissionRepository.findBySellerId(1L);
        System.out.println("bySellerId = " + bySellerId);
    }

    void clear(){
        em.flush();
        em.clear();
    }
}