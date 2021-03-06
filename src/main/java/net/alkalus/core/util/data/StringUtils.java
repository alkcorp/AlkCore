package net.alkalus.core.util.data;

import org.apache.commons.lang3.StringEscapeUtils;

public class StringUtils {

    public static String linebreak = linebreak();

    public static String linebreak() {
        return System.getProperty("line.separator");
    }

    public static String superscript(String str) {
        str = str.replaceAll("0", "\u2070");
        str = str.replaceAll("1", "\u00B9");
        str = str.replaceAll("2", "\u00B2");
        str = str.replaceAll("3", "\u00B3");
        str = str.replaceAll("4", "\u2074");
        str = str.replaceAll("5", "\u2075");
        str = str.replaceAll("6", "\u2076");
        str = str.replaceAll("7", "\u2077");
        str = str.replaceAll("8", "\u2078");
        str = str.replaceAll("9", "\u2079");
        return str;
    }

    public static String subscript(String str) {
        str = str.replaceAll("0", "\u2080");
        str = str.replaceAll("1", "\u2081");
        str = str.replaceAll("2", "\u2082");
        str = str.replaceAll("3", "\u2083");
        str = str.replaceAll("4", "\u2084");
        str = str.replaceAll("5", "\u2085");
        str = str.replaceAll("6", "\u2086");
        str = str.replaceAll("7", "\u2087");
        str = str.replaceAll("8", "\u2088");
        str = str.replaceAll("9", "\u2089");
        return str;
    }

    public static boolean containsSuperOrSubScript(final String s) {
        if (s.contains(StringUtils.superscript("0"))) {
            return true;
        } else if (s.contains(StringUtils.superscript("1"))) {
            return true;
        } else if (s.contains(StringUtils.superscript("2"))) {
            return true;
        } else if (s.contains(StringUtils.superscript("3"))) {
            return true;
        } else if (s.contains(StringUtils.superscript("4"))) {
            return true;
        } else if (s.contains(StringUtils.superscript("5"))) {
            return true;
        } else if (s.contains(StringUtils.superscript("6"))) {
            return true;
        } else if (s.contains(StringUtils.superscript("7"))) {
            return true;
        } else if (s.contains(StringUtils.superscript("8"))) {
            return true;
        } else if (s.contains(StringUtils.superscript("9"))) {
            return true;
        }
        if (s.contains(StringUtils.subscript("0"))) {
            return true;
        } else if (s.contains(StringUtils.subscript("1"))) {
            return true;
        } else if (s.contains(StringUtils.subscript("2"))) {
            return true;
        } else if (s.contains(StringUtils.subscript("3"))) {
            return true;
        } else if (s.contains(StringUtils.subscript("4"))) {
            return true;
        } else if (s.contains(StringUtils.subscript("5"))) {
            return true;
        } else if (s.contains(StringUtils.subscript("6"))) {
            return true;
        } else if (s.contains(StringUtils.subscript("7"))) {
            return true;
        } else if (s.contains(StringUtils.subscript("8"))) {
            return true;
        } else if (s.contains(StringUtils.subscript("9"))) {
            return true;
        }
        return false;
    }

    // Can call this Enum for formatting.
    public static enum TextUtils {
        blue('1'), green('2'), teal('3'), maroon('4'), purple('5'), orange('6'),
        lightGray('7'), darkGray('8'), lightBlue('9'), black('0'), lime('a'),
        aqua('b'), red('c'), pink('d'), yellow('e'), white('f');

        private final char colourValue;

        private TextUtils(final char value) {
            this.colourValue = value;
        }

        public String colour() {
            return getFormatter() + this.colourValue;
        }

        private String getFormatter() {
            return "\u00A7"; // Returns §.
        }
    }

    public static String firstLetterCaps(final String data) {
        final String firstLetter = data.substring(0, 1).toUpperCase();
        final String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }

    public static String escape(final char aChar) {
        return escape("" + aChar);
    }

    public static String escape(final String string) {
        return StringEscapeUtils.escapeJava(string);
    }

    public static <V> String getDataStringFromArray(final V[] parameterTypes) {
        if (parameterTypes == null || parameterTypes.length == 0) {
            return "empty/null";
        } else {
            String aData = "";
            for (final V y : parameterTypes) {
                if (y != null) {
                    aData += ", " + y.toString();
                }
            }
            return aData;
        }
    }

    public static String sanitizeString(final String input) {
        String temp = input.replace(" ", "");
        temp = temp.replace("(", "");
        temp = temp.replace(")", "");
        temp = temp.replace("{", "");
        temp = temp.replace("}", "");
        temp = temp.replace("[", "");
        temp = temp.replace("]", "");
        return sanitizeStringKeepBrackets(temp);
    
    }

    public static String sanitizeStringKeepBrackets(final String input) {
        String temp;
        String output;
    
        temp = input.replace(" ", "");
        temp = temp.replace("-", "");
        temp = temp.replace("_", "");
        temp = temp.replace("?", "");
        temp = temp.replace("!", "");
        temp = temp.replace("@", "");
        temp = temp.replace("#", "");
        temp = temp.replace(" ", "");
        output = temp;
        return output;
    
    }
}
