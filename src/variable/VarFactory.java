package oop.ex6.variable;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class VarFactory {

    /**
     * This class represents the variables factory
     */
    public static final String STRING_TYPE = "String";
    public static final String INT_TYPE = "int";
    public static final String BOOLEAN_TYPE = "boolean";
    public static final String CHAR_TYPE = "char";
    public static final String DOUBLE_TYPE = "double";

    /**
     * This method is the variable factory which returns a new variable
     * @param name name of variable
     * @param type type of variable
     * @param value variable's value
     * @param isFinalStr is variable final
     * @return returns a new variable
     * @throws VariableException
     */
    public static Variable createVar(String name, String type, String value, String isFinalStr) throws
            VariableException{

        Boolean isVarFinal = null;
        if(isFinalStr != null){
            Matcher m = Pattern.compile("final\\s*").matcher(isFinalStr);
            if(m.matches())
                isVarFinal = true;
        }
        else
            isVarFinal = false;

        //if variable is final and was not initialized
        if(isVarFinal && (value == null || value.equals("\\s*")))
            throw new UninitializedFinalVariableException();

        Variable.VarType typeOfVar;
        switch (type){
            case STRING_TYPE: {
                typeOfVar = Variable.VarType.STRING;
                return new StrVariable(name, typeOfVar, value, isVarFinal);
            }
            case INT_TYPE: {
                typeOfVar = Variable.VarType.INT;
                return new IntVariable(name, typeOfVar, value, isVarFinal);
            }
            case BOOLEAN_TYPE: {
                typeOfVar = Variable.VarType.BOOLEAN;
                return new BoolVariable(name, typeOfVar, value, isVarFinal);
            }
            case CHAR_TYPE: {
                typeOfVar = Variable.VarType.CHAR;
                return new CharVariable(name, typeOfVar, value, isVarFinal);
            }
            case DOUBLE_TYPE: {
                typeOfVar = Variable.VarType.DOUBLE;
                return new DoubleVariable(name, typeOfVar, value, isVarFinal);
            }
            default: throw new IllegalTypeException();

        }

    }

}
