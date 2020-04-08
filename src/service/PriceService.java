package service;

import Models.Price;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceService {

    // Rename to readPrices() instead? this method is called both in initializeProdcuts() and will be called when viewing imports
    public List initializePrices() {

        String pricesFilepath = "";

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
}
