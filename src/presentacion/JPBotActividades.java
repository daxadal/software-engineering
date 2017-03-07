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

import presentacion.actividades.JFAnyadirPreso;
import presentacion.actividades.JFCrear;
import presentacion.actividades.JFEliminar;
import presentacion.actividades.JFEliminarPreso;
import presentacion.actividades.JFMostrarDePreso;
import presentacion.actividades.JFMostrarTodos;
import presentacion.actividades.JFMostrarUno;

@SuppressWarnings("serial")
public class JPBotActividades extends JPanel{
	
	public JPBotActividades() {
		
		this.setLayout(new GridLayout(9,1));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		this.setBorder(new CompoundBorder());
		this.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"ACTIVIDADES",
											TitledBorder.CENTER, TitledBorder.TOP,
											new Font("Courier New", Font.TRUETYPE_FONT, 18), 
											Color.BLACK));
		
		jbCrearNueva = new JButton("Crear Actividad");
		URL iconCN = JFPrincipal.class.getResource("iconos/calendar_add.png");
		jbCrearNueva.setVerticalTextPosition(JButton.BOTTOM);
		jbCrearNueva.setHorizontalTextPosition(JButton.CENTER);
		Dimension dAN = jbCrearNueva.getPreferredSize();
		dAN.setSize(Math.max(dAN.getWidth(), SIZE), SIZE);
		jbCrearNueva.setPreferredSize(dAN);
		jbCrearNueva.setVerticalTextPosition(JButton.BOTTOM);
		jbCrearNueva.setIcon(new ImageIcon(iconCN));
		jbCrearNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFCrear());	
			}
		});
		
		jbEliminar = new JButton("Eliminar Actividad");
		URL iconE = JFPrincipal.class.getResource("iconos/calendar_delete.png");
		jbEliminar.setVerticalTextPosition(JButton.BOTTOM);
		jbEliminar.setHorizontalTextPosition(JButton.CENTER);
		Dimension dE = jbEliminar.getPreferredSize();
		dE.setSize(Math.max(dE.getWidth(), SIZE), SIZE);
		jbEliminar.setPreferredSize(dE);
		jbEliminar.setVerticalTextPosition(JButton.BOTTOM);
		jbEliminar.setIcon(new ImageIcon(iconE));
		jbEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFEliminar());
			}
		});
		
		jbAnyadirPresp = new JButton("Añadir Preso");
		URL iconAP = JFPrincipal.class.getResource("iconos/add_persona.png");
		jbAnyadirPresp.setVerticalTextPosition(JButton.BOTTOM);
		jbAnyadirPresp.setHorizontalTextPosition(JButton.CENTER);
		Dimension dAP = jbAnyadirPresp.getPreferredSize();
		dAP.setSize(Math.max(dAP.getWidth(), SIZE), SIZE);
		jbAnyadirPresp.setPreferredSize(dAP);
		jbAnyadirPresp.setVerticalTextPosition(JButton.BOTTOM);
		jbAnyadirPresp.setIcon(new ImageIcon(iconAP));
		jbAnyadirPresp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFAnyadirPreso());
			}
		});
		
		jbEliminarPreso = new JButton("Eliminar Preso");
		URL iconEP = JFPrincipal.class.getResource("iconos/delete_persona.png");
		jbEliminarPreso.setVerticalTextPosition(JButton.BOTTOM);
		jbEliminarPreso.setHorizontalTextPosition(JButton.CENTER);
		Dimension dEP = jbEliminarPreso.getPreferredSize();
		dEP.setSize(Math.max(dEP.getWidth(), SIZE), SIZE);
		jbEliminarPreso.setPreferredSize(dEP);
		jbEliminarPreso.setVerticalTextPosition(JButton.BOTTOM);
		jbEliminarPreso.setIcon(new ImageIcon(iconEP));
		jbEliminarPreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFEliminarPreso());
			}
		});

		jbMostrarUna = new JButton("Mostrar Una");
		URL iconMU = JFPrincipal.class.getResource("iconos/calendar.png");
		jbMostrarUna.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarUna.setHorizontalTextPosition(JButton.CENTER);
		Dimension dMU = jbMostrarUna.getPreferredSize();
		dMU.setSize(Math.max(dMU.getWidth(), SIZE), SIZE);
		jbMostrarUna.setPreferredSize(dMU);
		jbMostrarUna.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarUna.setIcon(new ImageIcon(iconMU));
		jbMostrarUna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFMostrarUno());
			}
		});
		
		jbMostrarTodas = new JButton("Mostrar Todas");
		URL iconMT = JFPrincipal.class.getResource("iconos/calendars.png");
		jbMostrarTodas.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarTodas.setHorizontalTextPosition(JButton.CENTER);
		Dimension dMT = jbMostrarTodas.getPreferredSize();
		dMT.setSize(Math.max(dMT.getWidth(), SIZE), SIZE);
		jbMostrarTodas.setPreferredSize(dMT);
		jbMostrarTodas.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarTodas.setIcon(new ImageIcon(iconMT));
		jbMostrarTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFMostrarTodos());
			}
		});
		
		jbMostrarActPreso = new JButton("Mostrar de un Preso");
		URL iconAC = JFPrincipal.class.getResource("iconos/calendar_person.png");
		jbMostrarActPreso.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarActPreso.setHorizontalTextPosition(JButton.CENTER);
		Dimension dAC = jbMostrarActPreso.getPreferredSize();
		dAC.setSize(Math.max(dAC.getWidth(), SIZE), SIZE);
		jbMostrarActPreso.setPreferredSize(dAC);
		jbMostrarActPreso.setVerticalTextPosition(JButton.BOTTOM);
		jbMostrarActPreso.setIcon(new ImageIcon(iconAC));
		jbMostrarActPreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFPrincipal.getInstance().abrirVentana(new JFMostrarDePreso());
			}
		});
		
		this.add(this.jbCrearNueva);
		this.add(this.jbEliminar);
		this.add(this.jbAnyadirPresp);
		this.add(this.jbEliminarPreso);
		this.add(this.jbMostrarUna);
		this.add(this.jbMostrarTodas);
		this.add(this.jbMostrarActPreso);
	}
	
	private JButton jbCrearNueva;
	private JButton jbEliminar;
	private JButton jbAnyadirPresp;
	private JButton jbEliminarPreso;
	private JButton jbMostrarUna;
	private JButton jbMostrarTodas;
	private JButton jbMostrarActPreso;
	private static final int  SIZE = 80;

}
