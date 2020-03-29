import javafx.util.Pair;

import java.util.Scanner;

public class Main {

    private static Pair[] STOCK_TABLE = new Pair[]{new Pair(10, "Macbook"), new Pair(0, "Samsung TV"), new Pair(7, "GPS")};

    public static void main(String[] args) {

       String name = gatherAndCheckName();
       float price = gatherAndCheckPrice();
       int numberOfPayments = gatherAndCheckNumberOfPayments();
       long pesel = gatherAndCheckPesel();
       calculateAndShowPayments(price, numberOfPayments, name, pesel);
    }

    private static void calculateAndShowPayments(float price, int numberOfPayments, String name, long pesel){

        float interest = calculateInterest(numberOfPayments);
        float totalPayment = calculateTotalPayment(price, interest);
        float totalInterest = totalPayment - price;
        float monthlyPayment = totalPayment / numberOfPayments;
        float monthlyCapitalPayment = price / numberOfPayments;
        float monthlyInterest = totalInterest / numberOfPayments;

        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("Zakup udany! Prosimy sprawdź poprawność danych");
        System.out.println("Twój numer PESEL to " + pesel);
        System.out.println("Kupiłeś: " + name);
        System.out.println("Twoje miesieczna rata to: " + monthlyPayment + "zl");
        System.out.println("Kapital raty: " + monthlyCapitalPayment + "zl" + " | Odsetki w racie: " + monthlyInterest + "zl");
        System.out.println("Calkowita kwota do splaty to: " + totalPayment + "zl");
        System.out.println("Calkowita kwota odsetek: " + totalInterest + "zl");
        System.out.println("-----------------------------------------------------");
    }

    private static String gatherAndCheckName() {
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.println("Witamy w sklepie z elektroniką!");
        System.out.println();
        listProducts();
        System.out.println();
        System.out.print("Wpisz nazwę produktu z listy: ");
        String name = in.next();

        if(!isInStock(name)){
            System.out.println("Przepraszam, ale obecnie ma tego produktu.");
            System.out.println();
            return gatherAndCheckName();
        }
        return name;
    }

    private static float gatherAndCheckPrice(){
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj cene: ");
        float price = in.nextFloat();

        if(!isValidPrice(price)){
            System.out.println("Nieprawidlowa cena. Podaj cene z przedzialu 1500 - 45000");
            return gatherAndCheckPrice();
        }
        return price;
    }

    private static int gatherAndCheckNumberOfPayments(){
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj liczbe rat: ");
        int numberOfPayments = in.nextInt();

        if(!isValidNumberOfPayments(numberOfPayments)){
            System.out.println("Nieprawidlowa liczba rat. Dostepna oferta to miedzy 8 a 48");
            return gatherAndCheckNumberOfPayments();
        }
        return numberOfPayments;
    }

    private static long gatherAndCheckPesel(){
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj pesel: ");
        long pesel = in.nextLong();

        if(!isCredible(pesel)){
            System.out.println("Niestety nie posiadasz zdolnosci kredytowej");
            return gatherAndCheckPesel();
        }
        return pesel;
    }

    private static boolean isValidPrice(float price){
        return price >= 1500 && price <= 45000;
    }

    private static boolean isValidNumberOfPayments(int numberOfPayments){
        return numberOfPayments >= 8 && numberOfPayments <= 48;
    }

    private static boolean isCredible(long pesel){

        long[] badPesels = new long[]{123456, 654321};
        for(int i = 0; i < badPesels.length; i++)
        {
            if(badPesels[i] == pesel){
                return false;
            }
        }
        return true;
    }

    private static float calculateInterest(int numberOfPayments){
        if(numberOfPayments >= 8 && numberOfPayments <= 12){
            return (float) 0.035;
        }
        else if(numberOfPayments >= 13 && numberOfPayments <= 24){
            return (float) 0.055;
        }
        else {
            return (float) 0.12;
        }
    }

    private static float calculateTotalPayment(float price, float interest){
        float totalPrice = price + price * interest;

        return totalPrice;
    }

    private static boolean isInStock(String product){
        int tempStock = 0;
        for(int i = 0; i < STOCK_TABLE.length; i++){
            Pair tempProduct = STOCK_TABLE[i];

            if (tempProduct.getValue().toString().equalsIgnoreCase(product)){
                tempStock = (int)tempProduct.getKey();
            }
        }
        return !(tempStock == 0);
    }

    private static void listProducts() {
        for(int i = 0; i < STOCK_TABLE.length; i++){
            Pair product = STOCK_TABLE[i];
            System.out.println(i + 1 + ". " + product.getValue().toString() + " - Obecny stan w magazynie: " + product.getKey());
        }
    }
}
