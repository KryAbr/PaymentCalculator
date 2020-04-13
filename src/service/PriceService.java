package service;

import Models.Price;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceService {

    private String PRICES_FILEPATH = "/home/krystian/Projects/Java/Java training/PaymentCalculatorFiles/prices.csv";

    public List<Price> readPricesFromFile(String filepath) {

        if (filepath.equalsIgnoreCase("defaultPriceList")) {
            filepath = PRICES_FILEPATH;
        }

        ArrayList priceList = new ArrayList();
        String[] priceLine;
        String priceLineFromCSV;
        try {
            Scanner scanner = new Scanner(new FileReader(filepath));
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
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PRICES_FILEPATH, true));

            String separator = ",";

            System.out.print("Podaj sciezke pliku do zaimportowania: ");
            Scanner in = new Scanner(System.in);

            String importFilepath = in.nextLine();
            List<Price> newPriceList = readPricesFromFile(importFilepath);

            for (int i = 0; i < newPriceList.size(); i++) {
                Price currentPriceObj = newPriceList.get(i);
                String CSVLine = currentPriceObj.getProductName() + separator + currentPriceObj.getValue().toString()
                        + separator + currentPriceObj.getDate().toString();
                writer.append(CSVLine);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException during price import");
        }
    }

    public void showPriceImportHistory() {
        List<Price> priceList = readPricesFromFile(PRICES_FILEPATH);
        int OFFSET = 1;
        DecimalFormat df = new DecimalFormat("###.00");
        if (!(priceList == null)) {
            for (int i = 0; i < priceList.size(); i++) {
                Price tempPriceObj = priceList.get(i);
                System.out.println();
                System.out.print(i + OFFSET + ". " +
                        tempPriceObj.getProductName() +
                        " Cena: " + df.format(tempPriceObj.getValue()) + " zł" +
                        " | " + "Data: " + tempPriceObj.getDate());
            }
            System.out.println();
        } else {
            System.out.println("Brak pliku z historią zmian cen");
        }
    }
}
