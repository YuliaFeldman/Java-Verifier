package oop.ex6.blocks;

/**
 * In case flow block receives not a boolean parameter
 */
public class ParametersInFlowBlockAreNotBooleans extends MethodException{
    private final String msg = "Parameters in flow block are invalid";

    public String getMessage(){
        return msg;
    }
}
