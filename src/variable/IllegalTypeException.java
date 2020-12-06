package oop.ex6.variable;


public class IllegalTypeException extends VariableException {

    private final String msg = "type of variable is illegal";

    public String getMessage(){
        return msg;
    }

}
