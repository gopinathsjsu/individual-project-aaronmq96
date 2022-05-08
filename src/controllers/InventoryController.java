package controllers;

import models.InventoryItems;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryController {

    public static ArrayList<String> data = new ArrayList<>();
    public static HashMap<String, InventoryItems> items = new HashMap<>();

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
            loadItems();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public static void loadItems() {
        for (String record : data) {
            String[] item = record.split(",");
            items.put(item[1],
                    new InventoryItems(item[0], item[1], Integer.parseInt(item[2]) , Double.parseDouble(item[3])));
        }

    }

    public static void printInventory(){
        System.out.println("Inventory List: ");
        for (Map.Entry<String, InventoryItems> set :
                items.entrySet()) {
            System.out.println(set.getKey() + " => "
                    + set.getValue().getItemName()+ " " +set.getValue().getItemCategory()+" "+set.getValue().getItemQuantity()+" "+set.getValue().getItemPrice());
        }

    }

}
