package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Modelo.Coordinador.CoordinadorLogin;
import Modelo.Logica.LogicaLogin;
import Modelo.VO.UsuarioVO;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;

public class ConfirmacionUser extends JDialog {
	private ComponentCustomization custom = new ComponentCustomization();
	private CoordinadorLogin coordLogin;
	private LogicaLogin logicaLogin;
	private boolean confirmacion;
	public boolean isConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(boolean confirmacion) {
		this.confirmacion = confirmacion;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfirmacionUser dialog = new ConfirmacionUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public ConfirmacionUser() throws FontFormatException, IOException {
		setBounds(100, 100, 450, 225);
		getContentPane().setLayout(null);
		setTitle("Ventana de Confirmacion de Usuario con Admin");
		initCoordLogin();
		initComponent();
		initActionButton();
		setModal(true);
	}
	private void initCoordLogin() {
		coordLogin = new CoordinadorLogin();
		logicaLogin = new LogicaLogin();
		coordLogin.setLogica(logicaLogin);
		logicaLogin.setCoordinador(coordLogin);
	}
	private void initComponent() throws FontFormatException, IOException {
Font lblfont = new Font("Tahoma", Font.BOLD, 15);
Font txtFont = new Font("Tahoma", Font.PLAIN, 15);
		
		lblTitle = new JLabel();
		lblTitle.setText("Ingrese Credenciales de Usuario Admin");
		lblTitle.setBounds(75, 0, 500, 50);
		getContentPane().add(lblTitle);
		lblTitle.setFont(lblfont);
		
		lblUser = new JLabel("Usuario");
		lblUser.setBounds(60, 42, 150, 50);
		getContentPane().add(lblUser);
		lblUser.setFont(lblfont);
		
		lblPassword = new JLabel("Contraseña");
		lblPassword.setBounds(44, 82, 150, 50);
		getContentPane().add(lblPassword);
		lblPassword.setFont(lblfont);
		
		txtUser = new JTextField();
		txtUser.setBounds(158, 55, 175, 25);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		txtUser.setFont(txtFont);
		
		txtpssw = new JPasswordField();
		txtpssw.setBounds(158, 95, 175, 25);
		getContentPane().add(txtpssw);
		txtpssw.setFont(txtFont);

		btnConfirmar = new JButton();
		btnConfirmar.setBounds(130, 135, 175, 45);
		custom.button(btnConfirmar, "Confirmar", "\uf2f5", "solid",4);
		btnConfirmar.setBorder(new LineBorder(Color.BLACK,3));
		getContentPane().add(btnConfirmar);
	}
	private void initActionButton() {
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ingresar();
				} catch (SQLException | FontFormatException | IOException | ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	boolean ingresar() throws SQLException, FontFormatException, IOException, ParseException{
			boolean bool = false;
	        UsuarioVO usuarioActivo = new  UsuarioVO(); 
	        usuarioActivo.setNombre(txtUser.getText());
	        usuarioActivo.setPassword(new String(txtpssw.getPassword()));
	        if(!(coordLogin.usuarioID(usuarioActivo))== false) {
	        	dispose();
	        	bool = true;
	        	setConfirmacion(bool);
	        }
	return bool;
}
	private JButton btnConfirmar;
	private JLabel lblTitle;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JTextField txtUser;
	private JPasswordField txtpssw;
}
