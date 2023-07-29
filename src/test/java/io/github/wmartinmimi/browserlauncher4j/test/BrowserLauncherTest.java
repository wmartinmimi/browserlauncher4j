package io.github.wmartinmimi.browserlauncher4j.test;

import io.github.wmartinmimi.browserlauncher4j.BrowserLauncher;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowserLauncherTest {

    @Test
    public void recommendedUsage() throws URISyntaxException {
        // BrowserLaunch.launch(uri) runs concurrently,
        // program may end before execution finishes.
        BrowserLauncher.launch(new URI("https://example.com"));
    }

    @Test
    public void runWithCustomExecutor() throws URISyntaxException, ExecutionException, InterruptedException {
        // allows for custom executor for future.
        CompletableFuture<Boolean> launchSuccessFuture = BrowserLauncher.launch(new URI("https://example.com"), Executors.newSingleThreadExecutor());
        assertTrue(launchSuccessFuture.get());
    }

    @Test
    public void asyncTest() throws URISyntaxException, ExecutionException, InterruptedException {
        // completable future can allow for callbacks
        CompletableFuture<Boolean> launchSuccessFuture = BrowserLauncher.launch(new URI("https://example.com"));
        assertTrue(launchSuccessFuture.get());
    }

    @Test
    public void blockingTest() throws URISyntaxException{
        // BrowserLauncher.launchBlocking(uri) blocks execution,
        // ensuring that launchBlocking() either launches a browser or failed before continuing.
        boolean launchSuccess = BrowserLauncher.launchBlocking(new URI("https://example.com"));
        assertTrue(launchSuccess);
    }
}
