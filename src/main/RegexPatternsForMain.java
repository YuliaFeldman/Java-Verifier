package oop.ex6.main;

/**
 *
 */
public class RegexPatternsForMain {
    static final String SPACES = "\\s*";
    static final String RETURN_STATEMENT = "\\s*return;\\s*";

    static final String COMAS_SPLITTER = "\\s*,\\s*";

    static final String EQUAL_SPLITTER = "\\s*=\\s*";

    static final String OR_AND_SPLITTER = "(&&|\\|\\|)";

    static final String DEFINE_VARS_LINE = "((final\\s)?(\\s+)?((int|String|double|char|boolean)\\s+" +
            "((((([a-zA-Z]|_\\w+))((\\s*)?=(\\s*)?\"?'?\\w*\\s*?-?\\d*?.?\\d*?.*?\"?'?)(\\s+)?)|([a-zA-Z]|_\\w+)" +
            "(\\,)*)+)));\\s?";

    static final String DEFINE_METHOD_LINE = "(\\s+)?(void(\\s+))(([a-zA-Z])\\w*)" +
            "(\\s+)?\\(\\s*(((final)?(\\s+)?(int|String|double|boolean|" +
            "char)(\\s+)(([a-zA-Z]|_)\\w*)(\\s+)" +
            "?(,?)(\\s+)?)*)\\)(\\s+)\\{(\\s+)?";


    static final String IF_WHILE_FIRST_LINE = "\\s*(if|while)\\s*\\((\\s*(\\S+|true|false)\\s*" +
            "(\\s*(&&|\\|\\|)(\\s*(true|false|\\S+))\\s*)*)\\)\\s*\\{\\s*";

    static final String CALL_METHOD_LINE = "\\s*(\\w+)\\s*\\(\\s*" +
            "((\\S+\\s*)?(\\s*,\\s*\\S+\\s*)*)\\s*\\)\\s*;\\s*";

    static final String ASSIGN_VALUE_TO_VAR_LINE = "\\s*(\\S+)\\s*=\\s*(\\S+)\\s*;\\s*";

}
