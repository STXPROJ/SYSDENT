package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.placeholder.PlaceHolder;

import Modelo.Coordinador.CoordinadorLogin;
import Modelo.Logica.LogicaLogin;
import Modelo.VO.UsuarioVO;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Login extends JFrame {

private JPanel contentPane;
private CoordinadorLogin coordLogin;
private LogicaLogin logicaLogin;
private ComponentCustomization custom = new ComponentCustomization();
private UsuarioVO usuarioActivo;
private JButton btnLogin;
private Menu menu;
	    public UsuarioVO getUsuario() {
	        return usuarioActivo;
	    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setBackground(Color.black);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public Login() throws ParseException, FontFormatException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(null);
		
		initCoordLogin();
		initComponent();
		initActionButton();
	}
	private void initCoordLogin() {
		coordLogin = new CoordinadorLogin();
		logicaLogin = new LogicaLogin();
		coordLogin.setLogica(logicaLogin);
		logicaLogin.setCoordinador(coordLogin);
	}
	private void initComponent() throws FontFormatException, IOException {
		Font font = new Font("Lucida Bright", Font.PLAIN, 25);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(89, 173, 238));
	
		lblSitema = new JLabel("Sistema");
		lblSitema.setBounds(150, 0, 300, 50);
		lblSitema.setForeground(Color.WHITE);
		lblSitema.setFont(new Font("Lucida Bright", Font.PLAIN, 40));
		
		lblConsult= new JLabel("Consultorio Dental");
		lblConsult.setBounds(50, 25, 500, 100);
		lblConsult.setForeground(Color.WHITE);
		lblConsult.setFont(new Font("Lucida Bright", Font.PLAIN, 40));
		
		lblUser = new JLabel("Usuario:");
		lblUser.setBounds(150, 100, 500, 100);
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(font);
		
		lblPass = new JLabel("Contrasena:");
		lblPass.setBounds(140, 200, 500, 100);
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(font);
		
		txtUser = new JTextField();
		txtUser.setBounds(115,175,200,40);
		txtUser.setFont(font);
		txtUser.setBackground(new Color(100,100,100));
		txtUser.setForeground(Color.white);
		txtUser.setSelectionColor(Color.WHITE);
		
		txtpssw = new JPasswordField();
		txtpssw.setBounds(115,275,200,40);
		txtpssw.setFont(font);
		txtpssw.setBackground(new Color(100,100,100));
		txtpssw.setForeground(Color.white);
		
		btnLogin = new JButton();
		btnLogin.setBounds(100, 335, 225, 60);
		custom.button(btnLogin, "Iniciar Sesion", "\uf2f5", "solid",2);
		btnLogin.setBackground(new Color(100,100,100));
		
		contentPane.add(lblSitema);
		contentPane.add(lblConsult);
		contentPane.add(lblUser);
		contentPane.add(lblPass);
		contentPane.add(txtUser);		
		contentPane.add(txtpssw);
		contentPane.add(btnLogin);
	}
	private void initActionButton() {
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			try {
				ingresar();
			} catch (SQLException | FontFormatException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
	}
	private void ingresar() throws SQLException, FontFormatException, IOException, ParseException{
	
	        UsuarioVO usuarioActivo = new  UsuarioVO(); 
	        usuarioActivo.setNombre(txtUser.getText());
	        usuarioActivo.setPassword(new String(txtpssw.getPassword()));
	        if(!(coordLogin.usuarioID(usuarioActivo))== false) {
	        	menu = new Menu();
	        	usuarioActivo = coordLogin.usuarioActivo(usuarioActivo.getNombre());
	        	this.dispose();
	        	menu.setUsuarioActivo(usuarioActivo);
	        	menu.setVisible(true);
}}
		JLabel lblConsult;
		JLabel lblSitema;
		JLabel lblUser;
		JLabel lblPass; 
		private JTextField txtUser;
		private JPasswordField txtpssw;
}
