public class Billing {
    public static void main(String args[]){

        Chain chain = Chain.getInstance();

        System.out.println("Initializing Inventory...");
        chain.initializeInventory();

        System.out.println("Validating Order...");
        chain.validateOrder();

        System.out.println("Writing Output to File...");
        chain.createOutput();

        System.out.println("Adding any new cards...");
        chain.addNewCards();
    }

}
