package presentacion;


import java.awt.*;
import java.awt.event.*;
import java.net.*;

import javax.swing.*;
import javax.swing.border.*;

import presentacion.presos.*;


@SuppressWarnings("serial")
public class JPBotPresos extends JPanel {

	public JPBotPresos() {
		
		this.setLayout(new GridLayout(9,1));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		this.setBorder(new CompoundBorder());
		this.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"PRESOS",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 18), 
											Color.BLACK));
		
		jbAltaNuevo = new JButton("Alta (nuevo)");
		URL iconAN = JFPrincipal.class.getResource("iconos/add_persona.png");
		jbAltaNuevo.setVerticalTextPosition(JButton.BOTTOM);
		jbAltaNuevo.setHorizontalTextPosition(JButton.CENTER);
		Dimension dAN = jbAltaNuevo.getPreferredSize();
		dAN.setSize(Math.max(dAN.getWidth(), SIZE), SIZE);
		jbAltaNuevo.setPreferredSize(dAN);
		jbAltaNuevo.setVerticalTextPosition(JButton.BOTTOM);
		jbAltaNuevo.setIcon(new ImageIcon(iconAN));
		jbAltaNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFAltaNuevo());	
			}
		});
		
		jbAltaReincid = new JButton("Alta (reincid.)");
		URL iconAR = JFPrincipal.class.getResource("iconos/add_personaR.png");
		jbAltaReincid.setVerticalTextPosition(JButton.BOTTOM);
		jbAltaReincid.setHorizontalTextPosition(JButton.CENTER);
		Dimension dAR = jbAltaReincid.getPreferredSize();
		dAR.setSize(Math.max(dAR.getWidth(), SIZE), SIZE);
		jbAltaReincid.setPreferredSize(dAR);
		jbAltaReincid.setVerticalTextPosition(JButton.BOTTOM);
		jbAltaReincid.setIcon(new ImageIcon(iconAR));
		jbAltaReincid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFAltaReincidente());
			}
		});
		
		jbBaja = new JButton("Dar de baja");
		URL iconB = JFPrincipal.class.getResource("iconos/delete_persona.png");
		jbBaja.setVerticalTextPosition(JButton.BOTTOM);
		jbBaja.setHorizontalTextPosition(JButton.CENTER);
		Dimension dB = jbBaja.getPreferredSize();
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
		Dimension dM = jbModificar.getPreferredSize();
		dM.setSize(Math.max(dM.getWidth(), SIZE), SIZE);
		jbModificar.setPreferredSize(dM);
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
		
		jbMoverACelda = new JButton("Mover a Celda");
		URL iconAC = JFPrincipal.class.getResource("iconos/mover.png");
		jbMoverACelda.setVerticalTextPosition(JButton.BOTTOM);
		jbMoverACelda.setHorizontalTextPosition(JButton.CENTER);
		Dimension dAC = jbMoverACelda.getPreferredSize();
		dAC.setSize(Math.max(dAC.getWidth(), SIZE), SIZE);
		jbMoverACelda.setPreferredSize(dAC);
		jbMoverACelda.setVerticalTextPosition(JButton.BOTTOM);
		jbMoverACelda.setIcon(new ImageIcon(iconAC));
		jbMoverACelda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFMoverACelda());
			}
		});
		
		this.add(this.jbAltaNuevo);
		this.add(this.jbAltaReincid);
		this.add(this.jbBaja);
		this.add(this.jbModificar);
		this.add(this.jbMostrarUno);
		this.add(this.jbMostrarTodos);
		this.add(this.jbMoverACelda);
	}
	
	private JButton jbAltaNuevo;
	private JButton jbAltaReincid;
	private JButton jbBaja;
	private JButton jbModificar;
	private JButton jbMostrarUno;
	private JButton jbMostrarTodos;
	private JButton jbMoverACelda;
	private static final int  SIZE = 80;
}
