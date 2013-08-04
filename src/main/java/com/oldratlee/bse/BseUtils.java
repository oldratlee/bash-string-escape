package com.oldratlee.bse;

/**
 * @author Jerry Lee
 */
public class BseUtils {
    public static String escapeSimpleString(String origin) {
        StringBuilder sb = new StringBuilder(origin.length());
        sb.append('\'');
        for (char c : origin.toCharArray()) {
            switch (c) {
                case '\'':
                    sb.append("'\\''");
                    break;
                default:
                    sb.append(c);
            }
        }
        sb.append('\'');
        return sb.toString();
    }

    public static String escapeVarString(String origin) {
        StringBuilder sb = new StringBuilder(origin.length());
        sb.append('"');
        for (char c : origin.toCharArray()) {
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                default:
                    sb.append(c);
            }
        }
        sb.append('"');
        return sb.toString();
    }
}
