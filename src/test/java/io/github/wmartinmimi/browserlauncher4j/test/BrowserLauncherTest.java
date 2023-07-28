package io.github.wmartinmimi.browserlauncher4j.test;

import io.github.wmartinmimi.browserlauncher4j.BrowserLauncher;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowserLauncherTest {

    @Test
    public void recommendedUsage() throws URISyntaxException {
        BrowserLauncher.launch(new URI("https://example.com"));
    }

    @Test
    public void asyncTest() throws URISyntaxException, ExecutionException, InterruptedException {
        CompletableFuture<Boolean> launchSuccessFuture = BrowserLauncher.launch(new URI("https://example.com"));
        assertTrue(launchSuccessFuture.get());
    }

    @Test
    public void blockingTest() throws URISyntaxException{
        boolean launchSuccess = BrowserLauncher.launchBlocking(new URI("https://example.com"));
        assertTrue(launchSuccess);
    }
}
