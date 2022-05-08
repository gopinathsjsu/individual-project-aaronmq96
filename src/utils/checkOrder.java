package utils;

import controllers.InventoryController;
import controllers.OrderRequestController;
import models.InventoryItems;
import models.OrderRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class checkOrder {
    private static int essentialsCap = 3;
    private static int luxuryCap = 4;
    private static int miscCap = 6;
    public static List<Boolean> orderCapValidator = new ArrayList();
    public static ArrayList<OrderRequest> IncorrectOrders = new ArrayList<>();
    public static double total = 0.0D;

    public void checkCategoryCap(){
        int essCount=essentialsCap;
        int luxCount=luxuryCap;
        int miscCount=miscCap;

        for (Map.Entry<String, OrderRequest> set :
                OrderRequestController.orders.entrySet()) {

            String item=set.getKey();
            String category= InventoryController.items.get(item).getItemCategory();

            System.out.println(item+" "+category);
            if(category.equals("Essential"))
                essCount-=set.getValue().getOrderQuantity();
            else if(category.equals("Luxury"))
                luxCount-=set.getValue().getOrderQuantity();
            else
                miscCount-=set.getValue().getOrderQuantity();
        }
        System.out.println("Essentials Count: "+ (essentialsCap-essCount) + " vs. Essentials Capacity: " + essentialsCap);
        System.out.println("Luxury Count: "+ (luxuryCap-luxCount) + " vs. Luxury Capacity: "+ luxuryCap);
        System.out.println("Miscellaneous Count: "+ (miscCap-miscCount)+" vs. Miscellaneous Capacity: "+miscCap);

        orderCapValidator.add(essCount<0?false:true);
        orderCapValidator.add(luxCount<0?false:true);
        orderCapValidator.add(miscCount<0?false:true);
    }

    public void validateAvailability() {

        for (Map.Entry<String, OrderRequest> set :
                OrderRequestController.orders.entrySet()) {
            String item=set.getKey();
            InventoryItems inventoryItem=InventoryController.items.get(item);
            int inStockQuantity= inventoryItem.getItemQuantity();
            if(inStockQuantity>=set.getValue().getOrderQuantity()){
                if(inventoryItem.getItemCategory().equals("Essential") && orderCapValidator.get(0)){
                    total+=set.getValue().getOrderQuantity()*inventoryItem.getItemPrice();
                }
                else if(inventoryItem.getItemCategory().equals("Luxury") && orderCapValidator.get(1)){
                    total+=set.getValue().getOrderQuantity()*inventoryItem.getItemPrice();
                }
                else if(inventoryItem.getItemCategory().equals("Misc") && orderCapValidator.get(2)){
                    total+=set.getValue().getOrderQuantity()*inventoryItem.getItemPrice();
                }
                else
                    IncorrectOrders.add(set.getValue());
            }
            else{
                IncorrectOrders.add(set.getValue());
            }
        }

        System.out.println("Amount Payable: " + total );
    }



}

