package Vista;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JTextArea;

import Modelo.Coordinador.CoordinadorLaboratorio;
import Modelo.Logica.LogicaLaboratorio;
import Modelo.Logica.ValidationClass;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.UsuarioVO;

public class ModLaboratorioGUI extends JPanel {
private static String[] COLUMNS = new String[] {"ID","Nombre","Descripcion","Contacto","Telefono",
		"Correo","RNC"};
private static String[] tableLab = new String[] {"Laboratorio","Laboratorio"};
private ComponentCustomization custom = new ComponentCustomization();
private ValidationClass validation = new ValidationClass();
private CoordinadorLaboratorio coordLaboratorio;
private LogicaLaboratorio logicaLaboratorio;
private LaboratorioVO laboratorio;
private Listados pnl;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public ModLaboratorioGUI() throws FontFormatException, IOException, ParseException, SQLException {
	
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
		// Iniciar Logica y Coordinador
		initCoordLogic();
		// Iniciar Componentes
		initComponent();
		// Iniciar Accion de botones
		initActionButton();
	
		
	}
	private void initCoordLogic() {
		/* Set Logica y Coordinador */
		coordLaboratorio = new CoordinadorLaboratorio();
		logicaLaboratorio = new LogicaLaboratorio();
		coordLaboratorio.setLogica(logicaLaboratorio);
		logicaLaboratorio.setCoordinador(coordLaboratorio);
	}
	private void initComponent() throws ParseException, FontFormatException, IOException {
		lblidLab = new JLabel();
		lblidLab.setBounds(38,30,564,45);
		custom.label(lblidLab, "ID Laboratorio.: ", 3);
		// Creacion de Label Nombres Lab
		lblNombreLab = new JLabel("Id ");
		lblNombreLab.setBounds(38, 70, 564, 39);
		custom.label(lblNombreLab, "Nombres:",1);
		// Label  Contacto
		lblContacto = new JLabel("");
		lblContacto.setBounds(38, 190, 564, 39);
		custom.label(lblContacto, "Contacto:",1);
		// Label Correo
		lblCorreo = new JLabel();
		lblCorreo.setBounds(38, 310, 215, 39);
		custom.label(lblCorreo, "Correo:",1);
		//Label Descripcion
		lblDescripcion = new JLabel();
		lblDescripcion.setBounds(608,70,532,39);
		custom.label(lblDescripcion, "Descripcion:", 1);
		// Label Descripcion
		lblRNC = new JLabel();
		lblRNC.setBounds(608,180,265,39);
		custom.label(lblRNC, "RNC:", 1);
		// Label Telefono
		lblTelefono = new JLabel();
		lblTelefono.setBounds(875,180,265,39);
		custom.label(lblTelefono, "Telefono:", 1);
		// Label Dirccion
		lblDireccion = new JLabel();
		lblDireccion.setBounds(608,310,265,39);
		custom.label(lblDireccion, "Direccion:", 1);
		
		/* Creacion TextFields y ComboBox */
		// TextField de Nombre Laboratorio
		txtNombreLab = new JFormattedTextField();
		txtNombreLab.setBounds(38, 110, 525, 45);
		custom.txt(txtNombreLab,1,"Ingrese el Nombre del Laboratorio o Empresa");
		// TextField de Descripcion
		txtDescripcion = new JFormattedTextField();
		txtDescripcion.setBounds(608, 110, 525, 45);
		custom.txt(txtDescripcion,1,"Descripcion del Laboratorio (Especialidad o Tipo de Laboratorio o Empresa");
		// TextField de Contacto
		txtContacto = new JFormattedTextField();
		txtContacto.setBounds(38, 230, 525, 45);
		custom.txt(txtContacto,1,"Ingrese el Contacto dentro del Laboratorio o Empresa");
		// TextField de RNC
		txtRNC = new JFormattedTextField(custom.formato("rnc"));
		txtRNC.setBounds(608, 230, 230, 45);
		custom.txt(txtRNC,1,"Ingrese RNC de la Empresa");
		// TextField de Telefono
		txtTel = new JFormattedTextField(custom.formato("telefono"));
		txtTel.setBounds(875, 230, 235, 45);
		custom.txt(txtTel,1,"Ingrese el Telefono de la Empresa o Laboratorio");
		// TextField de Correo
		txtCorreo = new JFormattedTextField();
		txtCorreo.setBounds(38, 350, 525, 45);
		custom.txt(txtCorreo,1,"Ingrese el correo electronico del Laboratorio o Empresa");
		// Text Area de Direccion
		txtDireccion = new JTextArea();
		txtDireccion.setBounds(608,350,525,155);
		custom.txtArea(txtDireccion);
		/* Creacion de Botones */
		// Boton Guardar Cambios
		btnGuardar = new JButton("");
		btnGuardar.setBounds(175, 525, 245, 74);
		custom.button(btnGuardar, "Guardar Cambios ", "\uf0c7", "solid", 3);
		// Boton Cancelar Cambios
		btnCancelar = new JButton();
		btnCancelar.setBounds(600, 525, 255, 74);
		custom.button(btnCancelar, "Cancelar Cambios", "\uf05e", "solid", 6);
		// Background Modificar
		lblBackground = new JLabel();
		lblBackground.setBounds(25, 15, 1120, 510);
		custom.modificacionBG(lblBackground, "Laboratorio");
				/* Agregar Componentes */
				add(lblidLab);
				add(lblNombreLab);
				add(lblContacto);
				add(lblCorreo);
				add(lblRNC);
				add(lblDescripcion);
				add(lblTelefono);
				add(lblDireccion);
				add(txtNombreLab);
				add(txtContacto);
				add(txtCorreo);
				add(txtDescripcion);
				add(txtRNC);
				add(txtDireccion);
				add(txtTel);
				add(btnGuardar);
				add(btnCancelar);
				add(lblBackground);
	}
	private void initActionButton() {
		/* Accion de Botones */
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				laboratorio = new LaboratorioVO();
				laboratorio.setNombre(txtNombreLab.getText());
				laboratorio.setDireccion(txtDescripcion.getText());
				laboratorio.setRnc(txtRNC.getText());
				laboratorio.setTelefono(txtTel.getText());
				laboratorio.setContacto(txtContacto.getText());
				laboratorio.setCorreo(txtCorreo.getText());
				laboratorio.setDescripcion(txtDescripcion.getText());
				coordLaboratorio.addLaboratorio(laboratorio);
			}});
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(custom.msgConfirm("Desea Cancelar los cambios?", 1) == JOptionPane.YES_OPTION){
					goListado();}
				}});
	}
	private void goListado() {
		try {
			pnl = new Listados();
			removeAll();
			add(pnl.getPanel());
			revalidate();
			repaint();
			pnl.setTableLab(coordLaboratorio.listaLaboratorio(), COLUMNS,"Laboratorio",tableLab);
		} catch (SQLException | FontFormatException | IOException | ParseException e1) {
			e1.printStackTrace();
		}
	}
	public JPanel getLab(LaboratorioVO laboratorio) throws ParseException {
		lblidLab.setText(lblidLab.getText() +  String.format("%05d",laboratorio.getIdLaboratorio()));
		txtContacto.setText(laboratorio.getContacto());
		txtCorreo.setText(laboratorio.getCorreo());
		txtDescripcion.setText(laboratorio.getDescripcion());
		txtDireccion.setText(laboratorio.getDireccion());
		txtNombreLab.setText(laboratorio.getNombre());
		txtRNC.setText(laboratorio.getRnc());
		txtTel.setText(laboratorio.getTelefono());
		return this;
	}
	public String title() {
		String title = "Nuevo Laboratorio";
		return title;
	}
	/* Declaracion Componentes */
		JLabel lblidLab;
	 	JLabel lblNombreLab;
		JLabel lblContacto;
		JLabel lblCorreo;
		JLabel lblDescripcion;
		JLabel lblRNC;
		JLabel lblTelefono;
		JLabel lblDireccion;
		JFormattedTextField txtNombreLab;
		JFormattedTextField txtContacto;
		JFormattedTextField txtCorreo;
		JFormattedTextField txtDescripcion;
		JFormattedTextField txtRNC;
		JFormattedTextField txtTel;
		JTextArea txtDireccion;
		JButton btnGuardar;
		JButton btnCancelar;
		JLabel lblBackground;
}
