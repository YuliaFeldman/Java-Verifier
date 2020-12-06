package oop.ex6.blocks;

/**
 * In case that a variable is over used
 */
public class VarMultipleInitializationException extends MethodException {
    private final String msg = "Variable is over used";

    public String getMessage(){
        return msg;
    }
}
