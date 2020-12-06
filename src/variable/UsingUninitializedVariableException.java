package oop.ex6.variable;


public class UsingUninitializedVariableException extends VariableException {

    private final String msg = "using an uninitialized variable";

    public String getMessage(){
        return msg;
    }

}
