package oop.ex6.main;

/**
 * This class represents a general code component
 */
public abstract class CodeComponent {
    String name;

    /**
     * Constructor for CodeComponent object
     * @param name the name of the code component
     */
    public CodeComponent(String name){
        this.name = name;
    }

    /**
     * get the name of the code component
     * @return name of the code component
     */
    public String getName(){
        return name;
    }

    /**
     * set the name of the code component
     * @param name the new name
     */
    public void setName(String name){
        this.name = name;
    }
}
