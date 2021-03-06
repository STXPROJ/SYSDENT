package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabableView;

import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Coordinador.CoordinadorFactura;
import Modelo.DAO.Detalle_FacturaDAO;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaFactura;
import Modelo.Logica.ValidationClass;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.Detalle_FacturaVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.UsuarioVO;

public class FacturaGUI extends JPanel implements Calculator {
private static String[]COLUMNS = new String[] {"ID Factura","Paciente","Fecha","Estado","Descuento",
		"Total","Usuario"};
private static String[] tableFactura = new String[] {"Factura","factura"};
private JTable tablaServicio ;
private Detalle_FacturaDAO consultaDetalle;
private ComponentCustomization custom = new ComponentCustomization();
private CoordinadorFactura coordFactura;
private LogicaFactura logicaFactura;
private ArrayList<Detalle_FacturaVO> listaDetalle;
private ValidationClass validation = new ValidationClass();
private ArrayList<ServicioVO> listaServicio;
private ServicioVO servicio;
private PacienteVO paciente;
private DiagnosticoVO diagnostico;
private Detalle_FacturaVO detalleFactura;
private UsuarioVO usuarioActivo;
private ArrayList<Detalle_DiagnosticoVO> detalleDiagnostico;
private FacturaVO factura;
private Listados pnl;
private CoordinadorCRUD coordCrud;
private LogicaCRUD logicaCrud;
private PagosGUI pagoGUI;
private BuscadorDialog buscadorDialog;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public FacturaGUI() throws FontFormatException, IOException, ParseException, SQLException {
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
		
		coordCrud = new CoordinadorCRUD();
		logicaCrud = new LogicaCRUD();
		coordCrud.setLogica(logicaCrud);
		logicaCrud.setCoordinador(coordCrud);
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
		// ID Paciente/Diagnostico
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
			// Creacion de Label NO. Factura
			lblNoFactura = new JLabel();
			lblNoFactura.setBounds(38, 5, 564, 50);
			custom.label(lblNoFactura, "No. Factura.: " + String.format("%010d", coordCrud.selectTopID(tableFactura[0])+1),3);
			// Label  Nombre Paciente
				lblPaciente = new JLabel();
				lblPaciente.setBounds(38, 130, 564, 39);
				custom.label(lblPaciente, "Nombre del Paciente.:",1);
				// Label Nombre
				lblNombrePac = new JLabel();
				lblNombrePac.setBounds(295, 130, 750, 39);
				custom.label(lblNombrePac, "",2);
				lblNombrePac.setSize(lblNombrePac.getPreferredSize());
				// Label Servicio
				lblServicio = new JLabel();
				lblServicio.setBounds(38, 190, 300, 39);
				custom.label(lblServicio, "Servicio.:",1);
				// Label Cantidad
				lblCantidad = new JLabel();
				lblCantidad.setBounds(400, 190, 215, 53);
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
				// Rdio Button PAciente
				rbPaciente = new JRadioButton();
				rbPaciente.setText("Paciente");
				rbPaciente.setBounds(38, 70, 125, 39);
				custom.rb(rbPaciente,1);
				// Radio Button Diagnostico
				rbDiagnostico = new JRadioButton();
				rbDiagnostico.setText("Diagnostico");
				rbDiagnostico.setBounds(170, 70, 175, 39);
				custom.rb(rbDiagnostico,1);
				// TextField de idDiagnostico
				txtid = new JFormattedTextField();
				txtid.setBounds(345, 65, 215, 45);
				custom.txt(txtid,1,"Ingrese el ID del Diagnostico o del Paciente");
				// ComboBox Servicio
				// ComboBox Servicio
				cbbServicio = new JComboBox();
				custom.cbb(cbbServicio, "Servicio",3);
				listarServicios(cbbServicio);
				cbbServicio.setBounds(150,190,200,45);
				// TextField de Cantidad
				txtCantidad = new JFormattedTextField();
				txtCantidad.setBounds(520, 190, 200, 45);
				custom.txt(txtCantidad,1,"Ingrese la Cantidad del Servicio Seleccionado");
				txtDescuento = new JFormattedTextField(custom.formato("descuento"));
				txtDescuento.setBounds(145, 495, 100, 39);
				custom.txt(txtDescuento,2,"Ingrese el porciento de descuento a aplicar a la factura");
					
				/* Creacion de Botones */
				// Boton Buscar Paciente
				btnSearch = new JButton();
				btnSearch.setBounds(565, 53, 75, 66);
				custom.button(btnSearch, "", "\uf002", "solid",5);
				btnSearch.setToolTipText("Buscar por id");
				// Boton Buscador Dialog 
				btnBuscador = new JButton();
				btnBuscador.setBounds(635, 54, 75, 66);
				custom.button(btnBuscador, "", "\uf00e", "solid",9);
				btnBuscador.setToolTipText("Abrir Listado");
				// Boton Agregar Servicio
				btnServicio = new JButton("");
				btnServicio.setBounds(745, 180, 150, 65);
				btnServicio.setToolTipText("Agregar Servicio");
				custom.button(btnServicio, "Agregar", "\uf0fe", "solid", 3);
				// Boton Eliminar Servicio
				btndeleteServ = new JButton();
				btndeleteServ.setBounds(900, 180, 150, 65);
				btndeleteServ.setToolTipText("Eliminar Servicio");
				custom.button(btndeleteServ, "Eliminar", "\uf55a", "solid", 6);
				// Boton Guardar Factura
				btnAgregar = new JButton("");
				btnAgregar.setBounds(5, 555, 250, 74);
				custom.button(btnAgregar, "Guardar Factura", "\uf571", "solid", 3);
				// Boton Limpiar Campos	
				btnClean = new JButton("");
				btnClean.setBounds(495, 555, 242, 74);
				custom.button(btnClean, "Limpiar Campos", "\uf51a", "solid", 4);
				// Boton Listado Factura
				btnListado = new JButton();
				btnListado.setBounds(250, 555, 250, 74);
				custom.button(btnListado, "Listado Factura", "\uf03a", "solid", 6);
				
				/* Crear Tabla Generica */
				panelTabla= new JScrollPane(tablaServicio);
				panelTabla.setBounds(2,250,1145,240);
				
				/* Agregar Componentes */
				add(lblNoFactura);
				add(lblPaciente);
				add(lblNombrePac);
				add(lblServicio);
				add(lblCantidad);
				add(lblDescuento);
				add(rbDiagnostico);
				add(rbPaciente);
				add(txtid);
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
				add(btnSearch);
				add(btnServicio);
				add(btndeleteServ);
				add(btnAgregar);
				add(btnClean);
				add(btnListado);
				add(btnBuscador);
				setTable();
				componentEnabled(false);
	}
	private void initActionButton() {
		/* Buscar Paciente */
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getRegistro();
				} catch (SQLException e1) {
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
				
				btnAgregar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							addFacturar();
						} catch (FontFormatException | IOException | ParseException | SQLException e1) {
							e1.printStackTrace();
						}}});
			btnClean.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					limpiarCampos();
				}
			});
			btnListado.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					goListado();
				}
			});
			rbDiagnostico.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					isSelected(rbDiagnostico);
				}});
			rbPaciente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					isSelected(rbPaciente);
				}
			});
			btnBuscador.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						selectBuscador();
					
					} catch (FontFormatException | IOException | ParseException | SQLException e) {
						e.printStackTrace();
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
			listaDetalle.get(row).setCantidad(cantidad);
			getCalculated();}
			break;
		}}
	private void isSelected(JRadioButton rb) {
		rbDiagnostico.setSelected(false);
		rbPaciente.setSelected(false);
		rb.setSelected(true);
	}
	private void getRegistro() throws SQLException {
		if (rbPaciente.isSelected() == true && rbDiagnostico.isSelected() == false) {
			getPaciente();
		}else if (rbDiagnostico.isSelected() == true && rbPaciente.isSelected() == false) {
			getDiagnostico();
		}
		else{
			custom.msg("Debe seleccionar la casilla paciente o diagnostico", 7);
		}
	}
	private void getPaciente() {
		if (!(validation.validDigit(txtid.getText()) == false)) {
			paciente = coordFactura.buscarPaciente(Integer.parseInt(txtid.getText()));
			if(paciente!=null) {
			lblNombrePac.setText(paciente.getNombre());
			lblNombrePac.setSize(lblNombrePac.getPreferredSize());
			componentEnabled(true);}
		else {
			limpiadorBuscar();
			}}	else {
			limpiadorBuscar();
		custom.msg("id del paciente", 5);
		}}
	private void getDiagnostico() throws SQLException {
		if (!(validation.validDigit(txtid.getText()) == false)) {
			diagnostico = coordFactura.buscarDiagnostico(Integer.parseInt(txtid.getText()));
			if(diagnostico!=null) {
			lblNombrePac.setText(diagnostico.getPaciente().getNombre());
			lblNombrePac.setSize(lblNombrePac.getPreferredSize());
			paciente = diagnostico.getPaciente();
			componentEnabled(true);
			listaDiagnostico(diagnostico);}
		else {
			limpiadorBuscar();	}}
		else {
			limpiadorBuscar();
			custom.msg("id del Diagnostico", 5);
		}}
	private void goListado() {
			try {
				pnl = new Listados();
				removeAll();
				add(pnl.getPanel());
				revalidate();
				repaint();
				pnl.setTableFactura(coordFactura.listaFactura(), COLUMNS,"Factura",tableFactura);
			} catch (FontFormatException | IOException | ParseException | SQLException e) {
				e.printStackTrace();
			}}
	private void addFacturar() throws FontFormatException, IOException, ParseException, SQLException {
		factura = new FacturaVO();
		if (lblNombrePac.getText().isEmpty()) {
			custom.msg("",1);
		}
		else {
			factura.setIdFactura(coordCrud.selectTopID(tableFactura[0])+1);
			factura.setPaciente(paciente);
			factura.setUsuario(getUsuarioActivo());
			if(coordFactura.addFactura(factura, listaDetalle) == true) {
				if(custom.msgConfirm("Realizar Pago?", 1) == JOptionPane.YES_OPTION){
					goPago();
				}
				limpiarCampos();
			}}
	}
	private void goPago() throws FontFormatException, IOException, ParseException, SQLException {
		pagoGUI = new PagosGUI();
		pagoGUI.txtidFactura.setText(String.valueOf(factura.getIdFactura()));
		pagoGUI.getFactura();
		removeAll();
		revalidate();
		repaint();
		add(pagoGUI);
	}
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
		detalleFactura.setTotal(pricexcant(detalleFactura.getCantidad(), detalleFactura.getServicio().getPrecio()));
			if (isDuplicated(tablaServicio)== false) {
					listaDetalle.add(detalleFactura);
					agregarDetalle(detalleFactura);
					getCalculated();
				}else {
					custom.msg("Este Servicio ya ha sido agregado a la tabla",6);
				}}}
	}
	
	private void limpiadorBuscar() {
		txtid.setText("");
		lblNombrePac.setText("");
		lblNombrePac.setSize(lblNombrePac.getPreferredSize());
		
	}
	private void limpiarCampos() {
		lblNombrePac.setText("");
		lblNombrePac.setSize(lblNombrePac.getPreferredSize());
		txtCantidad.setText("");
		txtDescuento.setText("");
		txtid.setText("");
		cbbServicio.setSelectedIndex(-1);
		lblValoritbis.setText("$00.00");
		lblValorSub.setText("$00.00");
		lblValorTota.setText("$00.00");
		listaDetalle.clear();
		setTable();
		componentEnabled(false);
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
	private void setTable() {
		String[] titulo = {"ID Servicio", "Nombre", "Descripcion", "Cantidad","Precio","Total"};
		Object[][] fila = new Object[listaDetalle.size()][5];
		custom.table(tablaServicio, fila, titulo);
	}
	private void agregarDetalle(Detalle_FacturaVO detalle) {
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
		model.addRow(new Object[] {detalle.getServicio().getIdServicio(),detalle.getServicio().getNombre(),
				detalle.getServicio().getDescripcion(),detalle.getCantidad(),
				detalle.getServicio().getPrecio(),
				detalle.getTotal()});
	}

	private void listaDiagnostico(DiagnosticoVO diagnostico) throws SQLException {
		detalleDiagnostico = coordFactura.detalleDiagnostico(diagnostico.getIdDiagnostico());
		DefaultTableModel model = (DefaultTableModel) tablaServicio.getModel();
        for (int i = 0; i < detalleDiagnostico.size(); i++) {
        	detalleFactura = new Detalle_FacturaVO();
        	detalleFactura.setServicio(detalleDiagnostico.get(i).getServicio());
        	detalleFactura.setCantidad(detalleDiagnostico.get(i).getCantidad());
        	listaDetalle.add(detalleFactura);
        	model.addRow(new Object[] {
        			detalleDiagnostico.get(i).getServicio().getIdServicio(),
        			detalleDiagnostico.get(i).getServicio().getNombre(),
        			detalleDiagnostico.get(i).getServicio().getDescripcion(),
        			detalleDiagnostico.get(i).getCantidad(),
        			detalleDiagnostico.get(i).getServicio().getPrecio(),
        			pricexcant(detalleDiagnostico.get(i).getCantidad(), detalleDiagnostico.get(i).getServicio().getPrecio())});
        }getCalculated();
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
	private void componentEnabled(boolean bool) {

		cbbServicio.enable(bool);
		txtCantidad.setEnabled(bool);
		btndeleteServ.setEnabled(bool);
		btnServicio.setEnabled(bool);
		btnAgregar.setEnabled(bool);
	}
	private void selectBuscador() throws FontFormatException, IOException, ParseException, SQLException {
		if (rbPaciente.isSelected() == true && rbDiagnostico.isSelected() == false) {
			goBuscador("Paciente");
		
		}else if (rbDiagnostico.isSelected() == true && rbPaciente.isSelected() == false) {
			goBuscador("Diagnostico");
		
		}
		else{
			custom.msg("Debe seleccionar la casilla paciente o diagnostico", 7);
		}
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
		txtid.setText(String.valueOf(buscadorDialog.getIdTable()));	
		getPaciente();
		}});
		break;
		case "Diagnostico":
		buscadorDialog.setTableDiagnostico();
		buscadorDialog.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
		txtid.setText(String.valueOf(buscadorDialog.getIdTable()));	
			try {
				getDiagnostico();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}}});
		break;
		}
		}
	public String title() {
		String title = "Generar Factura";
		return title;
	}
	
	
	public UsuarioVO getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(UsuarioVO usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
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

	/* Declaracion de Componentes */
	JLabel lblNoFactura;
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
	JRadioButton rbPaciente;
	JRadioButton rbDiagnostico;
	JFormattedTextField txtid;
	JComboBox cbbServicio;
	JFormattedTextField txtCantidad;
	JFormattedTextField txtDescuento;
	JButton btnSearch;
	JButton btnServicio;
	JButton btndeleteServ;
	JButton btnAgregar;
	JButton btnClean;
	JButton btnListado;
	JButton btnBuscador;
	JScrollPane panelTabla;
	JLabel required;

}
