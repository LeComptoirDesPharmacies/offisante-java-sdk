package fr.lecomptoirdespharmacies.offisante.core.util;

/**
 * Helpers to inspect a raw HTTP payload before it is handed over to the Json parser
 */
public class PayloadUtil {

    /**
     * Maximum number of characters kept when a payload is quoted in an error message
     */
    public static final int EXCERPT_MAX_LENGTH = 200;

    private PayloadUtil() {
    }

    /**
     *                  A payload may only be read as a Json body when its first non blank
     *                  character opens an object or an array. Anything else (an html error
     *                  page served by a gateway, an empty body, a plain text message) is
     *                  not Json and cannot be parsed.
     *
     * @param payload   Raw payload returned by the remote
     * @return          True when the payload may be parsed as Json
     */
    public static boolean isJsonPayload(String payload) {
        if (payload == null) {
            return false;
        }

        for (int i = 0; i < payload.length(); i++) {
            char character = payload.charAt(i);

            if (Character.isWhitespace(character)) {
                continue;
            }

            return character == '{' || character == '[';
        }

        return false;
    }

    /**
     *                  Single line and truncated view of a payload, safe to put in an
     *                  exception message
     *
     * @param payload   Raw payload returned by the remote
     * @return          Readable excerpt of the payload
     */
    public static String excerpt(String payload) {
        if (payload == null) {
            return "<null>";
        }

        String flattened = payload.replaceAll("\\s+", " ").trim();

        if (flattened.isEmpty()) {
            return "<empty>";
        }

        if (flattened.length() <= EXCERPT_MAX_LENGTH) {
            return flattened;
        }

        return flattened.substring(0, EXCERPT_MAX_LENGTH)
                + "... (truncated, " + payload.length() + " characters)";
    }
}
