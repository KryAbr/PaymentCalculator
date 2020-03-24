import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       String name = gatherAndCheckName();
       float price = gatherAndCheckPrice();
       int numberOfPayments = gatherAndCheckNumberOfPayments();
       long pesel = gatherAndCheckPesel();
       calculateAndShowPayments(price, numberOfPayments);
    }

    private static void calculateAndShowPayments(float price, int numberOfPayments){

        float interest = calculateInterest(numberOfPayments);
        float totalPayment = calculateTotalPayment(price, interest);
        float totalInterest = totalPayment - price;
        float monthlyPayment = totalPayment / numberOfPayments;
        float monthlyCapitalPayment = price / numberOfPayments;
        float monthlyInterest = totalInterest / numberOfPayments;

        System.out.println();
        System.out.println("Zakup udany!");
        System.out.println("Twoje miesieczna rata to: " + monthlyPayment + "zl");
        System.out.println("Kapital raty: " + monthlyCapitalPayment + "zl" + " | Odsetki w racie: " + monthlyInterest + "zl");
        System.out.println("Calkowita kwota do splaty to: " + totalPayment + "zl");
        System.out.println("Calkowita kwota odsetek: " + totalInterest + "zl");
    }

    private static String gatherAndCheckName() {
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj nazwe towaru: ");
        String name = in.next().toString();

        return name;
    }

    private static float gatherAndCheckPrice(){
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj cene: ");
        Float price = in.nextFloat();

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

        if(!hasCredit(pesel)){
            System.out.println("Niestety nie posiadasz zdolnosci kredytowej");
            return gatherAndCheckPesel();
        }

        return pesel;
    }

    private static boolean isValidPrice(float price){
        if (price >= 1500 && price <= 45000) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean isValidNumberOfPayments(int numberOfPayments){
        if(numberOfPayments >= 8 && numberOfPayments <= 48){
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean hasCredit(long pesel){

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
}
