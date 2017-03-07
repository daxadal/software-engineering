package presentacion;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.*;

import presentacion.sectores.*;

@SuppressWarnings("serial")
public class JPBotSect extends JPanel {
	
	public JPBotSect() {
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
		this.setBorder(new CompoundBorder());
		this.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"SECTORES",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 18), 
											Color.BLACK));
		
		
		jbCrear = new JButton("Crear");
		URL iconC = JFPrincipal.class.getResource("iconos/add.png");
	    jbCrear.setVerticalTextPosition(JButton.BOTTOM);
		jbCrear.setHorizontalTextPosition(JButton.CENTER);
		Dimension dC = jbCrear.getPreferredSize();
		dC.setSize(Math.max(dC.getWidth(), SIZE), SIZE);
		jbCrear.setPreferredSize(dC);
	    jbCrear.setVerticalTextPosition(JButton.BOTTOM);
		jbCrear.setIcon(new ImageIcon(iconC));
		jbCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFCrear());
				
			}
		});
		
		jbEliminar = new JButton("Eliminar");
		URL iconB = JFPrincipal.class.getResource("iconos/delete.png");
		jbEliminar.setHorizontalTextPosition(JButton.CENTER);
		jbEliminar.setVerticalTextPosition(JButton.BOTTOM);
		Dimension dE= jbEliminar.getPreferredSize();
		dE.setSize(Math.max(dE.getWidth(), SIZE), SIZE);
		jbEliminar.setPreferredSize(dE);
		jbEliminar.setIcon(new ImageIcon(iconB));
		jbEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFEliminar());
			}
		});
		
		URL iconM = JFPrincipal.class.getResource("iconos/edit.png");
		jbModificar = new JButton("Modificar");
		jbModificar.setHorizontalTextPosition(JButton.CENTER);
		jbModificar.setVerticalTextPosition(JButton.BOTTOM);
		Dimension dM= jbModificar.getPreferredSize();
		dM.setSize(Math.max(dM.getWidth(), SIZE), SIZE);
		jbModificar.setPreferredSize(dE);
		jbModificar.setIcon(new ImageIcon(iconM));
		jbModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFModificar());
			}
		});
		
		jbMostrarUno = new JButton("Mostrar Uno");
		URL iconMU = JFPrincipal.class.getResource("iconos/show.png");
		jbMostrarUno.setHorizontalTextPosition(JButton.CENTER);
		jbMostrarUno.setVerticalTextPosition(JButton.BOTTOM);
		Dimension dMU= jbMostrarUno.getPreferredSize();
		dMU.setSize(Math.max(dMU.getWidth(), SIZE), SIZE);
		jbMostrarUno.setPreferredSize(dMU);
		jbMostrarUno.setIcon(new ImageIcon(iconMU));
		jbMostrarUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFMostrarUno());
			}
		});
		jbMostrarTodos = new JButton("Mostrar Todos");
		URL iconMT = JFPrincipal.class.getResource("iconos/showAll.png");		
		jbMostrarTodos.setHorizontalTextPosition(JButton.CENTER);
		jbMostrarTodos.setVerticalTextPosition(JButton.BOTTOM);
		Dimension dMT= jbMostrarTodos.getPreferredSize();
		dMT.setSize(Math.max(dMT.getWidth(), SIZE), SIZE);
		jbMostrarTodos.setPreferredSize(dMT);
		jbMostrarTodos.setIcon(new ImageIcon(iconMT));
		jbMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFMostrarTodos());
			}
		});
		
		add(this.jbCrear);
		add(this.jbEliminar);
		add(this.jbMostrarUno);
		add(this.jbMostrarTodos);
		add(this.jbModificar);

	}
	
	private JButton jbCrear;
	private JButton jbEliminar;
	private JButton jbModificar;
	private JButton jbMostrarUno;
	private JButton jbMostrarTodos;
	private static final int  SIZE = 100;
}
