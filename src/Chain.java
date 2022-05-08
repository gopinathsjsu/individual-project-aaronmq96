import controllers.CardsController;
import controllers.InventoryController;
import controllers.OrderRequestController;
import utils.CreateOutput;
import utils.checkOrder;

import java.util.Scanner;

public class Chain {
    public static String cardsFilePath;

    private static Chain chain=null;
    private Chain(){}

    public static Chain getInstance(){
        if(chain==null)
            chain = new Chain();
        return chain;
    }
    public static void initializeInventory(){



//        InventoryController inventoryController = new InventoryController();
//        CardsController cardsController=new CardsController();
//        OrderRequestController orderRequestController= new OrderRequestController();

        try (Scanner input = new Scanner(System.in)) {

            // Scan input from the Inventory csv
            System.out.println("Enter Inventory Data File Path: ");
            String inventoryPath = input.nextLine();
            InventoryController.readData(inventoryPath);
            InventoryController.printInventory();

            // Get the input file path for Cards csv
            System.out.println("\nEnter Cards Data File Path: ");
            String cardsPath = input.nextLine();
            cardsFilePath=cardsPath;
            CardsController.readData(cardsPath);
            CardsController.viewCards();

            // Scan Order from Order file
            System.out.println("\nEnter Order Request file path: " );
            String orderRequestPath = input.nextLine();
            OrderRequestController.readData(orderRequestPath);
            OrderRequestController.viewOrderRequests();

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
