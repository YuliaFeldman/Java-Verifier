package oop.ex6.blocks;
import oop.ex6.main.MainScopeException;
import oop.ex6.variable.VariableException;

/**
 * A utilities class with a Block objects factory, which creates a Block object
 * according to the values received
 */
public class BlockFactory {
    private static final String METHOD_TYPE = "method";
    private static final String If_TYPE = "if";
    private static final String WHILE_TYPE = "while";
    /**
     * A utility method which creates a Block object (Method of FlowBlock) according to the values received
     * @param name the name of the object
     * @param type the type of the object
     * @param parameters the parameters of the object
     * @return a new Method of FlowBlock object
     * @throws MainScopeException
     * @throws VariableException
     * @throws MethodException
     */
    public static Block createBlock(String name, String type, String[] parameters)
            throws MainScopeException, VariableException, MethodException {
        if(type.equals(METHOD_TYPE))
            return new Method(name, parameters);
        if(type.equals(If_TYPE) || type.equals(WHILE_TYPE))
            return new FlowBlock(name, parameters);
        //if block type is illegal
        throw new BlockTypeException();
    }
}
