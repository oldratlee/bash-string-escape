package com.oldratlee.bse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerry Lee
 */
public class BseUtils {
    public static String escapeSimpleString(String origin) {
        if (origin == null) {
            throw new NullPointerException();
        }

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

    public static String[] escapeSimpleString(String[] origin) {
        if (origin == null) {
            throw new NullPointerException("input array is null!");
        }

        String[] ret = new String[origin.length];
        for (int i = 0; i < origin.length; i++) {
            String o = origin[i];
            if (o == null) {
                throw new NullPointerException("element of index " + i + " is null!");
            }
            ret[i] = escapeSimpleString(o);
        }
        return ret;
    }

    public static List<String> escapeSimpleString(List<String> origin) {
        if (origin == null) {
            throw new NullPointerException("input list is null!");
        }

        List<String> ret = new ArrayList<String>(origin.size());
        for (int i = 0; i < origin.size(); i++) {
            String o = origin.get(i);
            if (o == null) {
                throw new NullPointerException("element of index " + i + " is null!");
            }
            ret.add(escapeSimpleString(o));
        }
        return ret;
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

    public static String[] escapeVarString(String[] origin) {
        if (origin == null) {
            throw new NullPointerException("input array is null!");
        }

        String[] ret = new String[origin.length];
        for (int i = 0; i < origin.length; i++) {
            String o = origin[i];
            if (o == null) {
                throw new NullPointerException("element of index " + i + " is null!");
            }
            ret[i] = escapeVarString(o);
        }
        return ret;
    }


    public static List<String> escapeVarString(List<String> origin) {
        if (origin == null) {
            throw new NullPointerException("input list is null!");
        }

        List<String> ret = new ArrayList<String>(origin.size());
        for (int i = 0; i < origin.size(); i++) {
            String o = origin.get(i);
            if (o == null) {
                throw new NullPointerException("element of index " + i + " is null!");
            }
            ret.add(escapeVarString(o));
        }
        return ret;
    }
}
