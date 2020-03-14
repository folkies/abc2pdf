package com.github.folkies.abc2pdf;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PrintApiTest {

    @Test
    public void shouldInjectCorpus() throws InterruptedException {
    	Thread.sleep(TimeUnit.MINUTES.toMillis(5));
    }

}