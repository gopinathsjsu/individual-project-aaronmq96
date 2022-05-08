import controllers.CardsController;
import controllers.InventoryController;
import controllers.OrderRequestController;
import utils.CreateOutput;
import utils.checkOrder;

import java.util.Scanner;

public class Chain {
    String cardsFilePath;
    public void initializeInventory(){

        InventoryController inventoryController = new InventoryController();
        CardsController cardsController=new CardsController();
        OrderRequestController orderRequestController= new OrderRequestController();

        try (Scanner input = new Scanner(System.in)) {

            // Scan input from the Inventory csv
            System.out.println("Enter Inventory Data File Path: ");
            String inventoryPath = input.nextLine();
            inventoryController.readData(inventoryPath);
            inventoryController.printInventory();

            // Get the input file path for Cards csv
            System.out.println("\nEnter Cards Data File Path: ");
            String cardsPath = input.nextLine();
            cardsFilePath=cardsPath;
            cardsController.readData(cardsPath);
            cardsController.viewCards();

            // Scan Order from Order file
            System.out.println("\nEnter Order Request file path: " );
            String orderRequestPath = input.nextLine();
            orderRequestController.readData(orderRequestPath);
            orderRequestController.viewOrderRequests();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void validateOrder(){
        checkOrder checkOrder=new checkOrder();
        checkOrder.checkCategoryCap();
        checkOrder.validateAvailability();
    }

    public void createOutput(){
        CreateOutput createOutput=new CreateOutput();
        createOutput.writeToFile();
    }

    public void addNewCards(){
        CardsController.addCard(cardsFilePath);
    }


}
