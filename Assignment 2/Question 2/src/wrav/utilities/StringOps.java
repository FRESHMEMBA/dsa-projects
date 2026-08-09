package wrav.utilities;

public class StringOps {
    public static boolean isNumeric(String str) {
        if (str == null)
            return false;

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}