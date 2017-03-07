package presentacion;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import presentacion.visitantes.JFAlta;
import presentacion.visitantes.JFBaja;
import presentacion.visitantes.JFModificar;
import presentacion.visitantes.JFMostrarTodos;
import presentacion.visitantes.JFMostrarUno;

@SuppressWarnings("serial")
public class JPBotVisitantes extends JPanel {

	public JPBotVisitantes() {
		
		this.setLayout(new GridLayout(9,1));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
		this.setBorder(new CompoundBorder());
		this.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"VISITANTES",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 18), 
											Color.BLACK));
		
		jbAlta = new JButton("Dar de alta");
		URL iconC = JFPrincipal.class.getResource("iconos/add_persona.png");
		jbAlta.setVerticalTextPosition(JButton.BOTTOM);
		jbAlta.setHorizontalTextPosition(JButton.CENTER);
		Dimension dA = jbAlta.getPreferredSize();
		dA.setSize(Math.max(dA.getWidth(), SIZE), SIZE);
		jbAlta.setPreferredSize(dA);
		jbAlta.setVerticalTextPosition(JButton.BOTTOM);
		jbAlta.setIcon(new ImageIcon(iconC));
		jbAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFAlta());
				
			}
		});
		
		jbBaja = new JButton("Dar de baja");
		URL iconB = JFPrincipal.class.getResource("iconos/delete_persona.png");
		jbBaja.setVerticalTextPosition(JButton.BOTTOM);
		jbBaja.setHorizontalTextPosition(JButton.CENTER);
		Dimension dB = jbAlta.getPreferredSize();
		dB.setSize(Math.max(dB.getWidth(), SIZE), SIZE);
		jbBaja.setPreferredSize(dB);
		jbBaja.setVerticalTextPosition(JButton.BOTTOM);
		jbBaja.setIcon(new ImageIcon(iconB));
		jbBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFBaja());
			}
		});
		
		jbModificar = new JButton("Modificar");
		URL iconM = JFPrincipal.class.getResource("iconos/edit_persona.png");
		jbModificar.setVerticalTextPosition(JButton.BOTTOM);
		jbModificar.setHorizontalTextPosition(JButton.CENTER);
		Dimension dM = jbAlta.getPreferredSize();
		dM.setSize(Math.max(dM.getWidth(), SIZE), SIZE);
		jbModificar.setPreferredSize(dB);
		jbModificar.setVerticalTextPosition(JButton.BOTTOM);
		jbModificar.setIcon(new ImageIcon(iconM));
		jbModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFModificar());
			}
		});
		
		jbMostrarUno = new JButton("Mostrar Uno");
		URL iconMU = JFPrincipal.class.getResource("iconos/persona.png");
		jbMostrarUno.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarUno.setHorizontalTextPosition(JButton.CENTER);
		Dimension dMU = jbMostrarUno.getPreferredSize();
		dMU.setSize(Math.max(dMU.getWidth(), SIZE), SIZE);
		jbMostrarUno.setPreferredSize(dMU);
		jbMostrarUno.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarUno.setIcon(new ImageIcon(iconMU));
		jbMostrarUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFMostrarUno());
			}
		});
		
		jbMostrarTodos = new JButton("Mostrar Todos");
		URL iconMT = JFPrincipal.class.getResource("iconos/personas.png");
		jbMostrarTodos.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarTodos.setHorizontalTextPosition(JButton.CENTER);
		Dimension dMT = jbMostrarTodos.getPreferredSize();
		dMT.setSize(Math.max(dMT.getWidth(), SIZE), SIZE);
		jbMostrarTodos.setPreferredSize(dMT);
		jbMostrarTodos.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarTodos.setIcon(new ImageIcon(iconMT));
		jbMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFMostrarTodos());
			}
		});
		
		this.add(this.jbAlta);
		this.add(this.jbBaja);
		this.add(this.jbModificar);
		this.add(this.jbMostrarUno);
		this.add(this.jbMostrarTodos);

	}
	
	private JButton jbAlta;
	private JButton jbBaja;
	private JButton jbModificar;
	private JButton jbMostrarUno;
	private JButton jbMostrarTodos;
	private static final int SIZE = 100;
}
