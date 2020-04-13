package Models;

import service.DisplayService;
import service.PriceService;
import service.ProductService;
import service.StockService;

public class Main {

    static ProductService productService = new ProductService();
    static PriceService priceService = new PriceService();
    static StockService stockService = new StockService();
    static DisplayService displayService = new DisplayService();

    public static void main(String[] args) {

        mainloop:
        while (true) {
            System.out.println();
            displayService.greeting();
            displayService.menu();

            int choice = displayService.getChoice();

            switch (choice) {
                case 1:
                    productService.listProducts();
                    break;
                case 2:
                    priceService.importPrices();
                    break;
                case 3:
                    stockService.importStocks();
                    break;
                case 4:
                    priceService.showPriceImportHistory();
                    break;
                case 5:
                    break mainloop;
            }
        }
    }
}
