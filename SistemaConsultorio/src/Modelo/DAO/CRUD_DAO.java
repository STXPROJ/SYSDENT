package Modelo.DAO;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;
import Conexion.Conexion;
import Vista.ComponentCustomization;

public class CRUD_DAO {
	private String consulta;
	private java.sql.PreparedStatement ps;
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	public void DeleteFromTable(String[] table,int idData ) {
		try {	
			// Declarar la consulta estructurada
			consulta = "Update " + table[0] +" set inactive = 1 where id" + table[1] + " = ?";
			ps = Conexion.getConnection().prepareStatement(consulta);
			// Dar valor al id de referencia
			ps.setInt(1,idData);
			// Ejecutar Consulta
			ps.executeUpdate();
			ps.close();
			custom.msg("Registro de la tabla " + table[0] + " eliminado satisfactoriamente", 4);  }
			catch (SQLException e) {
		        e.printStackTrace();
			}}
	public int selectTopID(String table) {
		int idNew=0;
		try {	
			// Declarar la consulta estructurada
			cs = Conexion.getConnection().prepareCall("{? = call topID"+table+"()}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			idNew = cs.getInt(1);
			cs.close();}
			catch (SQLException e) {
		        e.printStackTrace();
			}
		return idNew;
	}
	public String filterTable(String table) throws SQLException{
		String query = null;
		try {
		// Preparar el procedimiento antes de ejecutar
		cs = Conexion.getConnection().prepareCall("{? = call filterTable('"+ table + "')}");
		cs.registerOutParameter(1, Types.CHAR);
		cs.execute();
		query = cs.getString(1);
		cs.close();}
	catch (SQLException e) {
        e.printStackTrace();
	}
return query;
}
	public void UpdateFromTable(String table,String consulta,String[] column,String[] data,String idName,int idData,String msg) throws SQLException {
			ps = Conexion.getConnection().prepareStatement(consulta);
	}
}
