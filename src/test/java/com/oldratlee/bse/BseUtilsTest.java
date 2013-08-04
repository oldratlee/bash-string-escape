package com.oldratlee.bse;

import org.junit.Test;
import static com.oldratlee.bse.BseUtils.*;
import static org.junit.Assert.*;
/**
 * 
 * @author Jerry Lee
 */
public class BseUtilsTest {
    @Test
    public void test_escapeSimpleString() throws Exception {
        String out = escapeSimpleString("foo'bar\"123");
        assertEquals("'foo'\\''bar\"123'", out);
    }

    @Test
    public void test_escapeVarString() throws Exception {
        String out = escapeVarString("foo'bar\"123");
        assertEquals("\"foo'bar\\\"123\"", out);
    }
}
