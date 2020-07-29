package service;

import Models.Product;
import Models.Stock;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockService {

    String STOCKS_FILEPATH = "/home/krystian/Projects/Java/Java training/PaymentCalculatorFiles/stock.csv";

    public List readStocksFromFile(String filepath) {

        if (filepath.equalsIgnoreCase("defaultStocksList")) {
            filepath = STOCKS_FILEPATH;
        }

        List stockList = new ArrayList();
        String[] stockInfo;
        String stockLineFromCSV;
        int nameColumnNumber = 0;
        int quantityColumnNumber = 1;
        int dateColumnNumber = 2;

        try {
            Scanner scanner = new Scanner(new FileReader(filepath));
            scanner.nextLine(); // skipping headers
            while (scanner.hasNext()) {
                stockLineFromCSV = scanner.nextLine();
                stockInfo = stockLineFromCSV.split(",");

                String name = stockInfo[nameColumnNumber].trim();
                int quantity = Integer.parseInt(stockInfo[quantityColumnNumber].trim());
                LocalDate date = LocalDate.parse((stockInfo[dateColumnNumber].trim()));

                Stock stockObject = new Stock(name, quantity, date);
                stockList.add(stockObject);
            }
            return stockList;

        } catch (IOException e) {
            System.out.println("Brak pliku ze stockami!");
            return null;
        }
    }

    public void importStocks() {
        try {
            Scanner in = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new FileWriter(STOCKS_FILEPATH, false));
            String separator = ",";

            System.out.print("Podaj sciezke pliku do zaimportowania: ");
            String importFilepath = in.nextLine();
            List<Stock> newStocksList = readStocksFromFile(importFilepath);

            writer.write("name,quantity,date");
            writer.newLine();

            for (int i = 0; i < newStocksList.size(); i++) {
                Stock currentStocksObj = newStocksList.get(i);
                String CSVLine = currentStocksObj.getProductName() + separator + currentStocksObj.getQuantity()
                        + separator + currentStocksObj.getDate().toString();
                writer.write(CSVLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException during stocks import");
        }
    }

    public boolean isInStock(Product product) {
        return product.getStock().getQuantity() == 0;
    }
}
