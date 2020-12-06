package oop.ex6.blocks;

/**
 * In case the name of a method is invalid
 */
public class IllegalMethodNameException extends MethodException{
    private final String msg = "Name of method is invalid";

    public String getMessage(){
        return msg;
    }
}
