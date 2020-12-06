package oop.ex6.blocks;

public class RegexPatternForBlocks {

    static final String BOOLEAN_REGEX = "(true|false|-?\\d+(\\.\\d+)?)";

    static final String METHOD_NAME_CHARACTERS = "[a-zA-Z0-9_]+";

    static final String DIGITS_PATTERN = "[0-9]";

    static final String BOOL_PATTERN = "true|false|-?[0-9]+\\.?[0-9]*";

    static final String CHAR_PATTERN = "\'[^\\\'\",]?\'";

    static final String STRING_PATTERN = "\"[^\\\'\",]*\"";

    static final String INT_PATTERN = "-?[0-9]+";

    static final String DOUBLE_PATTERN = "-?[0-9]+\\.?[0-9]*";
}
