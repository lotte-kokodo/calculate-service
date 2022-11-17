package shop.kokodo.calculateservice.utils.coupondatafactory;

import java.util.ArrayList;

/**
 * packageName    : shop.kokodo.calculateservice.utils
 * fileName       : TestMaker
 * author         : namhyeop
 * date           : 2022/09/28
 * description    :
 * UserCouponData 생성기
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/28        namhyeop       최초 생성
 */
public class UserCoupon {
    public static void main(String[] args) {
        String tableName = "user_coupon";
        String column = "user_coupon_id, rate_coupon_id, fix_couponid, user_id, usage_status";
//        String column = "\'order_id\', \'user_id\', \'order_status\', \'delivery_member_name\', \'delivery_member_address\',\'total_price\', \'order_date\'";
        //3개
        ArrayList<Integer> atb1 = new ArrayList<>();
        atb1.add(0);
        atb1.add(1);
        atb1.add(2);
        atb1.add(3);
        atb1.add(4);
        atb1.add(5);
        atb1.add(6);
        atb1.add(7);
        atb1.add(8);
        atb1.add(9);
        atb1.add(10);

        ArrayList<Integer> atb2 = new ArrayList<>();
        atb2.add(0);
        atb2.add(1);

        int maxSize = 500;
        for (int i = 1; i <= maxSize; i++) {

            String iatb1 = String.valueOf(i);
            String iatb2 = String.valueOf(atb1.get((int) (Math.random() * 10000) % 10));
            String iatb3 = String.valueOf(atb1.get((int) (Math.random() * 10000) % 10));
            String iatb4 = String.valueOf(atb1.get((int) (Math.random() * 10000) % 10));
            String iatb5 = String.valueOf(atb1.get((int) (Math.random() * 2000) % 2));


            String column2 = iatb1 + ", " + iatb2 + ", " + iatb3 + ", " + iatb4 + ", " + iatb5;
            String query = "insert into " + tableName + " (" + column + ") values " + "(" + column2 + ");";
//            if(i == maxSize){
//                query = query.substring(0, query.length() - 1);
//            }
            System.out.println(query);
        }
    }
}
