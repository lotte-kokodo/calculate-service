package shop.kokodo.calculateservice.exception.message;

/**
 * packageName    : shop.kokodo.calculateservice.exception.message
 * fileName       : BatchErrorMessage
 * author         : namhyeop
 * date           : 2022/11/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/17        namhyeop       최초 생성
 */
public class BatchErrorMessage {
    public static final String ORDER_FEIGN_FAIL = "ORDER MS Feign 요청이 실패하였습니다.";
    public static final String PRODUCT_FEIGN_FAIL = "PRODUCT MS Feign 요청이 실패하였습니다.";
    public static final String PRODUCT_FEIGN_NULL = "PRODUCT MS 통신에서 값을 받아오지 못했습니다.";
    public static final String SELLER_FEIGN_NULL = "SELLER MS 통신에서 값을 받아오지 못했습니다.";
    public static final String SELLER_FEIGN_FINANCE_NULL = "SELLER MS 통신에서 판매자 금융 정보 값을 받아오지 못했습니다.";
    public static final String CALCULATE_DATA_NULL = "정산 데이터를 찾아오지 못했습니다.";

}
