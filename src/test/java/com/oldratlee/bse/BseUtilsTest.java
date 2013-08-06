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
        return OS.contains("win");
    }

    static String runEcho(String escapedString) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("bash", "-c", "echo -n " + escapedString);

        Process process = builder.start();
        OutputStreamCrawler stdOutCrawler = new OutputStreamCrawler(process.getInputStream());
        OutputStreamCrawler stdErrCrawler = new OutputStreamCrawler(process.getErrorStream());

        int exitCode = process.waitFor();
        if (0 != exitCode) {
            throw new IllegalStateException("exit code is not 0, not normal termination."
                    + " stdout: " + stdOutCrawler.getResult()
                    + ", stderr: " + stdErrCrawler.getResult());
        }

        return stdOutCrawler.getResult();
    }

    @Test
    public void test_escapeSimpleString() throws Throwable {
        final String input1 = "abc";
        assertEquals("'abc'", escapePlainString(input1));

        final String input2 = "foo'bar\"123";
        assertEquals("'foo'\\''bar\"123'", escapePlainString(input2));

        if (isWindows()) return;

        assertEquals(input1, runEcho(escapePlainString(input1)));
        assertEquals(input2, runEcho(escapePlainString(input2)));

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
        final String input1 = "abc";
        assertEquals("\"abc\"", escapeVarString(input1));

        final String input2 = "foo'bar\"123";
        assertEquals("\"foo'bar\\\"123\"", escapeVarString(input2));

        if (isWindows()) return;

        assertEquals(input1, escapeVarString(input1));
        assertEquals(input2, escapeVarString(input2));

        final String username = System.getProperty("user.name");

        assertEquals("Hello, " + username, escapeVarString("Hello, $USER"));
    }
}
