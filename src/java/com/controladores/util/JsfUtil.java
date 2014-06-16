package com.controladores.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;


public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
    
    public static void addWarnMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
    
   
    public static void AbrirVentana(String widgetVar){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('"+ widgetVar + "').show();");
    }
    
    public static void cerrarVentana(String widgetVar){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('"+ widgetVar + "').hide();");
    }
    
     public static void ventanaErrorEffect(String widgetVar){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("var jqDialog = jQuery('#'+ " + widgetVar +".id); jqDialog.effect('shake', { times:3 }, 100);");
    }
    
        
    public static void cerrarSessionUsuario(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
    }
    
    
     /*
     * FUNCION QUE ENCRIPTA EL PASSWORD A MD5
     */
    public static String getMD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest algorithm = MessageDigest.getInstance("MD5");
        algorithm.reset();
        algorithm.update(text.getBytes("utf-8"));
        final StringBuilder hexStringBuilder = new StringBuilder();
        final byte[] digest = algorithm.digest();
        for (byte digestItem : digest) {
            String hex = Integer.toHexString(0xFF & digestItem);
            if (hex.length() == 1) {
                hexStringBuilder.append('0');
            }
            hexStringBuilder.append(hex.toUpperCase());
        }
        return hexStringBuilder.toString();
    }
    
    
    /*Funcion que redirecciona a otra pagina existente en la aplicacion
     * recibe como parametro la url relativa a partir del nodo raiz
     * de la pagina que se quiere servir
     */ 
    public static void irA(String urlRelat) {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();  
            contex.getExternalContext().redirect("/KAROLSIG" + urlRelat);
        } catch (Exception e) {
            
        }
    }
    
    
     //Obtener la zona horaria
    public static TimeZone getTimeZone() {
        return TimeZone.getTimeZone("America/El_Salvador");
    }
    
    public static Date hoy() {
        TimeZone tz = getTimeZone();
        Calendar calendario = Calendar.getInstance();
        calendario.setTimeZone(tz);
        return calendario.getTime();
    }
    
    //Formatea la fecha a dd/MM/yyyy para poder visualizarse
    public static String setFechaFormateada(Date fecha,int format) {
        if (fecha != null) {
            SimpleDateFormat formato=new SimpleDateFormat("dd/mm/yyyy");;
            switch(format){
                case 1:
                    formato = new SimpleDateFormat("dd/MM/yyyy");
                    break;
                case 2:
                    formato = new SimpleDateFormat("yyyy-MM-dd"); //PARA LA BD
            }
            String fechaFormateada = formato.format(fecha);
            return fechaFormateada;
        } else {
            return "";
        }
    }
    
    //Formatea la fecha a dd/MM/yyyy para poder visualizarse
    public static Date setFechaFormateadaBD(Date fecha) {
        try {
            //Convierte el String en tipo Date
            String F = setFechaFormateada(fecha,2);
            //Cambia el Formato de dd/MM/yyyy a yyyy-MM-dd para la BD
            DateFormat dfMysql = new SimpleDateFormat("yyyy-MM-dd");
            fecha = dfMysql.parse(F);
        } catch (ParseException ex) { }
        
        return fecha;
    }
    
    public static float redondearMenos(float valor,int precision) {
        BigDecimal big = new BigDecimal(valor);
        big = big.setScale(precision, RoundingMode.HALF_DOWN);
        return big.floatValue();
    }
   
    public static float redondearMas(float valor,int precision) {
        BigDecimal big = new BigDecimal(valor);
        big = big.setScale(precision, RoundingMode.HALF_UP);
        return big.floatValue();
    }
   
    
    public static void postProcessXLS(Object document) {  
        HSSFWorkbook wb = (HSSFWorkbook) document;  
        HSSFSheet sheet = wb.getSheetAt(0);  
        HSSFRow header = sheet.getRow(0); 
        HSSFCellStyle cellStyle = wb.createCellStyle();    
        cellStyle.setFillBackgroundColor(HSSFColor.GREEN.index); 
        //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font=wb.createFont();
        /* set the weight of the font */
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        /* attach the font to the style created earlier */
        cellStyle.setFont(font);
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {  
            HSSFCell cell = header.getCell(i);  
            cell.setCellStyle(cellStyle); 
        }  
   }
}