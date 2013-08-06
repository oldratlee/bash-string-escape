package com.oldratlee.bse;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author Jerry Lee oldratlee(at)gmail(dot)com
 */
class OutputStreamCrawler {
    String result = null;
    Throwable throwable = null;
    CountDownLatch latch = new CountDownLatch(1);

    public OutputStreamCrawler(final InputStream os) {
        Thread crawler = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = IOUtils.toString(os);
                } catch (Throwable t) {
                    throwable = t;
                } finally {
                    latch.countDown();
                }
            }
        });
        crawler.setDaemon(true);
        crawler.start();
    }

    public String getResult() throws Exception {
        latch.await();
        if (throwable != null) {
            throw new ExecutionException(throwable);
        }
        return result;
    }
}
