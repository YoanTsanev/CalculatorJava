package bg.tu_varna.sit;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int) readNumber("Principal: ", 1_000, 1_000_000);
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Period (Years): ", 1,30);

        double mortgage = calculateMortgage(principal, annualInterest, years);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + mortgageFormatted);
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner  scanner = new Scanner(System.in).useLocale(Locale.US);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max + ".");
        }
        return value;
    }

    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentsMade) {

        float monthInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        double balance = principal
                * (Math.pow(1 + monthInterest, numberOfPayments) - Math.pow(1 + monthInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthInterest, numberOfPayments) - 1);

        return balance;
    }

    public static double calculateMortgage(
            int principal,
            float annualInterest,
            byte years) {

        float monthInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        double mortgage = principal
                * (monthInterest * Math.pow(1 + monthInterest, numberOfPayments))
                /(Math.pow(1 + monthInterest, numberOfPayments) - 1);

        return mortgage;
    }
}
