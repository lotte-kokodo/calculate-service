package shop.kokodo.calculateservice.repository.interfaces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.kokodo.calculateservice.TestContext;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.repository.commission.CommissionRepository;

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
@DataJpaTest
class CommissionRepositoryTest extends TestContext {

    @Autowired
    CommissionRepository commissionRepository;
    Commission commission1;

    @BeforeEach
    public void beforeEach() {
        commission1 = commissionRepository.save(createCommission());
    }

    @DisplayName("셀러 아이디를 사용한 수수료 조회")
    @Test
    public void findBySellerId() {
        Commission findCommission = commissionRepository.findBySellerId(commission1.getSellerId());
        Assertions.assertThat(commission1.getId()).isEqualTo(findCommission.getId());
    }
}