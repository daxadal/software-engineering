/**
 * 
 */
package presentacion.sectores;

import java.awt.BorderLayout;
import java.awt.Font;
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
public class JFEliminar extends JFrame{
	public class JBEliminar extends JButton{	

		public class ALEliminar implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(JFEliminar.this.campo.getText());
					Controlador.getInstance().actualiza(Controlador.ELIMINAR_SECTOR, id);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFEliminar.this, "Introduzca un entero" , "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public JBEliminar() {
			super("Eliminar");
			this.addActionListener(new ALEliminar());
		}
	}

	public JFEliminar() {
		super("Eliminar un Sector");
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
		JLabel texto = new JLabel("Introduzca el ID del Sector a eliminar.");
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
		jpBoton.add(new JBEliminar());
		this.add(jpBoton);
		
		
	}

	public void informar(int resultado) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
													(this, "Sector eliminado con éxito.", "Sector eliminado",
													JOptionPane.INFORMATION_MESSAGE); break;
			case Controlador.ESCRITURA_INCORRECTA: JOptionPane.showMessageDialog
													(this, "Error en la escritura de los datos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.SECTOR_NO_EXISTENTE: JOptionPane.showMessageDialog
													(this, "Sector no existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.SECTOR_CON_CELDAS: JOptionPane.showMessageDialog
													(this, "Sector con celdas asociadas", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFEliminar getInstance() {
		return instancia;
	}
	
	private static JFEliminar instancia = null;
	
	private JTextField campo;
}