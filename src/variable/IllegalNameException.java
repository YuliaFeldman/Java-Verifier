package oop.ex6.variable;


public class IllegalNameException extends VariableException {

    private final String msg = "name of variable is illegal";

    public String getMessage(){
        return msg;
    }

}
