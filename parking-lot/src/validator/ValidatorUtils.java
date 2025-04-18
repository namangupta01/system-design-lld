package validator;

public class ValidatorUtils {
    public static boolean isValidInteger(String string) throws NumberFormatException {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
