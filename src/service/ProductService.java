package service;

import Models.Price;
import Models.Product;
import Models.Stock;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductService {

    PriceService priceService = new PriceService();
    StockService stockService = new StockService();

    public Set<Product> initializeProducts() {
        List<Stock> stockList = stockService.readStocksFromFile("defaultStocksList");
        List priceList = priceService.readPricesFromFile("defaultPriceList");
        Set productList = new HashSet();

        for (int i = 0; i < stockList.size(); i++) {
            Stock currentStock = stockList.get(i);
            String productName = currentStock.getProductName();
            int stock = currentStock.getQuantity();
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
        final int OFFSET = 1;
        int numberOnList = 0;
        Set <Product> products = initializeProducts();

        if (!(products.size() == 0)) {
            DecimalFormat df = new DecimalFormat("###.00");
            for (Product product : products) {
                Product tempProduct = product;
                int stockQuantity = tempProduct.getStock().getQuantity();
                Price price = tempProduct.getPrice();
                System.out.println(numberOnList + 1 + OFFSET + ". " + product.getName() + " - Obecny stan w magazynie: " + stockQuantity + " | Obecna cena: " + df.format(price.getValue()) + " zł");
            }
        } else {
            System.out.println("Przepraszamy, obecnie nie ma żadnych dostępnych produktów");
        }
    }
}
