package Models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Payment {

    private int numberOfPayments;
    private BigDecimal monthlyPayment;
    private BigDecimal monthlyCapital;
    private BigDecimal monthlyInterestAmount;
    private BigDecimal totalPayment;
    private BigDecimal totalInterestAmount;

    public Payment(int numberOfPayments, float price) {

        BigDecimal priceAsBigDecimal = BigDecimal.valueOf(price);
        BigDecimal interestPercent = calculateInterest(numberOfPayments);
        BigDecimal totalPayment = priceAsBigDecimal.add(priceAsBigDecimal.multiply(interestPercent));
        BigDecimal monthlyPayment = totalPayment.divide(BigDecimal.valueOf(numberOfPayments), 2, RoundingMode.HALF_UP);
        BigDecimal monthlyCapital = priceAsBigDecimal.divide(BigDecimal.valueOf(numberOfPayments), 2, RoundingMode.HALF_UP);
        BigDecimal monthlyInterestAmount = monthlyPayment.subtract(monthlyCapital);
        BigDecimal totalInterestAmount = monthlyInterestAmount.multiply(BigDecimal.valueOf(numberOfPayments));

        this.numberOfPayments = numberOfPayments;
        this.totalPayment = totalPayment;
        this.monthlyInterestAmount = monthlyInterestAmount;
        this.monthlyPayment = monthlyPayment;
        this.monthlyCapital = monthlyCapital;
        this.totalInterestAmount = totalInterestAmount;
    }

    private BigDecimal calculateInterest(int numberOfPayments) {

        if (numberOfPayments >= 8 && numberOfPayments <= 12) {
            return BigDecimal.valueOf(0.035);
        } else if (numberOfPayments >= 13 && numberOfPayments <= 24) {
            return BigDecimal.valueOf(0.055);
        } else {
            return BigDecimal.valueOf(0.12);
        }
    }

    public static float gatherAndCheckPrice() {
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj cene: ");
        float price = in.nextFloat();

        if (!isValidPrice(price)) {
            System.out.println("Nieprawidlowa cena. Podaj cene z przedzialu 1500 - 45000");
            return gatherAndCheckPrice();
        }
        return price;
    }

    private static boolean isValidPrice(float price) {
        return price >= 1500 && price <= 45000;
    }

    public static int gatherAndCheckNumberOfPayments() {
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj liczbe rat: ");
        int numberOfPayments = in.nextInt();

        if (!isValidNumberOfPayments(numberOfPayments)) {
            System.out.println("Nieprawidlowa liczba rat. Dostepna oferta to miedzy 8 a 48");
            return gatherAndCheckNumberOfPayments();
        }
        return numberOfPayments;
    }

    public BigDecimal getTotalInterestAmount() {
        return totalInterestAmount;
    }

    private static boolean isValidNumberOfPayments(int numberOfPayments) {
        return numberOfPayments >= 8 && numberOfPayments <= 48;
    }

    public int getNumberOfPayments() {
        return numberOfPayments;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public BigDecimal getMonthlyCapital() {
        return monthlyCapital;
    }

    public BigDecimal getMonthlyInterestAmount() {
        return monthlyInterestAmount;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }
}
