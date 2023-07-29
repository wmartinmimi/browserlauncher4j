package io.github.wmartinmimi.browserlauncher4j;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * A browser launcher to open an url in a default browser.
 * @see BrowserLauncher#launch(URI)
 * @see BrowserLauncher#launch(URI, Executor)
 * @see BrowserLauncher#launchBlocking(URI)
 * @since 1.0.0
 * @version 1.0.1
 */
public class BrowserLauncher {

    /**
     * Launches the specified url in the default browser concurrently using the specified {@link Executor}.
     * <pre>{@code
     * Executor executor = Executors.newSingleThreadExecutor();
     * CompletableFuture<Boolean> launchSuccessFuture = BrowserLauncher.launch(new URI("https://example.com"), executor);
     * }</pre>
     * @param uri containing the url to launch
     * @param executor the executor to use by CompletableFuture.supplyAsync()
     * @return {@code CompletableFuture<Boolean>}, where {@code true} indicates the url launch was successful
     * @see BrowserLauncher#launch(URI)
     * @see BrowserLauncher#launchBlocking(URI)
     * @see CompletableFuture
     * @see Executor
     * @since 1.0.0
     */
    public static CompletableFuture<Boolean> launch(URI uri, Executor executor) {
        return CompletableFuture.supplyAsync(() -> launchBlocking(uri), executor);
    }

    /**
     * Launches the specified url in the default browser concurrently using default executor for {@link CompletableFuture}.
     * <pre>{@code
     * // example code
     * CompletableFuture<Boolean> launchSuccessFuture = BrowserLauncher.launch(new URI("https://example.com"));
     * }</pre>
     * @param uri containing the url to launch
     * @return {@code CompletableFuture<Boolean>}, where {@code true} indicates the url launch was successful
     * @see BrowserLauncher#launch(URI, Executor)
     * @see BrowserLauncher#launchBlocking(URI)
     * @see CompletableFuture
     * @since 1.0.0
     */
    public static CompletableFuture<Boolean> launch(URI uri) {
        return CompletableFuture.supplyAsync(() -> launchBlocking(uri));
    }

    /**
     * Launches the specified url in the default browser as a blocking operation.
     * <pre>{@code
     * // example code
     * boolean launchSuccess = BrowserLauncher.launchBlocking(new URI("https://example.com"));
     * }</pre>
     * @param uri containing the url to launch
     * @return {@code boolean}, where {@code true} indicates the url launch was successful
     * @see BrowserLauncher#launch(URI)
     * @see BrowserLauncher#launch(URI, Executor)
     * @since 1.0.0
     */
    public static boolean launchBlocking(URI uri) {
        if (launchWithAWTDesktop(uri)) return true;
        return launchWithXDGOpen(uri);
    }

    /**
     * Launches the browser using {@link Desktop}.
     * @param uri the url to be launched
     * @return {@code boolean}, where {@code true} indicates the url launch was successful
     * @see Desktop#browse(URI)
     * @since 1.0.0
     */
    private static boolean launchWithAWTDesktop(URI uri) {
        try {
            Desktop.getDesktop().browse(uri);
            return true;
        } catch (IOException | UnsupportedOperationException e) {
            return false;
        }
    }

    /**
     * Launches the browser using xdg-open.
     * @param uri the url to be launched
     * @return {@code boolean}, where {@code true} indicates the url launch was successful
     * @since 1.0.0
     */
    private static boolean launchWithXDGOpen(URI uri) {
        try {
            Process process = new ProcessBuilder("xdg-open", uri.toString()).start();
            process.waitFor();
            return process.exitValue() == 0;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }
}
