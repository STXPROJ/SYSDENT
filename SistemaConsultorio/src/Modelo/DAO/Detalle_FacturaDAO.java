package Modelo.DAO;
import Modelo.VO.FacturaVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.TipoServicioVO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import Conexion.Conexion;
import Modelo.VO.Detalle_FacturaVO;
import Modelo.VO.Detalle_FacturaVO;

public class Detalle_FacturaDAO {
	private TipoServicioVO tipoServicio;
	private CallableStatement cs;
	private CRUD_DAO selectTop;
	/* Registrar Detalle*/
	public void RegistrarDetalleFactura(Detalle_FacturaVO detalle) {
		selectTop = new CRUD_DAO();
	try {
		// Preparar el procedimiento antes de ejecutar
		    cs = Conexion.getConnection().prepareCall("Call insertDetalle_Factura"
		    		+ "(?,?,?,?)");
		    // Ingresar Parametros
		    cs.setInt(1, selectTop.selectTopID("Factura"));
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
	/* Lista de Detalle Factura*/
	public ArrayList<Detalle_FacturaVO> listaDetalle(int id) throws SQLException{
		ArrayList<Detalle_FacturaVO> detalles = new ArrayList<Detalle_FacturaVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("Call selectDetalleFacturaServicio(?)");
	    // Ingresar Parametros
	    cs.setInt(1, id);
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
		while(rs.next()) {
			// Datos Tipo Servicio
			tipoServicio = new TipoServicioVO();
			tipoServicio.setIdTipo(rs.getInt(5));
			tipoServicio.setNombre(rs.getString(6));
			//Datos del Servicio
			ServicioVO servicio = new ServicioVO();
			servicio.setIdServicio(rs.getInt(2));
			servicio.setTipoServicio(tipoServicio);
			servicio.setNombre(rs.getString(3));
			servicio.setDescripcion(rs.getString(4));
			servicio.setPrecio(rs.getInt(7));
			servicio.setTipoServicio(tipoServicio);
			//Datos del Detalle
			Detalle_FacturaVO detalleFactura = new Detalle_FacturaVO();	
			detalleFactura.setIdFactura(rs.getInt(1));
			detalleFactura.setServicio(servicio);
			detalleFactura.setCantidad(rs.getInt(8));
			detalles.add(detalleFactura);
		}
		rs.close();
		cs.close();
	    return detalles;
	}
}
