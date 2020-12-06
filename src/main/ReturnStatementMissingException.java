package oop.ex6.main;


public class ReturnStatementMissingException extends MainScopeException{

    private final String ERROR_CAUSE = "missing return command in method";

    public String getMessage(){
        return ERROR_CAUSE;
    }
}

