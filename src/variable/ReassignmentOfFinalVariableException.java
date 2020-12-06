package oop.ex6.variable;


public class ReassignmentOfFinalVariableException extends VariableException {

    private final String msg = "reassignment of final variable";

    public String getMessage(){
        return msg;
    }

}
