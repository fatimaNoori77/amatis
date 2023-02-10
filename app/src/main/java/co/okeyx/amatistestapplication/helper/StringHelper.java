package co.okeyx.amatistestapplication.helper;

import java.text.DecimalFormat;

public class StringHelper {

    public static String setComma(final String string) {
        double amount = Double.parseDouble(string);
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    public static String toEnglishDigits(String eng) {

        if (eng == null) return "";
        char[] chars = {'٩', '٨', '٧', '٦', '٥', '٤', '٣', '٢', '١', '٠'};

        char[] persian = {'۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < eng.length(); ++i) {
            if (eng.charAt(i) == chars[0] || eng.charAt(i) == persian[0]) {
                builder.append("0");
                continue;
            }
            if (eng.charAt(i) == chars[1] || eng.charAt(i) == persian[1]) {
                builder.append("1");
                continue;
            }
            if (eng.charAt(i) == chars[2] || eng.charAt(i) == persian[2]) {
                builder.append("2");
                continue;
            }
            if (eng.charAt(i) == chars[3] || eng.charAt(i) == persian[3]) {
                builder.append("3");
                continue;
            }
            if (eng.charAt(i) == chars[4] || eng.charAt(i) == persian[4]) {
                builder.append("4");
                continue;
            }
            if (eng.charAt(i) == chars[5] || eng.charAt(i) == persian[5]) {
                builder.append("5");
                continue;
            }
            if (eng.charAt(i) == chars[6] || eng.charAt(i) == persian[6]) {
                builder.append("6");
                continue;
            }
            if (eng.charAt(i) == chars[7] || eng.charAt(i) == persian[7]) {
                builder.append("7");
                continue;
            }
            if (eng.charAt(i) == chars[8] || eng.charAt(i) == persian[8]) {
                builder.append("8");
                continue;
            }
            if (eng.charAt(i) == chars[9] || eng.charAt(i) == persian[9]) {
                builder.append("9");
                continue;
            }
            builder.append(eng.charAt(i));
        }
        return builder.toString();
    }

    public static String toPersianDigits(String eng) {

        if (eng == null) return "";
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < eng.length(); ++i) {
            if (eng.charAt(i) == chars[0]) {
                builder.append("۰");
                continue;
            }
            if (eng.charAt(i) == chars[1]) {
                builder.append("۱");
                continue;
            }
            if (eng.charAt(i) == chars[2]) {
                builder.append("۲");
                continue;
            }
            if (eng.charAt(i) == chars[3]) {
                builder.append("۳");
                continue;
            }
            if (eng.charAt(i) == chars[4]) {
                builder.append("۴");
                continue;
            }
            if (eng.charAt(i) == chars[5]) {
                builder.append("۵");
                continue;
            }
            if (eng.charAt(i) == chars[6]) {
                builder.append("۶");
                continue;
            }
            if (eng.charAt(i) == chars[7]) {
                builder.append("۷");
                continue;
            }
            if (eng.charAt(i) == chars[8]) {
                builder.append("۸");
                continue;
            }
            if (eng.charAt(i) == chars[9]) {
                builder.append("۹");
                continue;
            }
            builder.append(eng.charAt(i));
        }
        return builder.toString();
    }

}