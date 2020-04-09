package service;

import Models.Price;
import Models.Product;
import Models.Stock;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    PriceService priceService = new PriceService();
    StockService stockService = new StockService();

    public List<Product> initializeProducts() {
        List<Stock> stockList = stockService.initializeStock();
        List priceList = priceService.readPricesFromFile();
        List productList = new ArrayList();

        for (int i = 0; i < stockList.size(); i++) {
            Stock tempObject = stockList.get(i);
            String productName = tempObject.getProductName();
            int stock = tempObject.getQuantity();
            BigDecimal price = findLastImportedProductPrice(productName, priceList);
            Product product = new Product(productName, stock, price);
            productList.add(product);
        }

        return productList;
    }

    private BigDecimal findLastImportedProductPrice(String name, List<Price> priceList) {

        BigDecimal productPrice = null;

        for (int i = 0; i < priceList.size(); i++) {
            Price tempPriceObject = priceList.get(i);
            String productName = tempPriceObject.getProductName();

            if (productName.equalsIgnoreCase(name)) {
                productPrice = tempPriceObject.getValue();
            }
        }

        return productPrice == null ? BigDecimal.valueOf(0) : productPrice;
    }

    public void listProducts() {
        int OFFSET = 1;
        List<Product> products = initializeProducts();

        if (!(products.size() == 0)) {
            DecimalFormat df = new DecimalFormat("###.00");
            for (int i = 0; i < products.size(); i++) {
                Product tempProduct = products.get(i);
                int stockQuantity = tempProduct.getStock().getQuantity();
                Price price = tempProduct.getPrice();
                System.out.println(i + OFFSET + ". " + products.get(i).getName() + " - Obecny stan w magazynie: " + stockQuantity + " | Obecna cena: " + df.format(price.getValue()) + " zł");
            }
        } else {
            System.out.println("Przepraszamy, obecnie nie ma żadnych dostępnych produktów");
        }
    }
}
