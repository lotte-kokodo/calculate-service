package shop.kokodo.calculateservice.querydsl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import shop.kokodo.calculateservice.TestContext;
import shop.kokodo.calculateservice.dto.SaleListDto;
import shop.kokodo.calculateservice.dto.SaleListSearchCondition;
import shop.kokodo.calculateservice.repository.commission.CommissionRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : shop.kokodo.calculateservice.querydsl
 * fileName       : CommissionTest
 * author         : namhyeop
 * date           : 2022/11/07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/07        namhyeop       최초 생성
 */
@SpringBootTest
public class CommissionTest extends TestContext {
    @Autowired
    CommissionRepository commissionRepository;

    @DisplayName("동적 쿼리를 통한 판매 내역 내역 조회")
    @Test
    public void saleListSearch() throws Exception {
        SaleListSearchCondition saleListSearchCondition = new SaleListSearchCondition
                (1L, LocalDateTime.parse("1900-01-01T00:00:00"), LocalDateTime.parse("2999-01-01T00:00:00"), "");

        Pageable pageable = PageRequest.of(0, 2);
        Page<SaleListDto> saleListDtos = commissionRepository.searchSaleList(saleListSearchCondition, pageable);

        assertThat(saleListDtos.getSize()).isEqualTo(2);
        assertThat(saleListDtos.getContent()).extracting("finalPaymentCost").containsExactly(10000L,20000L);

    }
}
