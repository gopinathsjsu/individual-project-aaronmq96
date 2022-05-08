package utils;

import controllers.OrderRequestController;
import models.OrderRequest;

import java.io.FileWriter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class ValidOutput implements Output{
    @Override
    public void createFile() {

        int fileNum = ThreadLocalRandom.current().nextInt(1, 100);
try{
    FileWriter file = new FileWriter(
            "src/outputs" + "/Order_Output_" + fileNum + ".csv");

    file.write("Item,Quantity\n");

    for (Map.Entry<String, OrderRequest> set :
            OrderRequestController.orders.entrySet()) {
        file.write( set.getValue().getOrderItem()+","+ set.getValue().getOrderQuantity() +"\n");
    }

    file.write("Total Amount Paid : \t");
    file.write(checkOrder.total + "\n");
    file.close();

}catch (IOException err){
    err.printStackTrace();
}


    }
}
