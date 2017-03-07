package presentacion.presos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import presentacion.JFPrincipal;

@SuppressWarnings("serial")
public class JPMostrarDatos extends JPanel{

	public JPMostrarDatos(boolean editable, boolean celdaEditable, boolean reincidente, JTextField jtfId, JTextField jtfCelda, JTextField jtfNombre, JTextField jtfApellido,  JTextField jtfDni, JTextArea jtaCondena, JList<Integer> jlVisitas, JList<Integer> jlActividades)
	{
		initGUI(editable, celdaEditable, reincidente, jtfId, jtfCelda, jtfNombre, jtfApellido, jtfDni, jtaCondena, jlVisitas, jlActividades);
	}
	private void initGUI(boolean editable, boolean celdaEditable, boolean reincidente, JTextField jtfId, JTextField jtfCelda, JTextField jtfNombre, JTextField jtfApellidos,  JTextField jtfDni, JTextArea jtaCondena, JList<Integer> jlVisitas, JList<Integer> jlActividades)
	{
		this.setLayout(new GridLayout(4,1,10,10));
		//ID
		JPanel jpUp = new JPanel();
		jpUp.setLayout(new BorderLayout());
		URL icon = JFPrincipal.class.getResource("iconos/face.png");
		JLabel face = new JLabel(new ImageIcon(icon));
		face.setSize(20, 20);
		jpUp.add(face, BorderLayout.WEST);
		
		JPanel jpIdyCel = new JPanel(new BorderLayout());
		JPanel jpIdyCelW = new JPanel(new GridLayout(4, 1, 10, 0));
		jpIdyCelW.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel jpIdyCelC = new JPanel(new FlowLayout());
		jpIdyCelC.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jpIdyCelW.add(new JLabel("ID:", JLabel.RIGHT));
		jpIdyCelC.add(jtfId);
		jtfId.setEditable(false);
		jtfId.setColumns(10);
		jtfId.setBorder(BorderFactory.createLoweredBevelBorder());
		jpIdyCelW.add(new JLabel("Celda:", JLabel.RIGHT));
		jpIdyCel.add(jpIdyCelW, BorderLayout.WEST);
		jpIdyCel.add(jpIdyCelC, BorderLayout.CENTER);
		jpIdyCelC.add(jtfCelda);
		jtfCelda.setEditable(celdaEditable);
		jtfCelda.setColumns(10);
		jtfCelda.setBorder(BorderFactory.createLoweredBevelBorder());
		this.add(jpUp);
		jpUp.add(jpIdyCel);
		
		//Datos personales
		
		JPanel jpDPersonales = new JPanel();
		jpDPersonales.setLayout(new BorderLayout());
		jpDPersonales.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Datos personales",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 14), 
											Color.BLACK));
		
		JPanel jpEtiquetasDP = new JPanel();
		JPanel jpTextosDP = new JPanel();
		jpEtiquetasDP.setLayout(new GridLayout(3, 1, 10, 0));
		jpTextosDP.setLayout(new GridLayout(3, 1, 0, 10));
		jpDPersonales.setSize(100, 100);
		jpDPersonales.add(jpEtiquetasDP, BorderLayout.WEST);
		jpDPersonales.add(jpTextosDP, BorderLayout.CENTER);
		jpEtiquetasDP.add(new JLabel("Nombre: ", JLabel.RIGHT));
		jpTextosDP.add(jtfNombre);
		jtfNombre.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfNombre.setEditable(editable);
		jpEtiquetasDP.add(new JLabel("Apellido: ", JLabel.RIGHT));
		jpTextosDP.add(jtfApellidos);
		jtfApellidos.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfApellidos.setEditable(editable);
		jpEtiquetasDP.add(new JLabel("DNI: ", JLabel.RIGHT));
		jpTextosDP.add(jtfDni);
		jtfDni.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfDni.setEditable(editable);
		this.add(jpDPersonales);
		
		//Datos judiciales
		
		JPanel jpDJudiciales = new JPanel();
		jpDJudiciales.setLayout(new BorderLayout());
		jpDJudiciales.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Datos Judiciales",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 14), 
											Color.BLACK));
		jtaCondena.setEditable(editable || reincidente);
		jpDJudiciales.add(new JLabel("Condena: "), BorderLayout.NORTH);
		jpDJudiciales.add(new JScrollPane(jtaCondena, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		this.add(jpDJudiciales);
		
		//Datos Actividades y Visitas
		JPanel jpActYVis = new JPanel(new GridLayout(1,2,10,10));
		JPanel jpVisitas = new JPanel();
		jpVisitas.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Visitas",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		if (jlVisitas != null)
		{
			jlVisitas.setBackground(null);
			DefaultListCellRenderer rendererV =  (DefaultListCellRenderer)jlVisitas.getCellRenderer();  
			rendererV.setHorizontalAlignment(JLabel.CENTER);
			jpVisitas.add(jlVisitas, BorderLayout.CENTER);
		}
		JScrollPane sPVis = new JScrollPane
				(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sPVis.setViewportView(jpVisitas);
		sPVis.setBorder(null);
		jpActYVis.add(sPVis);
		
		
		JPanel jpActividades = new JPanel();
		jpActividades.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Actividades",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		if (jlActividades != null)
		{
			jlActividades.setBackground(null);
			DefaultListCellRenderer rendererA =  (DefaultListCellRenderer)jlActividades.getCellRenderer();  
			rendererA.setHorizontalAlignment(JLabel.CENTER);
			jpActividades.add(jlActividades, BorderLayout.CENTER);
		}
		JScrollPane sPAct = new JScrollPane
				(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sPAct.setViewportView(jpActividades);
		sPAct.setBorder(null);
		jpActYVis.add(sPAct);
		
		this.add(jpActYVis);
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		

	}
}
