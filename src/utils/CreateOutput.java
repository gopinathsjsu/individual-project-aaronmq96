package utils;

import controllers.InventoryController;
import models.OrderRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static utils.checkOrder.IncorrectOrders;

public class CreateOutput {
    static int randomNum = ThreadLocalRandom.current().nextInt(1, 100);

    public void writeToFile(){
        try{

            if (IncorrectOrders.size() == 0) {
                FileWriter errorFile = new FileWriter(
                        "src/outputs" + "/Order_" + randomNum + ".txt");
                errorFile.write("Total Amount Paid : \n");
                errorFile.write(checkOrder.total + "\n");
                errorFile.close();
                System.out.println("Output file for Orders has been generated at the given path !");
            } else {
                FileWriter errorFile = new FileWriter(
                        "src/outputs" + "/Order_" + randomNum + ".txt");
                errorFile.write("Please correct quantity of item(s) in Order: \n");
                for (OrderRequest order : IncorrectOrders) {
                    errorFile.write("Item: " + order.getOrderItem() + " \n " + " Requested Quantity : " + order.getOrderQuantity()
                            + " \t" + "Inventory Quantity Available: "
                            + InventoryController.items.get(order.getOrderItem()).getItemQuantity() + "\n");
                }
                errorFile.close();
            }

        } catch (IOException err) {
            err.printStackTrace();
        }
    }

}
