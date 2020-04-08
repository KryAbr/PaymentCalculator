package Models;

import service.ProductService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProductService productService = new ProductService();
        List<Product> products = productService.initializeProducts();

        greeting();

        if (!(products.size() == 0)) {
            listProducts(products);
        } else {
            System.out.println("Przepraszamy, obecnie nie ma żadnych dostępnych produktów");
        }
    }

    private static void greeting() {
        System.out.println();
        System.out.println("Witamy w sklepie z elektroniką!");
        System.out.println();
    }

    private static void listProducts(List<Product> products) {
        int OFFSET = 1;
        DecimalFormat df = new DecimalFormat("###.00");
        for (int i = 0; i < products.size(); i++) {
            Product tempProduct = products.get(i);
            int stockQuantity = tempProduct.getStock().getQuantity();
            Price price = tempProduct.getPrice();
            System.out.println(i + OFFSET + ". " + products.get(i).getName() + " - Obecny stan w magazynie: " + stockQuantity + " | Obecna cena: " + df.format(price.getValue()) + " zł");
        }
    }

    private static void showSummary(Customer customer, Product product, Payment payment) {
        DecimalFormat df = new DecimalFormat("###.00");

        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("Zakup udany!");
        System.out.println("Prosimy sprawdź poprawność danych");
        System.out.println();
        System.out.println("Twój numer PESEL to: " + customer.getPesel());
        System.out.println("Kupiłeś: " + product.getName());
        System.out.println("Cena produktu: " + df.format(product.getPrice()) + " zł");
        System.out.println("Ilość rat: " + payment.getNumberOfPayments());
        System.out.println("Twoje miesieczna rata to: " + df.format(payment.getMonthlyPayment()) + " zł");
        System.out.println("Kapital raty: " + df.format(payment.getMonthlyCapital()) + " zł" + " | Odsetki w racie: " + df.format(payment.getMonthlyInterestAmount()) + " zł");
        System.out.println("Calkowita kwota do splaty to: " + df.format(payment.getTotalPayment()) + " zł");
        System.out.println("Calkowita kwota odsetek: " + df.format(payment.getTotalInterestAmount()) + " zł");
        System.out.println("-----------------------------------------------------");
    }
}
