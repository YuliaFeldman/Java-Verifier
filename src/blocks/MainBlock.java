package oop.ex6.blocks;

import java.util.LinkedList;

/**
 *This class represents the most outer block of the code
 */
public class MainBlock extends Block{

    private LinkedList<Method> methods;

    /**
     * Constructor for the main block object
     * @param name the name of the block
     */
    public MainBlock(String name){
        super(name);
        methods = new LinkedList<Method>();
    }

    /**
     * get all the methods defined in the main block
     * @return a list of all methods defined in the main block
     */
    public LinkedList<Method> getMethods(){
        return methods;
    }

    /**
     * add a method to the main block object
     * @param method a new method to add
     */
    public void addMethod(Method method){
        methods.add(method);
    }
}
