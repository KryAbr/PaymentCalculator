import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.Scanner;

public class Product {

    private String name;
    private BigDecimal price;

    private static Pair[] STOCK_TABLE= new Pair[]{new Pair(10, "Macbook"), new Pair(0, "Samsung TV"), new Pair(7, "GPS")};

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public static void listProducts() {
        for (int i = 0; i < STOCK_TABLE.length; i++) {
            Pair product = STOCK_TABLE[i];
            System.out.println(i + 1 + ". " + product.getValue().toString() + " - Obecny stan w magazynie: " + product.getKey());
        }
    }

    public static boolean isInStock(String product) {
        int tempStock = 0;
        for (int i = 0; i < STOCK_TABLE.length; i++) {
            Pair tempProduct = STOCK_TABLE[i];

            if (tempProduct.getValue().toString().equalsIgnoreCase(product)) {
                tempStock = (int) tempProduct.getKey();
            }
        }
        return !(tempStock == 0);
    }

    public static String gatherAndCheckName() {
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.print("Wpisz nazwę produktu z listy: ");
        String name = in.next();

        if (!Product.isInStock(name)) {
            System.out.println("Przepraszam, ale obecnie nie ma tego produktu.");
            System.out.println();
            return gatherAndCheckName();
        }
        return name;
    }

    public static boolean isProductListEmpty() {
        if(STOCK_TABLE != null){
            return STOCK_TABLE.length == 0;
        } else {
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}