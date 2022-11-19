package shop.kokodo.calculateservice.utils.orderdatafactory;

import java.util.ArrayList;

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
    public static void main(String[] args) {
        String tableName = "orders";
        String column = "order_id, created_date, last_modified_date, member_id, order_status, delivery_member_name, delivery_member_address,total_price, order_date";
//        String column = "\'order_id\', \'user_id\', \'order_status\', \'delivery_member_name\', \'delivery_member_address\',\'total_price\', \'order_date\'";
        //3개
        ArrayList<String> atb3 = new ArrayList<>();
        atb3.add("ORDER_SUCCESS");
        atb3.add("PURCHASE_CONFIRM");
//        atb3.add("REFUND_PROCESS");

        //10개
        ArrayList<String> atb4 = new ArrayList<>();
        atb4.add("천호 452-3");
        atb4.add("구리 2-3");
        atb4.add("성수 42-3");
        atb4.add("군자 6-3");
        atb4.add("청량리 4-3");
        atb4.add("가산다지털단지 42-3");
        atb4.add("광나루 1-3");
        atb4.add("광나루 1-1");
        atb4.add("광나루 1-2");
        atb4.add("광나루 1-4");
        //10개
        ArrayList<String> atb5 = new ArrayList<>();
        atb5.add("니집");
        atb5.add("내집");
        atb5.add("우리집");
        atb5.add("그놈집");
        atb5.add("너네집");
        atb5.add("남의집");
        atb5.add("띠용");
        atb5.add("띠용1");
        atb5.add("띠용2");
        atb5.add("띠용3");

        ArrayList<Integer> atb6 = new ArrayList<>();
        atb6.add(10000);
        atb6.add(20000);
        atb6.add(30000);
        atb6.add(40000);
        atb6.add(50000);
        atb6.add(60000);
        atb6.add(70000);
        atb6.add(80000);
        atb6.add(90000);
        atb6.add(100000);

        ArrayList<String> atb7 = new ArrayList<>();
        atb7.add("1964-06-07 16:21:24");
        atb7.add("1964-06-08 16:21:24");
        atb7.add("1964-06-09 16:21:24");
        atb7.add("1964-06-10 16:21:24");
        atb7.add("1964-06-11 16:21:24");
        atb7.add("1964-06-12 16:21:24");
        atb7.add("1964-06-13 16:21:24");
        atb7.add("1964-06-14 16:21:24");
        atb7.add("1964-06-15 16:21:24");
        atb7.add("1964-06-16 16:21:24");

        int maxSize = 499;
        for (int i = 1; i <= maxSize; i++) {
            String iatb1 = String.valueOf(i);
            String iatb2 = String.valueOf(i + 1);
            String iatb3 = "\'" + String.valueOf(atb3.get((int) (Math.random() * 3000) % 3)) + "\'";
            String iatb4 = "\'" + atb4.get((int) (Math.random() * 9000) % 9) + "\'";
            String iatb5 = "\'" + atb5.get((int) (Math.random() * 9000) % 9) + "\'";
            String iatb6 = String.valueOf(atb6.get((int) (Math.random() * 9000) % 9));
            String iatb7 = "\'" + atb7.get((int) (Math.random() * 9000) % 9) + "\'";

            String column2 = iatb1 + ", " + iatb2 + ", " + iatb3 + ", " + iatb4 + ", " + iatb5 + ", " + iatb6 + ", " + iatb7;
            String query = "insert into " + tableName + " (" + column + ") values " + "(" + column2 + ");";
//            if(i == maxSize){
//                query = query.substring(0, query.length() - 1);
//            }
            System.out.println(query);
        }
    }
}
