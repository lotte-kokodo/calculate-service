package shop.kokodo.calculateservice.partition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import shop.kokodo.calculateservice.repository.interfaces.OrderRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : shop.kokodo.calculateservice.partition
 * fileName       : OrderIdRangePartitioner
 * author         : namhyeop
 * date           : 2022/09/29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/29        namhyeop       최초 생성
 */

@Slf4j
@RequiredArgsConstructor
public class OrderIdRangePartitioner implements Partitioner {

    private final OrderRepository orderRepository;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        log.info("live point1");
        long min = orderRepository.findMinId(startDate, endDate);
        long max = orderRepository.findMaxId(startDate, endDate);
        log.info("minid = {}, max id = {}", min, max);
        long targetSize = (max - min) / gridSize + 1;

        Map<String, ExecutionContext> result = new HashMap<>();
        long number = 0;
        long start = min;
        long end = start + targetSize - 1;

        while (start <= max) {
            ExecutionContext value = new ExecutionContext();
            result.put("partition" + number, value);

            if (end >= max) {
                end = max;
            }

            value.putLong("minId", start); // 각 파티션마다 사용될 minId
            value.putLong("maxId", end); // 각 파티션마다 사용될 maxId
            start += targetSize;
            end += targetSize;
            number++;
        }
        return result;
    }
}
