package oop.ex6.blocks;

import oop.ex6.main.CodeComponent;
import oop.ex6.variable.Variable;
import java.util.LinkedList;
import java.util.Objects;

/**
 * This class represent a general block in the code
 */
public abstract class Block extends CodeComponent{

    private Block outerBlock;
    private String name;
    private LinkedList<Variable> localVariables;

    /**
     * Constructor for Block object
     * @param name name of the block
     */
    public Block(String name){
        super(name);
        outerBlock = null;
        localVariables = new LinkedList<Variable>();
    }

    /**
     * get the outer block of yhis block (the block that contains this block)
     * @return the outer block
     */
    public Block getOuterBlock(){
        return outerBlock;
    }

    /**
     * set the outer block
     * @param outerBlock the new outer block
     */
    public void setOuterBlock(Block outerBlock){
        this.outerBlock = outerBlock;
    }

    /**
     * get local variables of this block
     * @return a list of local variables of this block
     */
    public LinkedList<Variable> getLocalVariables(){
        return localVariables;
    }

    /**
     * add local variables to this block
     * @param variable the variables to add
     * @throws VarMultipleInitializationException if a variable already exists
     */
    public void addVariable(Variable variable) throws VarMultipleInitializationException{
        for(Variable localVariable: localVariables){
            //if the variable is already in the block
            if(Objects.equals(localVariable.getName(), variable.getName()))
                throw new VarMultipleInitializationException();
        }
        localVariables.add(variable);
    }
}