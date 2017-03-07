package presentacion.visitas;

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
public class JFEliminarVisitante extends JFrame {
	public class JBEliminarVisitante extends JButton{
		public class ALEliminarVisitante implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int visita = Integer.parseInt(JFEliminarVisitante.this.campoVisita.getText());
					int visitante = Integer.parseInt(JFEliminarVisitante.this.campoVisitante.getText());
					int[] data = new int[]{visita, visitante};
					Controlador.getInstance().actualiza(Controlador.ELIMINAR_VISITANTE_DE_VISITA, data);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFEliminarVisitante.this, "Ambos campos deben contener enteros" , "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public JBEliminarVisitante() {
			super("Eliminar visitante de visita");
			this.addActionListener(new ALEliminarVisitante());
		}
	}
	public JFEliminarVisitante() {
		super("Eliminar visitante de visita");
		setSize(425,200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		initGUI();
	}

	private void initGUI() {
		
		//Panel de todos los campos
		JPanel jpTodosCampos = new JPanel();
		jpTodosCampos.setLayout(new BorderLayout());
		this.add(jpTodosCampos, BorderLayout.NORTH);
		
		
		//Panel actividad
		JPanel jpCampoActividad = new JPanel();
		jpCampoActividad.setLayout(new BorderLayout());
		jpCampoActividad.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca el ID de la Visita.");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		jpCampoActividad.add(texto, BorderLayout.NORTH);
		JLabel nom = new JLabel("ID:", JLabel.RIGHT);
		nom.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampoActividad.add(nom, BorderLayout.WEST);
		this.campoVisita = new JTextField();
		campoVisita.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampoActividad.add(this.campoVisita, BorderLayout.CENTER);
		jpTodosCampos.add(jpCampoActividad, BorderLayout.NORTH);
		
		//Panel Preso
		JPanel jpCampoPreso = new JPanel();
		jpCampoPreso.setLayout(new BorderLayout());
		jpCampoPreso.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textBPreso = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel textoPreso = new JLabel("Introduzca el ID del Visitante que quieres eliminar.");
		textoPreso.setFont(new Font("Courier New", Font.ITALIC, 12));
		textoPreso.setBorder(textBPreso);
		
		textoPreso.setBackground(null);
		jpCampoPreso.add(textoPreso, BorderLayout.NORTH);
		JLabel nomPreso = new JLabel("ID:", JLabel.RIGHT);
		nomPreso.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampoPreso.add(nomPreso, BorderLayout.WEST);
		this.campoVisitante = new JTextField();
		campoVisitante.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampoPreso.add(this.campoVisitante, BorderLayout.CENTER);
		jpTodosCampos.add(jpCampoPreso, BorderLayout.SOUTH);

		//Panel boton
		JPanel jpBoton = new JPanel();
		jpBoton.add(new JBEliminarVisitante());
		this.add(jpBoton, BorderLayout.CENTER);	
	}

	public void informar(int resultado) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
													(this, "Visitante eliminado con éxito", "Visitante eliminado",
													JOptionPane.INFORMATION_MESSAGE); break;
			case Controlador.ESCRITURA_INCORRECTA: JOptionPane.showMessageDialog
													(this, "Error en la escritura de los datos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.VISITA_NO_EXISTENTE: JOptionPane.showMessageDialog
													(this, "Visita no existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.VISITA_PASADA:JOptionPane.showMessageDialog
											(this, "Visita pasada", "Error",
											JOptionPane.ERROR_MESSAGE); break;
			case Controlador.VISITANTE_NO_PERTENECIENTE_VISITA:JOptionPane.showMessageDialog
																(this, "Visitante no perteneciente a la visita", "Error",
																JOptionPane.ERROR_MESSAGE); break;
			case Controlador.VISITANTE_NO_EXISTENTE:JOptionPane.showMessageDialog
													(this, "Visitante no existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.VISITA_UN_VISITANTE: JOptionPane.showMessageDialog
													(this, "La visita debe tener al menos un visitante", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFEliminarVisitante getInstance() {
		return instancia;
	}
	
	private static JFEliminarVisitante instancia = null;

	
	private JTextField campoVisita;
	private JTextField campoVisitante;
}
