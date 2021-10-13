/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Imac
 */

@ManagedBean(name = "Alquiler")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean{
    
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private List<Cliente> clientes;
    private List<Item> disponibles;
    private Cliente seleccionado;
    private List<ItemRentado> rentados;
    private int[] item = new int[2];
    private long multa;
    private long costo;
    
    
    public void inicio(){
        try{
            clientes = serviciosAlquiler.consultarClientes();
            
        }catch(ExcepcionServiciosAlquiler e){
            
        }
        
    }

    public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email){
        try {
            serviciosAlquiler.registrarCliente(new Cliente(nombre,documento,telefono,direccion,email));
            clientes = serviciosAlquiler.consultarClientes();

        } catch (ExcepcionServiciosAlquiler ex) {
            Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void consultarItemsRentados(){
        try {
            rentados = serviciosAlquiler.consultarItemsCliente(seleccionado.getDocumento());
        } catch (ExcepcionServiciosAlquiler ex) {
            Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void consultarMulta(){
        long total=0;
        try {
            if(rentados!=null){
                
                for(ItemRentado i: rentados){
                    total+=serviciosAlquiler.consultarMultaAlquiler(i.getItem().getId(),new Date(System.currentTimeMillis()));
                }
            }
        }catch (ExcepcionServiciosAlquiler ex) {
                Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        multa=total;
        
    }
    public void consultarCostoAlquiler(int itid, int numDias){
        try {
            costo=serviciosAlquiler.consultarCostoAlquiler(itid,numDias);
            item[0]=itid;
            item[1]=numDias;
        } catch (ExcepcionServiciosAlquiler ex) {
            Logger.getLogger(AlquilerItemsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}