//package shop.kokodo.calculateservice.classifier;
//
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.classify.Classifier;
//import shop.kokodo.calculateservice.dto.OrderDto;
//import shop.kokodo.calculateservice.entity.Calculate;
//import shop.kokodo.calculateservice.entity.Order;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * packageName    : shop.kokodo.calculateservice.classifier
// * fileName       : ProcessorClassifer
// * author         : namhyeop
// * date           : 2022/09/28
// * description    :
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2022/09/28        namhyeop       최초 생성
// */
//public class ProcessorClassifier<C,T> implements Classifier<C,T> {
//
//    private Map<String , ItemProcessor<Order, Calculate>> processorMap = new HashMap<>();
//
//    //classifiable에는 ProductVO가 들어오게된다, getType을 통해 1,2,3번인지 구분한다.
//    @Override
//    public T classify(C classifiable) {
//        return (T)processorMap.get(((OrderDto)classifiable).getId());
//    }
//
//    public void setProcessorMap(Map<String, ItemProcessor<OrderDto, ApiRequestVO>> processorMap) {
//        this.processorMap = processorMap;
//    }
//}
