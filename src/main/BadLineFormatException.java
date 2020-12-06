package oop.ex6.main;


public class BadLineFormatException extends MainScopeException{

    private final String ERROR_CAUSE = "one or more lines are in bad format";

    public String getMessage(){
        return ERROR_CAUSE;
    }
}
