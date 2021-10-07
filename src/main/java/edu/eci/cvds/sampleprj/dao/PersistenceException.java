package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception{

    public final static String MENSAJE_ERROR = "Error de persistencia";

    public PersistenceException(String message){
        super(message);
    }

}
