package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Modelo.Coordinador.CoordinadorLogin;
import Modelo.Logica.LogicaLogin;
import Modelo.VO.TipoPagoVO;

public class FormaPagoDialog extends JDialog {
	private ComponentCustomization custom = new ComponentCustomization();
	private TipoPagoVO tipo;
	private final JPanel contentPanel = new JPanel();
	private String descripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormaPagoDialog dialog = new FormaPagoDialog();
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
	public FormaPagoDialog() throws FontFormatException, IOException {
		setBounds(500, 150, 450, 275);
		getContentPane().setLayout(null);
		setTitle("Ventana de Descripcion Adicional de Forma de Pago");
		initComponent();
		initActionButton();
		setModal(true);
}
	private void initComponent() throws FontFormatException, IOException {
		Font lblfont = new Font("Tahoma", Font.BOLD, 15);
		Font txtFont = new Font("Tahoma", Font.PLAIN, 15);
				
				lblTitle = new JLabel();
				lblTitle.setText("Ingrese la Descripcion de ");
				lblTitle.setBounds(60, 0, 500, 50);
				getContentPane().add(lblTitle);
				lblTitle.setFont(lblfont);
				
				lblDescripcion1 = new JLabel();
				lblDescripcion1.setBounds(60, 42, 150, 50);
				getContentPane().add(lblDescripcion1);
				lblDescripcion1.setFont(lblfont);
				
				lblDescripcion2 = new JLabel();
				lblDescripcion2.setBounds(44, 82, 150, 50);
				getContentPane().add(lblDescripcion2);
				lblDescripcion2.setFont(lblfont);
				
				lblMonto = new JLabel();
				lblMonto.setBounds(60,122,150,50);
				getContentPane().add(lblMonto);
				lblMonto.setFont(lblfont);
				
				txtDescripcion1 = new JTextField();
				txtDescripcion1.setBounds(158, 55, 250, 25);
				getContentPane().add(txtDescripcion1);
				txtDescripcion1.setColumns(10);
				txtDescripcion1.setFont(txtFont);
				
				txtDescripcion2 = new JTextField();
				txtDescripcion2.setBounds(158, 95, 250, 25);
				getContentPane().add(txtDescripcion2);
				txtDescripcion2.setFont(txtFont);

				txtMonto = new JTextField();
				txtMonto.setBounds(158,135,250,25);
				getContentPane().add(txtMonto);
				txtMonto.setFont(txtFont);
				
				btnConfirmar = new JButton();
				btnConfirmar.setBounds(130, 175, 175, 45);
				custom.button(btnConfirmar, "Continuar", "\uf2f5", "solid",4);
				btnConfirmar.setBorder(new LineBorder(Color.BLACK,3));
				getContentPane().add(btnConfirmar);
			}
	private void initActionButton() {
		btnConfirmar.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
			if (custom.msgConfirm("Desea Realizar esta accion?", 1) == JOptionPane.YES_OPTION) {	
				setDescripcion(txtDescripcion1.getText() + "/" + txtDescripcion2.getText());
				dispose();
			}}});
	}
	public void setLabelText(TipoPagoVO tipo) {
		this.tipo = tipo;
		if (!(tipo.getTipo().equals("Cheque"))) {
			lblDescripcion1.setText("Operador");
			lblDescripcion2.setText("Descripcion");
		}
		else {
			lblDescripcion1.setText("Banco");
			lblDescripcion2.setText("No. de CK");
		}
		lblTitle.setText(lblTitle.getText() + tipo.getTipo());
		lblMonto.setText("Monto");
	}
		
	public TipoPagoVO getTipo() {
		return tipo;
	}

	public void setTipo(TipoPagoVO tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	private JButton btnConfirmar;
	private JLabel lblTitle;
	private JLabel lblDescripcion1;
	private JLabel lblDescripcion2;
	private JTextField txtDescripcion1;
	private JTextField txtDescripcion2;
	private JLabel lblMonto;
	public JTextField txtMonto;
}