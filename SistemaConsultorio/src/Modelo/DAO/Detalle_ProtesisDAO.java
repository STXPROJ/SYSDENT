package Modelo.DAO;
import Modelo.VO.SolicitudProtesisVO;
import Modelo.VO.TipoServicioVO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import Conexion.Conexion;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.ServicioVO;

public class Detalle_ProtesisDAO {
	private CallableStatement cs;
	private TipoServicioVO tipoServicio;
	private CRUD_DAO selectTop;
	public void RegistrarDetalleProtesis(Detalle_ProtesisVO detalle) {
	try {
		selectTop = new CRUD_DAO();
		// Preparar el procedimiento antes de ejecutar
		    CallableStatement cs = Conexion.getConnection().prepareCall("Call insertDetalle_Protesis"
		    		+ "(?,?,?,?)");
		    // Ingresar Parametros
		    cs.setInt(1, selectTop.selectTopID("Solicitud"));
		    cs.setInt(2, detalle.getServicio().getIdServicio());
		    cs.setInt(3, detalle.getCantidad());
		    cs.setString(4, detalle.getServicio().getDescripcion());
	    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    }
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	/* Lista de Detalle Protesis*/
	public ArrayList<Detalle_ProtesisVO> listaDetalle(int id) throws SQLException{
		ArrayList<Detalle_ProtesisVO> detalles = new ArrayList<Detalle_ProtesisVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("Call selectDetalleProtesisServicio(?)");
	    // Ingresar Parametros
	    cs.setInt(1, id);
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
		while(rs.next()) {
			// Datos Tipo Servicio
			tipoServicio = new TipoServicioVO();
			tipoServicio.setIdTipo(rs.getInt(3));
			tipoServicio.setNombre(rs.getString(4));
			//Datos del Servicio
			ServicioVO servicio = new ServicioVO();
			servicio.setIdServicio(rs.getInt(2));
			servicio.setTipoServicio(tipoServicio);
			servicio.setNombre(rs.getString(5));
			servicio.setDescripcion(rs.getString(6));
			//Datos del Detalle
			Detalle_ProtesisVO detalleProtesis = new Detalle_ProtesisVO();	
			detalleProtesis.setIdSolicitud(rs.getInt(1));
			detalleProtesis.setServicio(servicio);
			detalleProtesis.setCantidad(rs.getInt(8));
			detalles.add(detalleProtesis);
		}
		rs.close();
		cs.close();
	    return detalles;
	}
}
