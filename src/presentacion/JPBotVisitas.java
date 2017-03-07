package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import presentacion.visitas.JFAnyadirVisitante;
import presentacion.visitas.JFCrear;
import presentacion.visitas.JFEliminar;
import presentacion.visitas.JFEliminarVisitante;
import presentacion.visitas.JFMostrarDePreso;
import presentacion.visitas.JFMostrarDeVisitante;
import presentacion.visitas.JFMostrarTodos;
import presentacion.visitas.JFMostrarUno;

@SuppressWarnings("serial")
public class JPBotVisitas extends JPanel {
	
	public JPBotVisitas() {
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		this.setBorder(new CompoundBorder());
		this.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"VISITAS",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 18), 
											Color.BLACK));
		
		
		jbCrear = new JButton("Crear");
		URL iconC = JFPrincipal.class.getResource("iconos/date_add.png");
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
		URL iconB = JFPrincipal.class.getResource("iconos/date_delete.png");
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
		jbMostrarUno = new JButton("Mostrar Una");
		URL iconMU = JFPrincipal.class.getResource("iconos/date.png");
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
		
		jbMostrarTodos = new JButton("Mostrar Todas");
		URL iconMT = JFPrincipal.class.getResource("iconos/dates.png");		
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
		
		jbAnyadirVisitante = new JButton("Añadir Visitante");		
		URL iconAV = JFPrincipal.class.getResource("iconos/add_persona.png");		
		jbAnyadirVisitante.setHorizontalTextPosition(JButton.CENTER);
		jbAnyadirVisitante.setVerticalTextPosition(JButton.BOTTOM);
		Dimension dAV = jbAnyadirVisitante.getPreferredSize();
		dAV.setSize(Math.max(dAV.getWidth(), SIZE), SIZE);
		jbAnyadirVisitante.setPreferredSize(dAV);
		jbAnyadirVisitante.setIcon(new ImageIcon(iconAV));
		jbAnyadirVisitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFAnyadirVisitante());
			}
		});
		
		jbEliminarVisitante = new JButton("Eliminar Visitante");
		URL iconEV = JFPrincipal.class.getResource("iconos/add_persona.png");		
		jbEliminarVisitante.setHorizontalTextPosition(JButton.CENTER);
		jbEliminarVisitante.setVerticalTextPosition(JButton.BOTTOM);
		Dimension dEV = jbEliminarVisitante.getPreferredSize();
		dEV.setSize(Math.max(dEV.getWidth(), SIZE), SIZE);
		jbEliminarVisitante.setPreferredSize(dEV);
		jbEliminarVisitante.setIcon(new ImageIcon(iconEV));
		jbEliminarVisitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFEliminarVisitante());
			}
		});
		
		
		jbMostrarVisPreso = new JButton("Mostrar de Preso");
		URL iconAC = JFPrincipal.class.getResource("iconos/persona_calendar.png"); //FIXME Las imágenes supongo que las tendras que cambiar
		jbMostrarVisPreso.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarVisPreso.setHorizontalTextPosition(JButton.CENTER);
		Dimension dAC = jbMostrarVisPreso.getPreferredSize();
		dAC.setSize(Math.max(dAC.getWidth(), SIZE), SIZE);
		jbMostrarVisPreso.setPreferredSize(dAC);
		jbMostrarVisPreso.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarVisPreso.setIcon(new ImageIcon(iconAC));
		jbMostrarVisPreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFMostrarDePreso());
			}
		});
		
		jbMostrarVisVisitante = new JButton("Mostrar de Visitante");
		URL iconMVV = JFPrincipal.class.getResource("iconos/persona_calendar.png"); //FIXME Las imágenes supongo que las tendras que cambiar
		jbMostrarVisVisitante.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarVisVisitante.setHorizontalTextPosition(JButton.CENTER);
		Dimension dMVV = jbMostrarVisVisitante.getPreferredSize();
		dMVV.setSize(Math.max(dMVV.getWidth(), SIZE), SIZE);
		jbMostrarVisVisitante.setPreferredSize(dMVV);
		jbMostrarVisVisitante.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarVisVisitante.setIcon(new ImageIcon(iconMVV));
		jbMostrarVisVisitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFMostrarDeVisitante());
			}
		});
		
		this.add(this.jbCrear);
		this.add(this.jbEliminar);
		this.add(this.jbMostrarUno);
		this.add(this.jbMostrarTodos);
		this.add(this.jbAnyadirVisitante);
		this.add(this.jbEliminarVisitante);
		this.add(this.jbMostrarVisPreso);
		this.add(this.jbMostrarVisVisitante); //FIXME Este boton ya no cabe y no sale
	}
	
	private JButton jbCrear;
	private JButton jbEliminar;
	private JButton jbMostrarUno;
	private JButton jbMostrarTodos;
	private JButton jbAnyadirVisitante;
	private JButton jbEliminarVisitante;
	private JButton jbMostrarVisPreso;
	private JButton jbMostrarVisVisitante;
	private static final int  SIZE = 90;
}
