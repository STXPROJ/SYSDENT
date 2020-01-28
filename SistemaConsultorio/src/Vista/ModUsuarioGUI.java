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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Modelo.Coordinador.CoordinadorUsuario;
import Modelo.DAO.EmpleadoDAO;
import Modelo.DAO.RolDAO;
import Modelo.Logica.LogicaUsuario;
import Modelo.Logica.ValidationClass;
import Modelo.VO.EmpleadoVO;
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


public class ModUsuarioGUI extends JPanel {
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
	/**
	 * @throws SQLException 
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws  
	 */
	public ModUsuarioGUI() throws FontFormatException, IOException, ParseException, SQLException  {
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
		lblidUsuario = new JLabel();
		lblidUsuario.setBounds(38,115,564,45);
		custom.label(lblidUsuario, "ID Usuario.: ", 3);
		// Creacion de Label idEmpleado
		 lblidEmpleado = new JLabel();
		lblidEmpleado.setBounds(38, 155, 564, 39);
		custom.label(lblidEmpleado, "ID de Empleado.: ",1);
		// Label  Nombre Empleado
		lblEmpleado = new JLabel("");
		lblEmpleado.setBounds(38, 195, 564, 39);
		custom.label(lblEmpleado, "Nombre del Empleado:",1);
		// Label Nombre
		lblNombre = new JLabel("");
		lblNombre.setBounds(38, 235, 750, 39);
		custom.label(lblNombre, "",2);
		lblNombre.setSize(lblNombre.getPreferredSize());
		// Label Rol
		lblRol = new JLabel();
		lblRol.setBounds(38, 290, 215, 39);
		custom.label(lblRol, "Rol:",1);
		//Label Usuario
		lblUsuario = new JLabel();
		lblUsuario.setBounds(480,290,532,39);
		custom.label(lblUsuario, "Usuario:", 1);
		// Label Contraseña
		lblContra = new JLabel();
		lblContra.setBounds(38,405,215,39);
		custom.label(lblContra, "Contraseña:", 1);
		// Label confirmar Contraseña
		lblConfirmarcontra = new JLabel();
		lblConfirmarcontra.setBounds(480,405,532,39);
		custom.label(lblConfirmarcontra, "Confirmar Contraseña:", 1);
		// Label Nombre Rol
		lblNombreRol= new JLabel();
		custom.label(lblNombreRol, "Nuevo Rol:", 1);
		/* Creacion TextFields y ComboBox */
		// TextField de Usuario
		txtUsuario = new JFormattedTextField();
		txtUsuario.setBounds(480, 335, 260, 45);
		custom.txt(txtUsuario,1,"Ingrese el Nombre de Usuario");
		// TextField de Contraseña
		txtpassw = new JPasswordField(10);
		txtpassw.setBounds(38, 445, 215, 45);
		custom.passwordField(txtpassw);
		// TextField de Confirmar Contra
		txtConfirmarCont = new JPasswordField(10);
		txtConfirmarCont.setBounds(480, 445, 215, 45);
		custom.passwordField(txtConfirmarCont);
		// TextField de Nuevo Rol
		txtRol = new JTextField(10);
		custom.txt(txtRol, 1,"Ingrese el nombre del nuevo Rol de Usuario");
		// ComboBox Rol
		cbbRol = new JComboBox();
		custom.cbb(cbbRol, "Rol",8);
		listadoRol(cbbRol);
		cbbRol.setBounds(38, 335, 215, 45);	/* Creacion de Botones */
		// Boton Guardar Cambios
		 btnGuardar = new JButton("");
			btnGuardar.setBounds(175, 525, 245, 74);
			custom.button(btnGuardar, "Guardar Cambios ", "\uf0c7", "solid", 3);
			// Boton Cancelar Cambios
			btnCancelar = new JButton();
			btnCancelar.setBounds(600, 525, 255, 74);
			custom.button(btnCancelar, "Cancelar Cambios", "\uf05e", "solid", 6);
			// Boton rOL
			btnRol = new JButton();
			custom.button(btnRol, "", "\uf65e", "solid", 3);
			btnRol.setBorder(null);
			btnRol.setBackground(null);
			btnRol.setForeground(new Color(61, 204, 45));
			btnRol.setToolTipText("Agregar Nuevo Rol");
			// Panel Nuevo Rol
			background = new JPanel();
			custom.confirmacion(background, "Rol");		
			background.add(lblNombreRol);
			background.add(txtRol);
			background.add(btnRol);
			background.setBounds(225, 5, 600, 80);
			// Background Modificar
			lblBackground = new JLabel();
			lblBackground.setBounds(25, 95, 1120, 420);
			custom.modificacionBG(lblBackground, "Usuario");
		/* Agregar Componentes */
		add(lblidUsuario);
		add(lblidEmpleado);
		add(lblEmpleado);
		add(lblNombre);
		add(lblRol);
		add(lblContra);
		add(lblUsuario);
		add(lblConfirmarcontra);
		add(txtUsuario);
		add(txtpassw);
		add(txtConfirmarCont);
		add(cbbRol);
		add(btnGuardar);
		add(btnCancelar);
		add(background);
		add(lblBackground);
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
	
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
					coordUsuario.addUsuario(usuario);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				}}});
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(custom.msgConfirm("Desea Cancelar los cambios?", 1) == JOptionPane.YES_OPTION){
					goListado();}
				}});
		btnRol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					confirmacion();
				} catch (FontFormatException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
	/* Llenar ComboBox Rol*/
	private void listadoRol(JComboBox cbb) throws SQLException {
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
      	cbb.setSelectedIndex(selected); 
	}}private void goListado() {
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
	public String title() {
		String title = "Crear Usuario";
		return title;
	}
	public JPanel getUsuario(UsuarioVO usuario) throws ParseException {
		lblidUsuario.setText(lblidUsuario.getText() +  String.format("%05d",usuario.getIdUsuario()));
		lblidEmpleado.setText(lblidEmpleado.getText() + String.format("%05d", usuario.getEmpleado().getIdEmpleado()));
		lblNombre.setText(usuario.getEmpleado().getNombre());
		lblNombre.setSize(lblNombre.getPreferredSize());
		txtConfirmarCont.setText(usuario.getPassword());
		txtpassw.setText(usuario.getPassword());
		txtUsuario.setText(usuario.getNombre());
		cbbRol.setSelectedItem(usuario.getRol().getNombre());
		return this;
	}
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
			   
	/* Declarar Componentes */
	JLabel lblidUsuario;
	JLabel lblidEmpleado;
	JLabel lblEmpleado;
	JLabel lblNombre;
	JLabel lblRol;
	JLabel lblUsuario;
	JLabel lblContra;
	JLabel lblConfirmarcontra;
	JLabel lblNombreRol;
	JLabel lblBackground;
	JTextField txtRol;
	JPanel background;
	JFormattedTextField txtUsuario;
	JPasswordField txtpassw;
	JPasswordField txtConfirmarCont;
	JComboBox cbbRol;
	JButton btnGuardar;
	JButton btnRol;
	JButton btnCancelar;
}
