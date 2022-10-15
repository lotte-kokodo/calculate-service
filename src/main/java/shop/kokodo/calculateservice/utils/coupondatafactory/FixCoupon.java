package shop.kokodo.calculateservice.utils.coupondatafactory;

import java.util.ArrayList;

/**
 * packageName    : shop.kokodo.calculateservice.utils
 * fileName       : TestMaker
 * author         : namhyeop
 * date           : 2022/09/28
 * description    :
 * FixCouponData 생성기
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/28        namhyeop       최초 생성
 */
public class FixCoupon {
    public static void main(String[] args) {
        String tableName = "fix_coupon";
        String column = "fix_coupon_id, name, regdate, price, min_price, start_date, end_date, product_id";
//        String column = "\'order_id\', \'user_id\', \'order_status\', \'delivery_name\', \'delivery_address\',\'total_price\', \'order_date\'";
        //10개
        ArrayList<String> atb2 = new ArrayList<>();
        atb2.add("쿠폰이름1");
        atb2.add("쿠폰이름2");
        atb2.add("쿠폰이름3");
        atb2.add("쿠폰이름4");
        atb2.add("쿠폰이름5");
        atb2.add("쿠폰이름6");
        atb2.add("쿠폰이름7");
        atb2.add("쿠폰이름8");
        atb2.add("쿠폰이름9");
        atb2.add("쿠폰이름10");

        ArrayList<String> atb3 = new ArrayList<>();
        atb3.add("1964-06-07 16:21:24");
        atb3.add("1964-06-08 16:21:24");
        atb3.add("1964-06-09 16:21:24");
        atb3.add("1964-06-10 16:21:24");
        atb3.add("1964-06-11 16:21:24");
        atb3.add("1964-06-12 16:21:24");
        atb3.add("1964-06-13 16:21:24");
        atb3.add("1964-06-14 16:21:24");
        atb3.add("1964-06-15 16:21:24");
        atb3.add("1964-06-16 16:21:24");

        ArrayList<Integer> atb4 = new ArrayList<>();
        atb4.add(1400000);
        atb4.add(20000000);
        atb4.add(3132133);
        atb4.add(25800);
        atb4.add(1000);
        atb4.add(400);
        atb4.add(7000);
        atb4.add(8000);
        atb4.add(90000);

        ArrayList<Integer> atb5 = new ArrayList<>();
        atb5.add(10000);
        atb5.add(20000);
        atb5.add(30000);
        atb5.add(40000);
        atb5.add(50000);
        atb5.add(60000);
        atb5.add(70000);
        atb5.add(80000);
        atb5.add(90000);

        ArrayList<String> atb6 = new ArrayList<>();
        atb6.add("1964-06-07 16:21:24");
        atb6.add("1964-06-08 16:21:24");
        atb6.add("1964-06-09 16:21:24");
        atb6.add("1964-06-10 16:21:24");
        atb6.add("1964-06-11 16:21:24");
        atb6.add("1964-06-12 16:21:24");
        atb6.add("1964-06-13 16:21:24");
        atb6.add("1964-06-14 16:21:24");
        atb6.add("1964-06-15 16:21:24");
        atb6.add("1964-06-16 16:21:24");

        ArrayList<String> atb7 = new ArrayList<>();
        atb7.add("1964-06-20 16:21:24");
        atb7.add("1964-06-21 16:21:24");
        atb7.add("1964-06-22 16:21:24");
        atb7.add("1964-06-23 16:21:24");
        atb7.add("1964-06-24 16:21:24");
        atb7.add("1964-06-25 16:21:24");
        atb7.add("1964-06-26 16:21:24");
        atb7.add("1964-06-27 16:21:24");
        atb7.add("1964-06-28 16:21:24");
        atb7.add("1964-06-29 16:21:24");


        int maxSize = 500;
        for (int i = 1; i <= maxSize; i++) {
            String iatb1 = String.valueOf(i);
            String iatb2 = "\"" + String.valueOf(atb2.get((int) (Math.random() * 10000) % 10)) + "\"";
            String iatb3 = "\"" + String.valueOf(atb3.get((int) (Math.random() * 9000) % 10)) + "\"";
            String iatb4 = String.valueOf(atb4.get((int) (Math.random() * 8000) % 9));
            String iatb5 = String.valueOf(atb5.get((int) (Math.random() * 8000) % 9));
            String iatb6 = "\"" + String.valueOf(atb6.get((int) (Math.random() * 9000) % 10)) + "\"";
            String iatb7 = "\"" + String.valueOf(atb7.get((int) (Math.random() * 9000) % 10)) + "\"";
            String iatb8 = String.valueOf((int) (Math.random() * 1000000) % 1000);

            String column2 = iatb1 + ", " + iatb2 + ", " + iatb3 + ", " + iatb4 + ", " + iatb5 + ", " + iatb6 + ", " + iatb7 + "," + iatb8;
            String query = "insert into " + tableName + " (" + column + ") values " + "(" + column2 + ");";
//            if(i == maxSize){
//                query = query.substring(0, query.length() - 1);
//            }
            System.out.println(query);
        }
    }
}
