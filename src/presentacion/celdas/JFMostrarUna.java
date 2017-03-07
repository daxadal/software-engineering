/**
 * 
 */
package presentacion.celdas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import negocio.celdas.TransferCeldas;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFMostrarUna extends JFrame{
	public class JBMostrarUno extends JButton{	

		public class ALMostrarUno implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(JFMostrarUna.this.campo.getText());
					Controlador.getInstance().actualiza(Controlador.MOSTRAR_UNA_CELDA, id);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFMostrarUna.this, "Introduzca un entero" , "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public JBMostrarUno() {
			super("Mostrar");
			this.addActionListener(new ALMostrarUno());
		}
	}

	public JFMostrarUna() {
		super("Mostrar una Celda");
		setSize(400,130);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		initGUI();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent ev) {
				if (JFMostrarUna.this.abierto != null){
					JFMostrarUna.this.abierto.setVisible(false);
					JFMostrarUna.this.abierto.dispose();
				}
			}
		});
	}

	private void initGUI() {
		JPanel jpCampo = new JPanel();
		jpCampo.setLayout(new BorderLayout());
		jpCampo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca el ID de la Celda a mostrar.");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		jpCampo.add(texto, BorderLayout.NORTH);
		JLabel nom = new JLabel("ID:");
		nom.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampo.add(nom, BorderLayout.WEST);
		this.campo = new JTextField();
		campo.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampo.add(this.campo, BorderLayout.CENTER);
		this.add(jpCampo, BorderLayout.NORTH);
		
		JPanel jpBoton = new JPanel();
		jpBoton.add(new JBMostrarUno());
		this.add(jpBoton);
		
		
	}

	public void informar(int resultado, TransferCeldas celdas) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: 	this.abrirVentana(new JFMostrarUna2(celdas));
													break;
			case Controlador.CELDA_NO_EXISTENTE: 	JOptionPane.showMessageDialog
														(this, "Celda no existente", "Error",
														JOptionPane.ERROR_MESSAGE); 
													break;
		}
	}
	
	private void abrirVentana(JFMostrarUna2 vent) {
		if (this.abierto != null){
			this.abierto.setVisible(false);
			this.abierto.dispose();
		}
		this.abierto = vent;
		vent.setLocationRelativeTo(this);
		vent.setVisible(true);
	}

	public static JFMostrarUna getInstance() {
		return instancia;
	}
	
	private static JFMostrarUna instancia = null;
	private JTextField campo;
	
	private JFMostrarUna2 abierto;
}