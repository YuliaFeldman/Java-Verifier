package oop.ex6.variable;;

import oop.ex6.main.CodeComponent;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This class is an abstract class represents a generic variable
 */
public abstract class Variable extends CodeComponent{

    /**
     * This is an enum class represents all types of variables
     */
    public enum VarType {CHAR {
        public String toString(){
            return VarFactory.CHAR_TYPE;
        }
    }, STRING{
        public String toString(){
            return VarFactory.STRING_TYPE;
        }
    }, BOOLEAN{
        public String toString(){
            return VarFactory.BOOLEAN_TYPE;
        }
    }, DOUBLE{
        public String toString(){
            return VarFactory.DOUBLE_TYPE;
        }
    }, INT{
        public String toString(){
            return VarFactory.INT_TYPE;
        }
    }
}

    public final VarType type;
    public final boolean isFinal;
    public boolean isInitialized = false;


    /**
     * Constructor for Variable
     * @param name name of variable
     * @param type type of variable
     * @param varValue value of variable
     * @param isFinal is variable final or not
     * @throws VariableException
     */
    public Variable(String name, VarType type, String varValue, boolean isFinal) throws VariableException{

        super(name);
        this.type = type;
        this.isFinal = isFinal;
        if(varValue == null || !varValue.equals(""))
            this.isInitialized = true;
        if((!isInitialized) && isFinal)
            throw new UninitializedFinalVariableException();
        verifyVarName(name);

    }

    /**
     * This method checks whether or not the name of variable is a valid name
     * @param varName the given name
     * @throws IllegalNameException
     */
    private static void verifyVarName(String varName) throws IllegalNameException{

        if(varName.length() < 1)
            throw new IllegalNameException();
        Pattern pattern = Pattern.compile(RegexPatternsForVariable.VALID_VAR_NAME_PATTERN);
        Matcher matcher = pattern.matcher(varName);
        if(!(matcher.matches()))
            throw new IllegalNameException();

    }

    /**
     * this method returns whether or not the variable is initialized with a value
     * @return true if variable is initialized, false otherwise
     */
    public boolean isVarInitialized(){return isInitialized;}

    /**
     * This method returns type of variable
     * @return variable's type
     */
    public VarType getType(){return type;}

    /**
     * This method checks validity of variable's value according to a given pattern
     * @param pattern the suitable regex pattern
     * @param varInput a string representing content of value
     * @throws IllegalValueException
     */
    public static void verifyValuePattern(String pattern, String varInput) throws IllegalValueException{

        if(varInput == null)
            return;
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(varInput);
        if(!(matcher.matches()))
            throw new IllegalValueException();
    }

    /**
     * This abstract method checks validity of all type of variables
     * @param varValue the given string value
     * @throws IllegalValueException
     * @throws ReassignmentOfFinalVariableException
     */
    public abstract void checkVarValue(String varValue) throws IllegalValueException,
            ReassignmentOfFinalVariableException;

}
