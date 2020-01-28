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
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Coordinador.CoordinadorDiagnostico;
import Modelo.DAO.CRUD_DAO;
import Modelo.DAO.Detalle_DiagnosticoDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaDiagnostico;
import Modelo.Logica.ValidationClass;
import Modelo.VO.CargoVO;
import Modelo.VO.DentagramaVO;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.Detalle_FacturaVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.UsuarioVO;

public class DiagnosticoGUI extends JPanel {
	private static String[]COLUMNS = new String[] {"ID Diagnostico","Paciente","Doctor","Fecha"};
private static String[] tableDiagnostico = new String[] {"Diagnostico","Diagnostico"};
private JTable tablaServicio;
private Detalle_DiagnosticoVO detalleDiagnostico;
private ArrayList<Detalle_DiagnosticoVO> listaDetalle;
private ComponentCustomization custom = new ComponentCustomization();
private LogicaDiagnostico logicaDiagnostico;
private CoordinadorDiagnostico coordDiagnostico;
private PacienteVO paciente;
private DiagnosticoVO diagnostico;
private ArrayList<ServicioVO> listaServicio;
private ServicioVO servicio;
private UsuarioVO usuarioActivo;
private DentagramaVO dentagrama;
private ValidationClass validation = new ValidationClass();
private Listados pnl;
private CoordinadorCRUD coordCrud;
private LogicaCRUD logicaCrud;
private HistorialClinicoDialog historialDialog;
private BuscadorDialog buscadorDialog;
private DentagramaDialog dentagramaDialog;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public DiagnosticoGUI() throws FontFormatException, IOException, ParseException, SQLException {
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
		// Iniciar Coordinador Logica y Listados
		initCoordLogic();
		// Iniciar COmponentes
		initComponent();
		// Iniicar Accion Botones y ComboBox
		initActionButton();

	}

	private void initCoordLogic() throws SQLException {
		/* Set Logica y Coordinador */ 
		coordDiagnostico = new CoordinadorDiagnostico();
		logicaDiagnostico = new LogicaDiagnostico();
		coordDiagnostico.setLogica(logicaDiagnostico);
		logicaDiagnostico.setCoordDiagnostico(coordDiagnostico);
		
		coordCrud = new CoordinadorCRUD();
		logicaCrud = new LogicaCRUD();
		coordCrud.setLogica(logicaCrud);
		logicaCrud.setCoordinador(coordCrud);
		/* Agregar Listas y Objetos */
		diagnostico = new DiagnosticoVO();
		diagnostico.setIdDiagnostico(coordCrud.selectTopID(tableDiagnostico[0])+1);
		listaDetalle = new ArrayList<Detalle_DiagnosticoVO>();
		// Tabla Servicio
		tablaServicio = new JTable() {
			public boolean isCellEditable(int row,int column) {
				if(column ==3 ||column == 2) { 
				return true;}
				else
				return false;
				}};
		}
	private void initComponent() throws FontFormatException, IOException, ParseException, SQLException {
		// Datos Requeridos
		// ID
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,83,25,25);
		add(required);
		// Servicio
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,203,25,25);
		add(required);
				
		// Creacion de Label NODiagnostico
		lblNoDiagnostico = new JLabel();
		lblNoDiagnostico.setBounds(38, 5, 564, 50);
		custom.label(lblNoDiagnostico, "No. Diagnostico.: " + String.format("%010d", coordCrud.selectTopID(tableDiagnostico[0])+1),3);
		// Creacion de Label idPaciente
				lblidPaciente = new JLabel("");
				lblidPaciente.setBounds(38, 70, 564, 39);
				custom.label(lblidPaciente, "ID de Paciente.:",1);
				// Label  Nombre Paciente
				lblPaciente = new JLabel("");
				lblPaciente.setBounds(38, 135, 564, 39);
				custom.label(lblPaciente, "Nombre del Paciente.:",1);
				// Label Nombre
				lblNombre = new JLabel("");
				lblNombre.setBounds(300, 135, 750, 39);
				custom.label(lblNombre,"",2);
				lblNombre.setSize(lblNombre.getPreferredSize());
				// Label Servicio
				lblServicio = new JLabel();
				lblServicio.setBounds(38, 190, 300, 39);
				custom.label(lblServicio, "Servicio.:",1);
				// Label Cantidad
				lblCantidad = new JLabel();
				lblCantidad.setBounds(400, 190, 215, 39);
				custom.label(lblCantidad, "Cantidad.:", 1);
			
				/* Creacion TextFields y ComboBox */	
				// TextField de idPaciente
				txtidPaciente = new JFormattedTextField();
				txtidPaciente.setBounds(225, 65, 215, 45);
				custom.txt(txtidPaciente,1,"Ingrese el ID del paciente");
				// ComboBox Servicio
				cbbServicio = new JComboBox();
				custom.cbb(cbbServicio, "Servicio",3);
				listarServicios(cbbServicio);
				cbbServicio.setBounds(150,190,200,45);
				// TextField de Cantidad
				txtCantidad = new JFormattedTextField();
				txtCantidad.setBounds(520, 190, 200, 45);
				custom.txt(txtCantidad,1,"Ingrese la cantidad del servicio seleccionado");		
				
				/* Creacion de Botones */
				// Boton Buscar Pacinte
				btnSearch = new JButton();
				btnSearch.setBounds(445, 54, 75, 66);
				custom.button(btnSearch, "", "\uf002", "solid",5);
				btnSearch.setToolTipText("Buscar por id del Paciente");
				// Boton Buscador Dialog
				btnBuscador = new JButton();
				btnBuscador.setBounds(515, 54, 75, 66);
				custom.button(btnBuscador, "", "\uf00e", "solid",9);
				btnBuscador.setToolTipText("Abrir Listado de Pacientes");
				// Boton Agregar Servicio
				btnServicio = new JButton("");
				btnServicio.setBounds(745, 176, 150, 65);
				btnServicio.setToolTipText("Agregar Servicio");
				custom.button(btnServicio, "Agregar", "\uf0fe", "solid", 3);
				// Boton Eliminar Servicio
				btndeleteServ = new JButton();
				btndeleteServ.setBounds(900, 176, 150, 65);
				btndeleteServ.setToolTipText("Eliminar Servicio");
				custom.button(btndeleteServ, "Eliminar", "\uf55a", "solid", 6);
				// Boton Guardar Diagnostico
				btnAgregar = new JButton("");
				btnAgregar.setBounds(38, 555, 275, 74);
				custom.button(btnAgregar, "Guardar Diagnostico", "\uf56e", "solid", 3);
				// Boton Limpiar Campos	
				btnClean = new JButton("");
				btnClean.setBounds(760, 555, 242, 74);
				custom.button(btnClean, "Limpiar Campos", "\uf51a", "solid", 4);
				// Boton Listado Diagnostico
				btnListado = new JButton();
				btnListado.setBounds(400, 555, 275, 74);
				custom.button(btnListado, "Listado Diagnostico", "\uf03a", "solid", 6);
				// Boton Historial
				btnHistorial = new JButton();
				btnHistorial.setBounds(980,10,75,70);
				custom.button(btnHistorial, "", "\uf46d", "solid", 8);
				btnHistorial.setBorder(new LineBorder(Color.BLACK,5));
				btnHistorial.setToolTipText("Agregar o Modificar Historial Clinico de Paciente");
				// Boton Dentagrama
				btnDentagrama = new JButton();
				btnDentagrama.setBounds(1050,10,77,70);
				custom.button(btnDentagrama, "", "\uf5c9", "solid", 8);
				btnDentagrama.setBorder(new LineBorder(Color.BLACK,5));
				btnDentagrama.setToolTipText("Agregar o Modificar Dentagrama de Paciente");
				
				/* Crear Tabla Generica */
				panelTabla = new JScrollPane(tablaServicio);
				panelTabla.setBounds(2,260,1145,290);	
				/* Agregar Componentes */
				add(lblNoDiagnostico);
				add(lblidPaciente);
				add(lblPaciente);
				add(lblNombre);
				add(lblServicio);
				add(lblCantidad);
				add(txtidPaciente);
				add(cbbServicio);
				add(txtCantidad);
				add(panelTabla);
				add(btnSearch);
				add(btnBuscador);
				add(btnServicio);
				add(btndeleteServ);
				add(btnAgregar);
				add(btnClean);
				add(btnListado);
				add(btnHistorial);
				add(btnDentagrama);
				setTable();
				componentEnabled(false);
				
	}
	private void initActionButton() {
		/* Buscar Paciente */
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			getPaciente();
			}});
		/* Agregar Diagnostico */
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addDiagnostico();
			}});
		/* Guardar Datos Servicio de ComboBox */
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
					};}
				}});
		/* Agregar Detalle a la Tabla */
		btnServicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			try {
				addServicio();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}});
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
		btnClean.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
			limpiarCampos();
			}
		});
		btnListado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				goListado();
			}});
		btnHistorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					goHistorial();
				} catch (FontFormatException | IOException e1) {
				e1.printStackTrace();
				}}});
		btnBuscador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					goBuscador();
				} catch (FontFormatException | IOException | ParseException | SQLException e) {
					e.printStackTrace();
					}}});
		btnDentagrama.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					goDentagrama();
				} catch (FontFormatException | IOException e) {
					e.printStackTrace();
				}}	});
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {
				if(tablaServicio.isEditing() == true) {
					updateTable(tablaServicio.getEditingRow(), tablaServicio.getEditingColumn(),
							tablaServicio.getColumnName(tablaServicio.getEditingColumn()));
				}}});
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
		listaDetalle.get(row).setCantidad(cantidad);
		}
		break;
	}}	
	
	/* Llenar ComboBox Servicio*/
	private void listarServicios(JComboBox cbb) throws SQLException {
		 
		if (!coordDiagnostico.listaServicio().isEmpty()) {
			listaServicio = coordDiagnostico.listaServicio();
		/* Llenar ComboBox Servicio*/
		 DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
	        // Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<listaServicio.size();i++) {
	           model.addElement(listaServicio.get(i).getNombre());
	        }
	        // setting model with new data
	        cbb.setModel(model);
	        cbb.setRenderer(new MyComboBox("Servicios")); 
      	cbb.setSelectedIndex(-1); }
	}
	private void setTable() {
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
		String[] titulo = {"ID Servicio", "Nombre", "Descripcion", "Cantidad","Precio"};
		Object[][] fila = new Object[listaDetalle.size()][5];
		custom.table(tablaServicio, fila, titulo);

	}
	private void agregarDetalle(Detalle_DiagnosticoVO detalles) throws SQLException {
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
		model.addRow(new Object[] {detalles.getServicio().getIdServicio(),detalles.getServicio().getNombre(),
				detalles.getServicio().getDescripcion(),detalles.getCantidad(),
				detalles.getServicio().getPrecio()});
        }
	private void addServicio() throws SQLException {
		detalleDiagnostico = new Detalle_DiagnosticoVO();
		detalleDiagnostico.setPaciente(paciente);
		if (!(cbbServicio.getSelectedIndex()>=0)) {
			custom.msg("",1);}else {
				detalleDiagnostico.setServicio(servicio);
		if (validation.validDigit(txtCantidad.getText()) == false){	
			custom.msg("cantidad", 5);}
		else{
			detalleDiagnostico.setCantidad(Integer.parseInt(txtCantidad.getText()));
				if (isDuplicated(tablaServicio)== false) {
					listaDetalle.add(detalleDiagnostico);
					agregarDetalle(detalleDiagnostico);
				
				}else {
					custom.msg("Este Servicio ya ha sido agregado a la tabla",6);
				}}}
	}
	private void addDiagnostico() {
		diagnostico = new DiagnosticoVO();
		if (lblNombre.getText().isEmpty()) {
			custom.msg("",1);
		}else {
		diagnostico.setDentagrama(dentagrama);
		diagnostico.setPaciente(paciente);
		diagnostico.setUsuario(getUsuarioActivo());
		if (coordDiagnostico.addDiagnostico(diagnostico,listaDetalle) == true) {
			limpiarCampos();
		};}
	}
	private void limpiarCampos() {
		txtidPaciente.setText("");
		txtCantidad.setText("");
		cbbServicio.setSelectedIndex(-1);
		lblNombre.setText("");
		lblNombre.setSize(lblNombre.getPreferredSize());
		componentEnabled(false);
		listaDetalle.clear();
		setTable();
	}
	private void getPaciente() {
		if (!(validation.validDigit(txtidPaciente.getText()) == false)) {
			paciente = coordDiagnostico.buscarPaciente(Integer.parseInt(txtidPaciente.getText()));
			if(paciente!=null) {
			lblNombre.setText(paciente.getNombre());
			lblNombre.setSize(lblNombre.getPreferredSize());
			componentEnabled(true);}
		else {
			txtidPaciente.setText("");
			lblNombre.setText("");}
		}
		else {
			custom.msg("id del paciente", 5);
			txtidPaciente.setText("");
			lblNombre.setText("");
		}}
	private boolean isDuplicated(JTable table) {
		boolean bool = false;
		int countRepeated = 0;
		int column = 0;
		int id = 0;
		if (tablaServicio.getRowCount() >0) {
		for (int i=0;i<tablaServicio.getRowCount();i++) {
			 id = (int) tablaServicio.getValueAt(i, 0);
			 if (id == detalleDiagnostico.getServicio().getIdServicio()) {
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
				pnl.setTableDiagnostico(coordDiagnostico.listaDiagnostico(), COLUMNS,"Diagnostico",tableDiagnostico);
			} catch (FontFormatException | IOException | ParseException | SQLException e) {
				e.printStackTrace();}
		}
	private void goBuscador() throws FontFormatException, IOException, ParseException, SQLException {
	buscadorDialog = new BuscadorDialog();
	buscadorDialog.setVisible(true);
	buscadorDialog.setTablePaciente();
	buscadorDialog.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosed(WindowEvent e) {
	    	txtidPaciente.setText(String.valueOf(buscadorDialog.getIdTable()));	
	    	 	getPaciente();
				}});
	} 
	private void goHistorial() throws FontFormatException, IOException {
		historialDialog = new HistorialClinicoDialog();
		historialDialog.setVisible(true);
		historialDialog.setPaciente(paciente);
	}
	private void goDentagrama() throws FontFormatException, IOException {
		dentagramaDialog = new DentagramaDialog();
		if(dentagrama != null) {	
			dentagramaDialog.setDentagrama(dentagrama);
		}
		dentagramaDialog.setPaciente(paciente);
		dentagramaDialog.setDiagnostico(diagnostico);
		dentagramaDialog.setVisible(true);
		dentagramaDialog.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {
		    	dentagrama = dentagramaDialog.getDentagrama();
		    	
					}});
	}
	private void componentEnabled(boolean bool) {
		cbbServicio.enable(bool);
		txtCantidad.setEnabled(bool);
		btndeleteServ.setEnabled(bool);
		btnServicio.setEnabled(bool);
		btnAgregar.setEnabled(bool);
		btnHistorial.setEnabled(bool);
		btnDentagrama.setEnabled(bool);
	}
	
	public String title() {
		String title = "Realizar Diagnostico";
		return title;
	}

	public UsuarioVO getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(UsuarioVO usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

	/* Declarar Componentes */
	JLabel lblNoDiagnostico;
	JLabel lblidPaciente;
	JLabel lblPaciente;
	JLabel lblNombre;
	JLabel lblServicio;
	JLabel lblCantidad;
	JFormattedTextField txtidPaciente;
	JComboBox cbbServicio;
	JFormattedTextField txtCantidad;
	JButton btnSearch;
	JButton btnBuscador;
	JButton btnServicio;
	JButton btndeleteServ;
	JButton btnAgregar;
	JButton btnClean;
	JButton btnListado;
	JButton btnHistorial;
	JButton btnDentagrama;
	JScrollPane panelTabla;
	JLabel required;
}
