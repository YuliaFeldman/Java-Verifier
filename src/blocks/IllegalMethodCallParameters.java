package oop.ex6.blocks;

/**
 * In case a method receives an illegal parameter in method call
 */
public class IllegalMethodCallParameters extends MethodException{
    private final String msg = "Illegal parameter in method call";

    public String getMessage(){
        return msg;
    }
}
