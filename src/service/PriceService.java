package service;

import Models.Price;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceService {

    // Rename to readPrices() instead? this method is called both in initializeProdcuts() and will be called when viewing imports
    public List readPricesFromFile() {

        String pricesFilepath = "/home/krystian/Projects/Java/Java training/PaymentCalculatorFiles/prices.csv";

        // Read current price values from CSV file
        ArrayList priceList = new ArrayList();
        String[] priceLine;
        String priceLineFromCSV;
        try {
            Scanner scanner = new Scanner(new FileReader(pricesFilepath));
            scanner.nextLine(); // skipping headers
            while (scanner.hasNext()) {
                priceLineFromCSV = scanner.nextLine();
                priceLine = priceLineFromCSV.split(",");

                String name = priceLine[0].trim();
                float tempPrice = Float.parseFloat(priceLine[1].trim());
                BigDecimal price = BigDecimal.valueOf(tempPrice);
                LocalDate date = LocalDate.parse((priceLine[2].trim()));

                Price priceObject = new Price(name, price, date);
                priceList.add(priceObject);
            }
            return priceList;
        } catch (IOException e) {
            System.out.println("Brak pliku z cenami!");
            return null;
        }
    }

    public void importPrices() {
        Scanner in = new Scanner(System.in);
        String importFilepath = "";
        List<Price> currentPriceList = readPricesFromFile();
        List<Price>

    }

    public void showPriceImportHistory() {
        List<Price> priceList = readPricesFromFile();
        int OFFSET = 1;
        DecimalFormat df = new DecimalFormat("###.00");
        if (!(priceList == null)) {
            for (int i = 0; i < priceList.size(); i++) {
                Price tempPriceObj = priceList.get(i);
                System.out.println();
                System.out.println(i + OFFSET + ". " +
                                tempPriceObj.getProductName() +
                                " Cena: " + df.format(tempPriceObj.getValue()) + " zł" +
                                " | " + "Data: " + tempPriceObj.getDate());
            }
        }
        else {
            System.out.println("Brak pliku z historią zmian cen");
        }

    }
}
