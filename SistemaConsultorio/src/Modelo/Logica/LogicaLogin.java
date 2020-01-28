package Modelo.Logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordinadorLogin;
import Modelo.Coordinador.CoordinadorSolicitud;
import Modelo.DAO.UsuarioDAO;
import Modelo.VO.UsuarioVO;
import Vista.ComponentCustomization;

public class LogicaLogin {
private CoordinadorLogin coordLogin;
private ArrayList<UsuarioVO> listaUsuarios;
private UsuarioDAO consultaUsuario;
private ComponentCustomization custom = new ComponentCustomization();
public void setCoordinador(CoordinadorLogin coordLogin) {
	this.coordLogin = coordLogin;
}

public boolean validarUsuarioID(UsuarioVO user) throws SQLException {
	boolean usuarioActivo = false;
	consultaUsuario = new UsuarioDAO();
	listaUsuarios = consultaUsuario.listausuario();
	 for (int i = 0; i < listaUsuarios.size(); i++) {
         
         if(listaUsuarios.get(i).getNombre().compareTo(user.getNombre()) == 0 && 
             listaUsuarios.get(i).getPassword().compareTo(user.getPassword()) == 0){
             user = listaUsuarios.get(i);
             usuarioActivo = true;
             custom.msg("Acceso Garantizado \n Bienvenido " + user.getNombre(), 4);
            break;  
         } }
     if(usuarioActivo == false){
         user = null;
         custom.msg("Usuario o contraseña incorrectos" , 6);
     }
   return usuarioActivo;
}
public UsuarioVO validarUserActivo(String user) {
	consultaUsuario = new UsuarioDAO();
	if(consultaUsuario.buscarUsuario(user) == null ) {
		custom.msg("Usuario", 2);
		return null;
	}else {	
	return consultaUsuario.buscarUsuario(user);
}	
}
}

