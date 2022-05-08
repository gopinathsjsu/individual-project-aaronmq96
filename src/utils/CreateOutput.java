package utils;

import static utils.checkOrder.IncorrectOrders;

public class CreateOutput {

    public void writeToFile(){

            if (IncorrectOrders.size() == 0) {
                Output o = new ValidOutput();
                o.createFile();
            } else {
                Output o= new InvalidOutput();
                o.createFile();
            }


    }

}
