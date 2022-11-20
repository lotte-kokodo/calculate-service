package shop.kokodo.calculateservice.utils.orderdatafactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * packageName    : shop.kokodo.calculateservice.utils
 * fileName       : TestMaker
 * author         : namhyeop
 * date           : 2022/09/28
 * description    :
 * OrderData Maker
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/28        namhyeop       최초 생성
 */
public class OrderTestMaker {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("/Users/namhyeop/Desktop/OrderData_1000.sql");

        String orderTable = "orders";
        String orderProductTable = "order_product";
        String orderColumn = "order_id, created_date, last_modified_date, delivery_member_address, delivery_member_name, member_id, order_date, order_status, total_price";
        String orderProdcutColumn = "order_product_id, created_date, last_modified_date, member_id, product_id, qty, unit_price, order_id";


        //360개
        ArrayList<String> atb1 = getAddJanuaryCreateDate();
        getAddFebruaryCreateDate(atb1);
        getAddMarchCreateDate(atb1);
        getAddAprilCreateDate(atb1);
        getAddMayCreateDate(atb1);
        getAddJuneCreateDate(atb1);
        getAddJulyCreateDate(atb1);
        getAddAugustCreateDate(atb1);
        getAddSetemberCreateDate(atb1);
        getAddOctoberCreateDate(atb1);
        getAddNovemberCreateDate(atb1);
        getAddDecemberCreateDate(atb1);

        //360개
        ArrayList<String> atb2 = getAddJanuaryModifiedDate();
        getAddFebruaryModifiedDate(atb2);
        getAddMarchModifiedDate(atb2);
        getAddAprilModifiedDate(atb2);
        getAddMayModifiedDate(atb2);
        getAddJuneModifiedDate(atb2);
        getAddJulyModifiedDate(atb2);
        getAddAugustModifiedDate(atb2);
        getAddSetemberModifiedDate(atb2);
        getAddOctoberModifiedDate(atb2);
        getAddNovemberModifiedDate(atb2);
        getAddDecemberModifiedDate(atb2);

        //10개
        ArrayList<String> atb3 = new ArrayList<>();
        atb3.add("천호 452-3");
        atb3.add("구리 2-3");
        atb3.add("성수 42-3");
        atb3.add("군자 6-3");
        atb3.add("청량리 4-3");
        atb3.add("가산다지털단지 42-3");
        atb3.add("광나루 1-3");
        atb3.add("광나루 1-1");
        atb3.add("광나루 1-2");
        atb3.add("광나루 1-4");

        //5개
        ArrayList<String> atb4 = new ArrayList<>();
        atb4.add("김남협");
        atb4.add("권나연");
        atb4.add("도수호");
        atb4.add("오재곤");
        atb4.add("오지원");

        //5개
        ArrayList<Long> atb5 = new ArrayList<>();
        atb5.add(1L);
//        atb5.add(2L);
//        atb5.add(3L);
//        atb5.add(4L);
//        atb5.add(5L);

        //360개
        ArrayList<String> atb6 = getAddJanuaryModifiedDate();
        getAddFebruaryModifiedDate(atb6);
        getAddMarchModifiedDate(atb6);
        getAddAprilModifiedDate(atb6);
        getAddMayModifiedDate(atb6);
        getAddJuneModifiedDate(atb6);
        getAddJulyModifiedDate(atb6);
        getAddAugustModifiedDate(atb6);
        getAddSetemberModifiedDate(atb6);
        getAddOctoberModifiedDate(atb6);
        getAddNovemberModifiedDate(atb6);
        getAddDecemberModifiedDate(atb6);

        //1개
        ArrayList<String> atb7 = new ArrayList<>();
        atb7.add("ORDER_SUCCESS");
//        atb3.add("PURCHASE_CONFIRM");
//        atb3.add("REFUND_PROCESS");

        HashMap<String, List<String>> unitPrice = new HashMap<>();
        addUnitPrice(unitPrice);

        HashMap<String, List<String>> unitPrice2 = new HashMap<>();
        addUnitPrice2(unitPrice2);

        ArrayList<Integer> atb8 = new ArrayList<>();
        //product 1번 + product 11번 = 64500
        atb8.add(24600 + 39900);

        //product 2번 + product 12번 = 28700
        atb8.add(23700 + 5000);

        //product 3번 + product 13번 = 34860
        atb8.add(27860 + 7000);

        //product 4번 + product 14번 = 46900
        atb8.add(20900 + 26000);

        //product 5번 + product 15번 = 47800
        atb8.add(24900 + 22900);

        //product 6번 + product 16번 = 38850
        atb8.add(8950 + 29900);

        //product 7번 + product 17번 = 46400
        atb8.add(21900 + 24500);

        //product 8번 + product 18번 = 43400
        atb8.add(18900 + 24500);

        //product 9번 + product 19번 = 17500
        atb8.add(7500 + 10000);

        //product 10번 + product 20번 = 53800
        atb8.add(39900 + 13900);

        int maxSize = 1000;
        for (int i = 1; i <= maxSize; i++) {
            String iatb0 = String.valueOf(i);
            String iatb1 = "\'" + atb6.get((int) (Math.random() * 360000) % 360) + "\'";
            String iatb2 = "\'" + atb6.get((int) (Math.random() * 360000) % 360) + "\'";
            String iatb3 = "\'" + atb3.get((int) (Math.random() * 10000) % 10) + "\'";
            String iatb4 = "\'" + atb4.get((int) (Math.random() * 5000) % 5) + "\'";
            String iatb5 = String.valueOf(atb5.get((int) (Math.random() * 1000) % 1));
            String iatb6 = "\'" + atb6.get((int) (Math.random() * 360000) % 360) + "\'";
            String iatb7 = "\'" + atb7.get((int) (Math.random() * 1000) % 1) + "\'";
            String iatb8 = String.valueOf(atb8.get((int) (Math.random() * 10000) % 10));

            String firstProductId = null;
            String secondProductId = null;
            String qty = "1";

            if (iatb8.equals("64500")) {
                firstProductId = unitPrice.get(iatb8).get(0);
                secondProductId = unitPrice.get(iatb8).get(1);
            }

            if (iatb8.equals("28700")) {
                firstProductId = unitPrice.get(iatb8).get(0);
                secondProductId = unitPrice.get(iatb8).get(1);
            }

            if (iatb8.equals("34860")) {
                firstProductId = unitPrice.get(iatb8).get(0);
                secondProductId = unitPrice.get(iatb8).get(1);
            }

            if (iatb8.equals("46900")) {
                firstProductId = unitPrice.get(iatb8).get(0);
                secondProductId = unitPrice.get(iatb8).get(1);
            }

            if (iatb8.equals("47800")) {
                firstProductId = unitPrice.get(iatb8).get(0);
                secondProductId = unitPrice.get(iatb8).get(1);
            }

            if (iatb8.equals("38850")) {
                firstProductId = unitPrice.get(iatb8).get(0);
                secondProductId = unitPrice.get(iatb8).get(1);
            }

            if (iatb8.equals("46400")) {
                firstProductId = unitPrice.get(iatb8).get(0);
                secondProductId = unitPrice.get(iatb8).get(1);
            }

            if (iatb8.equals("43400")) {
                firstProductId = unitPrice.get(iatb8).get(0);
                secondProductId = unitPrice.get(iatb8).get(1);
            }

            if (iatb8.equals("17500")) {
                firstProductId = unitPrice.get(iatb8).get(0);
                secondProductId = unitPrice.get(iatb8).get(1);
            }

            if (iatb8.equals("53800")) {
                firstProductId = unitPrice.get(iatb8).get(0);
                secondProductId = unitPrice.get(iatb8).get(1);
            }

            //createDate == modifedDatet == OrderDate는 같은날 주문으로 위해 iatb1로 수정
            String orderValues = iatb0 + ", " + iatb1 + ", " + iatb1 + ", " + iatb3 + ", " + iatb4 + ", " + iatb5 + ", " + iatb1 + ", " + iatb7 + ", " + iatb8;
            String orderValues2 = "null" + ", " + iatb1 + ", " + iatb1 + ", " + iatb5 + ", " + firstProductId + ", " + qty + ", " + unitPrice2.get(iatb8).get(0) + ", " + iatb0;
            String orderValues3 = "null" + ", " + iatb1 + ", " + iatb1 + ", " + iatb5 + ", " + secondProductId + ", " + qty + ", " + unitPrice2.get(iatb8).get(1) + ", " + iatb0;

            String query = "insert into " + orderTable + " (" + orderColumn + ") values " + "(" + orderValues + ");\n";
            String query2 = "insert into " + orderProductTable + " (" + orderProdcutColumn + ") values " + "(" + orderValues2 + ");\n";
            String query3 = "insert into " + orderProductTable + " (" + orderProdcutColumn + ") values " + "(" + orderValues3 + ");\n";

            fw.write(query);
            fw.write(query2);
            fw.write(query3);
//            System.out.println(query);
        }
        fw.close();
    }

    private static void addUnitPrice(HashMap<String, List<String>> unitPrice) {
        unitPrice.put("64500", List.of("1", "11"));
        unitPrice.put("28700", List.of("2", "12"));
        unitPrice.put("34860", List.of("3", "13"));
        unitPrice.put("46900", List.of("4", "14"));
        unitPrice.put("47800", List.of("5", "15"));
        unitPrice.put("38850", List.of("6", "16"));
        unitPrice.put("46400", List.of("7", "17"));
        unitPrice.put("43400", List.of("8", "18"));
        unitPrice.put("17500", List.of("9", "19"));
        unitPrice.put("53800", List.of("10", "20"));
    }

    private static void addUnitPrice2(HashMap<String, List<String>> unitPrice2) {
        unitPrice2.put("64500", List.of("24600", "39900"));
        unitPrice2.put("28700", List.of("23700", "5000"));
        unitPrice2.put("34860", List.of("27860", "7000"));
        unitPrice2.put("46900", List.of("20900", "26000"));
        unitPrice2.put("47800", List.of("24900", "22900"));
        unitPrice2.put("38850", List.of("8950", "29900"));
        unitPrice2.put("46400", List.of("21900", "24500"));
        unitPrice2.put("43400", List.of("18900", "24500"));
        unitPrice2.put("17500", List.of("7500", "10000"));
        unitPrice2.put("53800", List.of("39900", "13900"));
    }

    private static void getAddDecemberCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-12-01T09:21:24");
        atb1.add("2022-12-02T10:21:24");
        atb1.add("2022-12-03T11:21:24");
        atb1.add("2022-12-04T12:21:24");
        atb1.add("2022-12-05T01:21:24");
        atb1.add("2022-12-06T02:21:24");
        atb1.add("2022-12-07T03:21:24");
        atb1.add("2022-12-08T04:21:24");
        atb1.add("2022-12-09T05:21:24");
        atb1.add("2022-12-10T06:21:24");
        atb1.add("2022-12-11T07:21:24");
        atb1.add("2022-12-12T08:21:24");
        atb1.add("2022-12-13T09:21:24");
        atb1.add("2022-12-14T10:21:24");
        atb1.add("2022-12-15T11:21:24");
        atb1.add("2022-12-16T12:21:24");
        atb1.add("2022-12-17T13:21:24");
        atb1.add("2022-12-18T14:21:24");
        atb1.add("2022-12-19T15:21:24");
        atb1.add("2022-12-20T16:21:24");
        atb1.add("2022-12-21T17:21:24");
        atb1.add("2022-12-22T18:21:24");
        atb1.add("2022-12-23T19:21:24");
        atb1.add("2022-12-24T20:21:24");
        atb1.add("2022-12-25T21:21:24");
        atb1.add("2022-12-26T22:21:24");
        atb1.add("2022-12-27T23:21:24");
        atb1.add("2022-12-28T23:21:24");
        atb1.add("2022-12-29T16:21:24");
        atb1.add("2022-12-30T16:21:24");
    }

    private static void getAddNovemberCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-11-01T09:21:24");
        atb1.add("2022-11-02T10:21:24");
        atb1.add("2022-11-03T11:21:24");
        atb1.add("2022-11-04T12:21:24");
        atb1.add("2022-11-05T01:21:24");
        atb1.add("2022-11-06T02:21:24");
        atb1.add("2022-11-07T03:21:24");
        atb1.add("2022-11-08T04:21:24");
        atb1.add("2022-11-09T05:21:24");
        atb1.add("2022-11-10T06:21:24");
        atb1.add("2022-11-11T07:21:24");
        atb1.add("2022-11-12T08:21:24");
        atb1.add("2022-11-13T09:21:24");
        atb1.add("2022-11-14T10:21:24");
        atb1.add("2022-11-15T11:21:24");
        atb1.add("2022-11-16T12:21:24");
        atb1.add("2022-11-17T13:21:24");
        atb1.add("2022-11-18T14:21:24");
        atb1.add("2022-11-19T15:21:24");
        atb1.add("2022-11-20T16:21:24");
        atb1.add("2022-11-21T17:21:24");
        atb1.add("2022-11-22T18:21:24");
        atb1.add("2022-11-23T19:21:24");
        atb1.add("2022-11-24T20:21:24");
        atb1.add("2022-11-25T21:21:24");
        atb1.add("2022-11-26T22:21:24");
        atb1.add("2022-11-27T23:21:24");
        atb1.add("2022-11-28T23:21:24");
        atb1.add("2022-11-29T16:21:24");
        atb1.add("2022-11-30T16:21:24");
    }

    private static void getAddOctoberCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-10-01T09:21:24");
        atb1.add("2022-10-02T10:21:24");
        atb1.add("2022-10-03T11:21:24");
        atb1.add("2022-10-04T12:21:24");
        atb1.add("2022-10-05T01:21:24");
        atb1.add("2022-10-06T02:21:24");
        atb1.add("2022-10-07T03:21:24");
        atb1.add("2022-10-08T04:21:24");
        atb1.add("2022-10-09T05:21:24");
        atb1.add("2022-10-10T06:21:24");
        atb1.add("2022-10-11T07:21:24");
        atb1.add("2022-10-12T08:21:24");
        atb1.add("2022-10-13T09:21:24");
        atb1.add("2022-10-14T10:21:24");
        atb1.add("2022-10-15T11:21:24");
        atb1.add("2022-10-16T12:21:24");
        atb1.add("2022-10-17T13:21:24");
        atb1.add("2022-10-18T14:21:24");
        atb1.add("2022-10-19T15:21:24");
        atb1.add("2022-10-20T16:21:24");
        atb1.add("2022-10-21T17:21:24");
        atb1.add("2022-10-22T18:21:24");
        atb1.add("2022-10-23T19:21:24");
        atb1.add("2022-10-24T20:21:24");
        atb1.add("2022-10-25T21:21:24");
        atb1.add("2022-10-26T22:21:24");
        atb1.add("2022-10-27T23:21:24");
        atb1.add("2022-10-28T23:21:24");
        atb1.add("2022-10-29T16:21:24");
        atb1.add("2022-10-30T16:21:24");
    }

    private static void getAddSetemberCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-09-01T09:21:24");
        atb1.add("2022-09-02T10:21:24");
        atb1.add("2022-09-03T11:21:24");
        atb1.add("2022-09-04T12:21:24");
        atb1.add("2022-09-05T01:21:24");
        atb1.add("2022-09-06T02:21:24");
        atb1.add("2022-09-07T03:21:24");
        atb1.add("2022-09-08T04:21:24");
        atb1.add("2022-09-09T05:21:24");
        atb1.add("2022-09-10T06:21:24");
        atb1.add("2022-09-11T07:21:24");
        atb1.add("2022-09-12T08:21:24");
        atb1.add("2022-09-13T09:21:24");
        atb1.add("2022-09-14T10:21:24");
        atb1.add("2022-09-15T11:21:24");
        atb1.add("2022-09-16T12:21:24");
        atb1.add("2022-09-17T13:21:24");
        atb1.add("2022-09-18T14:21:24");
        atb1.add("2022-09-19T15:21:24");
        atb1.add("2022-09-20T16:21:24");
        atb1.add("2022-09-21T17:21:24");
        atb1.add("2022-09-22T18:21:24");
        atb1.add("2022-09-23T19:21:24");
        atb1.add("2022-09-24T20:21:24");
        atb1.add("2022-09-25T21:21:24");
        atb1.add("2022-09-26T22:21:24");
        atb1.add("2022-09-27T23:21:24");
        atb1.add("2022-09-28T23:21:24");
        atb1.add("2022-09-29T16:21:24");
        atb1.add("2022-09-30T16:21:24");
    }

    private static void getAddAugustCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-08-01T09:21:24");
        atb1.add("2022-08-02T10:21:24");
        atb1.add("2022-08-03T11:21:24");
        atb1.add("2022-08-04T12:21:24");
        atb1.add("2022-08-05T01:21:24");
        atb1.add("2022-08-06T02:21:24");
        atb1.add("2022-08-07T03:21:24");
        atb1.add("2022-08-08T04:21:24");
        atb1.add("2022-08-09T05:21:24");
        atb1.add("2022-08-10T06:21:24");
        atb1.add("2022-08-11T07:21:24");
        atb1.add("2022-08-12T08:21:24");
        atb1.add("2022-08-13T09:21:24");
        atb1.add("2022-08-14T10:21:24");
        atb1.add("2022-08-15T11:21:24");
        atb1.add("2022-08-16T12:21:24");
        atb1.add("2022-08-17T13:21:24");
        atb1.add("2022-08-18T14:21:24");
        atb1.add("2022-08-19T15:21:24");
        atb1.add("2022-08-20T16:21:24");
        atb1.add("2022-08-21T17:21:24");
        atb1.add("2022-08-22T18:21:24");
        atb1.add("2022-08-23T19:21:24");
        atb1.add("2022-08-24T20:21:24");
        atb1.add("2022-08-25T21:21:24");
        atb1.add("2022-08-26T22:21:24");
        atb1.add("2022-08-27T23:21:24");
        atb1.add("2022-08-28T23:21:24");
        atb1.add("2022-08-29T16:21:24");
        atb1.add("2022-08-30T16:21:24");
    }

    private static void getAddJulyCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-07-01T09:21:24");
        atb1.add("2022-07-02T10:21:24");
        atb1.add("2022-07-03T11:21:24");
        atb1.add("2022-07-04T12:21:24");
        atb1.add("2022-07-05T01:21:24");
        atb1.add("2022-07-06T02:21:24");
        atb1.add("2022-07-07T03:21:24");
        atb1.add("2022-07-08T04:21:24");
        atb1.add("2022-07-09T05:21:24");
        atb1.add("2022-07-10T06:21:24");
        atb1.add("2022-07-11T07:21:24");
        atb1.add("2022-07-12T08:21:24");
        atb1.add("2022-07-13T09:21:24");
        atb1.add("2022-07-14T10:21:24");
        atb1.add("2022-07-15T11:21:24");
        atb1.add("2022-07-16T12:21:24");
        atb1.add("2022-07-17T13:21:24");
        atb1.add("2022-07-18T14:21:24");
        atb1.add("2022-07-19T15:21:24");
        atb1.add("2022-07-20T16:21:24");
        atb1.add("2022-07-21T17:21:24");
        atb1.add("2022-07-22T18:21:24");
        atb1.add("2022-07-23T19:21:24");
        atb1.add("2022-07-24T20:21:24");
        atb1.add("2022-07-25T21:21:24");
        atb1.add("2022-07-26T22:21:24");
        atb1.add("2022-07-27T23:21:24");
        atb1.add("2022-07-28T23:21:24");
        atb1.add("2022-07-29T16:21:24");
        atb1.add("2022-07-30T16:21:24");
    }

    private static void getAddJuneCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-06-01T09:21:24");
        atb1.add("2022-06-02T10:21:24");
        atb1.add("2022-06-03T11:21:24");
        atb1.add("2022-06-04T12:21:24");
        atb1.add("2022-06-05T01:21:24");
        atb1.add("2022-06-06T02:21:24");
        atb1.add("2022-06-07T03:21:24");
        atb1.add("2022-06-08T04:21:24");
        atb1.add("2022-06-09T05:21:24");
        atb1.add("2022-06-10T06:21:24");
        atb1.add("2022-06-11T07:21:24");
        atb1.add("2022-06-12T08:21:24");
        atb1.add("2022-06-13T09:21:24");
        atb1.add("2022-06-14T10:21:24");
        atb1.add("2022-06-15T11:21:24");
        atb1.add("2022-06-16T12:21:24");
        atb1.add("2022-06-17T13:21:24");
        atb1.add("2022-06-18T14:21:24");
        atb1.add("2022-06-19T15:21:24");
        atb1.add("2022-06-20T16:21:24");
        atb1.add("2022-06-21T17:21:24");
        atb1.add("2022-06-22T18:21:24");
        atb1.add("2022-06-23T19:21:24");
        atb1.add("2022-06-24T20:21:24");
        atb1.add("2022-06-25T21:21:24");
        atb1.add("2022-06-26T22:21:24");
        atb1.add("2022-06-27T23:21:24");
        atb1.add("2022-06-28T23:21:24");
        atb1.add("2022-06-29T16:21:24");
        atb1.add("2022-06-30T16:21:24");
    }

    private static void getAddMayCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-05-01T09:21:24");
        atb1.add("2022-05-02T10:21:24");
        atb1.add("2022-05-03T11:21:24");
        atb1.add("2022-05-04T12:21:24");
        atb1.add("2022-05-05T01:21:24");
        atb1.add("2022-05-06T02:21:24");
        atb1.add("2022-05-07T03:21:24");
        atb1.add("2022-05-08T04:21:24");
        atb1.add("2022-05-09T05:21:24");
        atb1.add("2022-05-10T06:21:24");
        atb1.add("2022-05-11T07:21:24");
        atb1.add("2022-05-12T08:21:24");
        atb1.add("2022-05-13T09:21:24");
        atb1.add("2022-05-14T10:21:24");
        atb1.add("2022-05-15T11:21:24");
        atb1.add("2022-05-16T12:21:24");
        atb1.add("2022-05-17T13:21:24");
        atb1.add("2022-05-18T14:21:24");
        atb1.add("2022-05-19T15:21:24");
        atb1.add("2022-05-20T16:21:24");
        atb1.add("2022-05-21T17:21:24");
        atb1.add("2022-05-22T18:21:24");
        atb1.add("2022-05-23T19:21:24");
        atb1.add("2022-05-24T20:21:24");
        atb1.add("2022-05-25T21:21:24");
        atb1.add("2022-05-26T22:21:24");
        atb1.add("2022-05-27T23:21:24");
        atb1.add("2022-05-28T23:21:24");
        atb1.add("2022-05-29T16:21:24");
        atb1.add("2022-05-30T16:21:24");
    }

    private static void getAddAprilCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-04-01T09:21:24");
        atb1.add("2022-04-02T10:21:24");
        atb1.add("2022-04-03T11:21:24");
        atb1.add("2022-04-04T12:21:24");
        atb1.add("2022-04-05T01:21:24");
        atb1.add("2022-04-06T02:21:24");
        atb1.add("2022-04-07T03:21:24");
        atb1.add("2022-04-08T04:21:24");
        atb1.add("2022-04-09T05:21:24");
        atb1.add("2022-04-10T06:21:24");
        atb1.add("2022-04-11T07:21:24");
        atb1.add("2022-04-12T08:21:24");
        atb1.add("2022-04-13T09:21:24");
        atb1.add("2022-04-14T10:21:24");
        atb1.add("2022-04-15T11:21:24");
        atb1.add("2022-04-16T12:21:24");
        atb1.add("2022-04-17T13:21:24");
        atb1.add("2022-04-18T14:21:24");
        atb1.add("2022-04-19T15:21:24");
        atb1.add("2022-04-20T16:21:24");
        atb1.add("2022-04-21T17:21:24");
        atb1.add("2022-04-22T18:21:24");
        atb1.add("2022-04-23T19:21:24");
        atb1.add("2022-04-24T20:21:24");
        atb1.add("2022-04-25T21:21:24");
        atb1.add("2022-04-26T22:21:24");
        atb1.add("2022-04-27T23:21:24");
        atb1.add("2022-04-28T23:21:24");
        atb1.add("2022-04-29T16:21:24");
        atb1.add("2022-04-30T16:21:24");
    }

    private static void getAddMarchCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-03-01T09:21:24");
        atb1.add("2022-03-02T10:21:24");
        atb1.add("2022-03-03T11:21:24");
        atb1.add("2022-03-04T12:21:24");
        atb1.add("2022-03-05T01:21:24");
        atb1.add("2022-03-06T02:21:24");
        atb1.add("2022-03-07T03:21:24");
        atb1.add("2022-03-08T04:21:24");
        atb1.add("2022-03-09T05:21:24");
        atb1.add("2022-03-10T06:21:24");
        atb1.add("2022-03-11T07:21:24");
        atb1.add("2022-03-12T08:21:24");
        atb1.add("2022-03-13T09:21:24");
        atb1.add("2022-03-14T10:21:24");
        atb1.add("2022-03-15T11:21:24");
        atb1.add("2022-03-16T12:21:24");
        atb1.add("2022-03-17T13:21:24");
        atb1.add("2022-03-18T14:21:24");
        atb1.add("2022-03-19T15:21:24");
        atb1.add("2022-03-20T16:21:24");
        atb1.add("2022-03-21T17:21:24");
        atb1.add("2022-03-22T18:21:24");
        atb1.add("2022-03-23T19:21:24");
        atb1.add("2022-03-24T20:21:24");
        atb1.add("2022-03-25T21:21:24");
        atb1.add("2022-03-26T22:21:24");
        atb1.add("2022-03-27T23:21:24");
        atb1.add("2022-03-28T23:21:24");
        atb1.add("2022-03-29T16:21:24");
        atb1.add("2022-03-30T16:21:24");
    }

    private static void getAddFebruaryCreateDate(ArrayList<String> atb1) {
        atb1.add("2022-02-01T09:21:24");
        atb1.add("2022-02-02T10:21:24");
        atb1.add("2022-02-03T11:21:24");
        atb1.add("2022-02-04T12:21:24");
        atb1.add("2022-02-05T01:21:24");
        atb1.add("2022-02-06T02:21:24");
        atb1.add("2022-02-07T03:21:24");
        atb1.add("2022-02-08T04:21:24");
        atb1.add("2022-02-09T05:21:24");
        atb1.add("2022-02-10T06:21:24");
        atb1.add("2022-02-11T07:21:24");
        atb1.add("2022-02-12T08:21:24");
        atb1.add("2022-02-13T09:21:24");
        atb1.add("2022-02-14T10:21:24");
        atb1.add("2022-02-15T11:21:24");
        atb1.add("2022-02-16T12:21:24");
        atb1.add("2022-02-17T13:21:24");
        atb1.add("2022-02-18T14:21:24");
        atb1.add("2022-02-19T15:21:24");
        atb1.add("2022-02-20T16:21:24");
        atb1.add("2022-02-21T17:21:24");
        atb1.add("2022-02-22T18:21:24");
        atb1.add("2022-02-23T19:21:24");
        atb1.add("2022-02-24T20:21:24");
        atb1.add("2022-02-25T21:21:24");
        atb1.add("2022-02-26T22:21:24");
        atb1.add("2022-02-27T23:21:24");
        atb1.add("2022-02-28T23:21:24");
        atb1.add("2022-02-28T16:21:24");
        atb1.add("2022-02-28T16:21:24");
    }

    private static ArrayList<String> getAddJanuaryCreateDate() {
        ArrayList<String> atb1 = new ArrayList<>();
        atb1.add("2022-01-01T09:21:24");
        atb1.add("2022-01-02T10:21:24");
        atb1.add("2022-01-03T11:21:24");
        atb1.add("2022-01-04T12:21:24");
        atb1.add("2022-01-05T01:21:24");
        atb1.add("2022-01-06T02:21:24");
        atb1.add("2022-01-07T03:21:24");
        atb1.add("2022-01-08T04:21:24");
        atb1.add("2022-01-09T05:21:24");
        atb1.add("2022-01-10T06:21:24");
        atb1.add("2022-01-11T07:21:24");
        atb1.add("2022-01-12T08:21:24");
        atb1.add("2022-01-13T09:21:24");
        atb1.add("2022-01-14T10:21:24");
        atb1.add("2022-01-15T11:21:24");
        atb1.add("2022-01-16T12:21:24");
        atb1.add("2022-01-17T13:21:24");
        atb1.add("2022-01-18T14:21:24");
        atb1.add("2022-01-19T15:21:24");
        atb1.add("2022-01-20T16:21:24");
        atb1.add("2022-01-21T17:21:24");
        atb1.add("2022-01-22T18:21:24");
        atb1.add("2022-01-23T19:21:24");
        atb1.add("2022-01-24T20:21:24");
        atb1.add("2022-01-25T21:21:24");
        atb1.add("2022-01-26T22:21:24");
        atb1.add("2022-01-27T23:21:24");
        atb1.add("2022-01-28T23:21:24");
        atb1.add("2022-01-29T16:21:24");
        atb1.add("2022-01-30T16:21:24");
        return atb1;
    }

    private static void getAddDecemberModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-12-01T09:21:24");
        atb1.add("2022-12-02T10:21:24");
        atb1.add("2022-12-03T11:21:24");
        atb1.add("2022-12-04T12:21:24");
        atb1.add("2022-12-05T01:21:24");
        atb1.add("2022-12-06T02:21:24");
        atb1.add("2022-12-07T03:21:24");
        atb1.add("2022-12-08T04:21:24");
        atb1.add("2022-12-09T05:21:24");
        atb1.add("2022-12-10T06:21:24");
        atb1.add("2022-12-11T07:21:24");
        atb1.add("2022-12-12T08:21:24");
        atb1.add("2022-12-13T09:21:24");
        atb1.add("2022-12-14T10:21:24");
        atb1.add("2022-12-15T11:21:24");
        atb1.add("2022-12-16T12:21:24");
        atb1.add("2022-12-17T13:21:24");
        atb1.add("2022-12-18T14:21:24");
        atb1.add("2022-12-19T15:21:24");
        atb1.add("2022-12-20T16:21:24");
        atb1.add("2022-12-21T17:21:24");
        atb1.add("2022-12-22T18:21:24");
        atb1.add("2022-12-23T19:21:24");
        atb1.add("2022-12-24T20:21:24");
        atb1.add("2022-12-25T21:21:24");
        atb1.add("2022-12-26T22:21:24");
        atb1.add("2022-12-27T23:21:24");
        atb1.add("2022-12-28T23:21:24");
        atb1.add("2022-12-29T16:21:24");
        atb1.add("2022-12-30T16:21:24");
    }

    private static void getAddNovemberModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-11-01T09:21:24");
        atb1.add("2022-11-02T10:21:24");
        atb1.add("2022-11-03T11:21:24");
        atb1.add("2022-11-04T12:21:24");
        atb1.add("2022-11-05T01:21:24");
        atb1.add("2022-11-06T02:21:24");
        atb1.add("2022-11-07T03:21:24");
        atb1.add("2022-11-08T04:21:24");
        atb1.add("2022-11-09T05:21:24");
        atb1.add("2022-11-10T06:21:24");
        atb1.add("2022-11-11T07:21:24");
        atb1.add("2022-11-12T08:21:24");
        atb1.add("2022-11-13T09:21:24");
        atb1.add("2022-11-14T10:21:24");
        atb1.add("2022-11-15T11:21:24");
        atb1.add("2022-11-16T12:21:24");
        atb1.add("2022-11-17T13:21:24");
        atb1.add("2022-11-18T14:21:24");
        atb1.add("2022-11-19T15:21:24");
        atb1.add("2022-11-20T16:21:24");
        atb1.add("2022-11-21T17:21:24");
        atb1.add("2022-11-22T18:21:24");
        atb1.add("2022-11-23T19:21:24");
        atb1.add("2022-11-24T20:21:24");
        atb1.add("2022-11-25T21:21:24");
        atb1.add("2022-11-26T22:21:24");
        atb1.add("2022-11-27T23:21:24");
        atb1.add("2022-11-28T23:21:24");
        atb1.add("2022-11-29T16:21:24");
        atb1.add("2022-11-30T16:21:24");
    }

    private static void getAddOctoberModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-10-01T09:21:24");
        atb1.add("2022-10-02T10:21:24");
        atb1.add("2022-10-03T11:21:24");
        atb1.add("2022-10-04T12:21:24");
        atb1.add("2022-10-05T01:21:24");
        atb1.add("2022-10-06T02:21:24");
        atb1.add("2022-10-07T03:21:24");
        atb1.add("2022-10-08T04:21:24");
        atb1.add("2022-10-09T05:21:24");
        atb1.add("2022-10-10T06:21:24");
        atb1.add("2022-10-11T07:21:24");
        atb1.add("2022-10-12T08:21:24");
        atb1.add("2022-10-13T09:21:24");
        atb1.add("2022-10-14T10:21:24");
        atb1.add("2022-10-15T11:21:24");
        atb1.add("2022-10-16T12:21:24");
        atb1.add("2022-10-17T13:21:24");
        atb1.add("2022-10-18T14:21:24");
        atb1.add("2022-10-19T15:21:24");
        atb1.add("2022-10-20T16:21:24");
        atb1.add("2022-10-21T17:21:24");
        atb1.add("2022-10-22T18:21:24");
        atb1.add("2022-10-23T19:21:24");
        atb1.add("2022-10-24T20:21:24");
        atb1.add("2022-10-25T21:21:24");
        atb1.add("2022-10-26T22:21:24");
        atb1.add("2022-10-27T23:21:24");
        atb1.add("2022-10-28T23:21:24");
        atb1.add("2022-10-29T16:21:24");
        atb1.add("2022-10-30T16:21:24");
    }

    private static void getAddSetemberModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-09-01T09:21:24");
        atb1.add("2022-09-02T10:21:24");
        atb1.add("2022-09-03T11:21:24");
        atb1.add("2022-09-04T12:21:24");
        atb1.add("2022-09-05T01:21:24");
        atb1.add("2022-09-06T02:21:24");
        atb1.add("2022-09-07T03:21:24");
        atb1.add("2022-09-08T04:21:24");
        atb1.add("2022-09-09T05:21:24");
        atb1.add("2022-09-10T06:21:24");
        atb1.add("2022-09-11T07:21:24");
        atb1.add("2022-09-12T08:21:24");
        atb1.add("2022-09-13T09:21:24");
        atb1.add("2022-09-14T10:21:24");
        atb1.add("2022-09-15T11:21:24");
        atb1.add("2022-09-16T12:21:24");
        atb1.add("2022-09-17T13:21:24");
        atb1.add("2022-09-18T14:21:24");
        atb1.add("2022-09-19T15:21:24");
        atb1.add("2022-09-20T16:21:24");
        atb1.add("2022-09-21T17:21:24");
        atb1.add("2022-09-22T18:21:24");
        atb1.add("2022-09-23T19:21:24");
        atb1.add("2022-09-24T20:21:24");
        atb1.add("2022-09-25T21:21:24");
        atb1.add("2022-09-26T22:21:24");
        atb1.add("2022-09-27T23:21:24");
        atb1.add("2022-09-28T23:21:24");
        atb1.add("2022-09-29T16:21:24");
        atb1.add("2022-09-30T16:21:24");
    }

    private static void getAddAugustModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-08-01T09:21:24");
        atb1.add("2022-08-02T10:21:24");
        atb1.add("2022-08-03T11:21:24");
        atb1.add("2022-08-04T12:21:24");
        atb1.add("2022-08-05T01:21:24");
        atb1.add("2022-08-06T02:21:24");
        atb1.add("2022-08-07T03:21:24");
        atb1.add("2022-08-08T04:21:24");
        atb1.add("2022-08-09T05:21:24");
        atb1.add("2022-08-10T06:21:24");
        atb1.add("2022-08-11T07:21:24");
        atb1.add("2022-08-12T08:21:24");
        atb1.add("2022-08-13T09:21:24");
        atb1.add("2022-08-14T10:21:24");
        atb1.add("2022-08-15T11:21:24");
        atb1.add("2022-08-16T12:21:24");
        atb1.add("2022-08-17T13:21:24");
        atb1.add("2022-08-18T14:21:24");
        atb1.add("2022-08-19T15:21:24");
        atb1.add("2022-08-20T16:21:24");
        atb1.add("2022-08-21T17:21:24");
        atb1.add("2022-08-22T18:21:24");
        atb1.add("2022-08-23T19:21:24");
        atb1.add("2022-08-24T20:21:24");
        atb1.add("2022-08-25T21:21:24");
        atb1.add("2022-08-26T22:21:24");
        atb1.add("2022-08-27T23:21:24");
        atb1.add("2022-08-28T23:21:24");
        atb1.add("2022-08-29T16:21:24");
        atb1.add("2022-08-30T16:21:24");
    }

    private static void getAddJulyModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-07-01T09:21:24");
        atb1.add("2022-07-02T10:21:24");
        atb1.add("2022-07-03T11:21:24");
        atb1.add("2022-07-04T12:21:24");
        atb1.add("2022-07-05T01:21:24");
        atb1.add("2022-07-06T02:21:24");
        atb1.add("2022-07-07T03:21:24");
        atb1.add("2022-07-08T04:21:24");
        atb1.add("2022-07-09T05:21:24");
        atb1.add("2022-07-10T06:21:24");
        atb1.add("2022-07-11T07:21:24");
        atb1.add("2022-07-12T08:21:24");
        atb1.add("2022-07-13T09:21:24");
        atb1.add("2022-07-14T10:21:24");
        atb1.add("2022-07-15T11:21:24");
        atb1.add("2022-07-16T12:21:24");
        atb1.add("2022-07-17T13:21:24");
        atb1.add("2022-07-18T14:21:24");
        atb1.add("2022-07-19T15:21:24");
        atb1.add("2022-07-20T16:21:24");
        atb1.add("2022-07-21T17:21:24");
        atb1.add("2022-07-22T18:21:24");
        atb1.add("2022-07-23T19:21:24");
        atb1.add("2022-07-24T20:21:24");
        atb1.add("2022-07-25T21:21:24");
        atb1.add("2022-07-26T22:21:24");
        atb1.add("2022-07-27T23:21:24");
        atb1.add("2022-07-28T23:21:24");
        atb1.add("2022-07-29T16:21:24");
        atb1.add("2022-07-30T16:21:24");
    }

    private static void getAddJuneModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-06-01T09:21:24");
        atb1.add("2022-06-02T10:21:24");
        atb1.add("2022-06-03T11:21:24");
        atb1.add("2022-06-04T12:21:24");
        atb1.add("2022-06-05T01:21:24");
        atb1.add("2022-06-06T02:21:24");
        atb1.add("2022-06-07T03:21:24");
        atb1.add("2022-06-08T04:21:24");
        atb1.add("2022-06-09T05:21:24");
        atb1.add("2022-06-10T06:21:24");
        atb1.add("2022-06-11T07:21:24");
        atb1.add("2022-06-12T08:21:24");
        atb1.add("2022-06-13T09:21:24");
        atb1.add("2022-06-14T10:21:24");
        atb1.add("2022-06-15T11:21:24");
        atb1.add("2022-06-16T12:21:24");
        atb1.add("2022-06-17T13:21:24");
        atb1.add("2022-06-18T14:21:24");
        atb1.add("2022-06-19T15:21:24");
        atb1.add("2022-06-20T16:21:24");
        atb1.add("2022-06-21T17:21:24");
        atb1.add("2022-06-22T18:21:24");
        atb1.add("2022-06-23T19:21:24");
        atb1.add("2022-06-24T20:21:24");
        atb1.add("2022-06-25T21:21:24");
        atb1.add("2022-06-26T22:21:24");
        atb1.add("2022-06-27T23:21:24");
        atb1.add("2022-06-28T23:21:24");
        atb1.add("2022-06-29T16:21:24");
        atb1.add("2022-06-30T16:21:24");
    }

    private static void getAddMayModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-05-01T09:21:24");
        atb1.add("2022-05-02T10:21:24");
        atb1.add("2022-05-03T11:21:24");
        atb1.add("2022-05-04T12:21:24");
        atb1.add("2022-05-05T01:21:24");
        atb1.add("2022-05-06T02:21:24");
        atb1.add("2022-05-07T03:21:24");
        atb1.add("2022-05-08T04:21:24");
        atb1.add("2022-05-09T05:21:24");
        atb1.add("2022-05-10T06:21:24");
        atb1.add("2022-05-11T07:21:24");
        atb1.add("2022-05-12T08:21:24");
        atb1.add("2022-05-13T09:21:24");
        atb1.add("2022-05-14T10:21:24");
        atb1.add("2022-05-15T11:21:24");
        atb1.add("2022-05-16T12:21:24");
        atb1.add("2022-05-17T13:21:24");
        atb1.add("2022-05-18T14:21:24");
        atb1.add("2022-05-19T15:21:24");
        atb1.add("2022-05-20T16:21:24");
        atb1.add("2022-05-21T17:21:24");
        atb1.add("2022-05-22T18:21:24");
        atb1.add("2022-05-23T19:21:24");
        atb1.add("2022-05-24T20:21:24");
        atb1.add("2022-05-25T21:21:24");
        atb1.add("2022-05-26T22:21:24");
        atb1.add("2022-05-27T23:21:24");
        atb1.add("2022-05-28T23:21:24");
        atb1.add("2022-05-29T16:21:24");
        atb1.add("2022-05-30T16:21:24");
    }

    private static void getAddAprilModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-04-01T09:21:24");
        atb1.add("2022-04-02T10:21:24");
        atb1.add("2022-04-03T11:21:24");
        atb1.add("2022-04-04T12:21:24");
        atb1.add("2022-04-05T01:21:24");
        atb1.add("2022-04-06T02:21:24");
        atb1.add("2022-04-07T03:21:24");
        atb1.add("2022-04-08T04:21:24");
        atb1.add("2022-04-09T05:21:24");
        atb1.add("2022-04-10T06:21:24");
        atb1.add("2022-04-11T07:21:24");
        atb1.add("2022-04-12T08:21:24");
        atb1.add("2022-04-13T09:21:24");
        atb1.add("2022-04-14T10:21:24");
        atb1.add("2022-04-15T11:21:24");
        atb1.add("2022-04-16T12:21:24");
        atb1.add("2022-04-17T13:21:24");
        atb1.add("2022-04-18T14:21:24");
        atb1.add("2022-04-19T15:21:24");
        atb1.add("2022-04-20T16:21:24");
        atb1.add("2022-04-21T17:21:24");
        atb1.add("2022-04-22T18:21:24");
        atb1.add("2022-04-23T19:21:24");
        atb1.add("2022-04-24T20:21:24");
        atb1.add("2022-04-25T21:21:24");
        atb1.add("2022-04-26T22:21:24");
        atb1.add("2022-04-27T23:21:24");
        atb1.add("2022-04-28T23:21:24");
        atb1.add("2022-04-29T16:21:24");
        atb1.add("2022-04-30T16:21:24");
    }

    private static void getAddMarchModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-03-01T09:21:24");
        atb1.add("2022-03-02T10:21:24");
        atb1.add("2022-03-03T11:21:24");
        atb1.add("2022-03-04T12:21:24");
        atb1.add("2022-03-05T01:21:24");
        atb1.add("2022-03-06T02:21:24");
        atb1.add("2022-03-07T03:21:24");
        atb1.add("2022-03-08T04:21:24");
        atb1.add("2022-03-09T05:21:24");
        atb1.add("2022-03-10T06:21:24");
        atb1.add("2022-03-11T07:21:24");
        atb1.add("2022-03-12T08:21:24");
        atb1.add("2022-03-13T09:21:24");
        atb1.add("2022-03-14T10:21:24");
        atb1.add("2022-03-15T11:21:24");
        atb1.add("2022-03-16T12:21:24");
        atb1.add("2022-03-17T13:21:24");
        atb1.add("2022-03-18T14:21:24");
        atb1.add("2022-03-19T15:21:24");
        atb1.add("2022-03-20T16:21:24");
        atb1.add("2022-03-21T17:21:24");
        atb1.add("2022-03-22T18:21:24");
        atb1.add("2022-03-23T19:21:24");
        atb1.add("2022-03-24T20:21:24");
        atb1.add("2022-03-25T21:21:24");
        atb1.add("2022-03-26T22:21:24");
        atb1.add("2022-03-27T23:21:24");
        atb1.add("2022-03-28T23:21:24");
        atb1.add("2022-03-29T16:21:24");
        atb1.add("2022-03-30T16:21:24");
    }

    private static void getAddFebruaryModifiedDate(ArrayList<String> atb1) {
        atb1.add("2022-02-01T09:21:24");
        atb1.add("2022-02-02T10:21:24");
        atb1.add("2022-02-03T11:21:24");
        atb1.add("2022-02-04T12:21:24");
        atb1.add("2022-02-05T01:21:24");
        atb1.add("2022-02-06T02:21:24");
        atb1.add("2022-02-07T03:21:24");
        atb1.add("2022-02-08T04:21:24");
        atb1.add("2022-02-09T05:21:24");
        atb1.add("2022-02-10T06:21:24");
        atb1.add("2022-02-11T07:21:24");
        atb1.add("2022-02-12T08:21:24");
        atb1.add("2022-02-13T09:21:24");
        atb1.add("2022-02-14T10:21:24");
        atb1.add("2022-02-15T11:21:24");
        atb1.add("2022-02-16T12:21:24");
        atb1.add("2022-02-17T13:21:24");
        atb1.add("2022-02-18T14:21:24");
        atb1.add("2022-02-19T15:21:24");
        atb1.add("2022-02-20T16:21:24");
        atb1.add("2022-02-21T17:21:24");
        atb1.add("2022-02-22T18:21:24");
        atb1.add("2022-02-23T19:21:24");
        atb1.add("2022-02-24T20:21:24");
        atb1.add("2022-02-25T21:21:24");
        atb1.add("2022-02-26T22:21:24");
        atb1.add("2022-02-27T23:21:24");
        atb1.add("2022-02-28T23:21:24");
        atb1.add("2022-02-28T16:21:24");
        atb1.add("2022-02-28T16:21:24");
    }

    private static ArrayList<String> getAddJanuaryModifiedDate() {
        ArrayList<String> atb1 = new ArrayList<>();
        atb1.add("2022-01-01T09:21:24");
        atb1.add("2022-01-02T10:21:24");
        atb1.add("2022-01-03T11:21:24");
        atb1.add("2022-01-04T12:21:24");
        atb1.add("2022-01-05T01:21:24");
        atb1.add("2022-01-06T02:21:24");
        atb1.add("2022-01-07T03:21:24");
        atb1.add("2022-01-08T04:21:24");
        atb1.add("2022-01-09T05:21:24");
        atb1.add("2022-01-10T06:21:24");
        atb1.add("2022-01-11T07:21:24");
        atb1.add("2022-01-12T08:21:24");
        atb1.add("2022-01-13T09:21:24");
        atb1.add("2022-01-14T10:21:24");
        atb1.add("2022-01-15T11:21:24");
        atb1.add("2022-01-16T12:21:24");
        atb1.add("2022-01-17T13:21:24");
        atb1.add("2022-01-18T14:21:24");
        atb1.add("2022-01-19T15:21:24");
        atb1.add("2022-01-20T16:21:24");
        atb1.add("2022-01-21T17:21:24");
        atb1.add("2022-01-22T18:21:24");
        atb1.add("2022-01-23T19:21:24");
        atb1.add("2022-01-24T20:21:24");
        atb1.add("2022-01-25T21:21:24");
        atb1.add("2022-01-26T22:21:24");
        atb1.add("2022-01-27T23:21:24");
        atb1.add("2022-01-28T23:21:24");
        atb1.add("2022-01-29T16:21:24");
        atb1.add("2022-01-30T16:21:24");
        return atb1;
    }
}
