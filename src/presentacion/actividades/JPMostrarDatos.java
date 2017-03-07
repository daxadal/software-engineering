package presentacion.actividades;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import presentacion.JPAuxFecha;

@SuppressWarnings("serial")
public class JPMostrarDatos extends JPanel{
	
	public JPMostrarDatos(boolean editable, JTextField jtfId, JTextField jtfNombre, JTextArea jtaDescripcion, JTextField jtfAforo, JTextField jtfNoPresos, JPAuxFecha jpFecha, JList<Integer> listaPresos)
	{
		initGUI(editable, jtfId, jtfNombre, jtaDescripcion, jtfAforo, jtfNoPresos, jpFecha, listaPresos);
	}
	private void initGUI(boolean editable, JTextField jtfId, JTextField jtfNombre, JTextArea jtaDescripcion, JTextField jtfAforo, JTextField jtfNoPresos, JPAuxFecha jpFecha, JList<Integer> listaPresos)
	{
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(2, 1, 0, 0));
		JPanel jpPrin = new JPanel( new GridLayout(1, 2, 0, 0));
		JPanel jpOeste = new JPanel(new BorderLayout());
		JPanel jpDatos =  new JPanel(new BorderLayout());
		JPanel jpEste =  new JPanel();
		
		this.add(jpPrin);
		jpPrin.add(jpOeste);
		jpOeste.add(jpDatos);
		jpPrin.add(jpEste);
		//Panel oesteSup
		jpOeste.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Datos",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 14), 
											Color.BLACK));
		
		JPanel jpEtiquetas = new JPanel();
		JPanel jpTextos = new JPanel();
		jpEtiquetas.setLayout(new GridLayout(6, 1, 0, 0));
		jpTextos.setLayout(new GridLayout(6, 1, 0, 10));
		jpDatos.add(jpEtiquetas, BorderLayout.WEST);
		jpDatos.add(jpTextos, BorderLayout.CENTER);
		jpTextos.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		
		jpEtiquetas.add(new JLabel("ID:", JLabel.RIGHT));
		jpTextos.add(jtfId);
		jtfId.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfId.setEditable(false);
		
		jpEtiquetas.add(new JLabel("Nombre:", JLabel.RIGHT));
		jpTextos.add(jtfNombre);
		jtfNombre.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfNombre.setEditable(editable);
		
		jpEtiquetas.add(new JLabel("Aforo:", JLabel.RIGHT));
		jpTextos.add(jtfAforo);
		jtfAforo.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfAforo.setEditable(editable);
		
		jpEtiquetas.add(new JLabel("Nº de Presos:", JLabel.RIGHT));
		jpTextos.add(jtfNoPresos);
		jtfNoPresos.setEditable(false);
		jtfNoPresos.setBorder(BorderFactory.createLoweredBevelBorder());
		
		jpEtiquetas.add(new JLabel("Fecha:", JLabel.RIGHT));
		jpTextos.add(jpFecha);
		
		JPanel jpDescri = new JPanel();
		jpDescri.setLayout(new BorderLayout());
		jpDescri.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Descripción",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 14), 
											Color.BLACK));
		jtaDescripcion.setEditable(editable);
		jpDescri.add(new JScrollPane(jtaDescripcion, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		this.add(jpDescri);
		
		
		//Panel este
		JPanel listaP = new JPanel();
		
		jpEste.setLayout(new BorderLayout());
		jpEste.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Presos",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		if (listaPresos != null)
		{
			listaPresos.setBackground(null);
			DefaultListCellRenderer rendererA =  (DefaultListCellRenderer)listaPresos.getCellRenderer();  
			rendererA.setHorizontalAlignment(JLabel.CENTER);
			listaP.add(listaPresos, BorderLayout.CENTER);
		}
		JScrollPane sPAct = new JScrollPane
				(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sPAct.setViewportView(listaP);
		sPAct.setBorder(null);
		jpEste.add(sPAct);

	}
	
	
}
