package shop.kokodo.calculateservice.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shop.kokodo.calculateservice.dto.OrderDto;

/**
 * packageName    : shop.kokodo.calculateservice.messagequeue
 * fileName       : OrderKafkaProducer
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderStatus(String topic, Long orderId){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try{
            jsonInString = mapper.writeValueAsString(orderId);
        } catch (JsonProcessingException ex){
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data of orderId from the Order microservice: " + orderId);
    }
}
