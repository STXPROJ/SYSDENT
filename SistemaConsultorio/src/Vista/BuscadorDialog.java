package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Coordinador.CoordinadorDiagnostico;
import Modelo.Coordinador.CoordinadorEmpleado;
import Modelo.Coordinador.CoordinadorFactura;
import Modelo.Coordinador.CoordinadorLaboratorio;
import Modelo.Coordinador.CoordinadorPaciente;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaDiagnostico;
import Modelo.Logica.LogicaEmpleado;
import Modelo.Logica.LogicaFactura;
import Modelo.Logica.LogicaLaboratorio;
import Modelo.Logica.LogicaPaciente;
import Modelo.Logica.ValidationClass;
import Modelo.VO.CargoVO;
import Modelo.VO.CitasVO;
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

public class BuscadorDialog extends JDialog {

	private JPanel pnl;
	private ComponentCustomization custom = new ComponentCustomization();
	private ValidationClass validation = new ValidationClass();
	private CoordinadorPaciente coordPaciente;
	private LogicaPaciente logicaPaciente;
	private CoordinadorLaboratorio coordLab;
	private LogicaLaboratorio logicaLab;
	private CoordinadorEmpleado coordEmpleado;
	private LogicaEmpleado logicaEmpleado;
	private CoordinadorDiagnostico coordDiagnostico;
	private LogicaDiagnostico logicaDiagnostico;
	private LogicaFactura logicaFactura;
	private CoordinadorFactura coordFactura;
	
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
	private String idTable ;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscadorDialog dialog = new BuscadorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws SQLException 
	 */
	public BuscadorDialog() throws FontFormatException, IOException, ParseException, SQLException {
		setBounds(400, 100, 490, 300);
		getContentPane().setBackground(new Color(32,35,64));
		setLayout(null);
		
		// Iniciar Logicas y Coordinadores
		initCoordLogic();
		// Iniciar Componentes
		initComponent();
		// Iniciar Acciones
		initActionButton();
		setModal(true);
	}
	private void initCoordLogic() throws SQLException {
		// Paciente
		logicaPaciente = new LogicaPaciente();
		coordPaciente = new CoordinadorPaciente();
		logicaPaciente.setCoordinador(coordPaciente);
		coordPaciente.setLogica(logicaPaciente);
		// Empleado
		logicaEmpleado = new LogicaEmpleado();
		coordEmpleado = new CoordinadorEmpleado();
		coordEmpleado.setLogica(logicaEmpleado);
		logicaEmpleado.setCoordinador(coordEmpleado);
		// Labotatorio
		coordLab = new CoordinadorLaboratorio();
		logicaLab = new LogicaLaboratorio();
		coordLab.setLogica(logicaLab);
		logicaLab.setCoordinador(coordLab);
		// Diagnostico
		coordDiagnostico = new CoordinadorDiagnostico();
		logicaDiagnostico = new LogicaDiagnostico();
		coordDiagnostico.setLogica(logicaDiagnostico);
		logicaDiagnostico.setCoordDiagnostico(coordDiagnostico);
		// Factura
		coordFactura = new  CoordinadorFactura();
		logicaFactura = new LogicaFactura();
		coordFactura.setLogica(logicaFactura);
		logicaFactura.setCoordi(coordFactura);
		// Declarar Tabla
		tablaListado = new JTable();
		
	}
	private void initComponent() throws FontFormatException, IOException, ParseException {
		// CheckBoxk autoBusqueda
		ckAuto = new JCheckBox();
		ckAuto.setText("Auto-Busqueda");
		custom.ck(ckAuto,3);
		ckAuto.setBounds(10,5,150,30);
		/* Creacion TextFields y ComboBOx */
		
		// TextField de buscar
		txtSearch = new JFormattedTextField();
		txtSearch.setBounds(10, 42, 150, 30);
		custom.txt(txtSearch,3,"Buscar Registro/s");
		/* Creacion de ComboBox y Button*/
		// Combobox
		cbbTipo= new JComboBox();
		custom.cbbBuscador(cbbTipo, "Tipo",1);
		cbbTipo.setBounds(180,42,150,30);
		
		/* Creacion de Botones */
		// Boton Buscar Registro
		btnSearch = new JButton();
		btnSearch.setBounds(340, 38, 52, 35);
		custom.button(btnSearch, "", "\uf002", "solid",5);
		btnSearch.setBorder(new LineBorder(Color.WHITE,2));
		// Boton Actualizar	
		btnClean = new JButton("");
		btnClean.setBounds(400, 38, 56, 35);
		custom.button(btnClean,"", "\uf51a", "solid", 4);
		btnClean.setBorder(new LineBorder(Color.WHITE,2));
		/* Crear Tabla Generica */
		panelTabla = new JScrollPane(tablaListado);
		panelTabla.setBounds(2,80,470,180);
	
		/* Agregar componentes */
		add(cbbTipo);
		add(btnSearch);
		add(btnClean);
		add(panelTabla);
		add(txtSearch);
		add(ckAuto);
	}
	private void initActionButton() throws SQLException {
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
			}});
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
			}});
	
		tablaListado.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	setIdTable( String.valueOf(table.getValueAt(row, 0)));
		        	dispose();
		        }}});
		}
	
	public void setTablePaciente() throws SQLException{
		listaPaciente = coordPaciente.listaPaciente();
		if (listaPaciente != null) {
		columnas = new String[] {"ID","Nombre","Telefono"};
		Object[][] fila = new Object[listaPaciente.size()][4];
        for (int i = 0; i < listaPaciente.size(); i++) {
        	fila[i][0] = listaPaciente.get(i).getIdPaciente();
            fila[i][1] = listaPaciente.get(i).getNombre();
            fila[i][2] = listaPaciente.get(i).getFechaNac();
            fila[i][3] = listaPaciente.get(i).getTelefono();
        }custom.tablaBuscador(tablaListado, fila, columnas);	
        filterCbb(cbbTipo);}
	}
	public void setTableEmpleado() throws SQLException{
		listaEmpleado = coordEmpleado.listaEmpleado();
		if (listaEmpleado != null) {
		columnas = new String[] {"ID","Nombre","Cedula"};
		Object[][] fila = new Object[listaEmpleado.size()][4];
        for (int i = 0; i < listaEmpleado.size(); i++) {
            fila[i][0] = listaEmpleado.get(i).getIdEmpleado();
            fila[i][1] = listaEmpleado.get(i).getNombre() + " "+ listaEmpleado.get(i).getApellido();;
            fila[i][2] = listaEmpleado.get(i).getCedula();
            fila[i][3] = listaEmpleado.get(i).getCargo();
        }custom.table(tablaListado, fila, columnas);
        filterCbb(cbbTipo);}
	}
	public void setTableLab() throws SQLException{
		listaLaboratorio = coordLab.listaLaboratorio();
		if (listaLaboratorio != null) {
		columnas = new String[] {"ID","Nombre","Contacto"};
		Object[][] fila = new Object[listaLaboratorio.size()][3];
        for (int i = 0; i < listaLaboratorio.size(); i++) {
            fila[i][0] = listaLaboratorio.get(i).getIdLaboratorio();
            fila[i][1] = listaLaboratorio.get(i).getNombre();
            fila[i][2] = listaLaboratorio.get(i).getContacto();
        }custom.table(tablaListado, fila, columnas);	
        filterCbb(cbbTipo);
		}
	}	
	
	public void setTableDiagnostico() throws SQLException{
		listaDiagnostico = coordDiagnostico.listaDiagnostico();
		if (listaDiagnostico != null) {
		columnas = new String[] {"ID","Paciente","Doctor","Fecha"};
		Object[][] fila = new Object[listaDiagnostico.size()][4];
        for (int i = 0; i < listaDiagnostico.size(); i++) {
        	fila[i][0] = listaDiagnostico.get(i).getIdDiagnostico();
        	fila[i][1] = listaDiagnostico.get(i).getPaciente().getNombre();
        	fila[i][2] = listaDiagnostico.get(i).getUsuario().getEmpleado().getNombre();
        	fila[i][3] = listaDiagnostico.get(i).getFechaDiagnostico();
        }custom.table(tablaListado, fila, columnas);	
        filterCbb(cbbTipo);
		}
	}
	public void setTableFactura() throws SQLException{
		listaFactura = coordFactura.facturasPendientes();
		if (listaFactura != null) {
		columnas = new String[] {"ID","Paciente","Monto","Fecha"};
		Object[][] fila = new Object[listaFactura.size()][4];
        for (int i = 0; i < listaFactura.size(); i++) {
          	if (listaFactura.get(i).isEstado() == false) {
        	fila[i][0] = listaFactura.get(i).getIdFactura();
        	fila[i][1] = listaFactura.get(i).getPaciente().getNombre();
        	fila[i][2] = listaFactura.get(i).getTotal();
        	fila[i][3] = listaFactura.get(i).getFecha();
          	}
        }custom.table(tablaListado, fila, columnas);	
        filterCbb(cbbTipo);
		}
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
	private void filtrar() {
		sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tablaListado.getModel())); 
	    if (cbbTipo.getSelectedIndex() > 0){
	    	sorter.setRowFilter(RowFilter.regexFilter(txtSearch.getText(), cbbTipo.getSelectedIndex()));
	    }else {
	    	sorter.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
	    }
	    tablaListado.setRowSorter(sorter);
	}
	private void autoBusqueda() {
		if (ckAuto.isSelected() == true) {
			filtrar();}

	}
	public String getIdTable() {
		return idTable;
	}

	public void setIdTable(String idTable) {
		this.idTable = idTable;
	}
	/* Declarar componentes*/
private	JFormattedTextField txtSearch;
private	JComboBox cbbTipo;
private	JLabel background;
private	JButton btnSearch;
private JButton btnClean;
private	TableRowSorter<TableModel> sorter;
private	List<RowSorter.SortKey> sortKeys;
private JScrollPane panelTabla;
private JTable tablaListado;
private JCheckBox ckAuto;
}
