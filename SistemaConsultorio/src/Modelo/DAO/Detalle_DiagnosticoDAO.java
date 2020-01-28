package Modelo.DAO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.TipoServicioVO;
import Vista.ComponentCustomization;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import Conexion.Conexion;
import Modelo.VO.Detalle_DiagnosticoVO;

public class Detalle_DiagnosticoDAO {
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	private CRUD_DAO selectID;
	private TipoServicioVO tipoServicio;
	public void RegistrarDetalleDiagnostico(Detalle_DiagnosticoVO detalle) {
		selectID = new CRUD_DAO();
		try {
			// Preparar el procedimiento antes de ejecutar
			     cs = Conexion.getConnection().prepareCall("Call insertDetalle_Diagnostico"
			    		+ "(?,?,?,?)");
			    // Ingresar Parametros
			    cs.setInt(1,selectID.selectTopID("Diagnostico"));
			    cs.setInt(2, detalle.getServicio().getIdServicio());
			    cs.setInt(3, detalle.getCantidad());
			    cs.setString(4, detalle.getServicio().getDescripcion());
		    // Ejecutar Procedimiento
			    cs.execute();
			    cs.close();}
			catch (SQLException e) {
		        e.printStackTrace();
		    }}
	public ArrayList<Detalle_DiagnosticoVO> listaDetalle(int id) throws SQLException{
		ArrayList<Detalle_DiagnosticoVO> detalles = new ArrayList<Detalle_DiagnosticoVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("Call selectDetalleDiagnosticoServicio(?)");
	    // Ingresar Parametros
	    cs.setInt(1, id);
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
		while(rs.next()) {
			// Datos Tipo Servicio
			tipoServicio = new TipoServicioVO();
			tipoServicio.setIdTipo(rs.getInt(6));
			tipoServicio.setNombre(rs.getString(7));
			//Datos del Servicio
			ServicioVO servicio = new ServicioVO();
			servicio.setIdServicio(rs.getInt(2));
			servicio.setNombre(rs.getString(3));
			servicio.setDescripcion(rs.getString(4));
			servicio.setTipoServicio(tipoServicio);
			servicio.setPrecio(rs.getInt(5));
			//Datos del Detalle
			Detalle_DiagnosticoVO detalleDiagnostico = new Detalle_DiagnosticoVO();	
			detalleDiagnostico.setIdDiagnostico(rs.getInt(1));
			detalleDiagnostico.setServicio(servicio);
			detalleDiagnostico.setCantidad(rs.getInt(8));
			detalles.add(detalleDiagnostico);
		}
		rs.close();
		cs.close();
	    return detalles;
	}
	
}
