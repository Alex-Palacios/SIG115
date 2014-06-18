/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.controladores.util.JsfUtil;
import com.entidades.Pago;
import com.reportes.Comisiones;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex Palacios
 *    
 */
@Stateless
public class PagoFacade extends AbstractFacade<Pago> {
    @PersistenceContext(unitName = "KAROLSIGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoFacade() {
        super(Pago.class);
    }
    
    
    
    public List<Comisiones> reporte3(int mes, int anio){
        List<Object[]> consulta = null;
        List<Comisiones> reporte = new ArrayList<Comisiones>();
        try{
            String sql ="SELECT NOMBRE_VENDEDOR,SUM(MONTO_PAGO) AS PAGOS,SUM(DESCUENTO) AS DESCUENTO," +
                        "	 SUM(MONTO_PAGO) - SUM(DESCUENTO) AS PARA_COMISION," +
                        "        PORCENT_COMISION*100 AS PORCENTAJE_COMISION," +
                        "        (SUM(MONTO_PAGO) - SUM(DESCUENTO)) *PORCENT_COMISION AS COMISION " +
                        " FROM pago NATURAL JOIN vendedor " +
                        " WHERE YEAR(FECHA_PAGO) = " + anio+
                        " AND MONTH(FECHA_PAGO) = " + mes +
                        " GROUP BY COD_VENDEDOR";
            
            Query query = em.createNativeQuery(sql);
            consulta = query.getResultList();
            if(!consulta.isEmpty()){
                for(Object[] actual : consulta){
                    Comisiones item = new Comisiones();
                    item.setVendedor(actual[0].toString());
                    if(actual[1]!= null){
                        item.setPagos(BigDecimal.valueOf(Double.valueOf(actual[1].toString())));
                    }else{
                        item.setPagos(BigDecimal.ZERO);
                    }
                    if(actual[2]!= null){
                        item.setDescuentos(BigDecimal.valueOf(Double.valueOf(actual[2].toString())));
                    }else{
                        item.setDescuentos(BigDecimal.ZERO);
                    }
                    if(actual[3]!= null){
                        item.setAbonos(BigDecimal.valueOf(Double.valueOf(actual[3].toString())));
                    }else{
                        item.setAbonos(BigDecimal.ZERO);
                    }
                    if(actual[4]!= null){
                        item.setPorcentaje(BigDecimal.valueOf(Double.valueOf(actual[4].toString())));
                    }else{
                        item.setPorcentaje(BigDecimal.ZERO);
                    }
                    if(actual[5]!= null){
                        item.setComision(BigDecimal.valueOf(Double.valueOf(actual[5].toString())));
                    }else{
                        item.setComision(BigDecimal.ZERO);
                    }
                    reporte.add(item);
                }
            }
            return reporte;   
        }catch(Exception e){
            JsfUtil.addErrorMessage(e,"ERROR AL CONSULTAR REPORTE 3");
            return reporte;
        }
    }
}
