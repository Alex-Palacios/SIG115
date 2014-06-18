/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.beans.VentaFacade;
import com.controladores.util.JsfUtil;
import com.reportes.Facturadas;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Alex
 */
@ManagedBean(name = "reporte4Controller")
@SessionScoped
public class Reporte4Controller implements Serializable {
    //VARIABLES CONTROLADORAS
    @EJB
    private VentaFacade jpaVentas;
    private List<Facturadas> itemsFNF = new ArrayList<Facturadas>();
    private List<Facturadas> filtroFNF = new ArrayList<Facturadas>();
    
    
    //VARIABLES
    private Date fechaInicio;
    private Date fechaFin;
    
    //Totales
    private Integer totalVentas;
    private Integer totalVentasFacturadas;
    private Integer totalVentasNoFacturadas;
    private BigDecimal mtVentas;
    private BigDecimal mtVentasFacturadas;
    private BigDecimal mtVentasNoFacturadas;
    
    //CONSTRUCTOR
    public Reporte4Controller() {
    }

    //GETTERS AND SETTERS
    public VentaFacade getJpaVentas() {
        return jpaVentas;
    }

    public List<Facturadas> getItemsFNF() {
        return itemsFNF;
    }

    public void setItemsFNF(List<Facturadas> itemsFNF) {
        this.itemsFNF = itemsFNF;
    }

    public List<Facturadas> getFiltroFNF() {
        return filtroFNF;
    }

    public void setFiltroFNF(List<Facturadas> filtroFNF) {
        this.filtroFNF = filtroFNF;
    }
        
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getTotalVentas() {
        totalVentas = 0;
        for(Facturadas actual : filtroFNF){
            totalVentas = totalVentas + actual.getNumVentas();
        }
        return totalVentas;
    }

    public Integer getTotalVentasFacturadas() {
        totalVentasFacturadas = 0;
        for(Facturadas actual : filtroFNF){
            totalVentasFacturadas = totalVentasFacturadas + actual.getFacturadas();
        }
        return totalVentasFacturadas;
    }

    public Integer getTotalVentasNoFacturadas() {
        totalVentasNoFacturadas = 0;
        for(Facturadas actual : filtroFNF){
            totalVentasNoFacturadas = totalVentasNoFacturadas + actual.getNoFacturadas();
        }
        return totalVentasNoFacturadas;
    }

    public BigDecimal getMtVentas() {
        mtVentas = BigDecimal.ZERO;
        for(Facturadas actual : filtroFNF){
             mtVentas = mtVentas.add(actual.getMontoVentas());
        }
        return mtVentas;
    }

    public BigDecimal getMtVentasFacturadas() {
        mtVentasFacturadas = BigDecimal.ZERO;
        for(Facturadas actual : filtroFNF){
             mtVentasFacturadas = mtVentasFacturadas.add(actual.getMontoFacturadas());
        }
        return mtVentasFacturadas;
    }

    public BigDecimal getMtVentasNoFacturadas() {
        mtVentasNoFacturadas = BigDecimal.ZERO;
        for(Facturadas actual : filtroFNF){
             mtVentasNoFacturadas = mtVentasNoFacturadas.add(actual.getMontoNoFacturadas());
        }
        return mtVentasNoFacturadas;
    }

    
    //FUNCIONES

    public void generarReporte4(){
        itemsFNF.clear();
        filtroFNF.clear();
        if(fechaInicio != null && fechaFin != null){
            if(fechaInicio.before(fechaFin) || fechaInicio.equals(fechaFin)){
                //Generar Reporte
                try{
                    itemsFNF = getJpaVentas().reporte4(fechaInicio, fechaFin);
                    setFiltroFNF(itemsFNF);
                    JsfUtil.addSuccessMessage("Reporte Generado Correctamente");
                }catch(Exception ex){ }
            }else{
                JsfUtil.addErrorMessage("Fecha Fin debe ser mayor o igual a Fecha Inicio");
            }
        }else{
            JsfUtil.addErrorMessage("Fecha Inicio y/o Fin tienen valores nulos");
        }
    }
    
    
    public void preProcessXLS(Object document) throws IOException{  
        //LEER PLANTILLA
        File archivo=new File(JsfUtil.realPath()+"/resources/templates/reporte4.xls");
        FileInputStream file = new FileInputStream(archivo);
        HSSFWorkbook libro = new HSSFWorkbook(file);
        HSSFSheet hoja = libro.cloneSheet(0); // COPIAR PRIMERA HOJA
        //MODIFICAR DOCUMENTO
        HSSFWorkbook workbook = (HSSFWorkbook) document;
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow fila = sheet.getRow(11);
        HSSFCell celda = fila.getCell(0);
        
   }
}
