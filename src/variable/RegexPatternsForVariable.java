package oop.ex6.variable;

public class RegexPatternsForVariable {

    static final String VALID_VAR_NAME_PATTERN = "_[\\w]\\w*|[a-zA-Z]\\w*";

    static final String BOOL_PATTERN = "true|false|-?[0-9]+\\.?[0-9]*";

    static final String CHAR_PATTERN = "\'[^\\\'\",]?\'";

    static final String STRING_PATTERN = "\"[^\\\'\",]*\"";

    static final String INT_PATTERN = "-?[0-9]+";

    static final String DOUBLE_PATTERN = "-?[0-9]+\\.?[0-9]*";
}
