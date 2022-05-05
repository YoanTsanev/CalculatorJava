package bg.tu_varna.sit;

import java.util.Locale;
import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static double readNumber(String prompt) {
        return scanner.nextDouble();
    }
    public static double readNumber(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max + ".");
        }
        return value;
    }
}
