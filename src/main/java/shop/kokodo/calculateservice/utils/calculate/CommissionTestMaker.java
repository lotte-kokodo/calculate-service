package shop.kokodo.calculateservice.utils.calculate;

import java.util.ArrayList;

/**
 * packageName    : shop.kokodo.calculateservice.utils.commission
 * fileName       : commission
 * author         : namhyeop
 * date           : 2022/10/04
 * description    :
 * ComissionData 생성기
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/04        namhyeop       최초 생성
 */
public class CommissionTestMaker {
    public static void main(String[] args) {
        String tableName = "commission";
        String column = "commission_id, seller_id, basic, sales_promotion, first_payment_delivery, delivery_support, discount_support,medium_company_cost_refund,etc";
//        String column = "\'order_id\', \'user_id\', \'order_status\', \'delivery_member_name\', \'delivery_member_address\',\'total_price\', \'order_date\'";

        //판매자 아이디
        ArrayList<Integer> atb2 = new ArrayList<>();
        atb2.add(1);

        //기본 수수료
        ArrayList<Double> atb3 = new ArrayList<>();
        atb3.add(0.05);

        //기본 수수료
        ArrayList<Double> atb4 = new ArrayList<>();
        atb4.add(0.08);

        //기본 수수료
        ArrayList<Double> atb5 = new ArrayList<>();
        atb5.add(0.03);

        //기본 수수료
        ArrayList<Double> atb6 = new ArrayList<>();
        atb6.add(0.5);

        //기본 수수료
        ArrayList<Double> atb7 = new ArrayList<>();
        atb7.add(0.02);

        //기본 수수료
        ArrayList<Double> atb8 = new ArrayList<>();
        atb8.add(0.0);

        //기본 수수료
        ArrayList<Double> atb9 = new ArrayList<>();
        atb9.add(0.01);


        int maxSize = 10;
        for (int i = 1; i <= maxSize; i++) {
            String iatb1 = String.valueOf(i);
            String iatb2 = String.valueOf(1);
            String iatb3 = String.valueOf(atb3.get(0));
            String iatb4 = String.valueOf(atb4.get(0));
            String iatb5 = String.valueOf(atb5.get(0));
            String iatb6 = String.valueOf(atb6.get(0));
            String iatb7 = String.valueOf(atb7.get(0));
            String iatb8 = String.valueOf(atb8.get(0));
            String iatb9 = String.valueOf(atb9.get(0));

            String column2 = iatb1 + ", " + iatb2 + ", " + iatb3 + ", " + iatb4 + ", " + iatb5 + ", " + iatb6 + ", " + iatb7 + ", " + iatb8 + ", " + iatb9;
            String query = "insert into " + tableName + " (" + column + ") values " + "(" + column2 + ");";
//            if(i == maxSize){
//                query = query.substring(0, query.length() - 1);
//            }
            System.out.println(query);
        }
    }
}
