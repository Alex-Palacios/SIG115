/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.util;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author Alex
 */

@ManagedBean(name = "funciones")
@SessionScoped
public class Funciones {

    public Funciones() {
    }
    
    public String formatFecha(Date fecha){
        return JsfUtil.setFechaFormateada(fecha, 1);
    }
    
   
}
