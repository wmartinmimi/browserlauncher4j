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
        BrowserLauncher.launchBlocking(new URI("https://example.com"));
    }
}
```

For more examples see [BrowserLauncherTest](src/test/java/io/wmartinmimi/github/browserlauncher4j/test/BrowserLauncherTest.java)

## Add as dependency

### Maven pom.xml

```xml
<dependency>
    <groupId>io.github.wmartinmimi</groupId>
    <artifactId>browserlauncher4j</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation group: 'io.github.wmartinmimi', name: 'browserlauncher4j', version: '1.0.0'
```

### Gradle (short)

```groovy
implementation 'io.github.wmartinmimi:browserlauncher4j:1.0.0'
```

### Gradle (kotlin)

```kotlin
implementation("io.github.wmartinmimi:browserlauncher4j:1.0.0")
```

For other build systems see [here](https://central.sonatype.com/artifact/io.github.wmartinmimi/browserlauncher4j/1.0.0).

## Running and testing

### For library testing

```shell
mvn test
```

### For installing to local maven repository

```shell
mvn clean install
```

## License

Licensed under ```MIT License```.