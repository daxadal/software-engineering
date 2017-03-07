package presentacion.sectores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class JPMostrarDatos extends JPanel{
	
	public JPMostrarDatos(boolean editable, JTextField jtfId, JTextField jtfNombre, JTextField jtfNoCeldas, JList<Integer> listaCeldas)
	{
		initGUI(editable, jtfId, jtfNombre, jtfNoCeldas, listaCeldas);
	}
	private void initGUI(boolean editable, JTextField jtfId, JTextField jtfNombre, JTextField jtfNoCeldas, JList<Integer> listaCeldas)
	{
		setLayout(new GridLayout(1,2,10,10));
		JPanel jpOeste =  new JPanel();
		JPanel jpEste =  new JPanel();
		this.add(jpOeste);
		this.add(jpEste);
		//Panel oeste
		jpOeste.setLayout(new GridLayout(1,2,10,10));
		jpOeste.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Datos",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 14), 
											Color.BLACK));
		
		JPanel jpEtiquetas = new JPanel();
		JPanel jpTextos = new JPanel();
		jpEtiquetas.setLayout(new GridLayout(3, 1, 0, 15));
		jpTextos.setLayout(new GridLayout(3, 1, 15, 15));
		jpOeste.setSize(100, 100);
		jpOeste.add(jpEtiquetas);
		jpOeste.add(jpTextos);
		jpEtiquetas.add(new JLabel("ID:", JLabel.RIGHT));
		jpTextos.add(jtfId);
		jtfId.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfId.setEditable(false);
		jpEtiquetas.add(new JLabel("Nombre:", JLabel.RIGHT));
		jpTextos.add(jtfNombre);
		jtfNombre.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfNombre.setEditable(editable);
		jpEtiquetas.add(new JLabel("Nº de Celdas:", JLabel.RIGHT));
		jpTextos.add(jtfNoCeldas);
		jtfNoCeldas.setEditable(false);
		jtfNoCeldas.setBorder(BorderFactory.createLoweredBevelBorder());
		
		//Panel este
		jpEste.setLayout(new BorderLayout());
		jpEste.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Celdas",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		listaCeldas.setBackground(null);
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)listaCeldas.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		JPanel jpListaCeldas = new JPanel();
		jpListaCeldas.add(listaCeldas);
		JScrollPane sPAct = new JScrollPane
				(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sPAct.setViewportView(jpListaCeldas);
		sPAct.setBorder(null);
		jpEste.add(sPAct);
				
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}
	
	
}
