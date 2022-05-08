package utils;

import models.OrderRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static utils.checkOrder.IncorrectOrders;

public class InvalidOutput implements Output{
    @Override
    public void createFile() {

        int fileNum = ThreadLocalRandom.current().nextInt(1, 100);

        try{
            FileWriter errorFile = new FileWriter(
                    "src/outputs" + "/Error_" + fileNum + ".txt");
            errorFile.write("Please correct items in order: \n");
            for (OrderRequest order : IncorrectOrders) {
                errorFile.write("Item: " + order.getOrderItem() + " \n " + " Requested Quantity : " + order.getOrderQuantity());
            }
            errorFile.close();
        }
        catch (IOException err){
            err.printStackTrace();
        }

    }
}
