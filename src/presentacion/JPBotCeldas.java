package presentacion;import java.awt.Color;import java.awt.Dimension;import java.awt.FlowLayout;import java.awt.Font;import java.awt.GridLayout;import java.awt.Insets;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.net.URL;import javax.swing.ImageIcon;import javax.swing.JButton;import javax.swing.JPanel;import javax.swing.border.CompoundBorder;import javax.swing.border.LineBorder;import javax.swing.border.TitledBorder;import presentacion.celdas.*;@SuppressWarnings("serial")public class JPBotCeldas extends JPanel {	public JPBotCeldas() {		this.setLayout(new GridLayout(9,1));		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));		this.setBorder(new CompoundBorder());		this.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"CELDAS",											TitledBorder.CENTER, TitledBorder.TOP,											new Font("Courier New", Font.TRUETYPE_FONT, 18), 											Color.BLACK));		jbCrear = new JButton("Crear");		URL iconC = JFPrincipal.class.getResource("iconos/add.png");		jbCrear.setVerticalTextPosition(JButton.BOTTOM);		jbCrear.setHorizontalTextPosition(JButton.CENTER);		Dimension dC = jbCrear.getPreferredSize();		dC.setSize(Math.max(dC.getWidth(), SIZE), SIZE);		jbCrear.setPreferredSize(dC);	    jbCrear.setVerticalTextPosition(JButton.BOTTOM);		jbCrear.setIcon(new ImageIcon(iconC));		jbCrear.addActionListener(new ActionListener() {			public void actionPerformed(ActionEvent e) {				JFPrincipal.getInstance().abrirVentana(new JFCrear());			}		});				URL iconB = JFPrincipal.class.getResource("iconos/delete.png");		jbEliminar = new JButton("Eliminar");		jbEliminar.setHorizontalTextPosition(JButton.CENTER);		jbEliminar.setVerticalTextPosition(JButton.BOTTOM);		Dimension dE = jbEliminar.getPreferredSize();		dE.setSize(Math.max(dE.getWidth(), SIZE), SIZE);		jbEliminar.setPreferredSize(dE);		jbEliminar.setIcon(new ImageIcon(iconB));		jbEliminar.addActionListener(new ActionListener() {			public void actionPerformed(ActionEvent e) {				JFPrincipal.getInstance().abrirVentana(new JFEliminar());			}		});				URL iconMU = JFPrincipal.class.getResource("iconos/show.png");		jbMostrarUno = new JButton("Mostrar Una");		jbMostrarUno.setHorizontalTextPosition(JButton.CENTER);		jbMostrarUno.setVerticalTextPosition(JButton.BOTTOM);		Dimension dMU = jbMostrarUno.getPreferredSize();		dMU.setSize(Math.max(dMU.getWidth(), SIZE), SIZE);		jbMostrarUno.setPreferredSize(dMU);		jbMostrarUno.setIcon(new ImageIcon(iconMU));		jbMostrarUno.addActionListener(new ActionListener() {			public void actionPerformed(ActionEvent e) {				JFPrincipal.getInstance().abrirVentana(new JFMostrarUna());			}		});		URL iconMT = JFPrincipal.class.getResource("iconos/showAll.png");		jbMostrarTodos = new JButton("Mostrar Todas");				jbMostrarTodos.setHorizontalTextPosition(JButton.CENTER);		jbMostrarTodos.setVerticalTextPosition(JButton.BOTTOM);		Dimension dMT = jbMostrarTodos.getPreferredSize();		dMT.setSize(Math.max(dMT.getWidth(), SIZE), SIZE);		jbMostrarTodos.setPreferredSize(dMT);		jbMostrarTodos.setIcon(new ImageIcon(iconMT));		jbMostrarTodos.addActionListener(new ActionListener() {			public void actionPerformed(ActionEvent e) {				JFPrincipal.getInstance().abrirVentana(new JFMostrarTodas());			}		});		jbMoverDeSector = new JButton("Mover de Sector");		URL iconMo = JFPrincipal.class.getResource("iconos/move.png");		jbMoverDeSector = new JButton("Mover de Sector");				jbMoverDeSector.setHorizontalTextPosition(JButton.CENTER);		jbMoverDeSector.setVerticalTextPosition(JButton.BOTTOM);		Dimension dMS = jbMoverDeSector.getPreferredSize();		dMS.setSize(Math.max(dMS.getWidth(), SIZE), SIZE);		jbMoverDeSector.setPreferredSize(dMS);		jbMoverDeSector.setMargin(new Insets(0, 0, 0, 0));		jbMoverDeSector.setIcon(new ImageIcon(iconMo));		jbMoverDeSector.addActionListener(new ActionListener() {			public void actionPerformed(ActionEvent e) {				JFPrincipal.getInstance().abrirVentana(new JFMoverASector());			}		});				jbModificar = new JButton("Modificar");		URL iconM = JFPrincipal.class.getResource("iconos/edit.png");		jbModificar.setVerticalTextPosition(JButton.BOTTOM);		jbModificar.setHorizontalTextPosition(JButton.CENTER);		Dimension dM = jbModificar.getPreferredSize();		dM.setSize(Math.max(dM.getWidth(), SIZE), SIZE);		jbModificar.setPreferredSize(dM);		jbModificar.setVerticalTextPosition(JButton.BOTTOM);		jbModificar.setIcon(new ImageIcon(iconM));		jbModificar.addActionListener(new ActionListener() {			public void actionPerformed(ActionEvent e) {				JFPrincipal.getInstance().abrirVentana(new JFModificar());			}		});		this.add(this.jbCrear);		this.add(this.jbEliminar);		this.add(this.jbMostrarUno);		this.add(this.jbMostrarTodos);		this.add(this.jbMoverDeSector);		this.add(this.jbModificar);	}		private JButton jbCrear;	private JButton jbEliminar;	private JButton jbMostrarUno;	private JButton jbMostrarTodos;	private JButton jbMoverDeSector;	private JButton jbModificar;	private static final int  SIZE = 80;}