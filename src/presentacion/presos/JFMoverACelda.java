package presentacion.presos;

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
public class JFMoverACelda extends JFrame{
	public class JBMoverACelda extends JButton{
		public class ALMoverACelda implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int cell_destino = Integer.parseInt(JFMoverACelda.this.campoCeldaDestino.getText());
					int preso = Integer.parseInt(JFMoverACelda.this.campoPreso.getText());
					int[] data = new int[]{preso, cell_destino};
					Controlador.getInstance().actualiza(Controlador.MOVER_PRESO_CELDA, data);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFMoverACelda.this, "Ambos campos deben contener enteros" , "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public JBMoverACelda() {
			super("Mover preso de celda");
			this.addActionListener(new ALMoverACelda());
		}
	}
	public JFMoverACelda() {
		super("Mover preso de celda");
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
		
		//Panel Preso
		JPanel jpCampoPreso = new JPanel();
		jpCampoPreso.setLayout(new BorderLayout());
		jpCampoPreso.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textBPreso = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel textoPreso = new JLabel("Introduzca el ID del Preso.");
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
		
		//Panel actividad
		JPanel jpCampoActividad = new JPanel();
		jpCampoActividad.setLayout(new BorderLayout());
		jpCampoActividad.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca el ID de la Celda destino.");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		jpCampoActividad.add(texto, BorderLayout.NORTH);
		JLabel nom = new JLabel("ID:", JLabel.RIGHT);
		nom.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampoActividad.add(nom, BorderLayout.WEST);
		this.campoCeldaDestino = new JTextField();
		campoCeldaDestino.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampoActividad.add(this.campoCeldaDestino, BorderLayout.CENTER);
		jpTodosCampos.add(jpCampoActividad, BorderLayout.NORTH);
		
		

		//Panel boton
		JPanel jpBoton = new JPanel();
		jpBoton.add(new JBMoverACelda());
		this.add(jpBoton, BorderLayout.CENTER);
	}

	public void informar(int resultado) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
													(this, "Preso movido con éxito", "Preso movido",
													JOptionPane.INFORMATION_MESSAGE); break;
			case Controlador.ESCRITURA_INCORRECTA: JOptionPane.showMessageDialog
													(this, "Error en la escritura de los datos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.CELDA_LLENA: JOptionPane.showMessageDialog
													(this, "Celda llena", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.CELDA_NO_EXISTENTE:JOptionPane.showMessageDialog
													(this, "Celda no existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.PRESO_NO_EXISTENTE:JOptionPane.showMessageDialog
													(this, "Preso no existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFMoverACelda getInstance() {
		return instancia;
	}
	
	private static JFMoverACelda instancia = null;

	
	private JTextField campoCeldaDestino;
	private JTextField campoPreso;
}
