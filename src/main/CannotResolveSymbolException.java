package oop.ex6.main;


public class CannotResolveSymbolException extends MainScopeException{

    private final String ERROR_CAUSE = "cannot resolve name of method or variable";

    public String getMessage(){
        return ERROR_CAUSE;
    }
}

