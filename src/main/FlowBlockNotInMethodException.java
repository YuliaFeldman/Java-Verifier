package oop.ex6.main;


public class FlowBlockNotInMethodException extends MainScopeException{

    private final String ERROR_CAUSE = "if or while block are not within a method";

    public String getMessage(){
        return ERROR_CAUSE;
    }
}
