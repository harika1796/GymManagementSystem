
package util;

import java.util.Scanner;

public class InputUtil {

    public static final Scanner sc = new Scanner(System.in);

    public static int readNumber() {

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    public static String readLine() {
        return sc.nextLine();
    }
}
