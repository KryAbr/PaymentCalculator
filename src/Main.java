import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {

        greeting();

        if (!Product.isProductListEmpty()) {

            Product.listProducts();
            String name = Product.gatherAndCheckName();
            float price = Payment.gatherAndCheckPrice();
            int numberOfPayments = Payment.gatherAndCheckNumberOfPayments();
            long pesel = Customer.gatherAndCheckPesel();

            Payment payment = new Payment(numberOfPayments, price);
            Product product = new Product(name, BigDecimal.valueOf(price));
            Customer customer = new Customer(pesel);

            showSummary(customer, product, payment);

        } else {
            System.out.println("Przepraszamy, obecnie nie ma żadnych dostępnych produktów");
        }
    }

    private static void greeting() {
        System.out.println();
        System.out.println("Witamy w sklepie z elektroniką!");
        System.out.println();
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
