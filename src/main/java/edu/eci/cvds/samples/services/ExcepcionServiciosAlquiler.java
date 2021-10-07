package edu.eci.cvds.samples.services;

import edu.eci.cvds.sampleprj.dao.PersistenceException;

public class ExcepcionServiciosAlquiler extends Exception{
    public static final String ERROR_SERVICIOS = "Error en los servicios de alquiler";

    public ExcepcionServiciosAlquiler(String message, PersistenceException ex) {
        super(message+ex.toString());
    }

    public ExcepcionServiciosAlquiler(String string) {
        super(string);
    }
}
