# browserlaunch4j

## Description

A simple library for launching a default user browser.
Useful for making web apps.

Webpage: [https://wmartinmimi.github.io/projects/browserlauncher4j/](https://wmartinmimi.github.io/projects/browserlauncher4j/)

## Example code

```java
package com.example;

import io.github.wmartinmimi.browserlauncher4j.BrowserLauncher;

import java.net.URI;
import java.net.URISyntaxException;

public class BrowserLauncherTest {
    public static void main(String[] args) throws URISyntaxException {
        BrowserLauncher.launch(new URI("https://example.com"));
    }
}
```

For more examples see [BrowserLauncherTest](src/test/java/io/wmartinmimi/github/browserlauncher4j/test/BrowserLauncherTest.java)

## License

Licensed under ```MIT License```.