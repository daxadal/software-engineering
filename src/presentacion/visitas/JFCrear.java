/**
 * 
 */
package presentacion.visitas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import negocio.visitas.TransferVisitas;
import presentacion.JPAuxFecha;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFCrear extends JFrame{
	
	public class JBCrear extends JButton{
		
		public class ALCrear implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int preso = Integer.parseInt(JFCrear.this.campoPreso.getText());
					int visitante = Integer.parseInt(JFCrear.this.campoVisitante.getText());
					Calendar fecha = JFCrear.this.campoFecha.getFecha();
					Vector<Integer> vector = new Vector<Integer>();
					vector.add(visitante);
						TransferVisitas actividad = new TransferVisitas( 
								true, -1, fecha, 
								preso, 1, vector);
						Controlador.getInstance().actualiza(Controlador.CREAR_VISITA, actividad);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFCrear.this, "Todos los campos deben contener enteros" , "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException ex){
					JOptionPane.showMessageDialog
					(JFCrear.this, "La fecha indicada no es correcta" , "Error",
					JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public JBCrear() {
			super("Crear");
			this.addActionListener(new ALCrear());
		}
	}
	public JFCrear() {
		super("Crear una Visita");
		setSize(400,260);
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
		JLabel textoPreso = new JLabel("Introduzca el ID del Preso visitado.");
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
		jpTodosCampos.add(jpCampoPreso, BorderLayout.NORTH);
		
		//Panel Visitante
		JPanel jpCampoVisitante = new JPanel();
		jpCampoVisitante.setLayout(new BorderLayout());
		jpCampoVisitante.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textBVisitante = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel textoVisitante = new JLabel("Introduzca el ID del Visitante.");
		textoVisitante.setFont(new Font("Courier New", Font.ITALIC, 12));
		textoVisitante.setBorder(textBVisitante);
		
		textoVisitante.setBackground(null);
		jpCampoVisitante.add(textoVisitante, BorderLayout.NORTH);
		JLabel nomVisitante = new JLabel("ID:", JLabel.RIGHT);
		nomVisitante.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampoVisitante.add(nomVisitante, BorderLayout.WEST);
		this.campoVisitante = new JTextField();
		campoVisitante.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampoVisitante.add(this.campoVisitante, BorderLayout.CENTER);
		jpTodosCampos.add(jpCampoVisitante, BorderLayout.CENTER);

		//Panel Fecha
		JPanel jpCampoFecha = new JPanel();
		jpCampoFecha.setLayout(new BorderLayout());
		jpCampoFecha.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textBFecha = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel textoFecha = new JLabel("Introduzca la fecha de la Visita.");
		textoFecha.setFont(new Font("Courier New", Font.ITALIC, 12));
		textoFecha.setBorder(textBFecha);
		
		textoFecha.setBackground(null);
		jpCampoFecha.add(textoFecha, BorderLayout.NORTH);
		JLabel nomFecha = new JLabel("Fecha:", JLabel.RIGHT);
		nomFecha.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampoFecha.add(nomFecha, BorderLayout.WEST);
		
		//Panel Fecha -> Panel de campos
		this.campoFecha = new JPAuxFecha(true);
		jpCampoFecha.add(campoFecha, BorderLayout.CENTER);
		jpTodosCampos.add(jpCampoFecha, BorderLayout.SOUTH);
		
		//Panel boton
		JPanel jpBoton = new JPanel();
		jpBoton.add(new JBCrear());
		this.add(jpBoton, BorderLayout.CENTER);
		
		//Ancho de etiquetas
		Dimension dimNom = nomFecha.getPreferredSize();
		nomPreso.setPreferredSize(dimNom);
		nomVisitante.setPreferredSize(dimNom);
		
		
	}

	public void informar(int resultado, int id) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
													(this, "Visita creada con éxito.\nID: " + id, "Visita creada",
													JOptionPane.INFORMATION_MESSAGE); break;
			case Controlador.ESCRITURA_INCORRECTA: JOptionPane.showMessageDialog
													(this, "Error en la escritura de los datos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.DATOS_INCORRECTOS: JOptionPane.showMessageDialog
													(this, "Datos incorrectos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.VISITA_EXISTENTE:JOptionPane.showMessageDialog
													(this, "Visita ya existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFCrear getInstance() {
		return instancia;
	}
	
	private static JFCrear instancia = null;

	private JTextField campoPreso;
	private JTextField campoVisitante;
	private JPAuxFecha campoFecha;

	
}