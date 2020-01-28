package Vista;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Coordinador.CoordinadorPagos;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaPago;
import Modelo.Logica.ValidationClass;
import Modelo.VO.CargoVO;
import Modelo.VO.Detalle_FacturaVO;
import Modelo.VO.Detalle_PagoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.PagoFacturaVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.TipoPagoVO;
import Modelo.VO.UsuarioVO;

public class PagosGUI extends JPanel implements Calculator{
	private static String[]COLUMNS = new String[] {"ID Pago","ID Factura","Paciente","Fecha","Monto"};
private static String[] tablePago = new String[] {"Pago_Factura","Pago"};
private JTable tablaPago ;
private ComponentCustomization custom = new ComponentCustomization();
private ValidationClass validation = new ValidationClass();
private FacturaVO factura;
private PacienteVO paciente;
private UsuarioVO usuario;
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
private BuscadorDialog buscadorDialog;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public PagosGUI() throws FontFormatException, IOException, ParseException, SQLException {
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
		// ID Factura
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,168,25,25);
		add(required);
		// Tipo Pago
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(607,168,25,25);
		add(required);
		// Monto
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(607,265,25,25);
		add(required);
		// Creacion de Label NO. Pago
		lblNoPago = new JLabel();
		lblNoPago.setBounds(38, 95, 564, 50);
		custom.label(lblNoPago, "No. Pago.: " + String.format("%010d", coordCRUD.selectTopID(tablePago[1])+1),3);
		// Creacion de Label idFactura
		lblidFactura = new JLabel("");
		lblidFactura.setBounds(38, 155, 564, 39);
		custom.label(lblidFactura, "ID de Factura.:",1);
		// Label  Nombre Paciente
		lblPaciente = new JLabel("");
		lblPaciente.setBounds(38, 205, 564, 39);
		custom.label(lblPaciente, "Nombre del Paciente:",1);
		// Label Nombre
		lblNombre = new JLabel("");
		lblNombre.setBounds(38, 245, 750, 39);
		custom.label(lblNombre, "",2);
		lblNombre.setSize(lblNombre.getPreferredSize());
		// Label 	Total a Pagar
		lblTotal = new JLabel();
		lblTotal.setBounds(38, 280, 300, 39);
		custom.label(lblTotal, "Total de Factura.:",1);
		// Label Total a Pagar Get
		lblTotalShow = new JLabel();
		lblTotalShow.setBounds(240, 283, 215, 53);
		custom.label(lblTotalShow, "RD$00.00", 2);
		lblTotalShow.setSize(lblTotalShow.getPreferredSize());
		// Label Pendiente a Pagar
		lblPendiente = new JLabel();
		lblPendiente.setBounds(38, 320, 300, 39);
		custom.label(lblPendiente, "Saldo Pendiente.:",1);
		// Label Pendiente Show
		lblPendienteShow = new JLabel();
		lblPendienteShow.setBounds(240, 323, 215, 39);
		custom.label(lblPendienteShow, "RD$00.00", 3);
		lblPendienteShow.setSize(lblPendienteShow.getPreferredSize());
		//Label Monto
		lblMonto = new JLabel();
		lblMonto.setBounds(620,245,532,53);
		custom.label(lblMonto, "Monto a Pagar:", 1);
		//Label Moneda
		lblMoneda = new JLabel();
		lblMoneda.setBounds(570,290,532,53);
		custom.label(lblMoneda, "RD.:", 1);
		// Label FormaPago
		lblFormaPago = new JLabel();
		lblFormaPago.setBounds(620,147,250,53);
		custom.label(lblFormaPago, "Forma de Pago:", 1);
		// Label Valor Total
		lblValorTotal = new JLabel();
		lblValorTotal.setBounds(925, 475, 213, 53);
		custom.label(lblValorTotal, "Total.: ", 4);
		// Label Ingreso  Total
		lblTotalIngreso = new JLabel();
		lblTotalIngreso.setBounds(1000, 475, 213, 53);
		custom.label(lblTotalIngreso, "$00.00", 3);
		// Label Nombre Tipo PAho
		lblNombreTipo = new JLabel();
		custom.label(lblNombreTipo, "Nueva Forma de Pago:",1);
		/* Creacion TextFields y ComboBox */
		// TextField de idFactura
		txtidFactura = new JFormattedTextField();
		txtidFactura.setBounds(210, 150, 215, 45);
		custom.txt(txtidFactura,1,"Ingrese el ID de Factura");
		// TextField de Nuevo Forma de Pago
		txtTipo = new JTextField(10);
		custom.txt(txtTipo, 1,"Ingrese el nombre de la nueva Forma de Pago");
		// ComboBox forma Pago
		cbbForma = new JComboBox();
		custom.cbb(cbbForma, "Forma de Pago",7);
		cbbForma.setBounds(620,187,250,45);
		listaTipo(cbbForma);
		// TextField de Monto
		txtMonto = new JFormattedTextField();
		txtMonto.setBounds(620, 290, 260, 45);
		custom.txt(txtMonto,1,"Ingrese el Monto de Pago");
		
		/* Creacion de Botones */
		// Boton Buscar factura
		btnSearch = new JButton();
		btnSearch.setBounds(425, 140, 75, 66);
		custom.button(btnSearch, "", "\uf002", "solid",5);
		btnSearch.setToolTipText("Buscar por id de Factura");
		// Boton Buscador Dialog
		btnBuscador = new JButton();
		btnBuscador.setBounds(495, 140, 75, 66);
		custom.button(btnBuscador, "", "\uf00e", "solid",9);
		btnBuscador.setToolTipText("Abrir Listado de Facturas Pendientes");
		
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
		// Boton Procesar Pago

		btnAgregar = new JButton("");
		btnAgregar.setBounds(38, 525, 242, 74);
		custom.button(btnAgregar, "Procesar Pago", "\uf0d6", "solid", 3);
		// Boton Limpiar Campos	
		btnClean = new JButton("");
		btnClean.setBounds(760, 525, 242, 74);
		custom.button(btnClean, "Limpiar Campos", "\uf51a", "solid", 4);
		// Boton Listado PAgos
		btnListado = new JButton();
		btnListado.setBounds(400, 525, 255, 74);
		custom.button(btnListado, "Listado Pagos", "\uf03a", "solid", 6);
		
		/* Crear Tabla Generica */
		panelTabla = new JScrollPane(tablaPago);
		panelTabla.setBounds(2,360,1145,120);
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
		add(lblMonto);
		add(lblMoneda);
		add(lblValorTotal);
		add(lblTotalIngreso);
		add(txtidFactura);
		add(cbbForma);
		add(txtMonto);
		add(panelTabla);
		add(btnSearch);
		add(btnAddPago);
		add(btndeletePago);
		add(btnAgregar);
		add(btnClean);
		add(btnBuscador);
		add(btnListado);
		add(background);
		setTable();
		componentEnabled(false);
	}
	private void initActionButton() throws FontFormatException, IOException {
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getFactura();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}});
		btnTipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					confirmacion();
				} catch (FontFormatException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnClean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			limpiarCampos();	
			}});
		btnAddPago.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addPago();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}});
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			registrarPago();
			}});
		btnListado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			goListado();
			}});
		btnBuscador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				goBuscador();
			} catch (FontFormatException | IOException | ParseException | SQLException e1) {
				e1.printStackTrace();
			}}});
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
				}
			 }	}});	
				
		DefaultTableModel model = (DefaultTableModel) tablaPago.getModel();
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {
				if(tablaPago.isEditing() == true) {
					updateTable(tablaPago.getEditingRow(), tablaPago.getEditingColumn(),
							tablaPago.getColumnName(tablaPago.getEditingColumn()));
				}
			}
		});
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
	public void getFactura() throws SQLException {
		if (!(validation.validDigit(txtidFactura.getText()) == false)) {
			factura = coordPago.buscarFactura(Integer.parseInt(txtidFactura.getText()));
			if(factura!=null) {
			if(factura.isEstado() == true) {
				custom.msg("Esta Factura ya ha sido pagada", 4);
				limpiadorBuscar();
			}else {
				lblNombre.setText(factura.getPaciente().getNombre());
				lblNombre.setSize(lblNombre.getPreferredSize());
				lblTotalShow.setText("RD$"  +String.valueOf(factura.getTotal()));
				lblTotalShow.setSize(lblTotalShow.getPreferredSize());
				lblPendienteShow.setText("RD$"  +String.valueOf(factura.getPendiente()));
				lblPendienteShow.setSize(lblPendienteShow.getPreferredSize());
				paciente = factura.getPaciente();
				usuario = factura.getUsuario();
				componentEnabled(true);	
			}}else {
				limpiadorBuscar();
			}}else {
			limpiadorBuscar();
			custom.msg("id de Factura", 5);
		}}
	private void addPago() throws SQLException {
		detallePago = new Detalle_PagoVO();
		detallePago.setFactura(factura);
		if (!(cbbForma.getSelectedIndex()>=0)) {
			custom.msg("",1);}else {
			detallePago.setTipoPago(tipo);
			if(!detallePago.getTipoPago().getTipo().equals("Efectivo")) {
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
					cbbForma.setSelectedIndex(-1);
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
				limpiarCampos();
			}}
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
	private void limpiadorBuscar() {
		txtidFactura.setText("");
		lblNombre.setText("");
		lblNombre.setSize(lblNombre.getPreferredSize());
		lblTotalShow.setText("RD$00.00");
		lblTotalShow.setSize(lblTotalShow.getPreferredSize());
		lblPendienteShow.setText("RD$00.00");
		lblPendienteShow.setSize(lblPendienteShow.getPreferredSize());
	}
	private void limpiarCampos() {
		txtidFactura.setText("");
		lblNombre.setText("");
		lblNombre.setSize(lblNombre.getPreferredSize());
		lblTotalShow.setText("RD$00.00");
		lblTotalShow.setSize(lblTotalShow.getPreferredSize());
		lblPendienteShow.setText("RD$00.00");
		lblPendienteShow.setSize(lblPendienteShow.getPreferredSize());
		txtMonto.setText("");
		cbbForma.setSelectedIndex(-1);
		lblTotalIngreso.setText("$00.00");
		listaDetalle.clear();
		setTable();
		componentEnabled(false);
		custom.label(lblNoPago, "No. Pago.: " + String.format("%010d", coordCRUD.selectTopID(tablePago[1])+1),3);
	}
	private void componentEnabled(boolean bool) {
		cbbForma.enable(bool);
		txtMonto.setEnabled(bool);
		btndeletePago.setEnabled(bool);
		btnAddPago.setEnabled(bool);
		btnAgregar.setEnabled(bool);
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
			pnl.setTablePagos(coordPago.listaPagos(), COLUMNS,"Pago",tablePago);
		} catch (FontFormatException | IOException | ParseException | SQLException e) {
			e.printStackTrace();
		}}
	private void goBuscador() throws FontFormatException, IOException, ParseException, SQLException {
	buscadorDialog = new BuscadorDialog();
	buscadorDialog.setVisible(true);
	buscadorDialog.setTableFactura();
	buscadorDialog.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosed(WindowEvent e) {
	    	txtidFactura.setText(String.valueOf(buscadorDialog.getIdTable()));	
	    	 	try {
					getFactura();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}});
	} 
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
		    	try {
		    		txtMonto.setText(dialogPago.txtMonto.getText());
			    	addPago();
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	}});}
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
	JTextField txtTipo;
	JFormattedTextField txtidFactura;
	JComboBox cbbForma;
	JFormattedTextField txtMonto;
	JButton btnSearch;
	JButton btnAddPago;
	JButton btndeletePago;
	JButton btnAgregar;
	JButton btnClean;
	JButton btnListado;
	JButton btnTipo;
	JButton btnBuscador;
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
