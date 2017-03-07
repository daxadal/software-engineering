package presentacion;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

@SuppressWarnings("serial")
public class JFPrincipal extends JFrame {
	
	public JFPrincipal() {
		super("Sistema Español de Control de Prisiones");
		setSize(900,400);
		setExtendedState(MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}
	
	private void initGUI() {
		this.abierto = null;
		instancia = this;
		
		JPanel jpBot = new JPanel();
		jpBot.setLayout(new GridLayout(3,2,10,10));
		this.add(jpBot, BorderLayout.CENTER);
		
		jpBot.add(new JPBotSect());
		jpBot.add(new JPBotCeldas());
		jpBot.add(new JPBotPresos());
		jpBot.add(new JPBotVisitantes());
		jpBot.add(new JPBotVisitas());
		jpBot.add(new JPBotActividades());
		
	}
	
	public void abrirVentana(JFrame vent) {
		if (this.abierto != null){
			this.abierto.setVisible(false);
			this.abierto.dispose();
		}
		this.abierto = vent;
		vent.setLocationRelativeTo(this);
		vent.setVisible(true);
	}

	private JFrame abierto;
	
	public static JFPrincipal getInstance() {
		return instancia;
	}
	
	private static JFPrincipal instancia = null;

	//////////////////////////////
	public static void main(String[] args) {
		presentacion(); 
		JFPrincipal jfPrincipal = new JFPrincipal();
		jfPrincipal.setVisible(true);
	}
	
	public static void presentacion()
	{
		JFrame pres = new JFrame("Sistema Español de Control de Prisiones");
		URL icon = JFPrincipal.class.getResource("iconos/pres.png"); 
		pres.add(new JLabel(new ImageIcon(icon)));
		pres.setSize(500,420);
		JProgressBar p = new JProgressBar();
		pres.add(p, BorderLayout.SOUTH);
		pres.setAlwaysOnTop( true );
		pres.setLocationRelativeTo(null);
		pres.setVisible(true);
		int suma = 0;
		while(p.getValue() < 100)
		{
			suma = (int) ( Math.random()*10);
			p.setValue(p.getValue() + suma);
			p.setStringPainted(true);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {	} 
		}
		pres.setVisible(false);
	}
	
	
}
