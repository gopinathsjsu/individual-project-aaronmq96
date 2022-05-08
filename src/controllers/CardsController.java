package controllers;

import models.OrderRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;

public class CardsController {

    public static HashSet<String> creditCards = new HashSet<>();
    private static HashSet<String> newCards = new HashSet<>();
    private static HashSet<String> orderCards = new HashSet<>();


    public static void readData(String path) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(Paths.get(path).toFile()));
            String dataLine = "";
            boolean heading = true;
            while (true) {
                dataLine = br.readLine();
                if (heading) {
                    heading = false;
                    continue;
                }
                if (dataLine == null)
                    break;
                else
                    creditCards.add(dataLine);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    public void viewCards(){
        System.out.println("Cards : ");
        for(String card:creditCards){
            System.out.println(card);
        }
    }

    public static void addCard(String path) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(Paths.get(path).toFile()));
            String dataLine = "";
            while ((dataLine = br.readLine()) != null) {
                creditCards.add(dataLine);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        //add cards from order
        for (String name : OrderRequestController.orders.keySet()) {
            OrderRequest order = OrderRequestController.orders.get(name);
            orderCards.add(order.getCardNumber());
        }


        for (String card : orderCards) {
            if (!creditCards.contains(card)) {
                newCards.add(card);
            }
        }

        try {
            FileWriter fileWriter = new FileWriter("Cards.csv", true);
            for (String line : newCards) {
                fileWriter.append("\n " + line + " ");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


