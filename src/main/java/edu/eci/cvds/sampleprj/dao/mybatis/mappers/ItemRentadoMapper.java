/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.ItemRentado;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Imac
 */
public interface ItemRentadoMapper {
    public ItemRentado consultarItemRentado(@Param("idit")int iditem);
    public List<ItemRentado> consultarItemsCliente(@Param("idc")long idcliente);
    
}
