package presentacion.celdas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFMoverASector extends JFrame{
	public class JBMover extends JButton{	

		public class ALMover implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int[] id = {Integer.parseInt(JFMoverASector.this.celda.getText()), Integer.parseInt(JFMoverASector.this.sectorN.getText())};
					Controlador.getInstance().actualiza(Controlador.MOVER_CELDA_SECTOR, id);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFMoverASector.this, "Introduzca un entero" , "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public JBMover() {
			super("Mover Celda");
			this.addActionListener(new ALMover());
		}
	}


	public JFMoverASector() {
		super("Mover Celda a un Sector");
		setSize(520,170);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		initGUI();
	}

	private void initGUI() {
		JPanel jpCampo = new JPanel();
		jpCampo.setLayout(new BorderLayout());
		jpCampo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca el ID de la celda a mover y el ID del sector destino");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		jpCampo.add(texto, BorderLayout.NORTH);
		JLabel idCelda = new JLabel("ID de Celda:");
		JLabel idSector = new JLabel("ID de Sector:");
		idCelda.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		idSector.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

		JPanel jpEtiq = new JPanel();
		jpEtiq.setLayout(new GridLayout(2,1,0,10));
		jpEtiq.add(idCelda);
		jpEtiq.add(idSector);
		jpCampo.add(jpEtiq, BorderLayout.WEST);
		
		this.celda = new JTextField();
		this.sectorN = new JTextField();
		celda.setBorder(BorderFactory.createLoweredBevelBorder());
		sectorN.setBorder(BorderFactory.createLoweredBevelBorder());
		
		JPanel jpText = new JPanel();
		jpText.setLayout(new GridLayout(2,1,0,10));
		jpText.add(celda);
		jpText.add(sectorN);
		jpCampo.add(jpText, BorderLayout.CENTER);
		
		this.add(jpCampo, BorderLayout.NORTH);
		
		JPanel jpBoton = new JPanel();
		jpBoton.add(new JBMover());
		this.add(jpBoton);
		
	}

	public void informar(int resultado) {
		switch (resultado ) {
		case Controlador.OPERACION_CORRECTA: 	JOptionPane.showMessageDialog
												(this, "Celda trasladada con éxito.", "Celda trasladada",
												JOptionPane.INFORMATION_MESSAGE); break;
		case Controlador.ESCRITURA_INCORRECTA: 	JOptionPane.showMessageDialog
													(this, "Escritura incorrecta", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		case Controlador.SECTOR_NO_EXISTENTE: JOptionPane.showMessageDialog
													(this, "Sector no existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		case Controlador.CELDA_NO_EXISTENTE: JOptionPane.showMessageDialog
													(this, "Celda no existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFMoverASector getInstance() {
		return instancia;
	}
	
	private static JFMoverASector instancia = null;
	private JTextField celda;
	private JTextField sectorN;
	
}