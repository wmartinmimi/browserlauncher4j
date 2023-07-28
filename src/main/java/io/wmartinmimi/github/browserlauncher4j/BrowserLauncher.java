package io.wmartinmimi.github.browserlauncher4j;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * A browser launcher to open an url in a default browser.
 */
public class BrowserLauncher {

    /**
     * Launches the specified url in the default browser concurrently using the specified executor.
     * @param uri containing the url to launch
     * @param executor the executor to use by CompletableFuture.supplyAsync()
     * @return CompletableFuture&lt;Boolean&gt;, where true indicates the url launch was successful
     */
    public static CompletableFuture<Boolean> launch(URI uri, Executor executor) {
        return CompletableFuture.supplyAsync(() -> launchBlocking(uri), executor);
    }

    /**
     * Launches the specified url in the default browser concurrently using default executor for CompletableFuture.
     * @param uri containing the url to launch
     * @return CompletableFuture&lt;Boolean&gt;, where true indicates the url launch was successful
     */
    public static CompletableFuture<Boolean> launch(URI uri) {
        return CompletableFuture.supplyAsync(() -> launchBlocking(uri));
    }

    /**
     * Launches the specified url in the default browser as a blocking operation.
     * @param uri containing the url to launch
     * @return boolean, where true indicates the url launch was successful
     */
    public static boolean launchBlocking(URI uri) {
        if (launchWithAWTDesktop(uri)) return true;
        return launchWithXDGOpen(uri);
    }

    /**
     * Launches the browser using java.awt.Desktop.
     * @param uri the url to be launched
     * @return boolean, where true indicates the url launch was successful
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
     * @return boolean, where true indicates the url launch was successful
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
