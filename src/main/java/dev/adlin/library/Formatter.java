package dev.adlin.library;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public final class Formatter {

    private static final String PLACEHOLDER = "{}";

    public static @Nullable String format(
            final @Nullable String value,
            final @Nullable Object... placeholders
    ) {
        if (value == null) return null;

        if (placeholders == null) return value;
        if (placeholders.length == 0) {
            return handleEmptyPlaceholders(value);
        }

        return buildFormattedString(value, placeholders);
    }

    private static @NonNull String handleEmptyPlaceholders(final @NonNull String value) {
        if (value.contains(PLACEHOLDER)) {
            throw new IllegalArgumentException("Mismatched arguments! Value contains placeholders, but 0 arguments were provided");
        }

        return value;
    }

    private static @NonNull String buildFormattedString(
            final @NonNull String value,
            final @Nullable Object... placeholders
    ) {
        final StringBuilder builder = new StringBuilder(value.length() + (placeholders.length * 16));

        int currentIndex = 0;
        int placeholderIndex = 0;

        while (placeholderIndex < placeholders.length) {
            final int nextPlaceholderIndex = value.indexOf(PLACEHOLDER, currentIndex);

            if (nextPlaceholderIndex == -1) {
                throw new IllegalArgumentException("Too many arguments provided! Expected " + placeholderIndex + ", but got " + placeholders.length);
            }

            builder.append(value, currentIndex, nextPlaceholderIndex);

            final Object placeholder = placeholders[placeholderIndex];
            if (placeholder != null) {
                builder.append(placeholder);
            } else {
                builder.append("null");
            }

            placeholderIndex++;
            currentIndex = nextPlaceholderIndex + PLACEHOLDER.length();
        }

        builder.append(value, currentIndex, value.length());
        return builder.toString();
    }
}
