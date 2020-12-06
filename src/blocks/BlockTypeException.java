package oop.ex6.blocks;

/**
 * In case block type is invalid
 */
public class BlockTypeException extends MethodException{
    private final String msg = "Block type is invalid";

    public String getMessage(){
        return msg;
    }
}
