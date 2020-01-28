package Vista;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import Modelo.VO.Detalle_DiagnosticoVO;

public class ComponentCustomization {
	Font font;
	
	/* Metodo para dar estilo al boton */
	public void button(JButton btn,String nombre,String ico,String fontype,int type) throws FontFormatException, IOException {
		
		Color buttonColor = null;
		switch(type){
			case 1: //Menu
			buttonColor = new Color(100, 100, 100);
			fontAwesome(fontype,31f);
			btn.setBackground(buttonColor);
			btn.setForeground(Color.WHITE);
			break;
			case 2: //Session
			btn.setBackground(buttonColor);
			fontAwesome(fontype,41f);
			buttonColor = new Color(100, 100, 100);
			btn.setForeground(Color.WHITE);
			break;
			case 3: //Agregar/+
			buttonColor = new Color(61, 204, 45);
			btn.setBackground(buttonColor);
			fontAwesome(fontype,45f);
			btn.setForeground(Color.WHITE);
			break;
			case 4: // Limpiar
			buttonColor = new Color(89, 173, 238);
			btn.setBackground(buttonColor);
			fontAwesome(fontype,45f);
			btn.setForeground(Color.WHITE);
			break;
			case 5: // Buscar
			buttonColor = new Color(106,90,205) ;
			btn.setBackground(buttonColor);
			fontAwesome(fontype, 45f);
			btn.setForeground(Color.WHITE);
			break;
			case 6: // Descartar/Actualizar/Eliminar/Rojo
			buttonColor = new Color(233, 0, 0);
			btn.setBackground(buttonColor);
			fontAwesome(fontype, 45f);
			btn.setForeground(Color.WHITE);
			break;
			case 7: // Close Button
			buttonColor = new Color(233, 0, 0);
			btn.setBackground(new Color(32,35,64));
			fontAwesome(fontype, 60f);
			btn.setForeground(Color.WHITE);
			break;
			case 8: // Boton Historial
			buttonColor = new Color(89, 173, 238);
			btn.setBackground(buttonColor);
			fontAwesome(fontype, 80f);
			btn.setForeground(Color.WHITE);
			break;
			case 9: // Buscador Listados
			buttonColor = new Color(30,144,255) ;
			btn.setBackground(buttonColor);
			fontAwesome(fontype, 45f);
			btn.setForeground(Color.WHITE);
			break;
			
		}
		btn.setBorder(new LineBorder(Color.WHITE,5));
		btn.setFont(font);
		btn.setText(nombre + " " +ico + " ");
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setMargin(new Insets(10, 10, 10, 10));

	}	
	/* Estilo de Label */
		public void label(JLabel label,String nombre,int type) {
			switch(type) {
			case 1: // Label Negro Sin Fondo
				label.setBackground(null);
				label.setOpaque(false);
				label.setFont(new Font("LucidaBright", Font.PLAIN, 25));
				break;
			case 2: // Label Fondo Gris
				label.setOpaque(true);
				label.setBackground(new Color(197, 197, 197));
				label.setFont(new Font("LucidaBright", Font.PLAIN, 25));
				break;
			case 3: // Label Negrita
				label.setOpaque(false);
				label.setText(nombre);
				label.setFont(new Font("LucidaBright", Font.BOLD, 25));
				break;
			case 4: // Label Totales
				label.setOpaque(false);
				label.setFont(new Font("LucidaBright", Font.PLAIN, 25));
				break;
			}
			label.setBackground(new Color(197, 197, 197));
			label.setText(nombre);
			label.setForeground(Color.BLACK);
			label.setBounds(label.getX(), label.getY(), label.getWidth(), label.getHeight());
		}
		/* Estilos TextField*/
		public void txt(JFormattedTextField txt, int type,String msg) throws ParseException {
			switch(type) {
			case 1: // TextField Grandes
			txt.setFont(new Font("LucidaBright", Font.PLAIN, 25));	
			break;
			case 2: //TextField Pequenos
				txt.setFont(new Font("LucidaBright", Font.PLAIN, 25));		
			break;
			case 3: // TextField Dialogs
				txt.setFont(new Font("Tahoma", Font.PLAIN, 15 ));
			}
			txt.setForeground(Color.BLACK);
			txt.setToolTipText(msg);
		}
		/* Estilos TextField normal*/
		public void txt(JTextField txt, int type,String msg) throws ParseException {
			switch(type) {
			case 1: // TextField Grandes
			txt.setFont(new Font("LucidaBright", Font.PLAIN, 25));	
			break;
			case 2: //TextField Pequenos
				txt.setFont(new Font("LucidaBright", Font.PLAIN, 25));		
			break;
			}
			txt.setForeground(Color.BLACK);
			txt.setToolTipText(msg);
		}
		
		/* Estilo de Password Field*/
		public void passwordField(JPasswordField txt) throws ParseException {
			txt.setForeground(Color.BLACK);
			txt.setFont(new Font("LucidaBright", Font.PLAIN, 25));		
		}
		/* Estilo de CheckBox */
		public void ck(JCheckBox ck,int type) {
			switch(type) {
			case 1:
			ck.setSelected(true);
			ImageIcon checkico = new ImageIcon(getClass().getResource("/check.png"));
			ImageIcon uncheckico = new ImageIcon(getClass().getResource("/uncheck.png"));
			ck.setIcon(uncheckico);
			ck.setSelectedIcon(checkico);
			ck.setDisabledIcon(uncheckico);
			ck.setBackground(new Color(255, 106, 106));
			ck.setBorder(new LineBorder(Color.BLACK,2));
			ck.setBorderPainted(true);
			ck.setFont(new Font("LucidaBright", Font.BOLD, 25));
			break;
			case 2:
			ck.setFont(new Font("LucidaBright", Font.PLAIN, 25));
			break;
			case 3:
				ck.setSelected(true);
			checkico = new ImageIcon(getClass().getResource("/check.png"));
			uncheckico = new ImageIcon(getClass().getResource("/uncheck.png"));
			ck.setIcon(uncheckico);
			ck.setSelectedIcon(checkico);
			ck.setDisabledIcon(uncheckico);
			ck.setBackground(new Color(255, 106, 106));
			ck.setBorder(new LineBorder(Color.BLACK,2));
			ck.setBorderPainted(true);
			ck.setFont(new Font("LucidaBright", Font.BOLD, 15));
			break;
			case 4:
			checkico = new ImageIcon(getClass().getResource("/checkX.png"));
			uncheckico = new ImageIcon(getClass().getResource("/uncheckX.png"));
			ck.setIcon(uncheckico);
			ck.setSelectedIcon(checkico);
			ck.setDisabledIcon(uncheckico);
			ck.setFont(new Font("LucidaBright", Font.BOLD, 15));
			break;
			}
			ck.setForeground(Color.BLACK);
			

		}
		public void rb(JRadioButton rb,int type) {
			switch(type) {
			case 1:
				rb.setBackground(Color.WHITE);
				rb.setBorder(new LineBorder(Color.BLACK,2));
			break;
			case 2: 
				break;
			}
			rb.setForeground(Color.BLACK);
			rb.setFont(new Font("LucidaBright", Font.PLAIN, 25));
			
			//rb.setBorderPainted(true);

		}
		/* Estilo ComboBox */
		public void cbb(JComboBox cbb,String text,int type) {
			DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
	        // Borrar Datos Viejos
	        model.removeAllElements(); 
	        // setting model with new data
	        cbb.setModel(model);
		        // setting model with new data
	      cbb.setBackground(new Color(100,100,100));
			cbb.setForeground(Color.WHITE);
			cbb.setFont(new Font("LucidaBright",Font.PLAIN,25));
			cbb.setSize(cbb.getPreferredSize());
		}
		public void cbbBuscador(JComboBox cbb,String text,int type) {
			DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
	        // Borrar Datos Viejos
	        model.removeAllElements(); 
	        // setting model with new data
	        cbb.setModel(model);
		        // setting model with new data
	      cbb.setBackground(Color.WHITE);
			cbb.setForeground(Color.BLACK);
			cbb.setFont(new Font("LucidaBright",Font.PLAIN,20));
			cbb.setSize(cbb.getPreferredSize());
		}
		/* Estilo Spinner */
		public void spinner(JSpinner spinner) {
			SpinnerDateModel model = new SpinnerDateModel();
			((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
			model.setCalendarField(Calendar.MINUTE);
		
			spinner.setModel(model);
			spinner.setEditor(new JSpinner.DateEditor(spinner, "h:mm a"));
			spinner.setForeground(Color.BLACK);
			spinner.setFont(new Font("LucidaBright", Font.PLAIN, 25));
			}
		/* Estilo TextArea */
		public void txtArea(JTextArea txtArea) {
			txtArea.setForeground(Color.BLACK);
			txtArea.setFont(new Font("LucidaBright", Font.PLAIN, 25));
			txtArea.setBorder(new LineBorder(Color.BLACK));
		}
		
		/* Estilo Tabla */
		public void table(JTable table,Object[][] fila,String[] titulo) {
					JTableHeader Theader = table.getTableHeader();
			        Color bgHeader = new Color(158,158,158);
			        Color bgLine = Color.WHITE;
			        Theader.setBackground(bgHeader); //background
			        Theader.setForeground(bgLine); // color de font 
			        Theader.setFont(new Font("LucidaBright", Font.BOLD, 24)); //font style size
			        table.setBackground(bgHeader);
			        table.setFont(new Font("LucidaBright", Font.PLAIN, 18));
			        table.setForeground(bgLine);
			        table.setRowHeight(30);
			        DefaultTableModel model = new DefaultTableModel(fila,titulo)
			        {public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
			        };
			       table.setModel(model);
		}
		public void tablaBuscador(JTable table,Object[][] fila,String[] titulo) {
			JTableHeader Theader = table.getTableHeader();
	        Color bgHeader = Color.WHITE;
	        Color bgLine = Color.black;
	        Theader.setBackground(bgHeader); //background
	        Theader.setForeground(bgLine); // color de font 
	        Theader.setFont(new Font("LucidaBright", Font.BOLD, 20)); //font style size
	        table.setBackground(bgHeader);
	        table.setFont(new Font("LucidaBright", Font.PLAIN, 14));
	        table.setForeground(bgLine);
	        table.setRowHeight(30);
	        DefaultTableModel model = new DefaultTableModel(fila,titulo)
	        {public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
	        };
	       table.setModel(model);
}
		public void confirmacion(JPanel pnl,String msg) {
			pnl.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 5),
			       "Añadir Nuevo " + msg));
			pnl.setBackground(Color.WHITE);
			pnl.setOpaque(true);
		}
		public void modificacionBG(JLabel background,String msg) {
			background.setBorder(new TitledBorder(new LineBorder(new Color(152,172,220), 5),
				      "Modificar " + msg));
			;
		}
		public MaskFormatter formato(String tipo) throws ParseException {
			MaskFormatter formato = new MaskFormatter();
			switch(tipo) {
			case "fechanow":
				int year=Year.now().getValue() % 100;
				formato = new MaskFormatter("##/##/"+year);
				break;
			case "fecha":
				formato = new MaskFormatter("##/##/##");
				break;	
			case "precio":
				formato = new MaskFormatter("RD$##########");	
				break;
			case "rnc":
				formato = new MaskFormatter("#-#######-#");
				break;
			case "telefono":
				formato = new MaskFormatter("###-###-####");
				break;
			case "cedula":
				formato = new MaskFormatter("###-#######-#");
				break;
			case "descuento":
				formato = new MaskFormatter("##%");
				break;
			case "numero":
				formato = new MaskFormatter("############");
			}
			return formato;
		}
		public void msg(String msg,int type) {
			switch(type) {
			case 1:
				JOptionPane.showMessageDialog(null, "Debe llenar todos los datos requeridos","Advertencia",JOptionPane.OK_CANCEL_OPTION);
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "No existe ningun "+ msg+" con este ID","Advertencia",JOptionPane.OK_CANCEL_OPTION);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "En este campo solo se aceptan Horas","Advertencia",JOptionPane.OK_CANCEL_OPTION);
				break;
			case 4:
				JOptionPane.showMessageDialog(null,msg,"Confirmacion",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "En el campo " + msg +" solo se aceptan numeros enteros","Advertencia",JOptionPane.OK_CANCEL_OPTION);
				break;
			case 6:
				JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.ERROR_MESSAGE);
				break;
			case 7: 
				JOptionPane.showMessageDialog(null, msg, "Advertencia", JOptionPane.OK_CANCEL_OPTION);
			}}
			
		public int msgConfirm(String msg,int type) {
			int result= 0;
			switch(type) {
			case 1:
			result =JOptionPane.showConfirmDialog (null, msg,"Confirmacion",JOptionPane.YES_NO_OPTION);
			break;
			}
			return result;
		}

			/* metodo para agregar icono a boton*/
			public void fontAwesome(String fontype,float size) throws FontFormatException, IOException {
				if (fontype == "regular"){
				font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Font Awesome 5 Free-Regular-400.otf"));
			    font = font.deriveFont(Font.BOLD,size);}
				else if (fontype == "solid"){
				font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Font Awesome 5 Free-Solid-900.otf"));
				font = font.deriveFont(Font.BOLD,size);
				}		
				
			    GraphicsEnvironment ge = 
			        GraphicsEnvironment.getLocalGraphicsEnvironment();
			    ge.registerFont(font);

		}
			
}
