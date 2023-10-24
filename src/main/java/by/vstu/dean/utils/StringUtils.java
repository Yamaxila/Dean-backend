package by.vstu.dean.utils;

public class StringUtils {


    public static String safeTrim(String in) {
        return in == null ? "" : in.trim().replace("  ", " ");
    }

    public static boolean canBeInt(String in) {
        try {
            Integer.parseInt(in);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

}
