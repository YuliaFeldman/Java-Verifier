package oop.ex6.variable;

/**
 * This class represents an int variable
 */
public class IntVariable extends Variable {

    /**
     * Constructor for IntVariable
     * @param name name of variable
     * @param type type of variable
     * @param varValue value of variable
     * @param isFinal is variable final
     * @throws VariableException
     */
    public IntVariable(String name, VarType type, String varValue, boolean isFinal) throws VariableException{
        super(name, type, varValue, isFinal);
        if(isInitialized)
            verifyValuePattern(RegexPatternsForVariable.INT_PATTERN, varValue);
    }

    /**
     * This method checks whether or the value of the variable is valid
     * @param varValue variable value
     * @throws IllegalValueException
     * @throws ReassignmentOfFinalVariableException
     */
    public void checkVarValue(String varValue) throws IllegalValueException,
            ReassignmentOfFinalVariableException{

        if(this.isFinal)
            throw new ReassignmentOfFinalVariableException();

        verifyValuePattern(RegexPatternsForVariable.INT_PATTERN, varValue);
    }
}
