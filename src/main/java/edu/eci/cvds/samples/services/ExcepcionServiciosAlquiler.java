package edu.eci.cvds.samples.services;

public class ExcepcionServiciosAlquiler extends Exception{
    public static final String ERROR_SERVICIOS = "Error en los servicios de alquiler";

    public ExcepcionServiciosAlquiler(String message){
        super(message);
    }
}
