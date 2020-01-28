package Vista;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Modelo.Coordinador.CoordCita;
import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaCita;
import Modelo.Logica.ValidationClass;
import Modelo.VO.CargoVO;
import Modelo.VO.CitasVO;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.PagoFacturaVO;
import Modelo.VO.RolVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.SexoVO;
import Modelo.VO.SolicitudProtesisVO;
import Modelo.VO.TipoServicioVO;
import Modelo.VO.UsuarioVO;

public class Listados extends JPanel {
private JPanel pnl;
private JTable tablaListado = new JTable();
private ComponentCustomization custom = new ComponentCustomization();
private ValidationClass validation = new ValidationClass();
private String form;
private String[] tableName;
private String[] columnas;
private CoordinadorCRUD coordCrud;
private LogicaCRUD logicaCrud;
private SexoVO sexo;
private CargoVO cargo;
private EstadoCivilVO estado;
private CitasVO cita;
private ArrayList<CitasVO> listaCitas;
private ArrayList<PacienteVO> listaPaciente;
private ArrayList<EmpleadoVO> listaEmpleado;
private ArrayList<UsuarioVO> listaUsuario;
private ArrayList<LaboratorioVO> listaLaboratorio;
private ArrayList<ServicioVO> listaServicio;
private ArrayList<DiagnosticoVO> listaDiagnostico;
private ArrayList<SolicitudProtesisVO> listaSolicitud;
private ArrayList<FacturaVO> listaFactura;
private ArrayList<PagoFacturaVO> listaPagos;
private EmpleadoVO empleado;
private LaboratorioVO laboratorio;
private PacienteVO paciente;
private RolVO rol;
private TipoServicioVO tipoServicio;
private ServicioVO servicio;
private UsuarioVO usuario;
private DiagnosticoVO diagnostico;
private SolicitudProtesisVO solicitud;
private FacturaVO factura;
private PagoFacturaVO pago;
private ModCitaGUI modGuiCita;
private ModPacienteGUI modPaciente;
private ModEmpleadoGUI modEmppleado;
private ModUsuarioGUI modUsuario;
private ModLaboratorioGUI modLaboratorio;
private ModServicioGUI modServicio;
private ModDiagnosticoGUI modDiagnostico;
private ModSolicitudGUI modSolicitud;
private ModFacturaGUI modFactura;
private ModPagoGUI modPago;

	public Listados() throws FontFormatException, IOException, ParseException, SQLException {
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
		// Iniciar Coordinador Logica
		initCoordLogic();
		// Iniciar componenetes
		initComponent();
		// Iniciar Acciones
		initActionButton();
    
	}
	
	private void initCoordLogic() throws SQLException {
		/* Set Logica y Coordinador */
		coordCrud = new CoordinadorCRUD();
		logicaCrud = new LogicaCRUD();
		coordCrud.setLogica(logicaCrud);
		logicaCrud.setCoordinador(coordCrud);
		}
	private void initComponent() throws FontFormatException, IOException, ParseException {
/* Creacion TextFields y ComboBOx */
		// CheckBoxk autoBusqueda
		ckAuto = new JCheckBox();
		ckAuto.setText("Auto-Busqueda");
		custom.ck(ckAuto,1);
		ckAuto.setBounds(0,44,230,35);
		// TextField de buscar
		txtSearch = new JFormattedTextField();
		txtSearch.setBounds(20, 95, 215, 53);
		custom.txt(txtSearch,1,"Buscar Registro/s");
		// Label Background
		background = new JLabel();
		background.setBounds(0, -4, 1150, 48);
		background.setBackground(new Color(32,35,64));
		background.setOpaque(true);
		/* Creacion de ComboBox y Button*/
		// Combobox
		cbbTipo= new JComboBox();
		custom.cbb(cbbTipo, "Tipo",1);
		cbbTipo.setBounds(240,95,220,53);
		
		/* Creacion de Botones */
		// Boton Buscar Registro
		btnSearch = new JButton();
		btnSearch.setBounds(465, 85, 125, 74);
		custom.button(btnSearch, "Buscar", "\uf002", "solid",5);
				// Boton Actualizar	
		btnClean = new JButton("");
		btnClean.setBounds(600, 85, 160, 74);
		custom.button(btnClean,"Refrescar", "\uf51a", "solid", 4);
		// Boton Borrar
		btnDelete = new JButton("");
		btnDelete.setBounds(955, 85, 180, 74);
		custom.button(btnDelete, "Eliminar","\uf05e", "solid", 6);
		// Boton Update
		btnUpdate = new JButton("");
		btnUpdate.setBounds(765, 85, 180, 74);
		custom.button(btnUpdate, "Actualizar","\uf51a", "solid", 6);
		// Boton Update
		btnClose = new JButton("");
		btnClose.setBounds(1092, -4, 54, 45);
		custom.button(btnClose, "","\uf410", "solid", 7);
		btnClose.setBorder(null);
		/* Crear Tabla Generica */
		panelTabla = new JScrollPane(tablaListado);
		panelTabla.setBounds(2,175,1145,500);

		/* Agregar componentes */
		add(ckAuto);
		add(cbbTipo);
		add(btnSearch);
		add(btnClean);
		add(btnUpdate);
		add(btnDelete);
		add(btnClose);
		add(panelTabla);
		add(txtSearch);
		add(background);
	}
	private void initActionButton() {
	
		/* Accion de Botones */	
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateTable(tableName[0]);
				} catch (FontFormatException | IOException | ParseException | SQLException e1) {
					e1.printStackTrace();
				}
				}});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					int row = tablaListado.getSelectedRow();
						if (row!= -1){
					int id =  (int) tablaListado.getValueAt(row, 0);
					if(coordCrud.delete(tableName, id) == true) {
					DefaultTableModel model =  (DefaultTableModel)tablaListado.getModel();
					model.removeRow(row);}}
						else {
						custom.msg("Seleccione un registro de la tabla",6);
					}	}});
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					closePanel(tableName[0]);
				} catch (FontFormatException | IOException | ParseException | SQLException e) {
					e.printStackTrace();
				}}});
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});
		btnClean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			refresh();
			}
		});
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				autoBusqueda();
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				autoBusqueda();
			}
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				autoBusqueda();
			}
		});}
		
	private void refresh() {
		sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tablaListado.getModel())); 
		txtSearch.setText("");
		cbbTipo.setSelectedIndex(-1);
		tablaListado.setRowSorter(sorter);
		sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();

	}
	
	private void autoBusqueda() {
		if (ckAuto.isSelected() == true) {
			filtrar();}

	}
	private void filtrar() {
		sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tablaListado.getModel())); 
	    if (cbbTipo.getSelectedIndex() > 0){
	    	sorter.setRowFilter(RowFilter.regexFilter(txtSearch.getText(), cbbTipo.getSelectedIndex()));
	    }else {
	    	sorter.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
	    }
	    tablaListado.setRowSorter(sorter);
	}
	private void closePanel(String table) throws FontFormatException, IOException, ParseException, SQLException {
		switch(table) {
		case "AgendarCita":
			pnl = new CitaGUI();
			break;
		case "Paciente":
			pnl = new PacienteGUI();
			break;
		case "Empleado":
			pnl = new EmpleadoGUI();
			break;
		case "Usuario":
			pnl = new UsuarioGUI();
			break;
		case "Laboratorio":
			pnl = new LaboratorioGUI();
			break;
		case "Servicio":
			pnl = new ServicioGUI();
			break;
		case "Diagnostico":
			pnl = new DiagnosticoGUI();
			break;
		case "orden_protesis":
			pnl = new SolicitudGUI();
			break;
		case "Factura":
			pnl = new FacturaGUI();
			break;
		case "PagoFactura":
			pnl = new PagosGUI();
			break;
		}removeAll();
			revalidate();
			repaint();
			add(pnl);	
		}
	
private void updateTable(String table) throws FontFormatException, IOException, ParseException, SQLException {
		int id = 0;
		int row = tablaListado.getSelectedRow();
		if (row!= -1){
		id = ((int) tablaListado.getValueAt(row, 0) -1);
		switch(table) {
		case "AgendarCita":
			for(int i=0;i<listaCitas.size();i++) {
				if(id+1 == listaCitas.get(i).getIdCita()) {	
			cita = new CitasVO();	
			paciente = new PacienteVO();
			cita.setIdCita(listaCitas.get(i).getIdCita());
			cita.setFechaProgramada(listaCitas.get(i).getFechaProgramada());
			paciente = listaCitas.get(i).getPaciente();
			cita.setPaciente(paciente);
			cita.setFechaCita(listaCitas.get(i).getFechaProgramada());
			cita.setHoraCita(listaCitas.get(i).getHoraCita());
			cita.setEstado(listaCitas.get(i).getEstado());
			empleado = listaCitas.get(i).getEmpleado();
			cita.setEmpleado(empleado);
				}}
			goCita(cita);
			break;
		case "Paciente":
			for(int i=0;i<listaPaciente.size();i++) {
				if(id+1 == listaPaciente.get(i).getIdPaciente()) {	
			paciente = new PacienteVO();
			paciente.setIdPaciente(listaPaciente.get(i).getIdPaciente());
			paciente.setNombre(listaPaciente.get(i).getNombre());
			paciente.setEstadoCivil(listaPaciente.get(i).getEstadoCivil());
			paciente.setFechaIngreso(listaPaciente.get(i).getFechaIngreso());
			paciente.setFechaNac(listaPaciente.get(i).getFechaNac());
			paciente.setOcupacion(listaPaciente.get(i).getOcupacion());
			paciente.setSexo(listaPaciente.get(i).getSexo());
			paciente.setTelefono(listaPaciente.get(id).getTelefono());
				}}
			goPaciente(paciente);
			break;
		case "Empleado":
			for(int i=0;i<listaEmpleado.size();i++) {
				if(id+1 == listaEmpleado.get(i).getIdEmpleado()) {	
			empleado = new EmpleadoVO();
			empleado.setIdEmpleado(listaEmpleado.get(i).getIdEmpleado());
			empleado.setApellido(listaEmpleado.get(i).getApellido());
			empleado.setCargo(listaEmpleado.get(i).getCargo());
			empleado.setCedula(listaEmpleado.get(i).getCedula());
			empleado.setCorreo(listaEmpleado.get(i).getCorreo());
			empleado.setDireccion(listaEmpleado.get(i).getDireccion());
			empleado.setFechaIngreso(listaEmpleado.get(i).getFechaIngreso());
			empleado.setNombre(listaEmpleado.get(i).getNombre());
			empleado.setSexo(listaEmpleado.get(i).getSexo());
			empleado.setSueldo(listaEmpleado.get(i).getSueldo());
			empleado.setTelefono(listaEmpleado.get(i).getTelefono());
				}}
			goEmpleado(empleado);
			break;
		case "Usuario":
			for(int i=0;i<listaUsuario.size();i++) {
				if(id+1 == listaUsuario.get(i).getIdUsuario()) {	
			usuario = new UsuarioVO();
			usuario.setEmpleado(listaUsuario.get(i).getEmpleado());
			usuario.setIdUsuario(listaUsuario.get(i).getIdUsuario());
			usuario.setNombre(listaUsuario.get(i).getNombre());
			usuario.setPassword(listaUsuario.get(i).getPassword());
			usuario.setRol(listaUsuario.get(i).getRol());
				}}
			goUsuario(usuario);
			break;
		case "Laboratorio":
			for(int i=0;i<listaLaboratorio.size();i++) {
				if(id +1== listaLaboratorio.get(i).getIdLaboratorio()) {	
			laboratorio = new LaboratorioVO();
			laboratorio.setContacto(listaLaboratorio.get(i).getContacto());
			laboratorio.setCorreo(listaLaboratorio.get(i).getCorreo());
			laboratorio.setDescripcion(listaLaboratorio.get(i).getDescripcion());
			laboratorio.setDireccion(listaLaboratorio.get(i).getDireccion());
			laboratorio.setIdLaboratorio(listaLaboratorio.get(i).getIdLaboratorio());
			laboratorio.setNombre(listaLaboratorio.get(i).getNombre());
			laboratorio.setRnc(listaLaboratorio.get(i).getRnc());
			laboratorio.setTelefono(listaLaboratorio.get(i).getTelefono());
				}}
			goLaboratorio(laboratorio);
			break;
		case "Servicio":
			for(int i=0;i<listaServicio.size();i++) {
				if(id +1== listaServicio.get(i).getIdServicio()) {	
			servicio = new ServicioVO();
			servicio.setDescripcion(listaServicio.get(i).getDescripcion());
			servicio.setIdServicio(listaServicio.get(i).getIdServicio());
			servicio.setNombre(listaServicio.get(i).getNombre());
			servicio.setPrecio(listaServicio.get(i).getPrecio());
			servicio.setTipoServicio(listaServicio.get(i).getTipoServicio());
				}}
			goServicio(servicio);
			break;
		case "Diagnostico":
			diagnostico = new DiagnosticoVO();
			for(int i=0;i<listaDiagnostico.size();i++) {
				if(id + 1 == listaDiagnostico.get(i).getIdDiagnostico()) {	
			diagnostico.setIdDiagnostico(listaDiagnostico.get(i).getIdDiagnostico());
			diagnostico.setPaciente(listaDiagnostico.get(i).getPaciente());
			diagnostico.setFechaDiagnostico(listaDiagnostico.get(i).getFechaDiagnostico());
			}}
			goDiagnostico(diagnostico);
			break;
		case "orden_protesis":
			for(int i=0;i<listaSolicitud.size();i++) {
			if(id +1== listaSolicitud.get(i).getIdSolicitud()) {	
			solicitud = new SolicitudProtesisVO();
			solicitud.setEstado(listaSolicitud.get(i).isEstado());
			solicitud.setFecha(listaSolicitud.get(i).getFecha());
			solicitud.setIdSolicitud(listaSolicitud.get(i).getIdSolicitud());
			solicitud.setLaboratorio(listaSolicitud.get(i).getLaboratorio());
			solicitud.setPaciente(listaSolicitud.get(i).getPaciente());
			}}
			goSolicitud(solicitud);
			break;
		case "Factura":
			for(int i=0;i<listaFactura.size();i++) {
			if(id +1== listaFactura.get(i).getIdFactura()) {	
			factura = new FacturaVO();
			factura.setUsuario(listaFactura.get(i).getUsuario());
			factura.setEstado(listaFactura.get(i).isEstado());
			factura.setFecha(listaFactura.get(i).getFecha());
			factura.setIdFactura(listaFactura.get(i).getIdFactura());
			factura.setPaciente(listaFactura.get(i).getPaciente());
			factura.setDescuento(listaFactura.get(i).getDescuento());
			}}
			goFactura(factura);
			break;
		case "PagoFactura":
			for(int i=0;i<listaPagos.size();i++) {
			if(id +1== listaPagos.get(i).getIdPago()) {	
			pago = new PagoFacturaVO();
			pago.setFactura(listaPagos.get(i).getFactura());
			pago.setFecha(listaPagos.get(i).getFecha());
			pago.setIdPago(listaPagos.get(i).getIdPago());
			pago.setTotal(listaPagos.get(i).getTotal());
			}}
			goPagos(pago);
			break;}
		}else {
			custom.msg("Seleccione un registro de la tabla",6);
		}
	}
	public String title() {
		String title = "Listado " + form;
		return title;
	}
	private void goCita(CitasVO cita) throws FontFormatException, IOException, ParseException, SQLException {
			modGuiCita = new ModCitaGUI();
			removeAll();
			add(modGuiCita.getCita(cita));
			revalidate();
			repaint();
		}
	private void goPaciente(PacienteVO paciente) throws FontFormatException, IOException, ParseException, SQLException {
			modPaciente = new ModPacienteGUI();
			removeAll();
			add(modPaciente.getPaciente(paciente));
			revalidate();
			repaint();
			}
	private void goEmpleado(EmpleadoVO empleado) throws FontFormatException, IOException, ParseException, SQLException {
		modEmppleado = new ModEmpleadoGUI();
			removeAll();
			add(modEmppleado.getEmpleado(empleado));
			revalidate();
			repaint();
	}
	private void goUsuario(UsuarioVO usuario) throws FontFormatException, IOException, ParseException, SQLException {
		modUsuario = new ModUsuarioGUI();
			removeAll();
			add(modUsuario.getUsuario(usuario));
			revalidate();
			repaint();
	}
	private void goLaboratorio(LaboratorioVO laboratorio) throws ParseException, FontFormatException, IOException, SQLException {
		modLaboratorio = new ModLaboratorioGUI();
		removeAll();
		add(modLaboratorio.getLab(laboratorio));
		revalidate();
		repaint();
	}
	private void goServicio(ServicioVO servicio) throws ParseException, FontFormatException, IOException, SQLException {
		modServicio = new ModServicioGUI();
		removeAll();
		add(modServicio.getServicio(servicio));
		revalidate();
		repaint();
	}
	private void goDiagnostico(DiagnosticoVO diagnostico) throws FontFormatException, IOException, ParseException, SQLException {
		modDiagnostico = new ModDiagnosticoGUI();
		removeAll();
		add(modDiagnostico.getDiagnostico(diagnostico));
		revalidate();
		repaint();
	}
	private void goSolicitud(SolicitudProtesisVO solicitud) throws FontFormatException, IOException, ParseException, SQLException {
		modSolicitud = new ModSolicitudGUI();
		removeAll();
		add(modSolicitud.getSolicitud(solicitud));
		revalidate();
		repaint();
	}
	private void goFactura(FacturaVO factura) throws FontFormatException, IOException, ParseException, SQLException {
		modFactura = new ModFacturaGUI();
		removeAll();
		add(modFactura.getFactura(factura));
		revalidate();
		repaint();
	}
	private void goPagos(PagoFacturaVO pago) throws FontFormatException, IOException, ParseException, SQLException {
		modPago = new ModPagoGUI();
		removeAll();
		add(modPago.getPago(pago));
		revalidate();
		repaint();
	}
	
	public void setTableCita(ArrayList<CitasVO> listado,String[]columns,String form,String tablename[]) throws SQLException{
		if (listado!=null) {
		Object[][] fila = new Object[listado.size()][7];
        for (int i = 0; i < listado.size(); i++) {
        	fila[i][0] = listado.get(i).getIdCita();
        	fila[i][1] = listado.get(i).getFechaCita();
            fila[i][2] = listado.get(i).getFechaProgramada();
            fila[i][3] = listado.get(i).getHoraCita();
            fila[i][4] = listado.get(i).getPaciente().getNombre();
            fila[i][5] = listado.get(i).getEstado().getNombre();
            fila[i][6] = listado.get(i).getEmpleado().getNombre();
        }custom.table(tablaListado, fila, columns);
        filterCbb(cbbTipo);     }
        this.listaCitas = listado;
        this.tableName = tablename;
        this.columnas = columns;

		
	}
	public void setTableEmpleado(ArrayList<EmpleadoVO> listado,String[]columns,String form,String[] tablename) throws SQLException{
		if (listado!=null) {
		Object[][] fila = new Object[listado.size()][9];
        for (int i = 0; i < listado.size(); i++) {
            fila[i][0] = listado.get(i).getIdEmpleado();
            fila[i][1] = listado.get(i).getNombre();
            fila[i][2] = listado.get(i).getApellido();
            fila[i][3] = listado.get(i).getSexo().getGenero();
            fila[i][4] = listado.get(i).getFechaIngreso();
            fila[i][5] = listado.get(i).getCedula();
            fila[i][6] = listado.get(i).getCargo().getNombre();
            fila[i][7] = listado.get(i).getSueldo();
            fila[i][8] = listado.get(i).getTelefono();
        }custom.table(tablaListado, fila, columns);	
        filterCbb(cbbTipo);}
        this.tableName = tablename;
        this.listaEmpleado = listado;
        this.columnas = columns;
       }
	
	public void setTableLab(ArrayList<LaboratorioVO> listado,String[]columns,String form,String[] tablename) throws SQLException{
		if (listado!=null) {
		Object[][] fila = new Object[listado.size()][7];
        for (int i = 0; i < listado.size(); i++) {
            fila[i][0] = listado.get(i).getIdLaboratorio();
            fila[i][1] = listado.get(i).getNombre();
            fila[i][2] = listado.get(i).getDescripcion();
            fila[i][3] = listado.get(i).getContacto();
            fila[i][4] = listado.get(i).getTelefono();
            fila[i][5] = listado.get(i).getCorreo();
            fila[i][6] = listado.get(i).getRnc();       
        }custom.table(tablaListado, fila, columns);
        filterCbb(cbbTipo);}
        this.tableName = tablename;
        this.listaLaboratorio = listado;
        this.columnas = columns;
       
	}	
	public void setTableServ(ArrayList<ServicioVO> listado,String[]columns,String form,String[] tablename) throws SQLException{
		if (listado!=null) {
		Object[][] fila = new Object[listado.size()][5];
        for (int i = 0; i < listado.size(); i++) {
            fila[i][0] = listado.get(i).getIdServicio();
            fila[i][1] = listado.get(i).getNombre();
            fila[i][2] = listado.get(i).getDescripcion();
            fila[i][3] = listado.get(i).getTipoServicio().getNombre();
            fila[i][4] = listado.get(i).getPrecio();         
        }custom.table(tablaListado, fila, columns);	
        filterCbb(cbbTipo);}
        this.tableName = tablename;
        this.listaServicio = listado;
        this.columnas = columns;
       
	}
	public void setTableUser(ArrayList<UsuarioVO> listado,String[]columns,String form,String[]tablename) throws SQLException{
		if (listado!=null) {
		Object[][] fila = new Object[listado.size()][4];
        for (int i = 0; i < listado.size(); i++) {
        	fila[i][0] = listado.get(i).getIdUsuario();
            fila[i][1] = listado.get(i).getEmpleado().getNombre();
            fila[i][2] = listado.get(i).getNombre();
            fila[i][3] = listado.get(i).getRol().getNombre();
        }custom.table(tablaListado, fila, columns);
        filterCbb(cbbTipo);}
        this.tableName = tablename;
        this.listaUsuario = listado;
        this.columnas = columns;

	}
	public void setTablePaciente(ArrayList<PacienteVO> listado,String[]columns,String form,String[]tablename) throws SQLException{
		if (listado!=null) {
		Object[][] fila = new Object[listado.size()][9];
        for (int i = 0; i < listado.size(); i++) {
        	fila[i][0] = listado.get(i).getIdPaciente();
            fila[i][1] = listado.get(i).getNombre();
            fila[i][2] = listado.get(i).getSexo().getGenero();
            fila[i][3] = listado.get(i).getFechaNac();
            fila[i][4] = listado.get(i).getFechaIngreso();
            fila[i][5] = listado.get(i).getEstadoCivil().getNombre();
            fila[i][6] = listado.get(i).getOcupacion();
            fila[i][7] = listado.get(i).getTelefono();
            if (listado.get(i).getBalance() < 0) {
            	fila[i][8] = "(" +listado.get(i).getBalance() * -1 + ")";
            }else {
            	 fila[i][8] = listado.get(i).getBalance();	
            }
           
        }custom.table(tablaListado, fila, columns);	
        filterCbb(cbbTipo);}
        this.tableName = tablename;
        this.listaPaciente = listado;
        this.columnas = columns;
      
	}
	public void setTableDiagnostico(ArrayList<DiagnosticoVO> listadoDiagnostico,String[]columns,String form,String tablename[]) throws SQLException{
		if (listadoDiagnostico!=null) {
		Object[][] fila = new Object[listadoDiagnostico.size()][4];
        for (int i = 0; i < listadoDiagnostico.size(); i++) {
        	fila[i][0] = listadoDiagnostico.get(i).getIdDiagnostico();
        	fila[i][1] = listadoDiagnostico.get(i).getPaciente().getNombre();
        	fila[i][2] = listadoDiagnostico.get(i).getUsuario().getEmpleado().getNombre();	
        	fila[i][3] = listadoDiagnostico.get(i).getFechaDiagnostico();
        }custom.table(tablaListado, fila, columns);
        filterCbb(cbbTipo);}
        this.tableName = tablename;
        this.listaDiagnostico = listadoDiagnostico;
        this.columnas = columns;
      
	}
	public void setTableSolicitud(ArrayList<SolicitudProtesisVO> listadoSolicitud,String[]columns,String form,String tablename[]) throws SQLException{
		if (listadoSolicitud!=null) {
		Object[][] fila = new Object[listadoSolicitud.size()][6];
        for (int i = 0; i < listadoSolicitud.size(); i++) {
        	fila[i][0] = listadoSolicitud.get(i).getIdSolicitud();
        	fila[i][1] = listadoSolicitud.get(i).getLaboratorio().getNombre();
        	fila[i][2] = listadoSolicitud.get(i).getPaciente().getNombre();
        	if (listadoSolicitud.get(i).isEstado() == true) {
        		fila[i][3] = "Pago";
        	}else {
        		fila[i][3] = "Pendiente";
        	}
        	fila[i][4] = listadoSolicitud.get(i).getFecha();
        }custom.table(tablaListado, fila, columns);
        filterCbb(cbbTipo);}
        this.tableName = tablename;
        this.listaSolicitud = listadoSolicitud;
        this.columnas = columns;
       
	}
	public void setTableFactura(ArrayList<FacturaVO> listadoFactura,String[]columns,String form,String tablename[]) throws SQLException{
		if (listadoFactura!=null) {
		Object[][] fila = new Object[listadoFactura.size()][8];
        for (int i = 0; i < listadoFactura.size(); i++) {
        	fila[i][0] = listadoFactura.get(i).getIdFactura();
        	fila[i][1] = listadoFactura.get(i).getPaciente().getNombre();
        	fila[i][2] = listadoFactura.get(i).getFecha();
        	if (listadoFactura.get(i).isEstado() == true) {
        		fila[i][3] = "Pago";
        	}else {
        		fila[i][3] = "Pendiente";
        	}
        	fila[i][4] = listadoFactura.get(i).getDescuento() + "%";
        	fila[i][5] = listadoFactura.get(i).getTotal();
        	fila[i][6] = listadoFactura.get(i).getUsuario().getNombre();
        }custom.table(tablaListado, fila, columns);	
        filterCbb(cbbTipo);}
        this.tableName = tablename;
        this.listaFactura = listadoFactura;
        this.columnas = columns;
      
	}
	public void setTablePagos(ArrayList<PagoFacturaVO> listadoPago,String[]columns,String form,String tablename[]) throws SQLException{
		if (listadoPago!=null) {
		Object[][] fila = new Object[listadoPago.size()][8];
        for (int i = 0; i < listadoPago.size(); i++) {
        	fila[i][0] = listadoPago.get(i).getIdPago();
        	fila[i][1] = listadoPago.get(i).getFactura().getIdFactura();
        	fila[i][2] = listadoPago.get(i).getFactura().getPaciente().getNombre();
        	fila[i][3] = listadoPago.get(i).getFecha();
        	fila[i][4] = listadoPago.get(i).getTotal();
        }custom.table(tablaListado, fila, columns);
        filterCbb(cbbTipo);}	
        this.tableName = tablename;
        this.listaPagos = listadoPago;
        this.columnas = columns;
  
	}
	public JPanel getPanel() {
		return this;
}
	private void filterCbb(JComboBox cbb) throws SQLException {
		/* Llenar ComboBox Filtro*/
		 DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
	        // Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<columnas.length;i++) {
	            model.addElement(columnas[i]);
	        }
	        // setting model with new data
	        cbb.setModel(model);
	        cbb.setRenderer(new MyComboBox("Filtrar por")); 
      	cbb.setSelectedIndex(-1); 
        refresh();
	}
	/* Declarar componentes*/
	JCheckBox ckAuto;
	JFormattedTextField txtSearch;
	JComboBox cbbTipo;
	JLabel background;
	JButton btnSearch;
	JButton btnClean;
	JButton btnDelete;
	JButton btnUpdate;
	JButton btnClose;
	JScrollPane panelTabla;
	TableRowSorter<TableModel> sorter;
	List<RowSorter.SortKey> sortKeys;
}

