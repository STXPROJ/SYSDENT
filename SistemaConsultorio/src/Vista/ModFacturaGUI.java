package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

import Modelo.Coordinador.CoordinadorFactura;
import Modelo.DAO.Detalle_FacturaDAO;
import Modelo.Logica.LogicaFactura;
import Modelo.Logica.ValidationClass;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.Detalle_FacturaVO;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.SolicitudProtesisVO;

public class ModFacturaGUI extends JPanel implements Calculator {
private static String[]COLUMNS = new String[] {"ID Factura","Paciente","Fecha","Estado","Descuento",
			"Total","Usuario"};
private static String[] tableFactura = new String[] {"Factura","factura"};
private JTable tablaServicio;
private Detalle_FacturaDAO consultaDetalle;
private ComponentCustomization custom = new ComponentCustomization();
private CoordinadorFactura coordFactura;
private LogicaFactura logicaFactura;
private ArrayList<Detalle_FacturaVO> listaDetalle;
private ValidationClass validation = new ValidationClass();
private ArrayList<ServicioVO> listaServicio;
private ServicioVO servicio;
private PacienteVO paciente;
private Detalle_FacturaVO detalleFactura;
private FacturaVO factura;
private Listados pnl;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public ModFacturaGUI() throws FontFormatException, IOException, ParseException, SQLException {
		
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
		// Iniciar Logica Coordinador y Listados
		initCoordLogic();
		// Iniciar Componenetes
		initComponent();
		/* Accion de Botones */
		initActionButton();
			}

	private void initCoordLogic() throws SQLException {
		/* Set Logica y Coordinador */
		coordFactura = new CoordinadorFactura();
		logicaFactura = new LogicaFactura();
		coordFactura.setLogica(logicaFactura);
		logicaFactura.setCoordi(coordFactura);
		/* Lista Detalle y Paciente */
		listaServicio = coordFactura.listaServicio();
		servicio = new ServicioVO();
		listaDetalle = new ArrayList<Detalle_FacturaVO>();
		paciente = new PacienteVO();
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
		// Creacion de Label NO. Factura
		lblNoFactura = new JLabel();
		lblNoFactura.setBounds(38, 10, 564, 50);
		custom.label(lblNoFactura, "No. Factura.: ",3);
		// Label idPaciente
			lblidPaciente = new JLabel();
			lblidPaciente.setBounds(38, 70, 564, 39);
			custom.label(lblidPaciente, "ID Paciente.: ", 1);
		// Label  Nombre Paciente
			lblPaciente = new JLabel();
			lblPaciente.setBounds(38, 120, 564, 39);
			custom.label(lblPaciente, "Nombre del Paciente.:",1);
			// Label Nombre
			lblNombrePac = new JLabel();
			lblNombrePac.setBounds(295, 120, 750, 39);
			custom.label(lblNombrePac, "",2);
			lblNombrePac.setSize(lblNombrePac.getPreferredSize());
			// Label Servicio
			lblServicio = new JLabel();
			lblServicio.setBounds(38, 185, 300, 39);
			custom.label(lblServicio, "Servicio.:",1);
			// Label Cantidad
			lblCantidad = new JLabel();
			lblCantidad.setBounds(400, 185, 215, 53);
			custom.label(lblCantidad, "Cantidad.:", 1);
			// Label Descuento
			lblDescuento = new JLabel();
			lblDescuento.setBounds(10, 485, 215, 53);
			custom.label(lblDescuento, "Descuento.:", 4);
			// Label Subtotal
			lblSubTotal = new JLabel();
			lblSubTotal.setBounds(860,510,215,53);
			custom.label(lblSubTotal, "SubTotal.:", 4);
			// Label Itbis
			lblitbis = new JLabel();
			lblitbis.setBounds(842,550,215,53);
			custom.label(lblitbis, "ITBIS 18%.:", 4);
			// Label Total
			lblTotal = new JLabel();
			lblTotal.setBounds(846,585,215,53);
			custom.label(lblTotal, "Total Neto.:", 4);
			// Label Valor SubTotal
			lblValorSub = new JLabel();
			lblValorSub.setBounds(1000,510,215,53);
			custom.label(lblValorSub, "$00.00", 3);
			// Label Valor Itbis
			lblValoritbis = new JLabel();
			lblValoritbis.setBounds(1000,550,215,53);
			custom.label(lblValoritbis, "$00.00", 3);
			// Label Valor Total
			lblValorTota = new JLabel();
			lblValorTota.setBounds(1000,585,215,53);
			custom.label(lblValorTota, "$00.00", 3);
			
			/* Creacion TextFields y ComboBox y RadioButton */
			// ComboBox Servicio
			cbbServicio = new JComboBox();
			custom.cbb(cbbServicio, "Servicio",3);
			listarServicios(cbbServicio);
			cbbServicio.setBounds(150,185,200,45);
			// TextField de Cantidad
			txtCantidad = new JFormattedTextField();
			txtCantidad.setBounds(520, 185, 200, 45);
			custom.txt(txtCantidad,1,"Ingrese la Cantidad del Servicio Seleccionado");
			txtDescuento = new JFormattedTextField(custom.formato("descuento"));
			txtDescuento.setBounds(145, 495, 100, 39);
			custom.txt(txtDescuento,2,"Ingrese el porciento de descuento a aplicar a la factura");
				/* Creacion de Botones */
				// Boton Agregar Servicio
				btnServicio = new JButton("");
				btnServicio.setBounds(745, 175, 150, 65);
				btnServicio.setToolTipText("Agregar Servicio");
				custom.button(btnServicio, "Agregar", "\uf0fe", "solid", 3);
				// Boton Eliminar Servicio
				btndeleteServ = new JButton();
				btndeleteServ.setBounds(900, 175, 150, 65);
				btndeleteServ.setToolTipText("Eliminar Servicio");
				custom.button(btndeleteServ, "Eliminar", "\uf55a", "solid", 6);
				// Boton Guardar Cambios
				btnGuardar = new JButton("");
				btnGuardar.setBounds(100, 555, 275, 74);
				custom.button(btnGuardar, "Guardar Cambios", "\uf0c7", "solid", 3);
				// Boton Cancelar Cambios
				btnCancelar = new JButton();
				btnCancelar.setBounds(525, 555, 275, 74);
				custom.button(btnCancelar, "Cancelar Cambios", "\uf05e", "solid", 6);
				
				/* Crear Tabla Generica */
				panelTabla= new JScrollPane(tablaServicio);
				panelTabla.setBounds(2,250,1145,240);
				// Background Modificar
				lblBackground = new JLabel();
				lblBackground.setBounds(25, 5, 1000, 170);
				custom.modificacionBG(lblBackground, "Factura");
				/* Agregar Componentes */
				add(lblNoFactura);
				add(lblidPaciente);
				add(lblPaciente);
				add(lblNombrePac);
				add(lblServicio);
				add(lblCantidad);
				add(lblDescuento);
				add(cbbServicio);
				add(txtCantidad);
				add(txtDescuento);
				add(lblSubTotal);
				add(lblitbis);
				add(lblTotal);
				add(lblValorSub);
				add(lblValoritbis);
				add(lblValorTota);
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
						};}	}});	
		
		/* Agregar Detalle a Tabla */
		btnServicio.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				addServicio();
			} catch (SQLException e1) {
				e1.printStackTrace();
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
						custom.msg("Se ha eliminado correctamente el servicio de la tabla", 4);
						getCalculated();}}
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
				getCalculated();}
				break;
			}}
	private void addServicio() throws SQLException {
		detalleFactura = new Detalle_FacturaVO();
		detalleFactura.setPaciente(paciente);
		if (!(cbbServicio.getSelectedIndex()>=0)) {
			custom.msg("",1);}else {
			detalleFactura.setServicio(servicio);
		if (validation.validDigit(txtCantidad.getText()) == false){	
			custom.msg("cantidad", 5);}
		else{
		detalleFactura.setCantidad(Integer.parseInt(txtCantidad.getText()));
				if (isDuplicated(tablaServicio)== false) {
					listaDetalle.add(detalleFactura);
					agregarDetalle(detalleFactura);
					getCalculated();
				}else {
					custom.msg("Este Servicio ya ha sido agregado a la tabla",6);
				}}}
	}

	@Override
	public void getCalculated() {
		lblValorSub.setText("$" + subtotal());
		lblValoritbis.setText("$" +itbis());
		lblValorTota.setText("$" + total(0));
	}
	@Override
	public double pricexcant(int cantidad,double precio) {
		double total = cantidad * precio;
		return total;
	}
	@Override
	public double subtotal() {
		double subtotal = 0;
		for (int i=0;i<listaDetalle.size();i++) {
		int price = (int) (listaDetalle.get(i).getServicio().getPrecio() * listaDetalle.get(i).getCantidad());	
		subtotal+= price;
		} 
		return Math.round(subtotal / 1.18 * 100.0) / 100.0;
	}
	@Override
	public double itbis() {
		return Math.round(subtotal() * 0.18 * 100.0)/100.0;
	}
	@Override
	public double total(int discount) {
		double total = subtotal() + itbis();
		double descuento = total * (discount / 100);
		total = total - descuento;
		return Math.round(total* 100.0)/100.0;
	}
	
	private void setTable() {
		String[] titulo = {"ID Servicio", "Nombre", "Descripcion", "Cantidad","Precio","Total"};
		Object[][] fila = new Object[listaDetalle.size()][5];
		custom.table(tablaServicio, fila, titulo);
	}
	private void listadoFactura(ArrayList<Detalle_FacturaVO> detalles) throws SQLException {
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
        for (int i = 0; i < detalles.size(); i++) {
        	model.addRow(new Object[] {
        			detalles.get(i).getServicio().getIdServicio(),
        			detalles.get(i).getServicio().getNombre(),
        			detalles.get(i).getServicio().getDescripcion(),
        			detalles.get(i).getCantidad(),
        			detalles.get(i).getServicio().getPrecio(),
        			pricexcant(detalles.get(i).getCantidad(), detalles.get(i).getServicio().getPrecio())});
        }getCalculated();
        }    
	private void agregarDetalle(Detalle_FacturaVO detalle) {
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
		model.addRow(new Object[] {detalle.getServicio().getIdServicio(),detalle.getServicio().getNombre(),
				detalle.getServicio().getDescripcion(),detalle.getCantidad()});
		
	}
	public JPanel getFactura(FacturaVO factura) throws ParseException, SQLException {
		txtDescuento.setText(String.valueOf(factura.getDescuento()));
		lblNoFactura.setText(lblNoFactura.getText() +  String.format("%010d",factura.getIdFactura()));
		lblidPaciente.setText(lblidPaciente.getText() +  String.format("%05d", factura.getPaciente().getIdPaciente()));
		txtCantidad.setText("");
		lblNombrePac.setText(factura.getPaciente().getNombre());
		lblNombrePac.setSize(lblNombrePac.getPreferredSize());
		listaDetalle = coordFactura.listaDetalle(factura.getIdFactura());
		listadoFactura(listaDetalle);
		return this;
	}
	private void listarServicios(JComboBox cbb) {/* Llenar ComboBox Servicio*/
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
			 if (id == detalleFactura.getServicio().getIdServicio()) {
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
			pnl.setTableFactura(coordFactura.listaFactura(), COLUMNS,"Factura",tableFactura);
		} catch (FontFormatException | IOException | ParseException | SQLException e) {
			e.printStackTrace();}
	}

	public String title() {
		String title = "Generar Factura";
		return title;
	}
	/* Declaracion de Componentes */
	JLabel  lblNoFactura;
	JLabel lblidPaciente;
	JLabel lblPaciente;
	JLabel lblNombrePac;
	JLabel lblServicio;
	JLabel lblCantidad;
	JLabel lblDescuento;
	JLabel lblSubTotal;
	JLabel lblitbis;
	JLabel lblTotal;
	JLabel lblValorSub;
	JLabel lblValoritbis;
	JLabel lblValorTota;
	JComboBox cbbServicio;
	JFormattedTextField txtCantidad;
	JFormattedTextField txtDescuento;
	JButton btnServicio;
	JButton btndeleteServ;
	JButton btnGuardar;
	JButton btnCancelar;
	JScrollPane panelTabla;
	JLabel lblBackground;

	
}
