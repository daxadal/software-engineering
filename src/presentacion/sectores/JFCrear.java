/**
 * 
 */
package presentacion.sectores;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import negocio.sectores.TransferSector;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFCrear extends JFrame{
	
	public class JBCrear extends JButton{
		
		public class ALCrear implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = JFCrear.this.campo.getText().trim();
				if (!nombre.equals("")) {
					TransferSector sector = new TransferSector(true, -1, nombre, 0, new Vector<Integer>());
					Controlador.getInstance().actualiza(Controlador.CREAR_SECTOR, sector);
				}
				else JOptionPane.showMessageDialog
							(JFCrear.this, "Introduzca un nombre" , "Error",
							JOptionPane.ERROR_MESSAGE);				
			}
		}
		
		public JBCrear() {
			super("Crear");
			this.addActionListener(new ALCrear());
		}
	}

	public JFCrear() {
		super("Crear un Sector");
		setSize(400,130);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		initGUI();
	}

	private void initGUI() {
		JPanel jpCampo = new JPanel();
		jpCampo.setLayout(new BorderLayout());
		jpCampo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca el nombre del Sector a crear.");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		jpCampo.add(texto, BorderLayout.NORTH);
		JLabel nom = new JLabel("Nombre:");
		nom.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampo.add(nom, BorderLayout.WEST);
		this.campo = new JTextField();
		campo.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampo.add(this.campo, BorderLayout.CENTER);
		this.add(jpCampo, BorderLayout.NORTH);
		
		JPanel jpBoton = new JPanel();
		jpBoton.add(new JBCrear());
		this.add(jpBoton);
		
		
	}

	public void informar(int resultado, int id) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
													(this, "Sector creado con éxito.\nID: " + id, "Sector creado",
													JOptionPane.INFORMATION_MESSAGE); break;
			case Controlador.ESCRITURA_INCORRECTA: JOptionPane.showMessageDialog
													(this, "Error en la escritura de los datos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.DATOS_INCORRECTOS: JOptionPane.showMessageDialog
													(this, "Datos incorrectos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.SECTOR_EXISTENTE: JOptionPane.showMessageDialog
													(this, "Sector ya existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFCrear getInstance() {
		return instancia;
	}
	
	private static JFCrear instancia = null;

	
	private JTextField campo;
}