package by.vstu.dean.utils;

public class StringUtils {


    public static String safeTrim(String in) {
        return in == null ? "" : in.trim();
    }

}
