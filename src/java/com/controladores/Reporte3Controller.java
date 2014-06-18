/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.beans.PagoFacade;
import com.controladores.util.JsfUtil;
import com.reportes.Comisiones;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
@ManagedBean(name = "reporte3Controller")
@SessionScoped
public class Reporte3Controller  implements Serializable {
    //VARIABLES CONTROLADORAS
    @EJB
    private PagoFacade jpaPagos;
    private List<Comisiones> itemsComisiones = new ArrayList<Comisiones>();
    private List<Comisiones> filtroComisiones = new ArrayList<Comisiones>();
    
    
    //VARIABLES
    private int mes;
    private int anio;
    
    //Totales
    private BigDecimal totalPagos;
    private BigDecimal totalDescuentos;
    private BigDecimal totalAbonos;
    private BigDecimal totalComisiones;
    
    //CONSTRUCTOR
    public Reporte3Controller() {
        anio = JsfUtil.getCalendar().get(Calendar.YEAR);
    }

    //GETTERS AND SETTERS
    public PagoFacade getJpaPagos() {
        return jpaPagos;
    }

    public List<Comisiones> getItemsComisiones() {
        return itemsComisiones;
    }

    public void setItemsComisiones(List<Comisiones> itemsComisiones) {
        this.itemsComisiones = itemsComisiones;
    }

    public List<Comisiones> getFiltroComisiones() {
        return filtroComisiones;
    }

    public void setFiltroComisiones(List<Comisiones> filtroComisiones) {
        this.filtroComisiones = filtroComisiones;
    }
    
    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public BigDecimal getTotalPagos() {
        totalPagos = BigDecimal.ZERO;
        for(Comisiones actual : filtroComisiones){
             totalPagos = totalPagos.add(actual.getPagos());
        }
        return totalPagos;
    }

    public BigDecimal getTotalDescuentos() {
        totalDescuentos = BigDecimal.ZERO;
        for(Comisiones actual : filtroComisiones){
             totalDescuentos = totalDescuentos.add(actual.getDescuentos());
        }
        return totalDescuentos;
    }

    public BigDecimal getTotalAbonos() {
        totalAbonos = BigDecimal.ZERO;
        for(Comisiones actual : filtroComisiones){
             totalAbonos = totalAbonos.add(actual.getAbonos());
        }
        return totalAbonos;
    }

    public BigDecimal getTotalComisiones() {
        totalComisiones = BigDecimal.ZERO;
        for(Comisiones actual : filtroComisiones){
             totalComisiones = totalComisiones.add(actual.getComision());
        }
        return totalComisiones;
    }

    
    
    //FUNCIONES

    public void generarReporte3(){
        itemsComisiones.clear();
        filtroComisiones.clear();
        //Generar Reporte
        try{
            itemsComisiones = getJpaPagos().reporte3(mes, anio);
            setFiltroComisiones(itemsComisiones);
            JsfUtil.addSuccessMessage("Reporte Generado Correctamente");
        }catch(Exception ex){ }
    }
    
    
    public void preProcessXLS(Object document) throws IOException{  
        //LEER PLANTILLA
        File archivo=new File(JsfUtil.realPath()+"/resources/templates/reporte3.xls");
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
