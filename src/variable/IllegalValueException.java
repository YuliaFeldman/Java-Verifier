package oop.ex6.variable;


public class IllegalValueException extends VariableException {

    private final String msg = "type of value does not match type of variable";

    public String getMessage(){
        return msg;
    }

}
