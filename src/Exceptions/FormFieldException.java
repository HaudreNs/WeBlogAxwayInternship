package Exceptions;

import java.util.HashMap;

public class FormFieldException extends Exception{

    private HashMap<String, String> errors;
    public FormFieldException(HashMap<String, String> errors){
        this.errors=errors;
    }
    public HashMap<String, String> getErrors(){
        return errors;
    }
}