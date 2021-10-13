/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.ItemRentado;
import java.util.List;

/**
 *
 * @author Imac
 */
public interface ItemRentadoDAO {
    public List<ItemRentado> consultarItemsCliente(long idCliente) throws PersistenceException;
    public ItemRentado consultarItemRentado(int iditem) throws PersistenceException;

}
