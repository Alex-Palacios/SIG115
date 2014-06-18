/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.controladores.util.JsfUtil;
import com.entidades.Venta;
import com.reportes.Facturadas;
import com.reportes.VentasVendedor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@Stateless
public class VentaFacade extends AbstractFacade<Venta> {
    @PersistenceContext(unitName = "KAROLSIGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }
    
    /* FUNCION QUE GENERA EL REPORTE #3 
     *      REPORTE DE VENTAS POR VENDEDOR ENTRE 2 FECHAS
     */
    
    public List<VentasVendedor> reporte2(Date fechaInicio, Date fechaFin){
        List<Object[]> consulta = null;
        List<VentasVendedor> reporte = new ArrayList<VentasVendedor>();
        String fi = JsfUtil.setFechaFormateada(fechaInicio,2);
        String ff = JsfUtil.setFechaFormateada(fechaFin,2);
        try{
            String sql ="SELECT ve.NOMBRE_VENDEDOR," +
                        "	(SELECT COUNT(*) FROM venta " +
                        "        WHERE TIPO_VENTA = 1 AND FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'" +
                        "        AND COD_VENDEDOR = v.COD_VENDEDOR) as Num_Contado," +
                        "        (SELECT SUM(MONTO_VENTA) FROM venta" +
                        "        WHERE TIPO_VENTA = 1 AND FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'" +
                        "        AND COD_VENDEDOR = v.COD_VENDEDOR) as Monto_Contado ," +
                        "        (SELECT COUNT(*) FROM venta " +
                        "        WHERE TIPO_VENTA = 2 AND FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'"  +
                        "        AND COD_VENDEDOR = v.COD_VENDEDOR) as Num_Credito ," +
                        "        (SELECT SUM(MONTO_VENTA) FROM venta" +
                        "        WHERE TIPO_VENTA = 2 AND FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'"  +
                        "        AND COD_VENDEDOR = v.COD_VENDEDOR) as Monto_Credito," +
                        "	COUNT(*) as Total_Venta,SUM(MONTO_VENTA) as Monto_Venta," +
                        "        SUM(MONTO_VENTA)/" +
                        "        (SELECT SUM(MONTO_VENTA) FROM venta " +
                        "        WHERE FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"') as PORCENT " +
                        "FROM venta v NATURAL JOIN vendedor ve " +
                        "WHERE v.FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"' " +
                        "GROUP BY v.COD_VENDEDOR";
            
            Query query = em.createNativeQuery(sql);
            consulta = query.getResultList();
            if(!consulta.isEmpty()){
                for(Object[] actual : consulta){
                    VentasVendedor item = new VentasVendedor();
                    item.setVendedor(actual[0].toString());
                    item.setNumContado(Integer.valueOf(actual[1].toString()));
                    if(actual[2]!= null){
                        item.setMontoContado(BigDecimal.valueOf(Double.valueOf(actual[2].toString())));
                    }else{
                        item.setMontoContado(BigDecimal.ZERO);
                    }
                    item.setNumCredito(Integer.valueOf(actual[3].toString()));
                    if(actual[4] != null){
                        item.setMontoCredito(BigDecimal.valueOf(Double.valueOf(actual[4].toString())));
                    }else{
                        item.setMontoCredito(BigDecimal.ZERO);
                    }
                    item.setTotalVenta(Integer.valueOf(actual[5].toString()));
                    if(actual[6] != null){
                        item.setMontoTotal(BigDecimal.valueOf(Double.valueOf(actual[6].toString())));
                    }else{
                        item.setMontoTotal(BigDecimal.ZERO);
                    }
                    if(actual[7] != null){
                        item.setPorcentaje(BigDecimal.valueOf(Double.valueOf(actual[7].toString())));
                    }else{
                        item.setPorcentaje(BigDecimal.ZERO);
                    }
                    reporte.add(item);
                }
            }
            return reporte;   
        }catch(Exception e){
            JsfUtil.addErrorMessage(e,"ERROR AL CONSULTAR REPORTE 2");
            return reporte;
        }
    }
    
    
    
    /* FUNCION QUE GENERA EL REPORTE #4
     *      REPORTE DE VENTAS FACTURADAS VRS NO FACTURADAS
     */
    
    public List<Facturadas> reporte4(Date fechaInicio, Date fechaFin){
        List<Object[]> consulta = null;
        List<Facturadas> reporte = new ArrayList<Facturadas>();
        String fi = JsfUtil.setFechaFormateada(fechaInicio,2);
        String ff = JsfUtil.setFechaFormateada(fechaFin,2);
        try{
            String sql ="SELECT v.FECHA_VENTA," +
                        "	(SELECT COUNT(*) FROM venta " +
                        "        WHERE FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'"  +
                        "        AND FECHA_VENTA=v.FECHA_VENTA) AS VENTAS," +
                        "        (SELECT SUM(MONTO_VENTA) FROM venta " +
                        "        WHERE FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'"  +
                        "        AND FECHA_VENTA=v.FECHA_VENTA) AS MONTO," +
                        "        (SELECT COUNT(*) FROM venta " +
                        "        WHERE FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'"  +
                        "        AND TIPO_DOC=1 AND FECHA_VENTA=v.FECHA_VENTA) AS FACTURADAS," +
                        "        (SELECT SUM(MONTO_VENTA) FROM venta " +
                        "        WHERE FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'"  +
                        "        AND TIPO_DOC=1 AND FECHA_VENTA=v.FECHA_VENTA) AS MONTO_FACT," +
                        "        (SELECT COUNT(*) FROM venta " +
                        "        WHERE FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'"  +
                        "        AND TIPO_DOC=2 AND FECHA_VENTA=v.FECHA_VENTA) AS NO_FACT," +
                        "        (SELECT SUM(MONTO_VENTA) FROM venta " +
                        "        WHERE FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'"  +
                        "        AND TIPO_DOC=2 AND FECHA_VENTA=v.FECHA_VENTA) AS MONTO_NO_FACT " +
                        "FROM venta v " +
                        "WHERE v.FECHA_VENTA BETWEEN '"+fi+"' AND '"+ff+"'"  +
                        "GROUP BY v.FECHA_VENTA";
            
            Query query = em.createNativeQuery(sql);
            consulta = query.getResultList();
            if(!consulta.isEmpty()){
                for(Object[] actual : consulta){
                    Facturadas item = new Facturadas();
                    item.setDia(JsfUtil.convertToFecha(actual[0].toString()));
                    item.setNumVentas(Integer.valueOf(actual[1].toString()));
                    if(actual[2] != null){
                        item.setMontoVentas(BigDecimal.valueOf(Double.valueOf(actual[2].toString())));
                    }else{
                        item.setMontoVentas(BigDecimal.ZERO);
                    }
                    item.setFacturadas(Integer.valueOf(actual[3].toString()));
                    if(actual[4] != null){
                        item.setMontoFacturadas(BigDecimal.valueOf(Double.valueOf(actual[4].toString())));
                    }else{
                        item.setMontoFacturadas(BigDecimal.ZERO);
                    }
                    item.setNoFacturadas(Integer.valueOf(actual[5].toString()));
                    if(actual[6] != null){
                        item.setMontoNoFacturadas(BigDecimal.valueOf(Double.valueOf(actual[6].toString())));
                    }else{
                        item.setMontoNoFacturadas(BigDecimal.ZERO);
                    }
                    reporte.add(item);
                }
            }
            return reporte;   
        }catch(Exception e){
            JsfUtil.addErrorMessage(e,"ERROR AL CONSULTAR REPORTE 4");
            return null;
        }
    }
    
    
    
}
