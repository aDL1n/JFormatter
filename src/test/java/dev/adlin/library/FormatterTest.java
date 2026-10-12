package dev.adlin.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormatterTest {

    @Test
    void oneArgument() {
        final String result = Formatter.format("Test string with format {}", "THIS IS FORMAT");
        Assertions.assertEquals("Test string with format THIS IS FORMAT", result);
    }

    @Test
    void manyArguments() {
        final String result = Formatter.format(
                "Many placeholders {}, {}, {}, {}, {}",
                "1",
                "2",
                "3",
                "4",
                "5"
        );

        Assertions.assertEquals("Many placeholders 1, 2, 3, 4, 5", result);
    }

    @Test
    void overPlaceholders() {
        final String result = Formatter.format("{} {} {}", 123, 123);
        Assertions.assertEquals("123 123 {}", result);
    }

    @Test
    void unusedPlaceholders() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Formatter.format("{}")
        );
    }

    @Test
    void tooManyArguments() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Formatter.format("Only one placeholder {}", "First", "Second/Unused")
        );
    }

    @Test
    void noPlaceholdersButArgsProvided() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Formatter.format("No placeholders here", "UnusedArg")
        );
    }

    @Test
    void noPlaceholdersNoArgs() {
        final String result = Formatter.format("Just a regular string");
        Assertions.assertEquals("Just a regular string", result);
    }


}
