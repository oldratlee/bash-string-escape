package com.oldratlee.bse;

import org.junit.Test;

import static com.oldratlee.bse.BseUtils.escapePlainString;
import static com.oldratlee.bse.BseUtils.escapeVarString;
import static org.junit.Assert.assertEquals;

/**
 * @author Jerry Lee oldratlee(at)gmail(dot)com
 */
public class BseUtilsTest {
    private static String OS = System.getProperty("os.name").toLowerCase();

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    static String runEcho(String escapedString) throws Throwable {
        ProcessBuilder builder = new ProcessBuilder("bash", "-c", "echo -n " + escapedString);

        Process process = builder.start();
        OutputStreamCrawler crawler = new OutputStreamCrawler(process.getInputStream());

        int exitCode = process.waitFor();
        if (0 != exitCode) {
            throw new IllegalStateException("exit code is not 0, not normal termination: " + crawler.getResult());
        }

        return crawler.getResult();
    }

    @Test
    public void test_escapeSimpleString() throws Throwable {
        String input = "abc";
        assertEquals("'abc'", escapePlainString(input));

        input = "foo'bar\"123";
        assertEquals("'foo'\\''bar\"123'", escapePlainString(input));

        if (isWindows()) return;

        assertEquals(input, runEcho(escapePlainString(input)));
        
        String s = "Follow e space . Follow a double quotation marks \". Follow a single quotation marks '. End!";
        assertEquals(s, runEcho(escapePlainString(s)));
        s = "  ! $USER ${HOME} ${USER:3} \"Hello\" [    ] \t {\"k1\":\"v1\", 36, [1, \"string\"]} ";
        assertEquals(s, runEcho(escapePlainString(s)));
        s = "abc\n123";
        assertEquals(s, runEcho(escapePlainString(s)));

        s = " ! $ \r \n \t abc Java   ";
        assertEquals(s, runEcho(escapePlainString(s)));

        s = "  Java™  你好，中华人民共和国！ ";
        assertEquals(s, runEcho(escapePlainString(s)));
    }

    @Test
    public void test_escapeVarString() throws Exception {
        String out = escapeVarString("foo'bar\"123");
        assertEquals("\"foo'bar\\\"123\"", out);
    }
}
