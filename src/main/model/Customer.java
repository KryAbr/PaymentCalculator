package main.model;

import java.util.Scanner;

public class Customer {

    private long pesel;

    public Customer(long pesel) {
        this.pesel = pesel;
    }

    public static long gatherAndCheckPesel() {
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj pesel: ");
        long pesel = in.nextLong();

        if (!isPeselBlacklisted(pesel)) {
            System.out.println("Niestety nie posiadasz zdolnosci kredytowej");
            return gatherAndCheckPesel();
        }
        return pesel;
    }

    private static boolean isPeselBlacklisted(long pesel) {

        long[] badPesels = new long[]{123456, 654321};
        for (int i = 0; i < badPesels.length; i++) {
            if (badPesels[i] == pesel) {
                return false;
            }
        }
        return true;
    }

    public long getPesel() {
        return pesel;
    }
}
