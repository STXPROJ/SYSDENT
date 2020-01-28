package Vista;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Coordinador.CoordinadorPagos;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaPago;
import Modelo.Logica.ValidationClass;
import Modelo.VO.Detalle_FacturaVO;
import Modelo.VO.Detalle_PagoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.PagoFacturaVO;
import Modelo.VO.TipoPagoVO;

public class ModPagoGUI extends JPanel implements Calculator {
	private static String[]COLUMNS = new String[] {"ID Pago","ID Factura","Paciente","Fecha","Monto"};
	private static String[] tablePago = new String[] {"PagoFactura","Pago"};
	private JTable tablaPago ;
	private ComponentCustomization custom = new ComponentCustomization();
	private ValidationClass validation = new ValidationClass();
	private FacturaVO factura;
	private PacienteVO paciente;
	private EmpleadoVO empleado;
	private TipoPagoVO tipo;
	private PagoFacturaVO pago;
	private Detalle_PagoVO detallePago;
	private LogicaPago logicaPago;
	private CoordinadorPagos coordPago;
	private LogicaCRUD logicaCRUD;
	private CoordinadorCRUD coordCRUD;
	private ArrayList<TipoPagoVO> listaTipos;
	private ArrayList<Detalle_PagoVO> listaDetalle;
	private Listados pnl;
	private ConfirmacionUser confirmaDialog;
	private FormaPagoDialog dialogPago;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public ModPagoGUI() throws SQLException, FontFormatException, IOException, ParseException {
	/* Customizacion de Panel Principal*/
	setBackground(Color.WHITE);
	setBounds(0, 0, 1160,630 );
	setLayout(null);
	// Iniciar Coordinador y Logica
	initCoordLogic();
	//Iniciar Componenetes
	initComponents();
	// Iniciar Acciones de Botones
	initActionButton();
	}
private void initCoordLogic() throws SQLException {
	//  Set Coordinador y Logica
	logicaPago = new LogicaPago();
	coordPago = new CoordinadorPagos();
	logicaPago.setCoordi(coordPago);
	coordPago.setLogica(logicaPago);
	
	coordCRUD = new CoordinadorCRUD();
	logicaCRUD = new LogicaCRUD();
	coordCRUD.setLogica(logicaCRUD);
	logicaCRUD.setCoordinador(coordCRUD);
	// Lista de Tipo de PAgo
	listaTipos = coordPago.listaTipoPago();
	listaDetalle = new  ArrayList<Detalle_PagoVO>();
	// Tabla Pago
	tablaPago = new JTable() {
	public boolean isCellEditable(int row,int column) {
	if(column ==1 ||column == 2) { 
	return true;}
	else
	return false;
	}};
}
private void initComponents() throws FontFormatException, IOException, ParseException, SQLException {
	
	// Creacion de Label NO. Pago
	lblNoPago = new JLabel();
	lblNoPago.setBounds(38, 95, 564, 50);
	custom.label(lblNoPago, "No. Pago.: ",3);
	
	// Creacion de Label idFactura
	lblidFactura = new JLabel("");
	lblidFactura.setBounds(38, 140, 564, 39);
	custom.label(lblidFactura, "ID de Factura.: ",1);
	// Label  Nombre Paciente
	lblPaciente = new JLabel("");
	lblPaciente.setBounds(38, 180, 564, 39);
	custom.label(lblPaciente, "Nombre del Paciente:",1);
	// Label Nombre
	lblNombre = new JLabel("");
	lblNombre.setBounds(38, 220, 750, 39);
	custom.label(lblNombre, "",2);
	lblNombre.setSize(lblNombre.getPreferredSize());
	// Label 	Total a Pagar
	lblTotal = new JLabel();
	lblTotal.setBounds(38, 260, 300, 39);
	custom.label(lblTotal, "Total de Factura.:",1);
	// Label Total a Pagar Get
	lblTotalShow = new JLabel();
	lblTotalShow.setBounds(240, 263, 215, 53);
	custom.label(lblTotalShow, "RD$00.00", 2);
	lblTotalShow.setSize(lblTotalShow.getPreferredSize());
	// Label Pendiente a Pagar
	lblPendiente = new JLabel();
	lblPendiente.setBounds(38, 305, 300, 39);
	custom.label(lblPendiente, "Saldo Pendiente.:",1);
	// Label Pendiente Show
	lblPendienteShow = new JLabel();
	lblPendienteShow.setBounds(240, 310, 215, 39);
	custom.label(lblPendienteShow, "RD$00.00", 3);
	lblPendienteShow.setSize(lblPendienteShow.getPreferredSize());
	//Label Monto
	lblMonto = new JLabel();
	lblMonto.setBounds(620,225,532,53);
	custom.label(lblMonto, "Monto a Pagar:", 1);
	//Label Moneda
	lblMoneda = new JLabel();
	lblMoneda.setBounds(570,270,532,53);
	custom.label(lblMoneda, "RD.:", 1);
	// Label FormaPago
	lblFormaPago = new JLabel();
	lblFormaPago.setBounds(620,135,250,53);
	custom.label(lblFormaPago, "Forma de Pago:", 1);
	// Label Valor Total
	lblValorTotal = new JLabel();
	lblValorTotal.setBounds(925, 505, 213, 53);
	custom.label(lblValorTotal, "Total.: ", 4);
	// Label Ingreso  Total
	lblTotalIngreso = new JLabel();
	lblTotalIngreso.setBounds(1000, 505, 213, 53);
	custom.label(lblTotalIngreso, "$00.00", 3);
	// Label Nombre Tipo PAho
	lblNombreTipo = new JLabel();
	custom.label(lblNombreTipo, "Nueva Forma de Pago:",1);
	/* Creacion TextFields y ComboBox */
	// TextField de Nuevo Forma de Pago
	txtTipo = new JTextField(10);
	custom.txt(txtTipo, 1,"Ingrese el nombre de la nueva Forma de Pago");
	// ComboBox forma Pago
	cbbForma = new JComboBox();
	custom.cbb(cbbForma, "Forma de Pago",7);
	cbbForma.setBounds(620,180,250,45);
	listaTipo(cbbForma);
	// TextField de Monto
	txtMonto = new JFormattedTextField();
	txtMonto.setBounds(620, 275, 260, 45);
	custom.txt(txtMonto,1,"Ingrese el Monto de Pago");
	
	/* Creacion de Botones */
	// Boton Agregar Pago
	btnAddPago = new JButton("");
	btnAddPago.setBounds(900, 215,150, 65);
	custom.button(btnAddPago, "Agregar", "\uf0fe", "solid", 3);
	btnAddPago.setToolTipText("Agregar Pago");
	// Boton Eliminar Pago
	btndeletePago = new JButton();
	btndeletePago.setBounds(900, 275,150, 65);
	custom.button(btndeletePago, "Eliminar", "\uf55a", "solid", 6);
	btnAddPago.setToolTipText("Eliminar Pago");
	// Boton Guardar Cambios
	btnGuardar = new JButton("");
	btnGuardar.setBounds(100, 555, 275, 74);
	custom.button(btnGuardar, "Guardar Cambios", "\uf0c7", "solid", 3);
	// Boton Cancelar Cambios
	btnCancelar = new JButton();
	btnCancelar.setBounds(525, 555, 275, 74);
	custom.button(btnCancelar, "Cancelar Cambios", "\uf05e", "solid", 6);
	/* Crear Tabla Generica */
	panelTabla = new JScrollPane(tablaPago);
	panelTabla.setBounds(2,360,1145,150);
	//listarTabla(detalleDAO.listaDetalle());
	// Boton Nueva FormaPago
	btnTipo = new JButton();
	custom.button(btnTipo, "", "\uf65e", "solid", 3);
	btnTipo.setBorder(null);
	btnTipo.setBackground(null);
	btnTipo.setForeground(new Color(61, 204, 45));
	btnTipo.setToolTipText("Agregar Nueva Forma de Pago");
	// Panel Nuevo Forma de Pago
	background = new JPanel();
	custom.confirmacion(background, "Tipo de Pago");
	background.add(lblNombreTipo);
	background.add(txtTipo);
	background.add(btnTipo);
	background.setBounds(225, 5, 600, 80);
	// Background Modificar
	lblBackground = new JLabel();
	lblBackground.setBounds(25, 90, 1100, 265);
	custom.modificacionBG(lblBackground, "Pagos");
	/* Agregar Componentes */
	add(lblNoPago);
	add(lblidFactura);
	add(lblPaciente);
	add(lblNombre);
	add(lblTotal);
	add(lblTotalShow);
	add(lblPendiente);
	add(lblPendienteShow);
	add(lblFormaPago);
	add(lblBackground);
	add(lblMonto);
	add(lblMoneda);
	add(lblValorTotal);
	add(lblTotalIngreso);
	add(cbbForma);
	add(txtMonto);
	add(panelTabla);
	add(btnAddPago);
	add(btndeletePago);
	add(btnGuardar);
	add(btnCancelar);
	add(background);
	setTable();
}
private void initActionButton() {

	
	btnAddPago.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				addPago();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}});
	btnTipo.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		try {
			confirmacion();
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}}	});
	
	cbbForma.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			for (int i =0;i<listaTipos.size();i++) {
					if (cbbForma.getSelectedItem() != null &&
					cbbForma.getSelectedItem().toString().equals(listaTipos.get(i).getTipo())) {
					tipo = new TipoPagoVO();
					tipo.setIdTipo((listaTipos.get(i).getIdTipo()));
					tipo.setTipo(listaTipos.get(i).getTipo());
					};}
			if(cbbForma.getSelectedItem() != null && 
					 !(cbbForma.getSelectedItem().toString().equals("Efectivo"))) {
				try {
					DescripcionPago();
				} catch (FontFormatException | IOException e1) {
					e1.printStackTrace();
				}}}});	
	btnCancelar.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(custom.msgConfirm("Desea Cancelar los cambios?", 1) == JOptionPane.YES_OPTION){
				goListado();}}
		});
	DefaultTableModel model = (DefaultTableModel) tablaPago.getModel();
	model.addTableModelListener(new TableModelListener() {
		@Override
		public void tableChanged(TableModelEvent arg0) {
			if(tablaPago.isEditing() == true) {
				updateTable(tablaPago.getEditingRow(), tablaPago.getEditingColumn(),
						tablaPago.getColumnName(tablaPago.getEditingColumn()));
			}}});
}
private void updateTable(int row,int column,String nameCol) {
switch(nameCol) {
case "Descripcion":
	String descripcion = String.valueOf(tablaPago.getValueAt(row, column));
	listaDetalle.get(row).setDescripcion(descripcion);
	break;
case "Monto":
	if (validation.validDouble(String.valueOf(tablaPago.getValueAt(row, column))) == false){	
		custom.msg("monto", 5);}
	else{
	Double monto = Double.valueOf(String.valueOf(tablaPago.getValueAt(row, column)));
	listaDetalle.get(row).setMonto(monto);}
	break;
}}

private void addPago() throws SQLException {
	detallePago = new Detalle_PagoVO();
	detallePago.setFactura(factura);
	if (!(cbbForma.getSelectedIndex()>=0)) {
		custom.msg("",1);}else {
		detallePago.setTipoPago(tipo);if(!detallePago.getTipoPago().getTipo().equals("Efectivo")) {
			detallePago.setDescripcion(dialogPago.getDescripcion());
		}else {
			detallePago.setDescripcion("Efectivo");
		}
	if (validation.validDouble(txtMonto.getText()) == false){	
		custom.msg("Monto", 5);
		txtMonto.setText("");}
	else{
	detallePago.setMonto(Double.parseDouble(txtMonto.getText()));
			if (isDuplicated(tablaPago)== false) {
				listaDetalle.add(detallePago);
				agregarDetalle(detallePago);
				txtMonto.setText("");
				getCalculated();
			}else {
				custom.msg("Esta Forma de pago ya ha sido agregado a la tabla",6);
			}}}
}
private void registrarPago() {
	pago = new PagoFacturaVO();
	if (lblPendiente.getText().isEmpty()) {
		custom.msg("",1);
	}
	else {
		pago.setFactura(factura);
		if(coordPago.RegistrarPago(pago, listaDetalle) == true) {
		
		}}
}
public JPanel getPago(PagoFacturaVO pago) throws ParseException, SQLException {
	lblNoPago.setText(lblNoPago.getText()+ (String.format("%010d", pago.getIdPago())));
	lblidFactura.setText(lblidFactura.getText() + String.format("%05d", pago.getFactura().getIdFactura()));
	lblTotalShow.setText("RD$" + String.valueOf(pago.getFactura().getTotal()));
	lblPendienteShow.setText("RD$" + String.valueOf(pago.getFactura().getPendiente()));
	lblNombre.setText(pago.getFactura().getPaciente().getNombre());
	lblNombre.setSize(lblNombre.getPreferredSize());
	lblTotalShow.setSize(lblTotalShow.getPreferredSize());
	lblPendienteShow.setSize(lblPendienteShow.getPreferredSize());
	listaDetalle = coordPago.listaDetallePagos(pago.getIdPago());
	listadoPagos(listaDetalle);
	return this;
}
private void listaTipo(JComboBox cbb) throws SQLException {/* Llenar ComboBox Servicio*/
	int selected = cbb.getSelectedIndex();
	 DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
	 if (!coordPago.listaTipoPago().isEmpty()) {
			listaTipos = coordPago.listaTipoPago();
       // Borrar Datos Viejos
       model.removeAllElements();
       for (int i=0;i<listaTipos.size();i++) {
          model.addElement(listaTipos.get(i).getTipo());
       }
       // setting model with new data
       cbb.setModel(model);
       cbb.setRenderer(new MyComboBox("Forma de Pago")); 
	cbb.setSelectedIndex(selected); 
}}
private void setTable() {
	String[] titulo = {"Forma de Pago","Descripcion", "Monto"};
	Object[][] fila = new Object[listaDetalle.size()][5];
	custom.table(tablaPago, fila, titulo);
}
private void agregarDetalle(Detalle_PagoVO detalle) {
	DefaultTableModel model = (DefaultTableModel) tablaPago.getModel();
	model.addRow(new Object[] {detalle.getTipoPago().getTipo(),detalle.getDescripcion(),detalle.getMonto()});
}
private void listadoPagos(ArrayList<Detalle_PagoVO> detalles) throws SQLException {
	DefaultTableModel model = (DefaultTableModel) tablaPago.getModel();
    for (int i = 0; i < detalles.size(); i++) {
    	model.addRow(new Object[] {
    			detalles.get(i).getTipoPago().getTipo(),
    			detalles.get(i).getDescripcion(),
    			detalles.get(i).getMonto()});
    }getCalculated();
    }    
private boolean isDuplicated(JTable table) {
	boolean bool = false;
	int countRepeated = 0;
	int column = 0;
	String tipo = null;
	if (tablaPago.getRowCount() >0) {
	for (int i=0;i<tablaPago.getRowCount();i++) {
		 tipo = (String) tablaPago.getValueAt(i, 0);
		 if (tipo.equals(detallePago.getTipoPago().getTipo())) {
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
		pnl.setTablePagos(coordPago.listaPagos(), COLUMNS,"Pagos",tablePago);
	} catch (FontFormatException | IOException | ParseException | SQLException e) {
		e.printStackTrace();
	}}
private void confirmacion() throws FontFormatException, IOException {
	confirmaDialog = new ConfirmacionUser();
	confirmaDialog.setVisible(true);
	confirmaDialog.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosed(WindowEvent e) {
	    	try {
	    	 if(confirmaDialog.isConfirmacion() == true) {
		    	  tipo = new TipoPagoVO();
		    	  tipo.setTipo(txtTipo.getText());
				  coordPago.registrarTipo(tipo);
				listaTipo(cbbForma);
				txtTipo.setText("");}
				} catch (SQLException e1) {
				e1.printStackTrace(); 
	    	}}});} 
private void DescripcionPago() throws FontFormatException, IOException {
	dialogPago = new FormaPagoDialog();
	dialogPago.setLabelText(tipo);
	dialogPago.setVisible(true);
	dialogPago.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosed(WindowEvent e) {
	    	
	    	}});}
public String title() {
	String title = "Realizar Pagos";
	return title;
}
/* Declaracion de Componenets*/
JLabel lblNoPago;
JLabel required;
JLabel lblidFactura;
JLabel lblPaciente;
JLabel lblNombre;
JLabel lblTotal;
JLabel  lblTotalShow;
JLabel lblPendiente;
JLabel lblPendienteShow;
JLabel lblMonto;
JLabel lblFormaPago;
JLabel lblValorTotal;
JLabel lblTotalIngreso;
JLabel lblNombreTipo;
JLabel lblMoneda;
JLabel lblBackground;
JTextField txtTipo;
JComboBox cbbForma;
JFormattedTextField txtMonto;
JButton btnAddPago;
JButton btndeletePago;
JButton btnGuardar;
JButton btnCancelar;
JButton btnTipo;
JScrollPane panelTabla;
JPanel background;
@Override
public double pricexcant(int cantidad, double precio) {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public double subtotal() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public double itbis() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public double total(int discount) {
	double subtotal = 0;
	for (int i=0;i<listaDetalle.size();i++) {
	double monto = (Double) (listaDetalle.get(i).getMonto());	
	subtotal+= monto;
	} 
	return subtotal;
}
@Override
public void getCalculated() {
	lblTotalIngreso.setText("$" + total(0));
	
}

}
