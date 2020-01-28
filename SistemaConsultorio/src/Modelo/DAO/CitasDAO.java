package Modelo.DAO;
import java.security.cert.PKIXRevocationChecker.Option;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Conexion.Conexion;
import Modelo.VO.CitasVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCitaVO;
import Modelo.VO.PacienteVO;
import Vista.ComponentCustomization;
import Modelo.VO.CitasVO;
public class CitasDAO {
private CallableStatement cs;
private ComponentCustomization custom = new ComponentCustomization();
private CRUD_DAO consulta;
private java.sql.PreparedStatement ps;
public void AgendarCita(CitasVO cita) {
	try {
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call AgendaCita(?,?,?,?,?)");
		    // Ingresar Parametros
		    cs.setString(1, cita.getFechaProgramada());
		    cs.setString(2, cita.getHoraCita());
		    cs.setInt(3, cita.getPaciente().getIdPaciente());
		    cs.setInt(4, cita.getEstado().getIdEstado());
		    cs.setInt(5, cita.getEmpleado().getIdEmpleado());
	    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Cita Agendada con exito", 4);
	}
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	public ArrayList<CitasVO> filtroCita(String table,String field,String value) throws SQLException{
		ArrayList<CitasVO> citas = new ArrayList<CitasVO>();
		consulta = new CRUD_DAO();
		String query = consulta.filterTable(table);
		query = query + " " + field + " = "+ value;
		System.out.println(query);
		// Preparar el procedimiento antes de ejecutar
	    ps = Conexion.getConnection().prepareStatement(query);
    // Ejecutar Procedimiento
	    ResultSet rs = 	 ps.executeQuery();
	    while(rs.next()) {
	    	// Datos Paciente
	    	PacienteVO paciente = new PacienteVO();
	    	paciente.setIdPaciente(rs.getInt(3));
	    	paciente.setNombre(rs.getString(4));
	    	// Datos Empleado
	    	EmpleadoVO empleado = new EmpleadoVO();
	    	empleado.setIdEmpleado(rs.getInt(8));
	    	empleado.setNombre(rs.getString(9));
	    	// Datos Cita
	    	CitasVO cita = new CitasVO();
			cita.setIdCita(rs.getInt(1));
			cita.setFechaCita(rs.getString(2));
			cita.setPaciente(paciente);
			cita.setFechaProgramada(rs.getString(5));
			cita.setHoraCita(rs.getString(6));
			cita.setEstado(null);
			cita.setEmpleado(empleado);
			citas.add(cita);	
		}
	    rs.close();
		ps.close();
		
	    return citas;
	}
	public ArrayList<CitasVO> listaCitas() throws SQLException{
		ArrayList<CitasVO> citas = new ArrayList<CitasVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("Call selectAllCitas()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	// Datos Paciente
	    	PacienteVO paciente = new PacienteVO();
	    	paciente.setIdPaciente(rs.getInt(3));
	    	paciente.setNombre(rs.getString(4));
	    	// Datos Empleado
	    	EmpleadoVO empleado = new EmpleadoVO();
	    	empleado.setIdEmpleado(rs.getInt(9));
	    	empleado.setNombre(rs.getString(10));
	    	// Datos Estado Cita
	    	EstadoCitaVO estado = new EstadoCitaVO();
	    	estado.setIdEstado(rs.getInt(7));
	    	estado.setNombre(rs.getString(8));
	    	// Datos Cita
	    	CitasVO cita = new CitasVO();
			cita.setIdCita(rs.getInt(1));
			cita.setFechaCita(rs.getString(2));
			cita.setPaciente(paciente);
			cita.setFechaProgramada(rs.getString(5));
			cita.setHoraCita(rs.getString(6));
			cita.setEstado(estado);
			cita.setEmpleado(empleado);
			citas.add(cita);	
		}
	    rs.close();
		cs.close();
		
	    return citas;
	}

}

