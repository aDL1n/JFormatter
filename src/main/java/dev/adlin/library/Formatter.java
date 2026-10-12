package dev.adlin.library;

public final class Formatter {

    private static final String PLACEHOLDER = "{}";

    public static String format(final String value, final Object... placeholders) {
        if (value == null) return null;

        if (placeholders == null) return value;
        if (placeholders.length == 0) {
            if (value.contains(PLACEHOLDER)) {
                throw new IllegalArgumentException("Mismatched arguments! Value contains placeholders, but 0 arguments were provided");
            }
            return value;
        }

        final StringBuilder builder = new StringBuilder(value.length() + (placeholders.length) * 16);

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
            currentIndex = nextPlaceholderIndex + 2;
        }

        builder.append(value, currentIndex, value.length());
        return builder.toString();
    }
}
