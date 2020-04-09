package Models;

import service.PriceService;
import service.ProductService;
import service.StockService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ProductService productService = new ProductService();
    static PriceService priceService = new PriceService();
    static StockService stockService = new StockService();

    public static void main(String[] args) {
        productService.initializeProducts();
        greeting();
        menu();
    }

    private static void greeting() {
        System.out.println();
        System.out.println("Witamy w sklepie z elektroniką!");
        System.out.println();
    }

    private static void menu() {
        Scanner in = new Scanner(System.in);

        System.out.println("1. Zakup (nieaktywne)");
        System.out.println("2. Import cen");
        System.out.println("3. Import stocków");
        System.out.println("4. Zobacz historię cen");

        int choice = in.nextInt();

        switch (choice){
            case 1:
                productService.listProducts();
            case 2:
                priceService.importPrices();
            case 3:
                stockService.importStocks();
            case 4:
                priceService.showPriceImportHistory();
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
