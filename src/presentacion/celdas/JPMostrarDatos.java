package presentacion.celdas;

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
	
	public JPMostrarDatos(int modoEditable, JTextField jtfId, JTextField jtfNuCelda, JTextField jtfCapacidad,  JTextField jtfSector, JList<Integer> jlPresos)
	{
		initGUI(modoEditable, jtfId, jtfNuCelda, jtfCapacidad, jtfSector, jlPresos);
	}
	private void initGUI(int modoEditable, JTextField jtfId, JTextField jtfNuCelda, JTextField jtfCapacidad,  JTextField jtfSector, JList<Integer> jlPresos)
	{
		JPanel jpDatos = new JPanel();
		JPanel jpPresos = new JPanel(new BorderLayout());
		this.setLayout(new GridLayout(1, 2, 10, 10));
		
		//Datos 
		jpDatos.setLayout(new GridLayout(1,2,10,10));
		jpDatos.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Datos",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 14), 
											Color.BLACK));
		
		JPanel jpEtiquetas = new JPanel();
		JPanel jpTextos = new JPanel();
		jpEtiquetas.setLayout(new GridLayout(4, 1, 0, 0));
		jpTextos.setLayout(new GridLayout(4, 1, 0, 10));
		jpDatos.add(jpEtiquetas);
		jpDatos.add(jpTextos);
		JPanel jpIA = new JPanel();
		jpIA.setLayout(new GridLayout(1,2,0,0));
		jpIA.add(new JLabel("ID:", JLabel.RIGHT));
		jpEtiquetas.add(jpIA);
		jpTextos.add(jtfId);
		jtfId.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfId.setEditable(false);
		jpEtiquetas.add(new JLabel("Número de Celda:", JLabel.RIGHT));
		jpTextos.add(jtfNuCelda);
		jtfNuCelda.setBorder(BorderFactory.createLoweredBevelBorder());
		if (modoEditable == JPMostrarDatos.MODO_NUEVO || modoEditable == JPMostrarDatos.MODO_MODIFICAR) jtfNuCelda.setEditable(true);
		else jtfNuCelda.setEditable(false);
		jpEtiquetas.add(new JLabel("Capacidad de Celda:", JLabel.RIGHT));
		jpTextos.add(jtfCapacidad);
		jtfCapacidad.setBorder(BorderFactory.createLoweredBevelBorder());
		if (modoEditable == JPMostrarDatos.MODO_NUEVO) jtfCapacidad.setEditable(true);
		else jtfCapacidad.setEditable(false);
		jpEtiquetas.add(new JLabel("ID del Sector:", JLabel.RIGHT));
		jpTextos.add(jtfSector);
		jtfSector.setBorder(BorderFactory.createLoweredBevelBorder());
		if (modoEditable == JPMostrarDatos.MODO_NUEVO) jtfCapacidad.setEditable(true);
		else jtfSector.setEditable(false);
		
		jpDatos.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.add(jpDatos);
		
		//Presos
		JPanel jpJL = new JPanel();
		jpJL.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Presos",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 14), 
											Color.BLACK));
		if (jlPresos != null)
		{
			jlPresos.setBackground(null);
			DefaultListCellRenderer rendererA =  (DefaultListCellRenderer)jlPresos.getCellRenderer();  
			rendererA.setHorizontalAlignment(JLabel.CENTER);
			jpJL.add(jlPresos, BorderLayout.CENTER);
		}
		JScrollPane sPAct = new JScrollPane
				(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sPAct.setViewportView(jpJL);
		sPAct.setBorder(null);
		jpPresos.add(sPAct);
		
		this.add(jpPresos);
	}
	
	public static final int MODO_NUEVO = 1;
	public static final int MODO_MODIFICAR = 2;
	public static final int MODO_NO = 0;
}
