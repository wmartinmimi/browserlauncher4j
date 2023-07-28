package io.wmartinmimi.github.browserlauncher4j;

import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowserLauncherTest {

    @Test
    public void asyncTest() throws URISyntaxException, ExecutionException, InterruptedException {
        CompletableFuture<Boolean> future = BrowserLauncher.launch(new URI("https://example.com"));
        assertTrue(future.get());
    }

    @Test
    public void blockingTest() throws URISyntaxException{
        assertTrue(BrowserLauncher.launchBlocking(new URI("https://example.com")));
    }
}
