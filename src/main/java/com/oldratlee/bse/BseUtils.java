package com.oldratlee.bse;

import java.util.ArrayList;
import java.util.List;

/**
 * Bash string escape utils.
 *
 * @author Jerry Lee oldratlee(at)gmail(dot)com
 */
public class BseUtils {
    /**
     * Escape the plain string. The result string can be place in the bash command line,
     * and input value to command is the exact origin string.<p>
     * use single quote mark to surround the origin string, and escape the meta character
     * in origin string.<p>
     * examples:
     * <ul>
     * <li><code>abc</code> => <code>'abc'</code>
     * <li><code>abc"xyz'123</code> => <code>'abc"xyz'\''123'</code>
     * </ul>
     *
     * @param origin input string
     * @return the result string after escape
     */
    public static String escapePlainString(String origin) {
        if (origin == null) {
            throw new NullPointerException();
        }

        StringBuilder sb = new StringBuilder(origin.length());
        sb.append('\'');
        for (char c : origin.toCharArray()) {
            switch (c) {
                case '\'':
                    sb.append("'\\''"); // insert string '\'' (4 char) instead of origin '
                    break;
                default:
                    sb.append(c);
            }
        }
        sb.append('\'');
        return sb.toString();
    }

    /**
     * Convenient method for {@link #escapePlainString(String)}.
     * Escape multiply string at one time.
     */
    public static String[] escapePlainString(String[] origin) {
        if (origin == null) {
            throw new NullPointerException("input array is null!");
        }

        String[] ret = new String[origin.length];
        for (int i = 0; i < origin.length; i++) {
            String o = origin[i];
            if (o == null) {
                throw new NullPointerException("element of index " + i + " is null!");
            }
            ret[i] = escapePlainString(o);
        }
        return ret;
    }

    /**
     * convenient method for {@link #escapePlainString(String)}.
     * Escape multiply string at one time.
     */
    public static List<String> escapePlainString(List<String> origin) {
        if (origin == null) {
            throw new NullPointerException("input list is null!");
        }

        List<String> ret = new ArrayList<String>(origin.size());
        for (int i = 0; i < origin.size(); i++) {
            String o = origin.get(i);
            if (o == null) {
                throw new NullPointerException("element of index " + i + " is null!");
            }
            ret.add(escapePlainString(o));
        }
        return ret;
    }

    /**
     * Ecapse String contains var usage, like <code>Hello, $username</code>.
     *
     * @param origin input string
     * @return the result string after escape
     */
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

    /**
     * convenient method for {@link #escapeVarString(String)}.
     * Escape multiply string at one time.
     */
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

    /**
     * convenient method for {@link #escapeVarString(String)}.
     * Escape multiply string at one time.
     */
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

    private BseUtils() {
    }
}
