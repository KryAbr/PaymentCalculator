package main.model;

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
    private final static int MINIMUM_NUMBER_OF_PAYMENTS = 8;
    private final static int MAXIMUM_NUMBER_OF_PAYMENTS = 48;
    private final int LOWER_THRESHOLD_FOR_TIER1_INTEREST = 8;
    private final int UPPER_THRESHOLD_FOR_TIER1_INTEREST = 12;
    private final int LOWER_THRESHOLD_FOR_TIER2_INTEREST = 13;
    private final int UPPER_THRESHOLD_FOR_TIER2_INTEREST = 24;
    private final BigDecimal TIER1_DISCOUNT = BigDecimal.valueOf(0.035);
    private final BigDecimal TIER2_DISCOUNT = BigDecimal.valueOf(0.055);
    private final BigDecimal TIER3_DISCOUNT = BigDecimal.valueOf(0.12);
    private final static int MINIMUM_PRICE = 1500;
    private final static int MAXIMUM_PRICE = 45000;

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

        if (numberOfPayments >= LOWER_THRESHOLD_FOR_TIER1_INTEREST && numberOfPayments <= UPPER_THRESHOLD_FOR_TIER1_INTEREST) {
            return TIER1_DISCOUNT;
        } else if (numberOfPayments >= LOWER_THRESHOLD_FOR_TIER2_INTEREST && numberOfPayments <= UPPER_THRESHOLD_FOR_TIER2_INTEREST) {
            return TIER2_DISCOUNT;
        } else {
            return TIER3_DISCOUNT;
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
        return price >= MINIMUM_PRICE && price <= MAXIMUM_PRICE;
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
        return numberOfPayments >= MINIMUM_NUMBER_OF_PAYMENTS && numberOfPayments <= MAXIMUM_NUMBER_OF_PAYMENTS;
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
