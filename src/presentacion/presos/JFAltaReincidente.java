package presentacion.presos;

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

import negocio.presos.TransferPresos;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFAltaReincidente extends JFrame{
	public class JBAltaReincidente extends JButton{	

		public class ALAltaReincidente implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(JFAltaReincidente.this.campo.getText());
					Controlador.getInstance().actualiza(Controlador.MOSTRAR_PARA_ALTA_REINCIDENTE, id);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFAltaReincidente.this, "Introduzca un entero" , "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public JBAltaReincidente() {
			super("Buscar Preso");
			this.addActionListener(new ALAltaReincidente());
		}
	}


	public JFAltaReincidente() {
		super("Dar de alta un Preso Reincidente");
		setSize(460,130);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		initGUI();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent ev) {
				if (JFAltaReincidente.this.abierto != null){
					JFAltaReincidente.this.abierto.setVisible(false);
					JFAltaReincidente.this.abierto.dispose();
				}
			}
		});
	}

	private void initGUI() {
		JPanel jpCampo = new JPanel();
		jpCampo.setLayout(new BorderLayout());
		jpCampo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca el ID del Preso que se vuelve a dar de alta.");
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
		jpBoton.add(new JBAltaReincidente());
		this.add(jpBoton);
		
	}

	public void informar(int resultado, TransferPresos presos) {
		switch (resultado ) {
		case Controlador.OPERACION_CORRECTA: 	this.abrirVentana(new JFAltaReincidente2(presos));
												break;
		case Controlador.PRESO_NO_EXISTENTE: 	JOptionPane.showMessageDialog
													(this, "Preso no existente o dado de alta", "Error",
													JOptionPane.ERROR_MESSAGE); 
												break;
		}
	}
	
	private void abrirVentana(JFAltaReincidente2 vent) {
		if (this.abierto != null){
			this.abierto.setVisible(false);
			this.abierto.dispose();
		}
		this.abierto = vent;
		vent.setLocationRelativeTo(this);
		vent.setVisible(true);
	}
	
	public static JFAltaReincidente getInstance() {
		return instancia;
	}
	
	private static JFAltaReincidente instancia = null;
	private JTextField campo;
	
	private JFAltaReincidente2 abierto;
}
