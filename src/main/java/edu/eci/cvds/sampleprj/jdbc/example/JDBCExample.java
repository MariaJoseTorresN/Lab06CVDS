/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            int suCodigoECI=2163298;
            registrarNuevoProducto(con, suCodigoECI, "Majo", 99999999);            
            con.commit();
                        
            
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        //Crear preparedStatement
        String insertarProducto = "INSERT INTO ORD_PRODUCTOS (codigo, nombre, precio) VALUES (?,?,?)";
        try (PreparedStatement registrarProducto = con.prepareStatement(insertarProducto);){
            //Asignar parámetros
            registrarProducto.setInt(1, codigo);
            registrarProducto.setString(2, nombre);
            registrarProducto.setInt(3, precio);
            //usar 'execute'
            registrarProducto.execute();
        } catch (Exception e) {
            System.out.println("Ocurrio un problema con la operacion");
        }
        
        con.commit();   
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido){
        List<String> np=new LinkedList<>();
        
        //Crear prepared statement
        String consultarProductos = "SELECT pr.nombre FROM ORD_PEDIDOS AS pe JOIN ORD_DETALLE_PEDIDO AS de ON (de.pedido_fk = pe.codigo) JOIN ORD_PRODUCTOS AS pr ON (de.producto_fk = pr.codigo) WHERE pe.codigo = ?";

        try (PreparedStatement nombresProductos = con.prepareStatement(consultarProductos);){
            //asignar parámetros
            nombresProductos.setInt(1, codigoPedido);
            //usar executeQuery
            ResultSet resultSet = nombresProductos.executeQuery();
            //Sacar resultados del ResultSet
            while (resultSet.next()) {
                //Llenar la lista y retornarla
                String productName = resultSet.getString("nombre");
                np.add(productName);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un problema con la operacion");
        }
             
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido){
        int valorPedido = 0;

        //Crear prepared statement
        String totalProductos = "SELECT SUM(pr.precio * de.cantidad) AS total FROM ORD_PEDIDOS AS pe JOIN ORD_DETALLE_PEDIDO AS de ON (de.pedido_fk = pe.codigo) JOIN ORD_PRODUCTOS AS pr ON (de.producto_fk = pr.codigo) WHERE pe.codigo = ?";

        try (PreparedStatement valorTotal = con.prepareStatement(totalProductos)){
            //asignar parámetros
            valorTotal.setInt(1, codigoPedido);
            //usar executeQuery
            ResultSet total = valorTotal.executeQuery();
            //Sacar resultado del ResultSet
            while (total.next()) {
                valorPedido = total.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un problema con la operacion");
        }
        
        return valorPedido;
    }
    

    
    
    
}