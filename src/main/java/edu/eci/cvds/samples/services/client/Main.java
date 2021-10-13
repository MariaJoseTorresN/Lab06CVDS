/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.services.client;

import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

/**
 *
 * @author Imac
 */
public class Main{
    
    
     public static void main(String args[]) throws ExcepcionServiciosAlquiler{
         System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarCliente(21377885));
         System.exit(0);
     }
}
