package main.service;

import main.model.Customer;
import main.model.Payment;
import main.model.Product;

import java.text.DecimalFormat;
import java.util.Scanner;

public class DisplayService {

    public void greeting() {
        System.out.println();
        System.out.println("Witamy w sklepie z elektroniką!");
        System.out.println();
    }

    public void menu() {
        System.out.println("1. Zakup (nieaktywne)");
        System.out.println("2. Import cen");
        System.out.println("3. Import stocków");
        System.out.println("4. Zobacz historię cen");
        System.out.println("5. Wyjdz");
    }

    public void showSummary(Customer customer, Product product, Payment payment) {
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

    public final void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getChoice() {
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        return choice;
    }
}
