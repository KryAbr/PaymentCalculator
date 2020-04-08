package service;

import Models.Product;
import Models.Stock;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockService {
    String stockFilepath = "";

    // Rename to readStocks() instead? this method is called both in initializeProdcuts() and will be called when viewing imports
    public List initializeStock() {
        // Read current stock values from CSV file
        ArrayList stockList = new ArrayList();
        String[] stockInfo;
        String stockLineFromCSV;
        try {
            Scanner scanner = new Scanner(new FileReader(stockFilepath));
            scanner.nextLine(); // skipping headers
            while (scanner.hasNext()) {
                stockLineFromCSV = scanner.nextLine();
                stockInfo = stockLineFromCSV.split(",");

                String name = stockInfo[0].trim();
                int quantity = Integer.parseInt(stockInfo[1].trim());
                LocalDate date = LocalDate.parse((stockInfo[2].trim()));

                Stock stockObject = new Stock(name, quantity, date);
                stockList.add(stockObject);
            }

            return stockList;
        } catch (IOException e) {

            System.out.println("Brak pliku ze stockami!");
            return null;
        }
    }

    public boolean isInStock(Product product) {
        return product.getStock().getQuantity() == 0;
    }
}
