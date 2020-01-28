package Vista;

import java.awt.Color;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Coordinador.CoordinadorSolicitud;
import Modelo.DAO.Detalle_ProtesisDAO;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaSolicitud;
import Modelo.Logica.ValidationClass;
import Modelo.VO.Detalle_FacturaVO;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.SolicitudProtesisVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.SolicitudProtesisVO;

public class SolicitudGUI extends JPanel {
	private static String[]COLUMNS = new String[] {"ID Solicitud","Laboratorio","Paciente","Estado","Fecha"};
private static String[] tableSolicitud = new String[] {"orden_protesis","solicitud"};
private JTable tablaServicio;
private ComponentCustomization custom = new ComponentCustomization();
private ValidationClass validation = new ValidationClass();
private ArrayList<ServicioVO> listaServicio;
private ArrayList<Detalle_ProtesisVO> listaDetalle;
private CoordinadorSolicitud coodSolicitud;
private LogicaSolicitud logicaSolicitud;
private PacienteVO paciente;
private LaboratorioVO laboratorio;
private SolicitudProtesisVO solicitud;
private Detalle_ProtesisVO detalleProtesis;
private ServicioVO servicio;
private Listados pnl;
private CoordinadorCRUD coordCrud;
private LogicaCRUD logicaCrud;
private BuscadorDialog buscadorDialog;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public SolicitudGUI() throws FontFormatException, IOException, ParseException, SQLException {
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
	// Iniciar Logica Coordinador y Listados
		initCoordLogic();
		// Iniciar componentes
		initComponent();
		// Iniciar Acciones
		initActionButton();

	}
	
	private void initCoordLogic() throws SQLException {
		/* Set Logica y Coordinador */
		coodSolicitud = new CoordinadorSolicitud();
		logicaSolicitud = new LogicaSolicitud();
		coodSolicitud.setLogica(logicaSolicitud);
		logicaSolicitud.setCoordinador(coodSolicitud);
		
		coordCrud = new CoordinadorCRUD();
		logicaCrud = new LogicaCRUD();
		coordCrud.setLogica(logicaCrud);
		logicaCrud.setCoordinador(coordCrud);
		/* Objetos y listas */
		listaDetalle = new ArrayList<Detalle_ProtesisVO>();
		listaServicio = coodSolicitud.listaServicio();
		paciente = new PacienteVO();
		laboratorio = new LaboratorioVO();
		// Tabla Servicio
		tablaServicio = new JTable() {
			public boolean isCellEditable(int row,int column) {
				if(column ==3 ||column == 2 || column == 5) { 
				return true;}
				else
				return false;
				}};
	}
	private void initComponent() throws FontFormatException, IOException, ParseException {
		// Datos Requeridos
		// ID Paciente
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,83,25,25);
		add(required);
		// ID Labo
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(577,83,25,25);
		add(required);
		// Servicio
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,223,25,25);
		add(required);
		// Creacion de Label NO. Solicitud
		lblNoSolicitud = new JLabel();
		lblNoSolicitud.setBounds(38, 5, 564, 50);
		custom.label(lblNoSolicitud, "No. Solicitud.: " + String.format("%010d", coordCrud.selectTopID(tableSolicitud[1])+1),3);
		// Creacion de Label idPaciente
		lblidPaciente = new JLabel("");
		lblidPaciente.setBounds(38, 70, 564, 39);
		custom.label(lblidPaciente, "ID Paciente.:",1);	
		// Label  Nombre Paciente
		lblPaciente = new JLabel("");
		lblPaciente.setBounds(38, 125, 564, 39);
		custom.label(lblPaciente, "Nombre del Paciente:",1);
		// Label Nombre
		lblNombrePac = new JLabel("");
		lblNombrePac.setBounds(38, 165, 750, 39);
		custom.label(lblNombrePac, "",2);
		lblNombrePac.setSize(lblNombrePac.getPreferredSize());
		// Creacion de Label idLab
		lblidLab = new JLabel("");
		lblidLab.setBounds(590, 70, 564, 39);
		custom.label(lblidLab, "ID Laboratorio.:",1);
		// Label  Nombre Lab
		lblLab = new JLabel("");
		lblLab.setBounds(590, 125, 564, 39);
		custom.label(lblLab, "Nombre del Laboratorio:",1);
		// Label Nombre
		lblNombreLab = new JLabel("");
		lblNombreLab.setBounds(590, 165, 750, 39);
		custom.label(lblNombreLab, "",2);
		lblNombreLab.setSize(lblNombreLab.getPreferredSize());
		// Label Servicio
		lblServicio = new JLabel();
		lblServicio.setBounds(38, 210, 300, 39);
		custom.label(lblServicio, "Servicio.:",1);
		// Label Cantidad
		lblCantidad = new JLabel();
		lblCantidad.setBounds(400, 210, 215, 53);
		custom.label(lblCantidad, "Cantidad.:", 1);
		
		/* Creacion TextField y ComboBOx */
		txtidPaciente = new JFormattedTextField();
		txtidPaciente.setBounds(205, 65, 215, 45);
		custom.txt(txtidPaciente,1,"Ingrese el ID del Paciente");
		// TextField de idLab
		txtidLabd = new JFormattedTextField();
		txtidLabd.setBounds(767, 70, 195, 45);
		custom.txt(txtidLabd,1,"Ingrese el ID del Laboratorio");
		// ComboBox Servicio
		cbbServicio = new JComboBox();
		custom.cbb(cbbServicio, "Servicio",3);
		listarServicios(cbbServicio);
		cbbServicio.setBounds(150,210,200,45);
		// TextField de Cantidad
		txtCantidad = new JFormattedTextField();
		txtCantidad.setBounds(520, 210, 200, 45);
		custom.txt(txtCantidad,1,"Ingrese la Cantidad del Servicio Seleccionado");
		
		/* Creacion de Botones */
		// Boton Buscar Paciente
		btnSearchPac = new JButton();
		btnSearchPac.setBounds(420, 54, 75, 66);
		custom.button(btnSearchPac, "", "\uf002", "solid",5);
		btnSearchPac.setToolTipText("Buscar por id del Paciente");
		// Boton Buscador Dialog Paciente
		btnBuscadorPac = new JButton();
		btnBuscadorPac.setBounds(490, 54, 75, 66);
		custom.button(btnBuscadorPac, "", "\uf00e", "solid",9);
		btnBuscadorPac.setToolTipText("Abrir Listado de Pacientes");
		// Boton Buscar Laboratorio
		btnSearchLab = new JButton();
		btnSearchLab.setBounds(962, 54, 75, 66);
		custom.button(btnSearchLab, "", "\uf002", "solid",5);
		btnSearchLab.setToolTipText("Buscar por id de Laboratorio");
		// Boton Buscador Dialog Paciente
		btnBuscadorLab = new JButton();
		btnBuscadorLab.setBounds(1032, 54, 75, 66);
		custom.button(btnBuscadorLab, "", "\uf00e", "solid",9);
		btnBuscadorLab.setToolTipText("Abrir Listado de Laboratorios");
		// Boton Agregar Servicio
		btnServicio = new JButton("");
		btnServicio.setBounds(745, 200, 150, 65);
		btnServicio.setToolTipText("Agregar Servicio");
		custom.button(btnServicio, "Agregar", "\uf0fe", "solid", 3);
		// Boton Eliminar Servicio
		btndeleteServ = new JButton();
		btndeleteServ.setBounds(900, 200, 150, 65);
		btndeleteServ.setToolTipText("Eliminar Servicio");
		custom.button(btndeleteServ, "Eliminar", "\uf55a", "solid", 6);
		// Boton Guardar solicitud
		btnAgregar = new JButton("");
		btnAgregar.setBounds(38, 555, 275, 74);
		custom.button(btnAgregar, "Guardar Solicitud", "\uf570", "solid", 3);
		// Boton Limpiar Campos	
		btnClean = new JButton("");
		btnClean.setBounds(760, 555, 242, 74);
		custom.button(btnClean, "Limpiar Campos", "\uf51a", "solid", 4);
		// Boton Listado solicitud
		btnListad = new JButton();
		btnListad.setBounds(400, 555, 275, 74);
		custom.button(btnListad, "Listado Solicitud", "\uf03a", "solid", 6);
		
		/* Crear Tabla Generica */
		panelTabla = new JScrollPane(tablaServicio);
		panelTabla.setBounds(2,275,1145,270);

		/* Agregar Componentes */
		add(lblNoSolicitud);
		add(lblidPaciente);
		add(lblPaciente);
		add(lblNombrePac);
		add(lblidLab);
		add(lblLab);
		add(lblNombreLab);
		add(lblServicio);
		add(lblCantidad);
		add(txtidPaciente);
		add(txtidLabd);
		add(cbbServicio);
		add(txtCantidad);
		add(panelTabla);
		add(btnSearchPac);
		add(btnSearchLab);
		add(btnServicio);
		add(btndeleteServ);
		add(btnAgregar);
		add(btnClean);
		add(btnListad);
		add(btnBuscadorPac);
		add(btnBuscadorLab);
		setTable();
		componentEnabled(false);
	}
	private void initActionButton() {
		/* Accion Botones */
		btnSearchPac.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
			getPaciente();
			}});
		btnSearchLab.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				getLab();
			} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
			}	
			}});
		cbbServicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i =0;i<listaServicio.size();i++) {
						if (cbbServicio.getSelectedItem() != null &&
								cbbServicio.getSelectedItem().toString().equals(listaServicio.get(i).getNombre())) {
						servicio = new ServicioVO();
						servicio.setNombre(listaServicio.get(i).getNombre());
						servicio.setIdServicio(listaServicio.get(i).getIdServicio());		
						servicio.setDescripcion(listaServicio.get(i).getDescripcion());
						servicio.setPrecio(listaServicio.get(i).getPrecio());
						};}	}});	
		
		/* Agregar Detalle a Tabla */
		btnServicio.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				addServicio();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}}});
		btndeleteServ.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int column = 0;
					int row = tablaServicio.getSelectedRow();
					int id = (int) tablaServicio.getValueAt(row, 0);
					int DialogResult = custom.msgConfirm("Desea Eliminar este servicio de la tabla?", 1);
				if (DialogResult == JOptionPane.YES_OPTION){
					for (int i=0;i<listaDetalle.size();i++) {
					if (id == listaDetalle.get(i).getServicio().getIdServicio()) {
						listaDetalle.remove(i);}}	
				DefaultTableModel model =  (DefaultTableModel)tablaServicio.getModel();
						model.removeRow(row);
						custom.msg("Se ha eliminado correctamente el servicio de la tabla", 4);}}
				catch (Exception ex) {
					custom.msg("Seleccione un servicio de la tabla",6);
				}}});
				
				btnAgregar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addSolicitud();
					}});
					btnListad.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
						goListado();
						}
					});
					btnClean.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
						limpiarCampos();	
						}});
					btnBuscadorPac.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								goBuscador("Paciente");
							} catch (FontFormatException | IOException | ParseException | SQLException e1) {
								e1.printStackTrace();
							}}});
					btnBuscadorLab.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								goBuscador("Laboratorio");
							} catch (FontFormatException | IOException | ParseException | SQLException e1) {
								e1.printStackTrace();
							}}});
					DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
					model.addTableModelListener(new TableModelListener() {
						@Override
						public void tableChanged(TableModelEvent arg0) {
							if(tablaServicio.isEditing() == true) {
								updateTable(tablaServicio.getEditingRow(), tablaServicio.getEditingColumn(),
										tablaServicio.getColumnName(tablaServicio.getEditingColumn()));
							}
						}
					});
			}
			private void updateTable(int row,int column,String nameCol) {
				switch(nameCol) {
				case "Descripcion":
					String descripcion = String.valueOf(tablaServicio.getValueAt(row, column));
					listaDetalle.get(row).getServicio().setDescripcion(descripcion);
					break;
				case "Cantidad":
					if (validation.validDigit(String.valueOf(tablaServicio.getValueAt(row, column))) == false){	
						custom.msg("cantidad", 5);}
					else{
					int cantidad = Integer.valueOf(String.valueOf(tablaServicio.getValueAt(row, column)));
					listaDetalle.get(row).setCantidad(cantidad);}
					break;
				}}
	
	private void getPaciente() {
		if (!(validation.validDigit(txtidPaciente.getText()) == false)) {
			paciente = coodSolicitud.buscarPaciente(Integer.parseInt(txtidPaciente.getText()));
			if(paciente!=null) {
			lblNombrePac.setText(paciente.getNombre());
			lblNombrePac.setSize(lblNombrePac.getPreferredSize());	
			componentEnabled(true);
			}
		else {limpiadorBuscadorPaciente();}
		}else {
			limpiadorBuscadorPaciente();
			custom.msg("id del paciente", 5);
		}}
	private void getLab() throws NumberFormatException, SQLException {
		if (!(validation.validDigit(txtidLabd.getText()) == false)) {
			laboratorio = coodSolicitud.buscarLaboratorio(Integer.parseInt(txtidLabd.getText()));
			if(laboratorio!=null) {
			lblNombreLab.setText(laboratorio.getNombre());
			lblNombreLab.setSize(lblNombreLab.getPreferredSize());
			componentEnabled(true);}
		else {limpiadorBuscadorLab();}
		}else {
			limpiadorBuscadorLab();
			custom.msg("id del laboratorio", 5);
		}}
	private void limpiadorBuscadorLab() {
		txtidLabd.setText("");
		lblNombreLab.setText("");
		lblNombreLab.setSize(lblNombreLab.getPreferredSize());
	}
	private void limpiadorBuscadorPaciente() {
		txtidPaciente.setText("");
		lblNombrePac.setText("");
		lblNombrePac.setSize(lblNombrePac.getPreferredSize());
	}
	private void limpiarCampos() {
		txtCantidad.setText("");
		txtidLabd.setText("");
		txtidPaciente.setText("");
		lblNombreLab.setText("");
		lblNombrePac.setText("");
		lblNombreLab.setSize(lblNombreLab.getPreferredSize());
		lblNombrePac.setSize(lblNombrePac.getPreferredSize());
		cbbServicio.setSelectedIndex(-1);
		listaDetalle.clear();
		setTable();
		componentEnabled(false);
	}
	private void addServicio() throws SQLException {
		detalleProtesis = new Detalle_ProtesisVO();
		detalleProtesis.setPaciente(paciente);
		if (!(cbbServicio.getSelectedIndex()>=0)) {
			custom.msg("",1);}else {
				detalleProtesis.setServicio(servicio);
		if (validation.validDigit(txtCantidad.getText()) == false){	
			custom.msg("cantidad", 5);}
		else{
			detalleProtesis.setCantidad(Integer.parseInt(txtCantidad.getText()));
				if (isDuplicated(tablaServicio)== false) {
					listaDetalle.add(detalleProtesis);
					agregarDetalle(detalleProtesis);
				
				}else {
					custom.msg("Este Servicio ya ha sido agregado a la tabla",6);
				}}}
	}
	
	private void addSolicitud() {
		solicitud = new SolicitudProtesisVO();
		if (lblNombrePac.getText().isEmpty() || lblNombreLab.getText().isEmpty()) {
			custom.msg("",1);
		}
		else {
			solicitud.setPaciente(paciente);
			solicitud.setLaboratorio(laboratorio);
		if(coodSolicitud.addSolicitud(solicitud, listaDetalle) == true) {
			limpiarCampos();
		}}}
	
	/* Llenar ComboBox Servicio*/
	private void listarServicios(JComboBox cbb) {
		 DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
	        // Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<listaServicio.size();i++) {
	           model.addElement(listaServicio.get(i).getNombre());
	        }
	        // setting model with new data
	        cbb.setModel(model);
	        cbb.setRenderer(new MyComboBox("Servicios")); 
      	cbb.setSelectedIndex(-1); 
	}
	/* Listar Tabla Detalle*/
	private void setTable() {
		String[] titulo = {"ID Servicio", "Nombre", "Descripcion", "Cantidad"};
		Object[][] fila = new Object[listaDetalle.size()][5];
		custom.table(tablaServicio, fila, titulo);
	}
	private void agregarDetalle(Detalle_ProtesisVO detalles) throws SQLException {
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
		model.addRow(new Object[] {detalles.getServicio().getIdServicio(),detalles.getServicio().getNombre(),
				detalles.getServicio().getDescripcion(),detalles.getCantidad()});
       }
	private boolean isDuplicated(JTable table) {
		boolean bool = false;
		int countRepeated = 0;
		int column = 0;
		int id = 0;
		if (tablaServicio.getRowCount() >0) {
		for (int i=0;i<tablaServicio.getRowCount();i++) {
			 id = (int) tablaServicio.getValueAt(i, 0);
			 if (id == detalleProtesis.getServicio().getIdServicio()) {
				 countRepeated ++;
					bool = true;}}}
			 else{
				 bool = false;}
		return bool;
	}
	private void goListado() {
		try {
			pnl = new Listados();
			removeAll();
			add(pnl.getPanel());
			revalidate();
			repaint();
			pnl.setTableSolicitud(coodSolicitud.listaSolicitud(), COLUMNS,"Solicitud",tableSolicitud);
		} catch ( IOException | ParseException | SQLException | FontFormatException e) {
			e.printStackTrace();}
	}
	private void goBuscador(String tabla) throws FontFormatException, IOException, ParseException, SQLException {
		buscadorDialog = new BuscadorDialog();
		buscadorDialog.setVisible(true);
		switch(tabla) {
		case "Paciente":
		buscadorDialog.setTablePaciente();
		buscadorDialog.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
		txtidPaciente.setText(String.valueOf(buscadorDialog.getIdTable()));	
		getPaciente();
		}});
		break;
		case "Laboratorio":
		buscadorDialog.setTableLab();
		buscadorDialog.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
		txtidLabd.setText(String.valueOf(buscadorDialog.getIdTable()));	
		try {
			getLab();
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}}});
		break;
		}}
	
	public String title() {
		String title = "Solicitud de Protesis";
		return title;
	}
	private void componentEnabled(boolean bool) {
		cbbServicio.enable(bool);
		txtCantidad.setEnabled(bool);
		btndeleteServ.setEnabled(bool);
		btnServicio.setEnabled(bool);
		btnAgregar.setEnabled(bool);
	}
	/* Declaracion de Componentes */
	JLabel lblNoSolicitud;
	JLabel lblidPaciente;
	JLabel lblPaciente;
	JLabel lblNombrePac;
	JLabel lblidLab;
	JLabel lblLab;
	JLabel lblNombreLab;
	JLabel lblServicio;
	JLabel lblCantidad;
	JFormattedTextField txtidPaciente;
	JFormattedTextField txtidLabd;
	JComboBox cbbServicio;
	JFormattedTextField txtCantidad;
	JButton btnSearchPac;
	JButton btnSearchLab;
	JButton btnServicio;
	JButton btndeleteServ;
	JButton btnAgregar;
	JButton btnClean;
	JButton btnListad;
	JButton btnBuscadorPac;
	JButton btnBuscadorLab;
	JScrollPane panelTabla;
	JLabel required;
	
}
