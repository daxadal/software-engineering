package presentacion.visitas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import presentacion.JPAuxFecha;

@SuppressWarnings("serial")
public class JPMostrarDatos extends JPanel{
	
	public JPMostrarDatos(boolean editable, JTextField jtfId, JTextField jtfPreso, JTextField jtfNoVisitantes, JPAuxFecha jpFecha, JList<Integer> listaVisitantes)
	{
		initGUI(editable, jtfId, jtfPreso, jtfNoVisitantes, jpFecha, listaVisitantes);
	}
	private void initGUI(boolean editable, JTextField jtfId, JTextField jtfPreso, JTextField jtfNoVisitantes, JPAuxFecha jpFecha, JList<Integer> listaVisitantes)
	{
		setLayout(new GridLayout(1,2,10,10));
		JPanel jpOeste =  new JPanel();
		JPanel jpOesteSup =  new JPanel();
		JPanel jpEste =  new JPanel();
		
		jpOeste.setLayout(new GridLayout(1,1,10,10));
		jpOeste.add(jpOesteSup);
		this.add(jpOeste);
		this.add(jpEste);
		//Panel oesteSup
		jpOesteSup.setLayout(new BorderLayout());
		jpOeste.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Datos",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 14), 
											Color.BLACK));
		
		JPanel jpEtiquetas = new JPanel();
		JPanel jpTextos = new JPanel();
		jpEtiquetas.setLayout(new GridLayout(4, 1, 0, 15));
		jpTextos.setLayout(new GridLayout(4, 1, 15, 15));
		jpOesteSup.setSize(100, 100);
		jpOesteSup.add(jpEtiquetas, BorderLayout.WEST);
		jpOesteSup.add(jpTextos);
		
		jpEtiquetas.add(new JLabel("ID Visita:", JLabel.RIGHT));
		jpTextos.add(jtfId);
		jtfId.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfId.setEditable(false);
		
		jpEtiquetas.add(new JLabel("ID Preso:", JLabel.RIGHT));
		jpTextos.add(jtfPreso);
		jtfPreso.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfPreso.setEditable(editable);
		/*
		jpEtiquetas.add(new JLabel("Nº Visitantes:", JLabel.RIGHT));
		jpTextos.add(jtfNoVisitantes);
		jtfNoVisitantes.setBorder(BorderFactory.createLoweredBevelBorder());
		jtfNoVisitantes.setEditable(false);
		*/
		jpEtiquetas.add(new JLabel("Nº de Visitantes:", JLabel.RIGHT));
		jpTextos.add(jtfNoVisitantes);
		jtfNoVisitantes.setEditable(false);
		jtfNoVisitantes.setBorder(BorderFactory.createLoweredBevelBorder());
		
		jpEtiquetas.add(new JLabel("Fecha:", JLabel.RIGHT));
		jpTextos.add(jpFecha);
		jpFecha.setEditable(false);
		
		//Panel este
		jpEste.setLayout(new BorderLayout());
		jpEste.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Visitantes",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		listaVisitantes.setBackground(null);
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)listaVisitantes.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		jpEste.add(listaVisitantes, BorderLayout.CENTER);
	}
	
	
}
