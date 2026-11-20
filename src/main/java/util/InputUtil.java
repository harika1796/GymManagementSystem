
package util;

import java.util.Scanner;

public class InputUtil {

    private static Scanner sc = new Scanner(System.in);

    // Read valid integer input
    public static int readNumber() {

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    // Read text input
    public static String readLine() {
        return sc.nextLine();
    }
}
