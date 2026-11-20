
package util;

public class ValidationUtil {

    // Validate a 10-digit Indian phone number
    public static boolean isValidPhone(String phone) {

        if (phone.length() != 10) {
            return false;
        }

        if (phone.charAt(0) < '6' || phone.charAt(0) > '9') {
            return false;
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // Validate a name containing only letters and spaces
    public static boolean isValidName(String name) {

        if (name.isEmpty()) {
            return false;
        }

        for (char ch : name.toCharArray()) {
            if (!Character.isLetter(ch) && ch != ' ') {
                return false;
            }
        }

        return true;
    }
}
