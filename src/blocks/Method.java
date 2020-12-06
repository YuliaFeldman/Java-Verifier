package oop.ex6.blocks;

import oop.ex6.variable.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a method in the code
 */
public class Method extends Block{

    private static final String FINAL_TYPE = "final";
    private String code;

    private Variable.VarType[] typesOfParameters;

    /**
     * Constructor for Method Object
     * @param name the nameof the method
     * @param parameters the parameters method receives
     * @throws VariableException
     */
    public Method(String name, String[] parameters)
            throws VariableException, VarMultipleInitializationException, IllegalMethodNameException{
        super(name);
        isNameValid(name);
        if(parameters != null && parameters.length != 0)
            addParameters(parameters);

    }

    /**
     * check if name of method is valid
     * @param name name of method
     * @throws IllegalNameException if name is illegal
     */
    private void isNameValid(String name) throws IllegalMethodNameException{
        if(name == null || name.length() < 1)
            throw  new IllegalMethodNameException();

        //if name of method contains an illegal char
        Pattern p = Pattern.compile(RegexPatternForBlocks.METHOD_NAME_CHARACTERS);
        Matcher m = p.matcher(name);
        if(!m.matches())
            throw new IllegalMethodNameException();

        //if name of method starts with underscore
        if(name.charAt(0) == '_')
                throw new IllegalMethodNameException();

        //if name of method starts with a digit
        p = Pattern.compile(RegexPatternForBlocks.DIGITS_PATTERN);
        m = p.matcher(name);
        if(m.lookingAt())
            throw new IllegalMethodNameException();
    }

    /**
     * add parameters to this method
     * @param parameters an array of strings representing the parameters needed to add
     * @throws VariableException
     */
    private void addParameters(String[] parameters)
            throws VariableException, VarMultipleInitializationException{

        int numOfParameters = parameters.length;
        int numOfParametersAdded = 0;
        typesOfParameters = new Variable.VarType[numOfParameters];
        String name, type, isFinalStr;

        if(!parameters[0].matches("\\s*")){
            for(String parameter: parameters){
                String[] splited = parameter.split("\\s+");

                //if variable is defined as final
                if(splited[0].equals(FINAL_TYPE)){
                    name = splited[2];
                    type = splited[1];
                    isFinalStr = splited[0];
                }
                else {
                    name = splited[1];
                    type = splited[0];
                    isFinalStr = null;
                }

                Variable var = VarFactory.createVar(name, type, null, isFinalStr);
                addVariable(var);
                typesOfParameters[numOfParametersAdded] = var.getType();
                numOfParametersAdded += 1;
            }
        }
    }

    /**
     * check validity of parameters received in method call
     * @param params the parameters received in method call
     * @throws VariableException
     * @throws IllegalMethodCallParameters
     */
    public void verifyParams(String[] params) throws VariableException, IllegalMethodCallParameters{

        if(typesOfParameters.length != params.length)
            throw new IllegalMethodCallParameters();

        int i = 0;
        if(!(params.length == 1 && params[0].equals(""))){
            for(String parameter: params){
                if(!parameter.equals(typesOfParameters[i].toString())){
                    Variable.VarType varType = typesOfParameters[i];
                    String varTypeStr = varType.toString();
                    Matcher m = Pattern.compile(getMyPattern(varTypeStr)).matcher(parameter);
                    if(!m.matches()){
                        throw new IllegalMethodCallParameters();
                    } else
                        i++;
                }
            }
        }
    }

    /**
     * set the code of this method
     * @param code a new code
     */
    public void setCode(String code){
        this.code = code;
    }

    /**
     * get the code of this Method
     * @return code of this method
     */
    public String getCode(){
        return code;
    }

    /**
     * Method receives a String representation of variable's type and returns the suitable regex pattern
     * @param typeStr a String representation of variable's type
     * @return the suitable regex pattern
     */
    public String getMyPattern(String typeStr){

        if(typeStr.equals("int"))
            return RegexPatternForBlocks.INT_PATTERN;
        else if(typeStr.equals("double"))
            return RegexPatternForBlocks.DOUBLE_PATTERN;
        else if(typeStr.equals("char"))
            return RegexPatternForBlocks.CHAR_PATTERN;
        else if(typeStr.equals("boolean"))
            return RegexPatternForBlocks.BOOL_PATTERN;
        else if(typeStr.equals("String"))
            return RegexPatternForBlocks.STRING_PATTERN;
        else
            return "";
    }
}
