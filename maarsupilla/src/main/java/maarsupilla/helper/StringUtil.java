package maarsupilla.helper;

/**
 * In progress.
 */
public class StringUtil {

    private StringUtil() {
    }

    public static boolean isEmpty(final String text) {
        return (text == null || text.length() == 0);
    }

    public static boolean hasCommand(final String cmd, final String text, final boolean hasArgs) {
        if (hasArgs) {
            return text.toLowerCase().startsWith(cmd.toLowerCase());
        } else  {
            return text.toLowerCase().equalsIgnoreCase(cmd);
        }
    }

    public static boolean startsWithIgnoreCase(final String text, final String prefix) {
        if (isEmpty(text)) {
            return  false;
        }
        if (isEmpty(prefix)){
            return  false;
        }
        if (prefix.length() > text.length()) {
            return false;
        }
        return text.toLowerCase().startsWith(prefix.toLowerCase());
    }
}
