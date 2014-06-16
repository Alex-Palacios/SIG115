package com.controladores;

import com.entidades.Venta;
import com.beans.VentaFacade;
import com.controladores.util.JsfUtil;
import com.reportes.VentasVendedor;
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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@ManagedBean(name = "reporte2Controller")
@SessionScoped
public class Reporte2Controller implements Serializable {

    //VARIABLES CONTROLADORAS
    @EJB
    private VentaFacade jpaVentas = null;
    private List<VentasVendedor> itemsVV = new ArrayList<VentasVendedor>();
    private List<VentasVendedor> filtroVV = new ArrayList<VentasVendedor>();
    private Venta selected;
    private Venta newItem;
    
    //VARIABLES
    private Date fechaInicio;
    private Date fechaFin;
    
    //Totales
    private Integer totalVentasContado;
    private Integer totalVentasCredito;
    private Integer totalVentas;
    private BigDecimal mtVentasContado;
    private BigDecimal mtVentasCredito;
    private BigDecimal mtVentas;
    
    //CONSTRUCTOR
    public Reporte2Controller() {
    }

    //GETTERS AND SETTERS
    public VentaFacade getJpaVentas() {
        return jpaVentas;
    }
        
    public Venta getSelected() {
        if (selected == null) {
            selected = new Venta();
        }
        return selected;
    }

    public void setSelected(Venta selected) {
        this.selected = selected;
    }
    
    public List<VentasVendedor> getItemsVV() {
        return itemsVV;
    }

    public void setItemsVV(List<VentasVendedor> itemsVV) {
        this.itemsVV = itemsVV;
    }

    public List<VentasVendedor> getFiltroVV() {
        return filtroVV;
    }

    public void setFiltroVV(List<VentasVendedor> filtroVV) {
        this.filtroVV = filtroVV;
    }
    
    
    public Venta getNewItem() {
        return newItem;
    }

    public void setNewItem(Venta newItem) {
        this.newItem = newItem;
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

    public Integer getTotalVentasContado() {
        totalVentasContado = 0;
        for(VentasVendedor actual : filtroVV){
            totalVentasContado = totalVentasContado + actual.getNumContado();
        }
        return totalVentasContado;
    }

    public Integer getTotalVentasCredito() {
        totalVentasCredito = 0;
        for(VentasVendedor actual : filtroVV){
            totalVentasCredito = totalVentasCredito + actual.getNumCredito();
        }
        return totalVentasCredito;
    }

    public Integer getTotalVentas() {
        totalVentas = totalVentasContado + totalVentasCredito;
        return totalVentas;
    }

    public BigDecimal getMtVentasContado() {
        mtVentasContado = BigDecimal.ZERO;
        for(VentasVendedor actual : filtroVV){
             mtVentasContado = mtVentasContado.add(actual.getMontoContado());
        }
        return mtVentasContado;
    }

    public BigDecimal getMtVentasCredito() {
        mtVentasCredito = BigDecimal.ZERO;
        for(VentasVendedor actual : filtroVV){
             mtVentasCredito = mtVentasCredito.add(actual.getMontoCredito());
        }
        return mtVentasCredito;
    }

    public BigDecimal getMtVentas() {
        mtVentas = mtVentasContado.add(mtVentasCredito);
        return mtVentas;
    }

    
    
    
    
    
    //FUNCIONES

    public void generarReporte2(){
        itemsVV.clear();
        filtroVV.clear();
        if(fechaInicio != null && fechaFin != null){
            if(fechaInicio.before(fechaFin) || fechaInicio.equals(fechaFin)){
                //Generar Reporte
                itemsVV = getJpaVentas().reporte2(fechaInicio, fechaFin);
                setFiltroVV(itemsVV);
                JsfUtil.addSuccessMessage("Reporte Generado Correctamente");
            }else{
                JsfUtil.addErrorMessage("Fecha Fin debe ser mayor o igual a Fecha Inicio");
            }
        }else{
            JsfUtil.addErrorMessage("Fecha Inicio y/o Fin tienen valores nulos");
        }
    }
    
    
    public void preProcessXLS(Object document) throws IOException{  
        File archivo=new File("/resources/templates/reporte2.xls");
        FileInputStream file = new FileInputStream(archivo);
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFWorkbook wb = (HSSFWorkbook) document; 
        wb = workbook;
        HSSFSheet sheet = workbook.getSheetAt(11);
        sheet.setActive(true);

   }
    
}
