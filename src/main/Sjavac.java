package oop.ex6.main;

import java.io.IOException;
import java.io.File;
import oop.ex6.blocks.*;
import oop.ex6.variable.*;

/**
 * This class contains the main method of the program which starts the process of reading and checking
 * the S-java code file received from user, and throwing exceptions if an error is found.
 */
public class Sjavac {

    private static final String BAD_FILE_NAME = "could not open the input file";

    private static MainBlock mainBlock = new MainBlock("mainBlock");

    public static MainBlock getMainBlock(){
        return mainBlock;
    }

    /**
     * The main method of the program
     * @param args the path of the S-java code file
     */
    public static void main(String[] args){

        //try reading and translating the code file
        try{
            mainBlock = new MainBlock("mainBlock");
            File givenFile = new File(args[0]);
            Reader parser = new Reader();
            parser.readMainBlock(givenFile, mainBlock);

            for(Method method : mainBlock.getMethods()){
                parser.readMethod(method);
            }
            System.out.println("0");
        } catch (IOException ex){ // if file was nor found
            System.out.println("2");
            System.err.print(BAD_FILE_NAME);
        } catch (MainScopeException|MethodException|VariableException ex){ //if code is written badly
            System.out.println("1");
            System.err.print(ex.getMessage());
        }

    }

}
