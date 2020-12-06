package oop.ex6.variable;


public class UninitializedFinalVariableException extends VariableException {

    private final String msg = "uninitialized final variable";

    public String getMessage(){
        return msg;
    }

}
