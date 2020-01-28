package Vista;

import java.awt.Color;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Coordinador.CoordinadorSolicitud;
import Modelo.DAO.Detalle_ProtesisDAO;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaSolicitud;
import Modelo.Logica.ValidationClass;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.SolicitudProtesisVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.SolicitudProtesisVO;

public class ModSolicitudGUI extends JPanel {
	private static String[]COLUMNS = new String[] {"ID Solicitud","Laboratorio","Paciente","Estado","Fecha"};
private static String[] tableSolicitud = new String[] {"orden_protesis","solicitud"};
private JTable tablaServicio = new JTable();
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
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public ModSolicitudGUI() throws FontFormatException, IOException, ParseException, SQLException {
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
		// Creacion de Label NO. Solicitud
				lblNoSolicitud = new JLabel();
				lblNoSolicitud.setBounds(38, 10, 564, 50);
				custom.label(lblNoSolicitud, "No. Solicitud.: ",3);
				// Creacion de Label idPaciente
				lblidPaciente = new JLabel("");
				lblidPaciente.setBounds(38, 50, 564, 39);
				custom.label(lblidPaciente, "ID Paciente.:",1);	
				// Label  Nombre Paciente
				lblPaciente = new JLabel("");
				lblPaciente.setBounds(38, 90, 564, 39);
				custom.label(lblPaciente, "Nombre del Paciente:",1);
				// Label Nombre
				lblNombrePac = new JLabel("");
				lblNombrePac.setBounds(38, 125, 750, 39);
				custom.label(lblNombrePac, "",2);
				lblNombrePac.setSize(lblNombrePac.getPreferredSize());
				// Creacion de Label idLab
				lblidLab = new JLabel("");
				lblidLab.setBounds(590, 50, 564, 39);
				custom.label(lblidLab, "ID Laboratorio.:",1);
				// Label  Nombre Lab
				lblLab = new JLabel("");
				lblLab.setBounds(590, 90, 564, 39);
				custom.label(lblLab, "Nombre del Laboratorio:",1);
				// Label Nombre
				lblNombreLab = new JLabel("");
				lblNombreLab.setBounds(590, 125, 750, 39);
				custom.label(lblNombreLab, "",2);
				lblNombreLab.setSize(lblNombreLab.getPreferredSize());
				// Label Servicio
				lblServicio = new JLabel();
				lblServicio.setBounds(38, 195, 300, 39);
				custom.label(lblServicio, "Servicio.:",1);
				// Label Cantidad
				lblCantidad = new JLabel();
				lblCantidad.setBounds(400, 195, 215, 53);
				custom.label(lblCantidad, "Cantidad.:", 1);
				
				/* Creacion TextField y ComboBOx */
				// ComboBox Servicio
				cbbServicio = new JComboBox();
				custom.cbb(cbbServicio, "Servicio",3);
				listarServicios(cbbServicio);
				cbbServicio.setBounds(150,195,200,45);
				// TextField de Cantidad
				txtCantidad = new JFormattedTextField();
				txtCantidad.setBounds(520, 195, 200, 45);
				custom.txt(txtCantidad,1,"Ingrese la Cantidad del Servicio Seleccionado");
		/* Creacion de Botones */
		// Boton Agregar Servicio
		btnServicio = new JButton("");
		btnServicio.setBounds(745, 185, 150, 65);
		btnServicio.setToolTipText("Agregar Servicio");
		custom.button(btnServicio, "Agregar", "\uf0fe", "solid", 3);
		// Boton Eliminar Servicio
		btndeleteServ = new JButton();
		btndeleteServ.setBounds(900, 185, 150, 65);
		btndeleteServ.setToolTipText("Eliminar Servicio");
		custom.button(btndeleteServ, "Eliminar", "\uf55a", "solid", 6);
		// Boton Guardar Cambios
		btnGuardar = new JButton("");
		btnGuardar.setBounds(175, 555, 275, 74);
		custom.button(btnGuardar, "Guardar Cambios", "\uf0c7", "solid", 3);
		// Boton Cancelar Cambios
		btnCancelar = new JButton();
		btnCancelar.setBounds(600, 555, 275, 74);
		custom.button(btnCancelar, "Cancelar Cambios", "\uf05e", "solid", 6);
		
		/* Crear Tabla Generica */
		panelTabla = new JScrollPane(tablaServicio);
		panelTabla.setBounds(2,255,1145,300);

		// Background Modificar
		lblBackground = new JLabel();
		lblBackground.setBounds(25, 5, 1000, 175);
		custom.modificacionBG(lblBackground, "Solicitud");
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
		add(cbbServicio);
		add(txtCantidad);
		add(panelTabla);
		add(btnServicio);
		add(btndeleteServ);
		add(btnGuardar);
		add(btnCancelar);
		add(lblBackground);
		setTable();

	}
	private void initActionButton() {
	
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
				
				btnGuardar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
					}});
					btnCancelar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if(custom.msgConfirm("Desea Cancelar los cambios?", 1) == JOptionPane.YES_OPTION){
								goListado();}}
					});
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
	
	private void setTable() {
		String[] titulo = {"ID Servicio", "Nombre", "Descripcion", "Cantidad"};
		Object[][] fila = new Object[listaDetalle.size()][5];
		custom.table(tablaServicio, fila, titulo);
	}
	private void listadoSolicitud(ArrayList<Detalle_ProtesisVO> detalles) throws SQLException {
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
		
        for (int i = 0; i < detalles.size(); i++) {
        	model.addRow(new Object[] {
        			detalles.get(i).getServicio().getIdServicio(),
        			detalles.get(i).getServicio().getNombre(),
        			detalles.get(i).getServicio().getDescripcion(),
        			detalles.get(i).getCantidad()});
        }    
	}
	private void agregarDetalle(Detalle_ProtesisVO detalle) {
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
		model.addRow(new Object[] {detalle.getServicio().getIdServicio(),detalle.getServicio().getNombre(),
				detalle.getServicio().getDescripcion(),detalle.getCantidad(),
				detalle.getServicio().getPrecio()});
		
	}

	
	
	public JPanel getSolicitud(SolicitudProtesisVO solicitud) throws ParseException, SQLException {
		lblNoSolicitud.setText(lblNoSolicitud.getText() +String.format("%010d",solicitud.getIdSolicitud()));
		lblidPaciente.setText(lblidPaciente.getText()+String.format("%05d",solicitud.getPaciente().getIdPaciente()));
		lblidLab.setText(lblidLab.getText()+String.format("%05d",solicitud.getLaboratorio().getIdLaboratorio()));
		txtCantidad.setText("");
		lblNombreLab.setText(solicitud.getLaboratorio().getNombre());
		lblNombrePac.setText(solicitud.getPaciente().getNombre());
		lblNombreLab.setSize(lblNombreLab.getPreferredSize());
		lblNombrePac.setSize(lblNombrePac.getPreferredSize());
		listaDetalle = coodSolicitud.listaDetalle(solicitud.getIdSolicitud());
		listadoSolicitud(listaDetalle);
		return this;
	}
	
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
		} catch (FontFormatException | IOException | ParseException | SQLException e) {
			e.printStackTrace();}
	}
	public String title() {
		String title = "Solicitud de Protesis";
		return title;
	}
	/* Declaracion de Componentes */
	JLabel lblNoSolicitud;
	JLabel lblidPaciente;
	JLabel lblidLab;
	JLabel lblPaciente;
	JLabel lblNombrePac;
	JLabel lblLab;
	JLabel lblNombreLab;
	JLabel lblServicio;
	JLabel lblCantidad;
	JLabel lblBackground;
	JComboBox cbbServicio;
	JFormattedTextField txtCantidad;
	JButton btnServicio;
	JButton btndeleteServ;
	JButton btnGuardar;
	JButton btnCancelar;
	JScrollPane panelTabla;
}
