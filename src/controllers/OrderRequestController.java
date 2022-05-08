package controllers;

import models.OrderRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderRequestController {

    public static ArrayList<String> data = new ArrayList();
    public static HashMap<String, OrderRequest> orders = new HashMap();

    public static void readData(String path) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(Paths.get(path).toFile()));
            String dataLine = "";
            boolean heading=true;
            while (true) {
                dataLine = br.readLine();
                if(heading)
                {
                    heading=false;
                    continue;
                }
                if (dataLine == null)
                    break;
                else
                    data.add(dataLine);
            }
            loadOrders();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public static void loadOrders() {
        for (String record : data) {
            String[] item = record.split(",");
            orders.put(item[0],
                    new OrderRequest(item[0],  Integer.parseInt(item[1]),item[2]));
        }

    }

    public void viewOrderRequests(){

        System.out.println("Orders: ");
        for (Map.Entry<String, OrderRequest> set :
                orders.entrySet()) {
            System.out.println(set.getKey() + " => "
                    + set.getValue().getOrderItem()+" "+ set.getValue().getOrderQuantity()+ " " + set.getValue().getCardNumber());

        }

    }
}
