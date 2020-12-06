package oop.ex6.main;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import oop.ex6.variable.*;
import oop.ex6.blocks.*;

/**
 * This class represents the translator which receives lines representing code components and
 * translate(create) them into suitable objects (Block or Method).
 */
public class Translator {

    private static final String EQUAL_SIGN = "=";

    private static final String METHOD_TYPE = "method";

    private static final String IF_WHILE_TYPE = "if";

    /**
     * checks if a variable that is used in the code, was declared beforehand.
     * @param localBlock the block in which the variable is being used
     * @param varName the name of the variable
     * @return if variable was declared, then return the variable. Otherwise, throw exception
     * @throws CannotResolveSymbolException
     */
    private Variable isVarExist(Block localBlock, String varName) throws CannotResolveSymbolException{

        while (true){

            for(Variable variable : localBlock.getLocalVariables()){
                if(variable.getName().equals(varName))
                    return variable;
            }

            if(localBlock.getOuterBlock() == null){
                break;}
            else
                localBlock = localBlock.getOuterBlock();
        }
        throw new CannotResolveSymbolException();
    }


    /**
     * translates a declaration line and creates a suitable Variable object
     * @param localBlock the block in which the variable was declared
     * @param newLine a string representation of the declaration
     * @throws MainScopeException
     * @throws VariableException
     * @throws MethodException
     */
    public void translateDeclareLine(Block localBlock, String newLine) throws MainScopeException,
            VariableException, MethodException{

        Matcher assigningValueToVarMatcher = Pattern.compile(RegexPatternsForMain.ASSIGN_VALUE_TO_VAR_LINE).matcher(newLine);
        Matcher declareVarsMatcher = Pattern.compile(RegexPatternsForMain.DEFINE_VARS_LINE).matcher(newLine);
        Matcher callingMethodMatcher = Pattern.compile(RegexPatternsForMain.CALL_METHOD_LINE).matcher(newLine);

        if(callingMethodMatcher.matches()){
            translateCallingMethod(localBlock, newLine, callingMethodMatcher);
        }
        else if(declareVarsMatcher.matches()){
            translateVarsDeclaration(localBlock, newLine, declareVarsMatcher);

        } else if(assigningValueToVarMatcher.matches()){
            translateAssignmentOfValueToVar(localBlock, newLine, assigningValueToVarMatcher);
        }
        else{
            throw new BadLineFormatException();
        }
    }

    /**
     * translates lines representing a block and creates a suitable Block object
     * @param localBlock the block in which the new block is at
     * @param firstLineOfBlock a string representation of the the first line of the block
     * @return a new block object
     * @throws MainScopeException
     * @throws MethodException
     * @throws VariableException
     */
    public Block translateToBlock(Block localBlock, String firstLineOfBlock) throws MainScopeException,
            MethodException, VariableException {

        Matcher flowBlock = Pattern.compile(RegexPatternsForMain.IF_WHILE_FIRST_LINE).matcher(firstLineOfBlock);
        Matcher newMethod = Pattern.compile(RegexPatternsForMain.DEFINE_METHOD_LINE).matcher(firstLineOfBlock);

        if (newMethod.matches()) {
            String methodName = newMethod.group(4);
            String[] methodParams = newMethod.group(7).split(RegexPatternsForMain.COMAS_SPLITTER);
            return BlockFactory.createBlock(methodName, METHOD_TYPE, methodParams);
        } else if (flowBlock.matches()) {
            return translateFlowBlock(localBlock, firstLineOfBlock, flowBlock);
        } else {
            throw new BadLineFormatException();
        }
    }

    private Block translateFlowBlock(Block localBlock, String firstLineOfBlock, Matcher flowBlock) throws
            MainScopeException, MethodException, VariableException{

        String[] boolParams = flowBlock.group(2).split(RegexPatternsForMain.OR_AND_SPLITTER);
        Integer j = 0;

        for(String boolpar : boolParams){
            boolParams[j] = boolpar.trim();
            j++;
        }
        Integer i = 0;
        for(String par : boolParams){
            try{
                Variable existingVar = isVarExist(localBlock, par);
                Variable.VarType varType = existingVar.getType();
                if(!existingVar.isVarInitialized())
                    throw new UsingUninitializedVariableException();
                if(varType == Variable.VarType.DOUBLE || varType == Variable.VarType.INT ||
                        varType == Variable.VarType.BOOLEAN){
                    boolParams[i] = "false";
                    i++;
                }

            }
            catch (Exception ex){i++;}
        }
        return BlockFactory.createBlock("" ,IF_WHILE_TYPE, boolParams);
    }

    private void translateCallingMethod(Block localBlock, String newLine, Matcher callingMethodMatcher)
            throws MainScopeException, VariableException, MethodException{

        if(localBlock.getOuterBlock() == null)
            throw new FlowBlockNotInMethodException();

        String methodName = callingMethodMatcher.group(1);
        String[] methodParams = callingMethodMatcher.group(2).split(RegexPatternsForMain.COMAS_SPLITTER);

        Integer j = 0;
        for(String methodparam : methodParams){
            methodParams[j] = methodparam.trim();
            j++;
        }
        boolean doesCalledMethodExist = false;
        Method existingMethod = null;

        for(Method method : Sjavac.getMainBlock().getMethods()){
            if(method.getName().equals(methodName)){
                doesCalledMethodExist = true;
                existingMethod = method;
            }
        }
        if(!doesCalledMethodExist)
            throw new CannotResolveSymbolException();

        Integer i = 0;
        for(String par : methodParams){
            try{
                Variable existingVar = isVarExist(localBlock, par);
                Variable.VarType varType = existingVar.getType();
                if(!existingVar.isVarInitialized())
                    throw new UsingUninitializedVariableException();
                methodParams[i] = varType.toString();
                i++;
            }
            catch (Exception ex){i++;}
        }
        existingMethod.verifyParams(methodParams);

    }

    private void translateVarsDeclaration(Block localBlock, String newLine, Matcher declareVarsMatcher)
            throws MainScopeException, VariableException, MethodException{

        String varName;
        String varValue;
        String isFinal;
        if(declareVarsMatcher.group(2) != null && !declareVarsMatcher.group(2).equals(""))
            isFinal = declareVarsMatcher.group(2).trim();
        else
            isFinal = null;
        String typeOfVars = declareVarsMatcher.group(5).trim();
        String varListStr = declareVarsMatcher.group(6).trim();
        String[] varList = varListStr.split(RegexPatternsForMain.COMAS_SPLITTER);

        for(String varStr : varList){
            if(varStr.contains(EQUAL_SIGN)){
                String[] varDetailsLst = varStr.split(RegexPatternsForMain.EQUAL_SPLITTER);
                varName = varDetailsLst[0].trim();
                varValue = varDetailsLst[1].trim();
            }
            else{
                varName = varStr;
                varValue = "";
            }
            try{

                Variable existingVar = isVarExist(localBlock, varValue);
                Variable.VarType varType = existingVar.getType();
                if(!existingVar.isVarInitialized())
                    throw new UsingUninitializedVariableException();
                if(varType.toString().equals(typeOfVars)){
                    Variable newVar = VarFactory.createVar(varName, typeOfVars, "1", isFinal);
                    localBlock.addVariable(newVar);
                }
            }
            catch (Exception ex){
                Variable newVar = VarFactory.createVar(varName, typeOfVars, varValue, isFinal);
                localBlock.addVariable(newVar);
            }
        }
    }

    private void translateAssignmentOfValueToVar(Block localBlock, String newLine,
                                                 Matcher assigningValueToVarMatcher) throws MainScopeException,
            VariableException, MethodException{

        String varName = assigningValueToVarMatcher.group(1);
        String varNewValue = assigningValueToVarMatcher.group(2);
        Variable foundVar = isVarExist(localBlock, varName);
        foundVar.checkVarValue(varNewValue);
    }

}
