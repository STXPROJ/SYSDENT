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
import javax.sound.midi.Soundbank;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Coordinador.CoordinadorDiagnostico;
import Modelo.DAO.Detalle_DiagnosticoDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaDiagnostico;
import Modelo.Logica.ValidationClass;
import Modelo.VO.CitasVO;
import Modelo.VO.DentagramaVO;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;

public class ModDiagnosticoGUI extends JPanel {
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
private DentagramaVO dentagrama;
private ValidationClass validation = new ValidationClass();
private Listados pnl;
private CoordinadorCRUD coordCrud;
private LogicaCRUD logicaCrud;
private DentagramaDialog dentagramaDialog;
private HistorialClinicoDialog historialDialog;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public ModDiagnosticoGUI() throws FontFormatException, IOException, ParseException, SQLException {
		
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
		paciente = new PacienteVO();
		listaServicio = coordDiagnostico.listaServicio();
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
	private void initComponent() throws FontFormatException, IOException, ParseException {
		// Creacion de Label NODiagnostico
		lblNoDiagnostico = new JLabel();
		lblNoDiagnostico.setBounds(38, 25, 564, 50);
		custom.label(lblNoDiagnostico, "No. Diagnostico.: ",3);
		// Creacion de Label idPaciente
				lblidPaciente = new JLabel("");
				lblidPaciente.setBounds(38, 75, 564, 39);
				custom.label(lblidPaciente, "ID de Paciente.: ",1);
				// Label  Nombre Paciente
				lblPaciente = new JLabel("");
				lblPaciente.setBounds(38, 115, 564, 39);
				custom.label(lblPaciente, "Nombre del Paciente.:",1);
				// Label Nombre
				lblNombre = new JLabel("");
				lblNombre.setBounds(300, 115, 750, 39);
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
				// Boton Agregar Servicio
				btnServicio = new JButton("");
				btnServicio.setBounds(745, 176, 150, 65);
				btnServicio.setToolTipText("Agregar");
				custom.button(btnServicio, "Agregar", "\uf0fe", "solid", 3);
				// Boton Eliminar Servicio
				btndeleteServ = new JButton();
				btndeleteServ.setBounds(900, 176, 150, 65);
				btndeleteServ.setToolTipText("Eliminar");
				custom.button(btndeleteServ, "Eliminar", "\uf55a", "solid", 6);
				// Boton Guardar Cambios
				btnGuardar = new JButton("");
				btnGuardar.setBounds(175, 555, 275, 74);
				custom.button(btnGuardar, "Guardar Cambios", "\uf0c7", "solid", 3);
				// Boton Cancelar Cambios
				btnCancelar = new JButton();
				btnCancelar.setBounds(600, 555, 275, 74);
				custom.button(btnCancelar, "Cancelar Cambios", "\uf05e", "solid", 6);
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
				
				// Background Modificar
				lblBackground = new JLabel();
				lblBackground.setBounds(25, 15, 950, 155);
				custom.modificacionBG(lblBackground, "Diagnostico");
				/* Agregar Componentes */
				add(lblNoDiagnostico);
				add(lblidPaciente);
				add(lblPaciente);
				add(lblNombre);
				add(lblServicio);
				add(lblCantidad);
				add(cbbServicio);
				add(txtCantidad);
				add(panelTabla);
				add(btnServicio);
				add(btndeleteServ);
				add(btnGuardar);
				add(btnCancelar);
				add(btnDentagrama);
				add(btnHistorial);
				add(lblBackground);
				setTable();
	}
	private void initActionButton() {
	/* Agregar Diagnostico */
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
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
					};}}});
		/* Agregar Detalle a la Tabla */
		btnServicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				detalleDiagnostico = new Detalle_DiagnosticoVO();
				detalleDiagnostico.setPaciente(paciente);
				if (!(cbbServicio.getSelectedIndex()>=0)) {
					custom.msg("",1);}else {
					detalleDiagnostico.setServicio(servicio);
				if(validation.validDigit(txtCantidad.getText()) == false) {
					custom.msg("cantidad", 5);}
				else {
				detalleDiagnostico.setCantidad(Integer.parseInt(txtCantidad.getText()));
				if (isDuplicated(tablaServicio)== false) {
					listaDetalle.add(detalleDiagnostico);
					agregarDetalle(detalleDiagnostico);
				}else {
					custom.msg("Este Servicio ya ha sido agregado a la tabla",6);
				}}}
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
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(custom.msgConfirm("Desea Cancelar los cambios?", 1) == JOptionPane.YES_OPTION){
					goListado();}}
		});
		btnDentagrama.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					goDentagrama();
				} catch (FontFormatException | IOException e1) {
					e1.printStackTrace();
				}	}	});
		btnHistorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					goHistorial();
				} catch (FontFormatException | IOException e1) {
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
	private void setTable() {
		String[] titulo = {"ID Servicio", "Nombre", "Descripcion", "Cantidad","Precio"};
		Object[][] fila = new Object[listaDetalle.size()][5];
		custom.table(tablaServicio, fila, titulo);
	}
	private void listadoDiagnostico(ArrayList<Detalle_DiagnosticoVO> detalles) throws SQLException {
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
	
        for (int i = 0; i < detalles.size(); i++) {
        	model.addRow(new Object[] {
        			detalles.get(i).getServicio().getIdServicio(),
        			detalles.get(i).getServicio().getNombre(),
        			detalles.get(i).getServicio().getDescripcion(),
        			detalles.get(i).getCantidad(),
        			detalles.get(i).getServicio().getPrecio()});
        }    
	}
	private void agregarDetalle(Detalle_DiagnosticoVO detalle) {
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
		model.addRow(new Object[] {detalle.getServicio().getIdServicio(),detalle.getServicio().getNombre(),
				detalle.getServicio().getDescripcion(),detalle.getCantidad(),
				detalle.getServicio().getPrecio()});
	}
public JPanel getDiagnostico(DiagnosticoVO diagnostico) throws ParseException, SQLException {
		lblNoDiagnostico.setText(lblNoDiagnostico.getText() +String.format("%010d",diagnostico.getIdDiagnostico()));
		lblidPaciente.setText(lblidPaciente.getText() + String.format("%05d",diagnostico.getPaciente().getIdPaciente()));
		txtCantidad.setText("");
		lblNombre.setText(diagnostico.getPaciente().getNombre());
		lblNombre.setSize(lblNombre.getPreferredSize());
		listaDetalle = coordDiagnostico.listaDetalle(diagnostico.getIdDiagnostico());
		
		listadoDiagnostico(listaDetalle);
		this.diagnostico = diagnostico;
		this.paciente = diagnostico.getPaciente();
		return this;
	}
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
	private void goHistorial() throws FontFormatException, IOException {
		historialDialog = new HistorialClinicoDialog();
		historialDialog.setVisible(true);
		historialDialog.setPaciente(paciente);
	}
	private void goDentagrama() throws FontFormatException, IOException {
		dentagramaDialog = new DentagramaDialog();
		dentagrama = coordDiagnostico.buscarDentagrama(paciente.getIdPaciente(), diagnostico.getIdDiagnostico());
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
	public String title() {
		String title = "Realizar Diagnostico";
		return title;
	}
	/* Declarar Componentes */
	JLabel lblNoDiagnostico;
	JLabel lblidPaciente;
	JLabel lblPaciente;
	JLabel lblNombre;
	JLabel lblServicio;
	JLabel lblCantidad;
	JLabel lblBackground;
	JComboBox cbbServicio;
	JFormattedTextField txtCantidad;
	JButton btnServicio;
	JButton btndeleteServ;
	JButton btnGuardar;
	JButton btnCancelar;
	JButton btnHistorial;
	JButton btnDentagrama;
	JScrollPane panelTabla;
}
