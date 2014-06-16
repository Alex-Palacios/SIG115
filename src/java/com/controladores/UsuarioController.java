package com.controladores;

import com.entidades.Usuario;
import com.controladores.util.JsfUtil;
import com.beans.UsuarioFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {
    
    //VARIABLES CONTROLADORAS
    @EJB
    private UsuarioFacade jpaUsuario = null;
    private List<Usuario> itemsUsuarios = null;
    private List<Usuario> filtroUsuarios = null;
    private Usuario selected;
    private Usuario newUser;
    private Usuario newItem;
    
    //VARIABLES
    private String username;
    private String password;
    private Usuario usuario; //USUARIO ACTUAL EN SESION
    private boolean p1=false;
    private boolean p2=false;
    private boolean p3=false;
    private boolean p4=false;
    private boolean p5=false;
    private boolean p6=false;
    private boolean p7=false;
    private boolean p8=false;
    private boolean p9=false;
    private boolean p10=false;
    private boolean p11=false;
    private boolean login = false;
    private boolean esEstrategico=false;
    private boolean esTactico=false;
    private String nuevoUsername;
    private String passwordAnterior;
    private String nuevoPassword;
    
    
    //CONSTRUCTOR
    public UsuarioController() {
        
    }

    //GETTERS y SETTERS
    public Usuario getSelected() {
        if (selected == null) {
            selected = new Usuario();
        }
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    public UsuarioFacade getJpaUsuario() {
        if (jpaUsuario == null) {
            jpaUsuario = new UsuarioFacade();
        }
        return jpaUsuario;
    }

    public List<Usuario> getItemsUsuarios() {
        return itemsUsuarios;
    }

    public void setItemsUsuarios(List<Usuario> itemsUsuarios) {
        this.itemsUsuarios = itemsUsuarios;
    }

    public List<Usuario> getFiltroUsuarios() {
        return filtroUsuarios;
    }

    public void setFiltroUsuarios(List<Usuario> filtroUsuarios) {
        this.filtroUsuarios = filtroUsuarios;
    }

    public Usuario getNewUser() {
        return newUser;
    }

    public void setNewUser(Usuario newUser) {
        this.newUser = newUser;
    }

    public Usuario getNewItem() {
        return newItem;
    }

    public void setNewItem(Usuario newItem) {
        this.newItem = newItem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isEsEstrategico() {
        return esEstrategico;
    }

    public void setEsEstrategico(boolean esEstrategico) {
        this.esEstrategico = esEstrategico;
    }

    public boolean isEsTactico() {
        return esTactico;
    }

    public void setEsTactico(boolean esTactico) {
        this.esTactico = esTactico;
    }
   
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isP1() {
        return p1;
    }

    public boolean isP2() {
        return p2;
    }

    public boolean isP3() {
        return p3;
    }

    public boolean isP4() {
        return p4;
    }

    public boolean isP5() {
        return p5;
    }

    public boolean isP6() {
        return p6;
    }

    public boolean isP7() {
        return p7;
    }

    public boolean isP8() {
        return p8;
    }

    public boolean isP9() {
        return p9;
    }

    public boolean isP10() {
        return p10;
    }

    public boolean isP11() {
        return p11;
    }

     
    
    public boolean isLogin() {
        return login;
    }

    
    public String getNuevoUsername() {
        return nuevoUsername;
    }

    public void setNuevoUsername(String nuevoUsername) {
        this.nuevoUsername = nuevoUsername;
    }

    public String getPasswordAnterior() {
        return passwordAnterior;
    }

    public void setPasswordAnterior(String passwordAnterior) {
        this.passwordAnterior = passwordAnterior;
    }

    public String getNuevoPassword() {
        return nuevoPassword;
    }

    public void setNuevoPassword(String nuevoPassword) {
        this.nuevoPassword = nuevoPassword;
    }

    
    
    //FUNCIONES
    private void recreateModel() {
        itemsUsuarios = null;
        if(filtroUsuarios != null){
            filtroUsuarios = itemsUsuarios;
        }
    }
    
    public void prepareList() {
        recreateModel();
        //JsfUtil.irA(ResourceBundle.getBundle("/Bundle").getString("url_usuarios"));
    }

    
    public void prepareCreate() {
        newUser = new Usuario();
    }
    

    public void prepareUpdateCredenciales() {
        nuevoUsername = null;
        passwordAnterior = null;
        nuevoPassword = null;
    }
    
    public void create() {
        try {
            getJpaUsuario().create(newUser);
            prepareCreate();
            recreateModel();
            JsfUtil.addSuccessMessage("USUARIO CREADO EXITOSAMENTE");
            //JsfUtil.cerrarVentana("crearUsuario");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "ERROR AL CREAR USUARIO");
            //JsfUtil.ventanaErrorEffect("crearUsuario");
        }
    }

    

    public void update() {
        try {
            getJpaUsuario().edit(selected);
            JsfUtil.addSuccessMessage("USUARIO ACTUALIZADO");
            //JsfUtil.cerrarVentana("editarUsuario");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "ERROR AL ACTUALIZAR USUARIO");
            //JsfUtil.ventanaErrorEffect("editarUsuario");
        }
    }
    
    
    public void update(Usuario user) {
        try {
            getJpaUsuario().edit(user);
            //JsfUtil.cerrarVentana("editarUsuario");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "ERROR AL ACTUALIZAR USUARIO");
            //JsfUtil.ventanaErrorEffect("editarUsuario");
        }
    }
    
    public void delete() {
        try {
            getJpaUsuario().remove(selected);
            recreateModel();
            JsfUtil.addSuccessMessage("USUARIO ELIMINADO");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "ERROR AL ELIMINAR USUARIO");
        }
    }

    
    
    public void logear(){
        List<Usuario> userX = getJpaUsuario().login(username, password);
        if(userX != null && !userX.isEmpty()){
            //SI LAS CREDENCIALES SON VALIDAS
            usuario = userX.get(0);
            if(usuario.getActivo()){
                if(usuario.getFechaExp() != null && usuario.getFechaExp().after(JsfUtil.hoy())){
                    JsfUtil.addErrorMessage("SU CUENTA DE USUARIO HA EXPIRADO CONSULTE CON EL ADMINISTRADOR");
                }else{
                    login = true;
                    username = null;
                    password = null;
                    JsfUtil.irA(ResourceBundle.getBundle("/Bundle").getString("url_home"));
                }
            }else{
                //SI EL USUARIO ESTA INACTIVO
                password = "";
                usuario = null;
                login = false;
                JsfUtil.addErrorMessage("SU CUENTA DE USUARIO ESTA INACTIVA CONSULTE CON EL ADMINISTRADOR");
            }
        }else{
            //SI LAS CREDENCIALES NO SON VALIDAS
            usuario = null;
            password = null;
            login = false;
            JsfUtil.addErrorMessage("CREDENCIALES INCORRECTOS POR FAVOR INTENTE NUEVAMENTE");
        }
    }

    
    //FUNCION DE CERRAR SESION
    public void logout(){
        username = null;
        password = null;
        usuario = null;
        login = false;
        JsfUtil.cerrarSessionUsuario();
        JsfUtil.irA("");
    }
    
    
    public void irHome(){
        JsfUtil.irA(ResourceBundle.getBundle("/Bundle").getString("url_home"));
    }
    
    
    public void updateUsername(){
        RequestContext context = RequestContext.getCurrentInstance();
        if(nuevoUsername != null ){
            if(!usuario.getUsername().equals("admin")){
                if(!usuario.getUsername().equals(nuevoUsername)){
                    if(getJpaUsuario().usernameDisponible(nuevoUsername)){
//                        context.addCallbackParam("OK", true);
//                        context.execute("confirmUsername.show();");
                    }else{
//                        JsfUtil.addWarnMessage("USERNAME YA ES USADO POR OTRO USUARIO DEL SISTEMA");
//                        context.addCallbackParam("OK", false);
                    }
                }else{
                    JsfUtil.addWarnMessage("NO EXISTEN CAMBIOS");
                   // context.addCallbackParam("OK", false);
                }
            }else{
                JsfUtil.addWarnMessage("USTED ES EL ADMINISTRADOR DEL SISTEMA ... NO PUEDE CAMBIAR USERNAME");
               // context.addCallbackParam("OK", false);
            }
        }
    }
    
    public void confirmUpdateUsername(){
        usuario.setUsername(nuevoUsername);
        update(usuario);
        JsfUtil.addSuccessMessage("USERNAME ACTUALIZADO ...");
        prepareUpdateCredenciales();
    }
    
    public void updatePassword(){
        RequestContext context = RequestContext.getCurrentInstance();
        if(nuevoPassword != null){
            try{
                if(usuario.getPassword().equals(JsfUtil.getMD5(passwordAnterior))){
                    nuevoPassword = JsfUtil.getMD5(nuevoPassword);
                    if(!usuario.getPassword().equals(nuevoPassword)){
                        context.addCallbackParam("OK", true);
                        context.execute("confirmPassword.show();");
                    }else{
                        JsfUtil.addWarnMessage("NO EXISTEN CAMBIOS");
                        context.addCallbackParam("OK", false);
                    }
                }else{
                    JsfUtil.addErrorMessage("CONTRASEÑA ACTUAL INCORRECTA");
                    context.addCallbackParam("OK", false);
                }
            }catch(Exception ex){
                JsfUtil.addErrorMessage("HA OCURRIDO UN ERROR AL CAMBIAR PASSWORD");
                context.addCallbackParam("OK", false);
            }
            
        }
    }
    
    
    public void recuperarPassword(){
        if(selected != null){
            try{
                String defaultPassword = ResourceBundle.getBundle("/Bundle").getString("PasswordDefault");
                defaultPassword = JsfUtil.getMD5(defaultPassword);
                selected.setPassword(defaultPassword);
                getJpaUsuario().edit(selected);
                JsfUtil.addSuccessMessage("PASSWORD REESTABLECIDO AL USUARIO " + selected.getUsername());
            }catch(Exception ex){
                JsfUtil.addErrorMessage("HA OCURRIDO UN ERROR AL RECUPERAR PASSWORD");
            }
        }
    }
    
    
    public void activarInactivarUsuario(){
        if(selected != null){
            try{
                if(selected.getActivo()){
                    selected.setActivo(Boolean.FALSE);
                }else{
                    selected.setActivo(Boolean.TRUE);
                }
                getJpaUsuario().edit(selected);
                if(selected.getActivo()){
                    JsfUtil.addSuccessMessage("USUARIO " + selected.getUsername()+ " ACTIVO");
                }else{
                    JsfUtil.addSuccessMessage("USUARIO " + selected.getUsername()+ " INACTIVO");
                }
            }catch(Exception ex){
                JsfUtil.addErrorMessage("HA OCURRIDO UN ERROR AL ACTIVAR/INACTIVAR USUARIO");
            }
        }
    }
    
    
    public void confirmUpdatePassword(){
        usuario.setPassword(nuevoPassword);
        update(usuario);
        JsfUtil.addSuccessMessage("PASSWORD ACTUALIZADO ...");
        prepareUpdateCredenciales();
    }
    
    
    
   public boolean tienePermiso(String permiso){
       boolean r=false;
       
       return r;
   }
    
   
    
    public SelectItem[] getRolesUsuariosItems() {
        SelectItem[] options = new SelectItem[3];
        options[0] = new SelectItem(ResourceBundle.getBundle("/Bundle").getString("ROL_ADMIN"), "TECNICO GACI");  
        options[1] = new SelectItem(ResourceBundle.getBundle("/Bundle").getString("ROL_ESTRATEGICO"), "ADMINISTRADOR GARANTIA");
        options[2] = new SelectItem(ResourceBundle.getBundle("/Bundle").getString("ROL_TACTICO"), "ARCHIVO");
        return options;
    }
    

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.jpaUsuario.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUser());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
            }
        }
    }
}
