package shop.kokodo.calculateservice.partition;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.item.ExecutionContext;
import shop.kokodo.calculateservice.repository.order.OrderRepository;

import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

/**
 * packageName    : shop.kokodo.calculateservice.partition
 * fileName       : OrderIdRangePartitionerTest
 * author         : namhyeop
 * date           : 2022/09/29
 * description    :트
 * Partioning 기능 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/29        namhyeop       최초 생성
 */
@ExtendWith(MockitoExtension.class)
class OrderIdRangePartitionerTest {
    private static OrderIdRangePartitioner partitioner;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void checkedGridSize() throws Exception {
        //given
        Mockito.lenient()
                .when(orderRepository.findMinId(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(1L); // (1)

        Mockito.lenient()
                .when(orderRepository.findMaxId(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(10L);

        partitioner = new OrderIdRangePartitioner(orderRepository, LocalDateTime.of(2021, 5, 1, 12, 10), LocalDateTime.of(2021, 12, 31, 12, 10)); // (2)

        //when
        Map<String, ExecutionContext> executionContextMap = partitioner.partition(5); // (3)

        //then
        // (4)
        ExecutionContext partition1 = executionContextMap.get("partition0");
        assertThat(partition1.getLong("minId")).isEqualTo(1L);
        assertThat(partition1.getLong("maxId")).isEqualTo(2L);

        // (5)
        ExecutionContext partition5 = executionContextMap.get("partition4");
        assertThat(partition5.getLong("minId")).isEqualTo(9L);
        assertThat(partition5.getLong("maxId")).isEqualTo(10L);
    }
}