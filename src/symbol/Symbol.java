package symbol;

import java.util.ArrayList;

public final class Symbol {

    public final static String PLUS           = "+";
    public final static String MINUS          = "\u2212";
    public final static String MULTIPLICATION = "\u00d7";
    public final static String DIVISION       = "\u00f7";

    public final static String EQUAL = "=";

    public final static String PLUS_MINUS = "+/\u2212";

    public final static String PERCENT     = "%";
    public final static String INVERSE     = "/";
    public final static String SQUARE      = "\u00b2";
    public final static String SQUARE_ROOT = "\u221a";

    public final static String BACKSPACE = "\u232b";

    public final static ArrayList<String> UNARY_OPERATOR = new ArrayList<String>() {
        private static final long serialVersionUID = -1736001328545737977L;

        {
            add(PERCENT);
            add(INVERSE);
            add(SQUARE);
            add(SQUARE_ROOT);
        }
    };

    public final static ArrayList<String> BINARY_OPERATOR = new ArrayList<String>() {
        private static final long serialVersionUID = -3584233266398714959L;

        {
            add(PLUS);
            add(MINUS);
            add(MULTIPLICATION);
            add(DIVISION);
        }
    };

}
