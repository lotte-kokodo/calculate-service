package shop.kokodo.calculateservice.repository.interfaces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.repository.commission.CommissionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static shop.kokodo.calculateservice.factory.entity.CommissionFactory.createCommission;

/**
 * packageName    : shop.kokodo.calculateservice.repository.interfaces
 * fileName       : CommissionRepositoryTest
 * author         : namhyeop
 * date           : 2022/10/04
 * description    :
 * CommissionRepository Test
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/04        namhyeop       최초 생성
 */
@SpringBootTest
@Transactional
class CommissionRepositoryTest {

    @Autowired
    CommissionRepository commissionRepository;

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    public void before(){
        commissionRepository.deleteAll();
    }

    @Test
    public void findBySellerId() {
        Commission commission = createCommission();
        Commission saveCommission = commissionRepository.save(commission);
        Commission findCommission = commissionRepository.findBySellerId(saveCommission.getSellerId());
        clear();
        Assertions.assertThat(commission).isEqualTo(saveCommission);
        Assertions.assertThat(commission.getId()).isEqualTo(findCommission.getId());
    }

    void clear() {
        em.flush();
        em.clear();
    }
}