<div align="center">

# JFormatter

**Simple `{}` string formatting for Java**

Turn a template and a few values into a readable string with one method call

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Language: Java](https://img.shields.io/badge/Language-Java-orange.svg)](src/main/java/dev/adlin/library/Formatter.java)
[![Build: Gradle](https://img.shields.io/badge/Build-Gradle-02303A.svg)](build.gradle.kts)

[Quick start](#quick-start) • [Installation](#installation) • [Behavior](#behavior) • [Contributing](#contributing)

</div>

```java
Formatter.format("Hello, {}!", "world");
// OUT -> "Hello, world!"
```

## Why JFormatter?

JFormatter is a small utility for assembling messages, labels, and other strings using literal `{}` placeholders

- **One method** - `Formatter.format(template, values...)`
- **Ordered substitution** - values fill placeholders from left to right

## Quick start

```java
import dev.adlin.library.Formatter;

String greeting = Formatter.format("Hello, {}!", "Alex");
// "Hello, Alex!"

String status = Formatter.format("Task {}: {} (attempt {})", "import", "ready", 3);
// "Task import: ready (attempt 3)"

String missing = Formatter.format("Value: {}", (Object) null);
// "Value: null"
```

Prefer a shorter call? Use a static import:

```java
import static dev.adlin.library.Formatter.format;

String message = format("Loaded {} of {} files", 8, 10);
// "Loaded 8 of 10 files"
```

## Installation

### Gradle (Kotlin DSL)

Add to `build.gradle.kts`:

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.aDL1n:JFormatter:1.0.0")
}
```

#### Maven

Add to `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.aDL1n</groupId>
        <artifactId>JFormatter</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

### Build from source

The current Gradle configuration builds a local JAR and does not configure artifact publishing. To use this checkout, build it with the included wrapper:

```bash
git clone https://github.com/aDL1n/JFormatter.git
cd JFormatter
```

On macOS or Linux:

```bash
./gradlew build
```

On Windows PowerShell:

```powershell
.\gradlew.bat build
```

The build runs the tests and produces `build/libs/JFormatter-1.0.0.jar`

## Behavior

The public entry point is:

```java
public static @Nullable String format(
    @Nullable String value,
    @Nullable Object... placeholders
)
```

Each argument replaces the next literal `{}` in the original template. Non-null objects use their string representation

| Call | Result |
| --- | --- |
| `format("Hello, {}!", "Java")` | `"Hello, Java!"` |
| `format("{} + {}", 2, 3)` | `"2 + 3"` |
| `format("{} / {}", "first")` | `"first / {}"` |
| `format("Ready")` | `"Ready"` |
| `format(null, "ignored")` | `null` |
| `format("Value: {}", (Object) null)` | `"Value: null"` |
| `format("Value: {}", (Object[]) null)` | `"Value: {}"` |
| `format("{}")` | Throws `IllegalArgumentException` |
| `format("{}", "one", "two")` | Throws `IllegalArgumentException` |
| `format("Ready", "extra")` | Throws `IllegalArgumentException` |

## Contributing

Bug reports and pull requests are welcome. When reporting a formatting issue, include the template, arguments, expected output, and actual result or exception.
For behavior changes, add or update tests and run the test suite before opening a pull request

## License

JFormatter is available under the [MIT License](LICENSE)
