package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Modelo.Coordinador.CoordinadorDiagnostico;
import Modelo.Logica.LogicaDiagnostico;
import Modelo.VO.DentagramaVO;
import Modelo.VO.DiagnosticoVO;

import Modelo.VO.PacienteVO;
import Modelo.VO.TipoPagoVO;

public class DentagramaDialog extends JDialog {
	private ComponentCustomization custom = new ComponentCustomization();
	private final JPanel contentPanel = new JPanel();
	private DentagramaVO dentagrama;
	private PacienteVO paciente;
	private DiagnosticoVO diagnostico;
	private CoordinadorDiagnostico coordDiagnostico;
	private LogicaDiagnostico logicaDiagnostico;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DentagramaDialog dialog = new DentagramaDialog();
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
	public DentagramaDialog() throws FontFormatException, IOException {
		setBounds(350, 100, 700, 270);
		getContentPane().setLayout(null);
		setTitle("Ventana Dentagrama de Diagnostico");
		// Iniciar Coordinador y Logica
		initCoordLogic();
		// Iniciar Componenetes
		initComponent();
		// Iniiciar Accion de Bootones
		initActionButton();
		setModal(true);
}
	private void initCoordLogic() {
		coordDiagnostico = new  CoordinadorDiagnostico();
		logicaDiagnostico = new LogicaDiagnostico();
		coordDiagnostico.setLogica(logicaDiagnostico);
		logicaDiagnostico.setCoordDiagnostico(coordDiagnostico);
	}
	private void initComponent() throws FontFormatException, IOException {
		
		// Lineas Separadoras 
		lineico = new ImageIcon(getClass().getResource("/DentagramaLine.png"));
		JLabel line = new JLabel();
		line.setHorizontalAlignment(SwingConstants.CENTER);
		line.setIcon(lineico);

		line.setBounds(18,-15,650,200);
		// Panel Izqierdo Arriba
		topLeftBox = new JPanel();
		topLeftBox.setLayout(new GridLayout(1, 0));
		topLeftBox.setBounds(20,30,300,60);
		// Panel IzquierdoAbajo
		bottomLeftBox = new JPanel();
		bottomLeftBox.setLayout(new GridLayout(1, 0));
		bottomLeftBox.setBounds(20,105,300,60);
		// Panel Derecho Arriba
		topRightBox = new JPanel();
		topRightBox.setLayout(new GridLayout(1, 0));
		topRightBox.setBounds(325,30,300,60);
		// Panel Derechoo Abajo
		bottomRightBox = new JPanel();
		bottomRightBox.setLayout(new GridLayout(1, 0));
		bottomRightBox.setBounds(325,105,300,60);
		// Background Modificar
		lblBackground = new JLabel();
		lblBackground.setBounds(10, -2, 670, 190);
		lblBackground.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5),
					      "Dentagrama de" ));

				/* Botones */
				// Boton clean
				btnclear = new JButton();
				btnclear.setBounds(160, 195, 160, 30);
				custom.button(btnclear, "Limpiar", "\uf51a", "solid", 4);
				btnclear.setBorder(null);		
				// Bootn OK
				btnOK = new JButton();
				btnOK.setBounds(325, 195, 160, 30);
				custom.button(btnOK, "Guardar", "\uf46d", "solid", 3);
				btnOK.setBorder(null);	
				// Check Box 8 arriba izq
				ck1 = new JCheckBox();
				// Check Box 7 arriba izq
				ck2 = new JCheckBox();
				// Check Box 6 arriba izq
				ck3 = new JCheckBox();
				// Check Box 5 arriba izq
				ck4 = new JCheckBox();
				// Check Box 4 arriba izq
				ck5 = new JCheckBox();
				// Check Box 3 arriba izq
				ck6 = new JCheckBox();
				// Check Box 2 arriba izq
				ck7 = new JCheckBox();
				// Check Box 1 arriba izq
				ck8 = new JCheckBox();
				
				// Check Box 8 abajo izq
				ck9 = new JCheckBox();
				// Check Box 7 abajo izq
				ck10 = new JCheckBox();
				// Check Box 6 abajo izq
				ck11 = new JCheckBox();
				// Check Box 5 abajo izq
				ck12 = new JCheckBox();
				// Check Box 4 abajo izq
				ck13 = new JCheckBox();
				// Check Box 3 abajo izq
				ck14 = new JCheckBox();
				// Check Box 2 abajo izq
				ck15 = new JCheckBox();
				// Check Box 1 abajo izq
				ck16 = new JCheckBox();
				
				
				// Check Box 1 arriba derecho
				ck17 = new JCheckBox();
				// Check Box 2 arriba derecho
				ck18 = new JCheckBox();
				// Check Box 3 arriba derecho
				ck19 = new JCheckBox();
				// Check Box 4 arriba derecho
				ck20 = new JCheckBox();
				// Check Box 5 arriba derecho
				ck21 = new JCheckBox();
				// Check Box 6 arriba derecho
				ck22 = new JCheckBox();
				// Check Box 7 arriba derecho
				ck23 = new JCheckBox();
				// Check Box 8 arriba derecho
				ck24 = new JCheckBox();
				
				// Check Box 1 abajo derecho
				ck25 = new JCheckBox();
				// Check Box 2 abajo derecho
				ck26 = new JCheckBox();
				// Check Box 3 abajo derecho
				ck27 = new JCheckBox();
				// Check Box 4 abajo derecho
				ck28 = new JCheckBox();
				// Check Box 5 abajo derecho
				ck29 = new JCheckBox();
				// Check Box 6 abajo derecho
				ck30 = new JCheckBox();
				// Check Box 7 abajo derecho
				ck31 = new JCheckBox();
				// Check Box 8 abajo derecho
				ck32 = new JCheckBox();
				// Agregar componenetes
				getContentPane().add(lblBackground);
				getContentPane().add(topLeftBox);
				getContentPane().add(bottomLeftBox);
				getContentPane().add(topRightBox);
				getContentPane().add(bottomRightBox);
				getContentPane().add(btnclear);
				getContentPane().add(btnOK);
				getContentPane().add(line);
				// Agregar CheckBox Izquierdo Arriba
				topLeftBox.add(createCheckBox("8", ck1));
				topLeftBox.add(createCheckBox("7",ck2));
				topLeftBox.add(createCheckBox("6",ck3));
				topLeftBox.add(createCheckBox("5",ck4));
				topLeftBox.add(createCheckBox("4",ck5));
				topLeftBox.add(createCheckBox("3",ck6));
				topLeftBox.add(createCheckBox("2",ck7));
				topLeftBox.add(createCheckBox("1",ck8));
				// Agregar CheckBox Abajo Izquierdo
				bottomLeftBox.add(createCheckBox("8",ck9));
				bottomLeftBox.add(createCheckBox("7",ck10));
				bottomLeftBox.add(createCheckBox("6",ck11));
				bottomLeftBox.add(createCheckBox("5",ck12));
				bottomLeftBox.add(createCheckBox("4",ck13));
				bottomLeftBox.add(createCheckBox("3", ck14));
				bottomLeftBox.add(createCheckBox("2",ck15));
				bottomLeftBox.add(createCheckBox("1",ck16));
				// Agregar CheckBox Derecho Arriba
				topRightBox.add(createCheckBox("1", ck17));
				topRightBox.add(createCheckBox("2",ck18));
				topRightBox.add(createCheckBox("3",ck19));
				topRightBox.add(createCheckBox("4",ck20));
				topRightBox.add(createCheckBox("5",ck21));
				topRightBox.add(createCheckBox("6",ck22));
				topRightBox.add(createCheckBox("7",ck23));
				topRightBox.add(createCheckBox("8",ck24));
				// Agregar CheckBox Abajo Derecho
				bottomRightBox.add(createCheckBox("1",ck25));
				bottomRightBox.add(createCheckBox("2",ck26));
				bottomRightBox.add(createCheckBox("3",ck27));
				bottomRightBox.add(createCheckBox("4",ck28));
				bottomRightBox.add(createCheckBox("5",ck29));
				bottomRightBox.add(createCheckBox("6", ck30));
				bottomRightBox.add(createCheckBox("7",ck31));
				bottomRightBox.add(createCheckBox("8",ck32));
	}
	private void initActionButton() {
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					addDentagrama();
					dispose();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}}});
		btnclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpiarSeleccion();
			}});
	}
    private JPanel createCheckBox(String s,JCheckBox ck) {
        JPanel p = new JPanel(new GridLayout(0,1));
        custom.ck(ck, 4);
        p.add(ck,BorderLayout.CENTER);
        return p;
    }
    private void addDentagrama() throws ClassNotFoundException {
    	if(paciente!= null && diagnostico != null) {
    		dentagrama = new DentagramaVO();
    		dentagrama.setPaciente(paciente);
    		dentagrama.setDiagnostico(diagnostico);
    		dentagrama.setIu8(ck1.isSelected());
    		dentagrama.setIu7(ck2.isSelected());
    		dentagrama.setIu6(ck3.isSelected());
    		dentagrama.setIu5(ck4.isSelected());
    		dentagrama.setIu4(ck5.isSelected());
    		dentagrama.setIu3(ck6.isSelected());
    		dentagrama.setIu2(ck7.isSelected());
    		dentagrama.setIu1(ck8.isSelected());
      		dentagrama.setId8(ck9.isSelected());
    		dentagrama.setId7(ck10.isSelected());
    		dentagrama.setId6(ck11.isSelected());
    		dentagrama.setId5(ck12.isSelected());
    		dentagrama.setId4(ck13.isSelected());
    		dentagrama.setId3(ck14.isSelected());
    		dentagrama.setId2(ck15.isSelected());
    		dentagrama.setId1(ck16.isSelected());
    		dentagrama.setDu8(ck17.isSelected());
    		dentagrama.setDu7(ck18.isSelected());
    		dentagrama.setDu6(ck19.isSelected());
    		dentagrama.setDu5(ck20.isSelected());
    		dentagrama.setDu4(ck21.isSelected());
    		dentagrama.setDu3(ck22.isSelected());
    		dentagrama.setDu2(ck23.isSelected());
    		dentagrama.setDu1(ck24.isSelected());
      		dentagrama.setDd8(ck25.isSelected());
    		dentagrama.setDd7(ck26.isSelected());
    		dentagrama.setDd6(ck27.isSelected());
    		dentagrama.setDd5(ck28.isSelected());
    		dentagrama.setDd4(ck29.isSelected());
    		dentagrama.setDd3(ck30.isSelected());
    		dentagrama.setDd2(ck31.isSelected());
    		dentagrama.setDd1(ck32.isSelected());
    	}	}
    
    private void limpiarSeleccion() {
      ck1.setSelected(false);
      ck2.setSelected(false);
      ck3.setSelected(false);
      ck4.setSelected(false);
      ck4.setSelected(false);
      ck6.setSelected(false);
      ck7.setSelected(false);
      ck8.setSelected(false);
      ck9.setSelected(false);
      ck10.setSelected(false);
      ck11.setSelected(false);
      ck12.setSelected(false);
      ck13.setSelected(false);
      ck14.setSelected(false);
      ck15.setSelected(false);
      ck16.setSelected(false);
      ck17.setSelected(false);
      ck18.setSelected(false);
      ck19.setSelected(false);
      ck20.setSelected(false);
      ck21.setSelected(false);
      ck22.setSelected(false);
      ck23.setSelected(false);
      ck24.setSelected(false);
      ck25.setSelected(false);
      ck26.setSelected(false);
      ck27.setSelected(false);
      ck28.setSelected(false);
      ck29.setSelected(false);
      ck30.setSelected(false);
      ck31.setSelected(false);
      ck32.setSelected(false);
    }

public DentagramaVO getDentagrama() {
		return dentagrama;
	}
	public void setDentagrama(DentagramaVO dentagrama) {
		this.dentagrama = dentagrama;
		ck1.setSelected(dentagrama.isIu8());
		ck2.setSelected(dentagrama.isIu7());
		ck3.setSelected(dentagrama.isIu6());
		ck4.setSelected(dentagrama.isIu5());
		ck5.setSelected(dentagrama.isIu4());
		ck6.setSelected(dentagrama.isIu3());
		ck7.setSelected(dentagrama.isIu2());
		ck8.setSelected(dentagrama.isIu1());
		ck9.setSelected(dentagrama.isId8());
		ck10.setSelected(dentagrama.isId7());
		ck11.setSelected(dentagrama.isId6());
		ck12.setSelected(dentagrama.isId5());
		ck13.setSelected(dentagrama.isId4());
		ck14.setSelected(dentagrama.isId3());
		ck15.setSelected(dentagrama.isId2());
		ck16.setSelected(dentagrama.isId1());
		ck17.setSelected(dentagrama.isDu8());
		ck18.setSelected(dentagrama.isDu7());
		ck19.setSelected(dentagrama.isDu6());
		ck20.setSelected(dentagrama.isDu5());
		ck21.setSelected(dentagrama.isDu4());
		ck22.setSelected(dentagrama.isDu3());
		ck23.setSelected(dentagrama.isDu2());
		ck24.setSelected(dentagrama.isDu1());
		ck25.setSelected(dentagrama.isDd8());
		ck26.setSelected(dentagrama.isDd7());
		ck27.setSelected(dentagrama.isDd6());
		ck28.setSelected(dentagrama.isDd5());
		ck29.setSelected(dentagrama.isDd4());
		ck30.setSelected(dentagrama.isDd3());
		ck31.setSelected(dentagrama.isDd2());
		ck32.setSelected(dentagrama.isDd1());
		
	}

	public PacienteVO getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteVO paciente) {
	this.paciente = paciente;
	lblBackground.setBorder(new TitledBorder(new LineBorder(new Color(152,172,220), 5),
		      "Dentagrama de " + paciente.getNombre() ));
	}
	
public DiagnosticoVO getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(DiagnosticoVO diagnostico) {
		this.diagnostico = diagnostico;
	}


private JPanel topLeftBox;
private JPanel bottomLeftBox;
private JPanel topRightBox;
private JPanel bottomRightBox;
private JLabel lblBackground;
private JButton btnclear;
private JButton btnOK;
private JCheckBox ck1;
private JCheckBox ck2;
private JCheckBox ck3;
private JCheckBox ck4;
private JCheckBox ck5;
private JCheckBox ck6;
private JCheckBox ck7;
private JCheckBox ck8;
private JCheckBox ck9;
private JCheckBox ck10;
private JCheckBox ck11;
private JCheckBox ck12;
private JCheckBox ck13;
private JCheckBox ck14;
private JCheckBox ck15;
private JCheckBox ck16;
private JCheckBox ck17;
private JCheckBox ck18;
private JCheckBox ck19;
private JCheckBox ck20;
private JCheckBox ck21;
private JCheckBox ck22;
private JCheckBox ck23;
private JCheckBox ck24;
private JCheckBox ck25;
private JCheckBox ck26;
private JCheckBox ck27;
private JCheckBox ck28;
private JCheckBox ck29;
private JCheckBox ck30;
private JCheckBox ck31;
private JCheckBox ck32;
private ImageIcon lineico; 
}