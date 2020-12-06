package oop.ex6.main;

import oop.ex6.blocks.*;
import oop.ex6.variable.VariableException;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

/**
 * This class represents the Reader which receives a file of code, reads and parses it,
 * and sends it to translation.
 */
public class Reader {

    private static final String startBracket = "{";
    private static final String closeBracket = "}";
    private static final String endDeclaration = ";";
    private static final String comment = "//";

    Translator translator = new Translator();

    /**
     * This method reads the main block of the code
     * @param file the file to read
     * @param mainBlock the object which represent the main block of the code
     * @throws FileNotFoundException
     * @throws VariableException
     * @throws MethodException
     * @throws MainScopeException
     */
    public void readMainBlock(File file, MainBlock mainBlock)
            throws FileNotFoundException, VariableException, MethodException, MainScopeException{
        Scanner scan = new Scanner(file);

        while(scan.hasNext()){

            String line= scan.nextLine();
            //if line contains a return statement or a comment, then ignore it and continue to next line
            if(Pattern.compile(RegexPatternsForMain.RETURN_STATEMENT).matcher(line).matches()
                    || line.trim().startsWith(comment)
                    || line.matches(RegexPatternsForMain.SPACES))
                continue;

            // if line contains a declaration of a variable
            else if(line.trim().endsWith(endDeclaration))
                translator.translateDeclareLine(mainBlock, line.trim());

            // if line contain a start of a block
            else if(line.trim().endsWith(startBracket)){
                manageStartOfBlockLine(file, mainBlock, scan, line);
            }
            else
                throw new BadLineFormatException();

        }
        scan.close();
    }

    /**
     * This method reads a method in the main block
     * @param method the method to read
     * @throws MainScopeException
     * @throws VariableException
     * @throws MethodException
     */
    public void readMethod(Method method)throws MainScopeException, VariableException, MethodException{
        String[] linesOfMethod = method.getCode().split("/n");
        Block block = method;
        boolean returnStatement = false;

        for(int i=0; i<linesOfMethod.length; i++){

            //if line contains a return statement
            if(Pattern.compile(RegexPatternsForMain.RETURN_STATEMENT).matcher(linesOfMethod[i]).matches()){
                returnStatement = true;
                //if the return statement is a part of an inner method, and not of the curreny method
                if(! method.getName().equals(block.getName()))
                    continue;
                break; //ignoring code that comes after return statement
            }

            //if line contains only space and tab chars
            else if(Pattern.compile(RegexPatternsForMain.SPACES).matcher(linesOfMethod[i]).matches())
                continue;

            //if line contains a declaration of a variable
            else if(linesOfMethod[i].trim().endsWith(endDeclaration))
                translator.translateDeclareLine(block, linesOfMethod[i].trim());

            //if line contains a comment, then ignore it and do nothing
            else if(linesOfMethod[i].trim().endsWith(comment))
                continue;

            //if line contains a start of a new block
            else if(linesOfMethod[i].trim().endsWith(startBracket)){
                Block curBlock = translator.translateToBlock(block, linesOfMethod[i].trim());
                curBlock.setOuterBlock(block);
                block = curBlock;
            }

            //if line contains the end of a block
            else if(linesOfMethod[i].trim().endsWith(closeBracket)){
                if(block.getOuterBlock() == null)
                        return;
            }

            // if line contains an illegal code
            else {
                throw new BadLineFormatException();
            }

        }
        if(!returnStatement)
            throw new ReturnStatementMissingException();
    }

    private void manageStartOfBlockLine(File file, MainBlock mainBlock, Scanner scan, String line)
            throws FileNotFoundException, VariableException , MethodException, MainScopeException{

        String firstLineOfBlock = line;
        String block = line;
        int numOfStartBrackets = 1;
        int numOfCloseBrackets = 0;

        while (scan.hasNextLine() && numOfStartBrackets > numOfCloseBrackets){
            line = scan.nextLine();
            block += "/n";
            block += line;

            if(line.trim().endsWith(startBracket))
                numOfStartBrackets += 1;
            else if(line.trim().endsWith(closeBracket))
                numOfCloseBrackets += 1;
        }

        Block newBlock = translator.translateToBlock(mainBlock, firstLineOfBlock.trim());
        Method method = (Method) newBlock;
        method.setCode(block);
        mainBlock.addMethod(method);
        newBlock.setOuterBlock(mainBlock);
    }
}