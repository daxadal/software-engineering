package presentacion.actividades;

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
public class JFEliminarPreso extends JFrame {
	public class JBEliminarPreso extends JButton{
		public class ALEliminarPreso implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int actividad = Integer.parseInt(JFEliminarPreso.this.campoActividad.getText());
					int preso = Integer.parseInt(JFEliminarPreso.this.campoPreso.getText());
					int[] data = new int[]{actividad, preso};
					Controlador.getInstance().actualiza(Controlador.ELIMINAR_PRESO_DE_ACTIVIDAD, data);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFEliminarPreso.this, "Ambos campos deben contener enteros" , "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public JBEliminarPreso() {
			super("Eliminar preso de actividad");
			this.addActionListener(new ALEliminarPreso());
		}
	}
	public JFEliminarPreso() {
		super("Eliminar preso de actividad");
		setSize(400,200);
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
		JLabel texto = new JLabel("Introduzca el ID de la Actividad.");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		jpCampoActividad.add(texto, BorderLayout.NORTH);
		JLabel nom = new JLabel("ID:", JLabel.RIGHT);
		nom.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampoActividad.add(nom, BorderLayout.WEST);
		this.campoActividad = new JTextField();
		campoActividad.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampoActividad.add(this.campoActividad, BorderLayout.CENTER);
		jpTodosCampos.add(jpCampoActividad, BorderLayout.NORTH);
		
		//Panel Preso
		JPanel jpCampoPreso = new JPanel();
		jpCampoPreso.setLayout(new BorderLayout());
		jpCampoPreso.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textBPreso = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel textoPreso = new JLabel("Introduzca el ID del Preso que quieres eliminar.");
		textoPreso.setFont(new Font("Courier New", Font.ITALIC, 12));
		textoPreso.setBorder(textBPreso);
		
		textoPreso.setBackground(null);
		jpCampoPreso.add(textoPreso, BorderLayout.NORTH);
		JLabel nomPreso = new JLabel("ID:", JLabel.RIGHT);
		nomPreso.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampoPreso.add(nomPreso, BorderLayout.WEST);
		this.campoPreso = new JTextField();
		campoPreso.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampoPreso.add(this.campoPreso, BorderLayout.CENTER);
		jpTodosCampos.add(jpCampoPreso, BorderLayout.SOUTH);

		//Panel boton
		JPanel jpBoton = new JPanel();
		jpBoton.add(new JBEliminarPreso());
		this.add(jpBoton, BorderLayout.CENTER);	
	}

	public void informar(int resultado) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
													(this, "Preso eliminado con �xito", "Preso eliminado",
													JOptionPane.INFORMATION_MESSAGE); break;
			case Controlador.ESCRITURA_INCORRECTA: JOptionPane.showMessageDialog
													(this, "Error en la escritura de los datos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.ACTIVIDAD_NO_EXISTENTE: JOptionPane.showMessageDialog
													(this, "Actividad no existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.ACTIVIDAD_PASADA:JOptionPane.showMessageDialog
												(this, "Actividad pasada", "Error",
												JOptionPane.ERROR_MESSAGE); break;
			case Controlador.PRESO_NO_EXISTENTE:JOptionPane.showMessageDialog
													(this, "Preso no existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.PRESO_NO_PERTENECIENTE:JOptionPane.showMessageDialog
														(this, "Preso no perteneciente a la actividad", "Error",
														JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFEliminarPreso getInstance() {
		return instancia;
	}
	
	private static JFEliminarPreso instancia = null;

	
	private JTextField campoActividad;
	private JTextField campoPreso;
}
