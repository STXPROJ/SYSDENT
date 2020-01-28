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
import javax.swing.JTextField;

import Modelo.Coordinador.CoordinadorUsuario;
import Modelo.DAO.EmpleadoDAO;
import Modelo.DAO.RolDAO;
import Modelo.Logica.LogicaUsuario;
import Modelo.Logica.ValidationClass;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.RolVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.UsuarioVO;

import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;


public class UsuarioGUI extends JPanel {
private static String[]COLUMNS = new String[] {"ID","Nombre","Usuario","Tipo de Usuario"};
private static String[] tableUsuario = new String[] {"Usuario","Usuario"};
private ComponentCustomization custom = new ComponentCustomization();
private ArrayList<RolVO> listaRol;
private CoordinadorUsuario coordUsuario;
private LogicaUsuario logicaUsuario;
private RolVO rol;
private EmpleadoVO empleado;
private RolDAO consultaRol;
private EmpleadoDAO consultaEmpleado;
private UsuarioVO usuario;
private ValidationClass validation = new ValidationClass();
private Listados pnl;
private ConfirmacionUser confirmaDialog;
private BuscadorDialog buscadorDialog;
	/**
	 * @throws SQLException 
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws  
	 */
	public UsuarioGUI() throws FontFormatException, IOException, ParseException, SQLException  {
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
		// Iniciar Logica Coordinador y ComboBox
		initCoordLogic();
		// Iniciar Componenetes
		initComponent();
		// Iniciar Acciones de botones
		initActionButton();
		
	
	}
	private void initCoordLogic() throws SQLException {
		/* Set Logica y Coordinador */
		coordUsuario = new CoordinadorUsuario();
		logicaUsuario = new LogicaUsuario();
		coordUsuario.setLogica(logicaUsuario);
		logicaUsuario.setCoordinador(coordUsuario);
		/* Listados de ComboBox*/
		rol = new RolVO();
		empleado = new EmpleadoVO();
	}
	private void initComponent() throws FontFormatException, IOException, ParseException, SQLException {
		// ID
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,128,25,25);
		add(required);
		// ROL
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,273,25,25);
		add(required);
		// Usuario
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(467,273,25,25);
		add(required);
		// Contrasena
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,387,25,25);
		add(required);
		// Repetir contrasena
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(467,387,25,25);
		add(required);
		
		
		// Creacion de Label idEmpleado
		 lblidEmpleado = new JLabel();
		lblidEmpleado.setBounds(38, 115, 564, 39);
		custom.label(lblidEmpleado, "ID de Empleado.:",1);
		// Label  Nombre Empleado
		lblEmpleado = new JLabel();
		lblEmpleado.setBounds(38, 170, 564, 39);
		custom.label(lblEmpleado, "Nombre del Empleado:",1);
		// Label Nombre
		lblNombre = new JLabel();
		lblNombre.setBounds(38, 210, 750, 39);
		custom.label(lblNombre, "",2);
		lblNombre.setSize(lblNombre.getPreferredSize());
		// Label Rol
		lblRol = new JLabel();
		lblRol.setBounds(38, 260, 215, 39);
		custom.label(lblRol, "Rol:",1);
		//Label Usuario
		lblUsuario = new JLabel();
		lblUsuario.setBounds(480,260,532,39);
		custom.label(lblUsuario, "Usuario:", 1);
		// Label Contraseña
		lblContra = new JLabel();
		lblContra.setBounds(38,375,215,39);
		custom.label(lblContra, "Contraseña:", 1);
		// Label confirmar Contraseña
		lblConfirmarcontra = new JLabel();
		lblConfirmarcontra.setBounds(480,375,532,39);
		custom.label(lblConfirmarcontra, "Confirmar Contraseña:", 1);
		// Label Nombre Rol
		lblNombreRol= new JLabel();
		custom.label(lblNombreRol, "Nuevo Rol:", 1);
		/* Creacion TextFields y ComboBox */
		// TextField de idEmpleado
		txtidEmpleado = new JFormattedTextField();
		txtidEmpleado.setBounds(235, 115, 215, 45);
		custom.txt(txtidEmpleado,1,"Ingrese el ID del Empleado");
		// TextField de Usuario
		txtUsuario = new JFormattedTextField();
		txtUsuario.setBounds(480, 300, 260, 45);
		custom.txt(txtUsuario,1,"Ingrese el Nombre de Usuario");
		// TextField de Contraseña
		txtpassw = new JPasswordField(10);
		txtpassw.setBounds(38, 415, 215, 45);
		custom.passwordField(txtpassw);
		// TextField de Confirmar Contra
		txtConfirmarCont = new JPasswordField(10);
		txtConfirmarCont.setBounds(480, 415, 215, 45);
		custom.passwordField(txtConfirmarCont);
		// TextField de Nuevo Rol
		txtRol = new JTextField(10);
		custom.txt(txtRol, 1,"Ingrese el nombre del nuevo Rol de Usuario");
		// ComboBox Rol
		cbbRol = new JComboBox();
		custom.cbb(cbbRol, "Rol",8);
		listadoRol(cbbRol);
		cbbRol.setBounds(38, 300, 215, 45);
		/* Creacion de Botones */
		// Boton Buscar Empleado
		btnSearch = new JButton();
		btnSearch.setBounds(455, 105, 75, 66);
		custom.button(btnSearch, "", "\uf002", "solid",5);
		btnSearch.setToolTipText("Buscar por id del Empleado");
		// Boton Buscador Dialog
		btnBuscador = new JButton();
		btnBuscador.setBounds(525, 105, 75, 66);
		custom.button(btnBuscador, "", "\uf00e", "solid",9);
		btnBuscador.setToolTipText("Abrir Listado de Empleado");
		// Boton Agregar Usuaro
		btnAgregar = new JButton("");
		btnAgregar.setBounds(38, 525, 242, 74);
		custom.button(btnAgregar, "Agregar Usuario", "\uf4ff", "solid", 3);
		// Boton Limpiar Campos	
		btnClean = new JButton("");
		btnClean.setBounds(760, 525, 242, 74);
		custom.button(btnClean, "Limpiar Campos", "\uf51a", "solid", 4);
		// Boton listado Usuario
		btnListado = new JButton();
		btnListado.setBounds(400, 525, 255, 74);
		custom.button(btnListado, "Listado Usuarios", "\uf03a", "solid", 6);
		// Boton Cargo
		btnRol = new JButton();
		custom.button(btnRol, "", "\uf65e", "solid", 3);
		btnRol.setBorder(null);
		btnRol.setBackground(null);
		btnRol.setForeground(new Color(61, 204, 45));
		btnRol.setToolTipText("Agregar Nuevo Rol");
		// Panel Nuevo Cargo
		background = new JPanel();
		custom.confirmacion(background, "Rol");		
		background.add(lblNombreRol);
		background.add(txtRol);
		background.add(btnRol);
		background.setBounds(225, 5, 600, 80);
		/* Agregar Componentes */
		add(lblidEmpleado);
		add(lblEmpleado);
		add(btnSearch);
		add(lblNombre);
		add(lblRol);
		add(lblContra);
		add(lblUsuario);
		add(lblConfirmarcontra);
		add(txtidEmpleado);
		add(txtUsuario);
		add(txtpassw);
		add(txtConfirmarCont);
		add(cbbRol);
		add(btnAgregar);
		add(btnClean);
		add(btnListado);
		add(btnBuscador);
		add(background);
		componentEnabled(false);
	}
	private void initActionButton() {
		cbbRol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i =0;i<listaRol.size();i++) {
					if (cbbRol.getSelectedItem() != null &&
							cbbRol.getSelectedItem().toString().equals(listaRol.get(i).getNombre())) {
					rol.setNombre(listaRol.get(i).getNombre());
					rol.setIdRol(listaRol.get(i).getIdRol());}
				}}});
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				getEmpleado();
			} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
			}}});
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addUsuario();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}});
		btnListado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goListado();
			}
		});
		btnClean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnRol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				confirmacion();
			} catch (FontFormatException | IOException e1) {
				// TODO Auto-generated catch block
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
	}
	/* Llenar ComboBox Rol*/
	private void listadoRol(JComboBox cbb) throws SQLException {
		if(!coordUsuario.listaRol().isEmpty()){
		int selected = cbb.getSelectedIndex();
		DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
		if (!coordUsuario.listaRol().isEmpty()) {
		listaRol = coordUsuario.listaRol();
		   // Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<listaRol.size();i++) {
	            model.addElement(listaRol.get(i).getNombre());
	        }
	        // setting model with new data
	        cbb.setModel(model);
	        cbb.setRenderer(new MyComboBox("Rol")); 
      	cbb.setSelectedIndex(selected); }
	}}
	private void goListado() {
		try {	
			pnl = new Listados();
			removeAll();
			add(pnl.getPanel());
			revalidate();
			repaint();
			pnl.setTableUser(coordUsuario.listaUsuario(), COLUMNS,"Usuario",tableUsuario);
		} catch (SQLException | FontFormatException | IOException | ParseException e1) {
			e1.printStackTrace();
		}
	}
	private void limpiarCampos() {
		txtConfirmarCont.setText("");
		txtidEmpleado.setText("");
		txtpassw.setText("");
		txtUsuario.setText("");
		cbbRol.setSelectedIndex(-1);
		lblNombre.setText("");
		lblNombre.setSize(lblNombre.getPreferredSize());
		componentEnabled(false);
	}
	private void addUsuario() throws SQLException {
		usuario = new UsuarioVO();
		if (!(cbbRol.getSelectedIndex()>=0)) 
		{custom.msg("Debe Seleccionar un Rol",7);}
		else if (!(txtpassw.getText().equals(txtConfirmarCont.getText()))) {
			custom.msg("La Contraseña no coincide", 6);	
			txtConfirmarCont.setText("");
			txtpassw.setText("");}
		else {
		usuario.setNombre(txtUsuario.getText());
		usuario.setRol(rol);
		usuario.setPassword(txtpassw.getText());
		usuario.setEmpleado(empleado);
		try {
			if(coordUsuario.addUsuario(usuario) == true) {
			limpiarCampos();}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}}
	}
	private void getEmpleado() throws NumberFormatException, SQLException {
			if (!(validation.validDigit(txtidEmpleado.getText()) == false)) {
				empleado = coordUsuario.buscarEmplead(Integer.parseInt(txtidEmpleado.getText()));
				if(empleado!=null) {
				lblNombre.setText(empleado.getNombre() + " " + empleado.getApellido());
				lblNombre.setSize(lblNombre.getPreferredSize());
				componentEnabled(true);}
			else {
				txtidEmpleado.setText("");}
			}
			else {
				custom.msg("id del paciente", 5);
				txtidEmpleado.setText("");
			}}
	private void confirmacion() throws FontFormatException, IOException {
		confirmaDialog = new ConfirmacionUser();
		confirmaDialog.setVisible(true);
		confirmaDialog.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {
		if(confirmaDialog.isConfirmacion() == true) {
	    	  rol = new RolVO();
	    	  rol.setNombre(txtRol.getText());
			  try {
	    		  coordUsuario.addRol(rol);
	    		  listadoRol(cbbRol);
				  txtRol.setText("");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}}}});
		
	}
	private void goBuscador() throws FontFormatException, IOException, ParseException, SQLException {
	buscadorDialog = new BuscadorDialog();
	buscadorDialog.setVisible(true);
	buscadorDialog.setTableEmpleado();
	buscadorDialog.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosed(WindowEvent e) {
	    	txtidEmpleado.setText(String.valueOf(buscadorDialog.getIdTable()));	
	    	 	try {
					getEmpleado();
				} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
				}}});
	} 
	private void componentEnabled(boolean bool) {
		cbbRol.enable(bool);
		txtConfirmarCont.setEnabled(bool);
		txtpassw.setEnabled(bool);
		txtUsuario.setEnabled(bool);
		btnAgregar.setEnabled(bool);
	}
	public String title() {
		String title = "Crear Usuario";
		return title;
	}
	/* Declarar Componentes */
	JLabel lblidEmpleado;
	JLabel lblEmpleado;
	JLabel lblNombre;
	JLabel lblRol;
	JLabel lblUsuario;
	JLabel lblContra;
	JLabel lblConfirmarcontra;
	JLabel lblNombreRol;
	JTextField txtRol;
	JPanel background;
	JFormattedTextField txtidEmpleado;
	JFormattedTextField txtUsuario;
	JPasswordField txtpassw;
	JPasswordField txtConfirmarCont;
	JComboBox cbbRol;
	JButton btnSearch;
	JButton btnAgregar;
	JButton btnClean;
	JButton btnListado;
	JButton btnRol;
	JButton btnBuscador;
	JLabel required;
}
