package Vista;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Modelo.Coordinador.CoordinadorHistorial;
import Modelo.Logica.LogicaHistorial;
import Modelo.VO.HistorialClinicoVO;
import Modelo.VO.PacienteVO;

public class HistorialClinicoDialog extends JDialog {
	private ComponentCustomization custom = new ComponentCustomization();
	private final JPanel contentPanel = new JPanel();
	private LogicaHistorial logicaHistorial;
	private CoordinadorHistorial coordHistorial;
	private HistorialClinicoVO historial;
	private PacienteVO paciente;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HistorialClinicoDialog dialog = new HistorialClinicoDialog();
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
	public HistorialClinicoDialog() throws FontFormatException, IOException {
		setBounds(100, 100, 550, 490);
		getContentPane().setLayout(null);
		setTitle("Ventana Historial Clinico Cliente");
		// Iniciar Coordinador y Logica
		initCoordLogic();
		// Iniciar Componenetes
		initComponent();
		// Iniiciar Accion de Bootones
		initActionButton();
		setModal(true);
}
	private void initCoordLogic() {
		logicaHistorial = new LogicaHistorial();
		coordHistorial = new CoordinadorHistorial();
		logicaHistorial.setCoordinador(coordHistorial);
		coordHistorial.setLogica(logicaHistorial);
	}
	private void initComponent() throws FontFormatException, IOException {
		// Panel Izqierdo
				leftBox = new JPanel();
				leftBox.setLayout(new GridLayout(0, 1));
				leftBox.setBounds(20,30,210,400);
				// Panel Derecho
				rightBox = new JPanel();
				rightBox.setLayout(new GridLayout(0, 1));
				rightBox.setBounds(250,30,250,325);
				// Background Modificar
				lblBackground = new JLabel();
				lblBackground.setBounds(10, 10, 500, 425);
				lblBackground.setBorder(new TitledBorder(new LineBorder(new Color(152,172,220), 5),
					      "Historial Clinico de " ));

				/* Botones */
				// Boton clean
				btnclear = new JButton();
				btnclear.setBounds(250, 395, 160, 30);
				custom.button(btnclear, "Limpiar", "\uf51a", "solid", 4);
				btnclear.setBorder(null);		
				// Bootn OK
				btnOK = new JButton();
				btnOK.setBounds(250, 360, 160, 30);
				custom.button(btnOK, "Guardar", "\uf46d", "solid", 3);
				btnOK.setBorder(null);	
				// Check Box Hipertension
				ckDiabetes = new JCheckBox();
				// Check Box Lesion Congenita
				ckLesionCorazon = new JCheckBox();
				// Check Box Hipertension
				ckHipertension = new JCheckBox();
				// Check Box Cefalea
				ckCefalea = new JCheckBox();
				// Check Box Mareo
				ckMareos = new JCheckBox();
				// Check Box hemofilia
				ckHemofilia = new JCheckBox();
				// Check Box Endocarditis
				ckEndocarditis = new JCheckBox();
				// Check Box Hepatitis
				ckHepatitis = new JCheckBox();
				// Check Box Asma
				ckAsma = new JCheckBox();
				// Check Box fiebre Reumatica
				ckFiebre = new JCheckBox();
				// Check Box Artritis
				ckArtritis = new JCheckBox();
				// Check Box Cancer
				ckCancer = new JCheckBox();
				// Check Box Ataque Nervioso
				ckNervios = new JCheckBox();
				// Check Box Hemorragia
				ckHemorragia = new JCheckBox();
				// Check Box alergico Anestesia Local
				ckAnestesia = new JCheckBox();
				// Check Box Alergico Alimentos
				ckAlergiaAlimento = new JCheckBox();
				// Check Box Alergico Penicilina 
				ckPenicilina = new JCheckBox();
				// Check Box Enfermedades Veneras
				ckVenerea = new JCheckBox();
				// Check Box Varices
				ckVarices = new JCheckBox();
				// Check Box RX
				ckRX = new JCheckBox();
				// Check Box TX
				ckTX = new JCheckBox();
				// Agregar componenetes
				getContentPane().add(lblBackground);
				getContentPane().add(leftBox);
				getContentPane().add(rightBox);
				getContentPane().add(btnclear);
				getContentPane().add(btnOK);
				// Agregar CheckBox Izquierdo
				leftBox.add(createCheckBox("Diabetes", ckDiabetes));
				leftBox.add(createCheckBox("Lesiones Congenitas al Corazon",ckLesionCorazon));
				leftBox.add(createCheckBox("Hipertension",ckHipertension));
				leftBox.add(createCheckBox("Cefalea",ckCefalea));
				leftBox.add(createCheckBox("Mareos o Desmayos",ckMareos));
				leftBox.add(createCheckBox("Hemofilia",ckHemofilia));
				leftBox.add(createCheckBox("Endocarditis Bacteriana",ckEndocarditis));
				leftBox.add(createCheckBox("Hepatitis",ckHepatitis));
				leftBox.add(createCheckBox("Asma",ckAsma));
				leftBox.add(createCheckBox("Fiebre Reumatica",ckFiebre));
				leftBox.add(createCheckBox("Artritis Reumatoide",ckArtritis));
				// Agregar CheckBox Derecho
				rightBox.add(createCheckBox("Cancer",ckCancer));
				rightBox.add(createCheckBox("Ataque Nervioso",ckNervios));
				rightBox.add(createCheckBox("Alergico Anestesia Local",ckAnestesia));
				rightBox.add(createCheckBox("Alergico Alimentos",ckAlergiaAlimento));
				rightBox.add(createCheckBox("Alergico Penicilina y\n otros antibioticos ",ckPenicilina));
				rightBox.add(createCheckBox("Enfermedades Venereas",ckVenerea));
				rightBox.add(createCheckBox("Varices",ckVarices));
				rightBox.add(createCheckBox("RX",ckRX));
				rightBox.add(createCheckBox("TX",ckTX));
	}
	private void initActionButton() {
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					addHistorial();
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
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel(s, JLabel.LEFT), BorderLayout.WEST);
        p.add(ck, BorderLayout.EAST);
     //   p.setBorder(BorderFactory.createLineBorder(C));
        return p;
    }
    private void addHistorial() throws ClassNotFoundException {
    	if(paciente != null) {
    	historial = new HistorialClinicoVO();
    	historial.setAlergaAnestesia(ckAnestesia.isSelected());
    	historial.setAlergiaAlimentos(ckAlergiaAlimento.isSelected());
    	historial.setalergiaAntibiotico(ckPenicilina.isSelected());
    	historial.setArtritis(ckArtritis.isSelected());
    	historial.setAsma(ckAsma.isSelected());
    	historial.setCancer(ckCancer.isSelected());
    	historial.setCefalea(ckCefalea.isSelected());
    	historial.setDiabetes(ckDiabetes.isSelected());
    	historial.setEndocarditis(ckEndocarditis.isSelected());
    	historial.setFiebre(ckFiebre.isSelected());
    	historial.setHemofilia(ckHemofilia.isSelected());
    	historial.setHemorragia(ckHemorragia.isSelected());
    	historial.setHepatitis(ckHepatitis.isSelected());
    	historial.setHipertension(ckHipertension.isSelected());
    	historial.setLesionCongenita(ckLesionCorazon.isSelected());
    	historial.setMareos(ckMareos.isSelected());
    	historial.setNervios(ckNervios.isSelected());
    	historial.setRx(ckRX.isSelected());
    	historial.setTx(ckTX.isSelected());
    	historial.setVarices(ckVarices.isSelected());
    	historial.setVenerea(ckVenerea.isSelected());
    	historial.setPaciente(getPaciente());
    	if(coordHistorial.addHistorial(historial) == true) {
    		limpiarSeleccion();
    	}}
    }
    public void getHistorial(int id) {
      	historial = coordHistorial.getHistorial(id);
      	ckAnestesia.setSelected(historial.isAlergaAnestesia());
    	ckAlergiaAlimento.setSelected(historial.isAlergiaAlimentos());
    	ckPenicilina.setSelected(historial.isalergiaAntibiotico());
    	ckArtritis.setSelected(historial.isArtritis());
    	ckAsma.setSelected(historial.isAsma());
    	ckCancer.setSelected(historial.isCancer());
    	ckCefalea.setSelected(historial.isCefalea());
    	ckDiabetes.setSelected(historial.isDiabetes());
    	ckEndocarditis.setSelected(historial.isEndocarditis());
    	ckFiebre.setSelected(historial.isFiebre());
    	ckHemofilia.setSelected(historial.isHemofilia());
    	ckHemorragia.setSelected(historial.isHemorragia());
    	ckHepatitis.setSelected(historial.isHepatitis());
    	ckHipertension.setSelected(historial.isHipertension());
    	ckLesionCorazon.setSelected(historial.isLesionCongenita());
    	ckMareos.setSelected(historial.isMareos());
    	ckNervios.setSelected(historial.isNervios());
    	ckRX.setSelected(historial.isRx());
    	ckTX.setSelected(historial.isTx());
    	ckVarices.setSelected(historial.isVarices());
    	ckVenerea.setSelected(historial.isVenerea());
    	paciente = historial.getPaciente();
    }
    private void limpiarSeleccion() {
      ckDiabetes.setSelected(false);
      ckHipertension.setSelected(false);
      ckLesionCorazon.setSelected(false);
      ckCefalea.setSelected(false);
      ckMareos.setSelected(false);
      ckHemofilia.setSelected(false);
      ckEndocarditis.setSelected(false);
      ckHepatitis.setSelected(false);
      ckAsma.setSelected(false);
      ckFiebre.setSelected(false);
      ckArtritis.setSelected(false);
      ckCancer.setSelected(false);
      ckNervios.setSelected(false);
      ckHemorragia.setSelected(false);
      ckAnestesia.setSelected(false);
      ckAlergiaAlimento.setSelected(false);
      ckPenicilina.setSelected(false);
      ckVenerea.setSelected(false);
      ckVarices.setSelected(false);
      ckRX.setSelected(false);
      ckTX.setSelected(false);
    }

public PacienteVO getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteVO paciente) {
		if (coordHistorial.getHistorial(paciente.getIdPaciente())!= null) {
    		getHistorial(paciente.getIdPaciente());
    	}else {	}
    	lblBackground.setBorder(new TitledBorder(new LineBorder(new Color(152,172,220), 5),
    		      "Historial Clinico de " + paciente.getNombre() ));
		this.paciente = paciente;
	}
	
private JPanel leftBox;
private JPanel rightBox;
private JLabel lblBackground;
private JButton btnclear;
private JButton btnOK;
private JCheckBox ckDiabetes;
private JCheckBox ckHipertension;
private JCheckBox ckLesionCorazon;
private JCheckBox ckCefalea;
private JCheckBox ckMareos;
private JCheckBox ckHemofilia;
private JCheckBox ckEndocarditis;
private JCheckBox ckHepatitis;
private JCheckBox ckAsma;
private JCheckBox ckFiebre;
private JCheckBox ckArtritis;
private JCheckBox ckCancer;
private JCheckBox ckNervios;
private JCheckBox ckHemorragia;
private JCheckBox ckAnestesia;
private JCheckBox ckAlergiaAlimento;
private JCheckBox ckPenicilina;
private JCheckBox ckVenerea;
private JCheckBox ckVarices;
private JCheckBox ckRX;
private JCheckBox ckTX;
}