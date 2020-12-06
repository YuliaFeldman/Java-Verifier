package oop.ex6.blocks;

import oop.ex6.main.MainScopeException;
import oop.ex6.variable.*;
import java.util.LinkedList;

/**
 * This class represents a flow block (if or while block)
 */
public class FlowBlock extends Block {

    private static final String INT_TYPE = "int";
    private static final String DOUBLE_TYPE = "double";
    private static final String BOOLEA_TYPE = "boolean";

    /**
     * Constructor for FlowBlock object
     * @param name the name of the object
     * @param parameters an array of parameters of the block
     * @throws MethodException if the name is illegal
     * @throws VariableException if parameters are illegal
     */
    public FlowBlock(String name, String[] parameters)
            throws MainScopeException, MethodException, VariableException{

        super(name);
        addParameters(parameters);

    }

    /**
     * checking if parameters are valid and adding them
     * @param parameters the parameters which flow block receives
     * @throws MainScopeException
     * @throws IllegalMethodCallParameters
     * @throws VariableException
     */
    private void addParameters(String[] parameters)
            throws MainScopeException, ParametersInFlowBlockAreNotBooleans, VariableException{

        for(String parameter: parameters){
            //if parameter is a variable, then check if it is initialized
            if(!parameter.trim().matches(RegexPatternForBlocks.BOOLEAN_REGEX)){
                Block currentBlock = this;
                Variable.VarType type = null;
                while(currentBlock != null){
                    LinkedList<Variable> localVariables = currentBlock.getLocalVariables();
                    for (Variable var: localVariables){
                        if(parameter.equals(var.getName())){
                            type = var.getType();
                            if(!var.isVarInitialized())
                                throw new UsingUninitializedVariableException();
                        }
                    }
                    currentBlock = this.getOuterBlock();
                }

                //if type of variable is missing or is invalid
                if((type == null) || !(type.toString().equals(INT_TYPE)
                        || (type.toString().equals(DOUBLE_TYPE)
                        || (type.toString().equals(BOOLEA_TYPE))))){
                    throw new ParametersInFlowBlockAreNotBooleans();
                }
            }
        }

    }
}
