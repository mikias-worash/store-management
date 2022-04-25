package main.store;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        DBConnection db = new DBConnection("main\\db\\store.db");

//        Account acc = db.fetchAccount("Asko");
        Account acc = db.fetchAccount("Bole");

//        Store[] sList = db.fetchStoreList();
//        System.out.println(Arrays.toString(sList));
//        System.out.println(sList[3].getStoreId());

//        Product[] pList = db.fetchProductList();
//        System.out.println(Arrays.toString(pList));
//        System.out.println(pList[2].getCategory());


        Request[] rList = db.fetchRequestList(acc);

//        Request r = db.getRequestList(acc);
//        for (Request r : list) {
//            System.out.println(r);
//        }

//        if (rList) {
//            System.out.println("No request yet");
//        } else {
//        }
        System.out.println(rList[0]);

//        db.newRequest(acc, pList[0], sList[4],  15);

//        Store s = db.fetchStore(8);
//        Product p = db.fetchProduct(2);
//        System.out.println(s);
//        System.out.println(p);
    }

}
