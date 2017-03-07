package presentacion.presos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import negocio.presos.TransferPresos;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFAltaNuevo extends JFrame{
	public class JBAltaNuevo extends JButton{
		public class ALAltaNuevo implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = JFAltaNuevo.this.jtfNombre.getText().trim();
					String apellido = JFAltaNuevo.this.jtfApellido.getText().trim();
					String dni = JFAltaNuevo.this.jtfDni.getText().trim();
					String condena = JFAltaNuevo.this.jtaCondena.getText().trim();
					if(jtaSinSaltos(condena)) {
						int celda = Integer.parseInt(JFAltaNuevo.this.jtfCelda.getText().trim());
						if (!nombre.equals("") && !apellido.equals("") && !dni.equals("")) {
							TransferPresos preso = new TransferPresos(true, -1, nombre, apellido, dni, celda, condena, new Vector<Integer>(), new Vector<Integer>());
							Controlador.getInstance().actualiza(Controlador.ALTA_PRESO, preso);
						}
						else JOptionPane.showMessageDialog
									(JBAltaNuevo.this, "Introduzca un nombre y Apellido" , "Error",
									JOptionPane.ERROR_MESSAGE);	
					}
					else {
						JOptionPane.showMessageDialog
						(JFAltaNuevo.this, "El campo condena no admite saltos de linea" , "Error",
						JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFAltaNuevo.this, "Introduzca un entero en el campo celda" , "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			private boolean jtaSinSaltos(String texto) {
				boolean jtaOk = true;
				for (int i=0; i<texto.length() && jtaOk ; i++)
					if (texto.charAt(i) == '\n')
						jtaOk = false;
				return jtaOk;
			}
		}
		
		public JBAltaNuevo() {
			super("Dar de Alta");
			this.addActionListener(new ALAltaNuevo());
		}
	}

	public JFAltaNuevo() {
		super("Dar de Alta un Preso");
		setSize(320,530);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		initGUI();
	}
	private void initGUI() {
		JPanel jpCampo = new JPanel();
		jpCampo.setLayout(new BorderLayout());
		jpCampo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca los datos del preso a crear.");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		JTextField jtfId = new JTextField();
		jtfCelda = new JTextField();
		jtfNombre = new JTextField();
		jtfApellido = new JTextField();
		jtfDni = new JTextField();
		jtaCondena = new JTextArea();
		JPMostrarDatos datos = new JPMostrarDatos(true, true, false, jtfId, jtfCelda, jtfNombre, jtfApellido, jtfDni, jtaCondena, null, null);
		
		add(datos);
		JPanel jpBoton = new JPanel();
		jpBoton.add(new JBAltaNuevo());
		this.add(jpBoton, BorderLayout.SOUTH);
		
		
	}
	
	public void informar(int resultado, int id) {
		switch (resultado ) {
		case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
												(this, "Preso creado con éxito.\nID: " + id, "Preso creado",
												JOptionPane.INFORMATION_MESSAGE); 	
												break;
		case Controlador.ESCRITURA_INCORRECTA:JOptionPane.showMessageDialog
												(this, "Escritura incorrecta", "Error",
												JOptionPane.ERROR_MESSAGE);  break;
		case Controlador.DATOS_INCORRECTOS: JOptionPane.showMessageDialog
												(this, "Datos incorrectos", "Error",
												JOptionPane.ERROR_MESSAGE); break;
		case Controlador.PRESO_EXISTENTE:JOptionPane.showMessageDialog
												(this, "Preso existente", "Error",
												JOptionPane.ERROR_MESSAGE); 
												break;
		case Controlador.PRESO_REINCIDENTE: 	JOptionPane.showMessageDialog
												(this, "Preso reincidente. Debe darse de alta con la opción \"Alta Reincidente\"", "Error",
												JOptionPane.ERROR_MESSAGE); 
												break;
		case Controlador.CELDA_LLENA: 	JOptionPane.showMessageDialog
				(this, "La celda está llena.", "Error",
				JOptionPane.ERROR_MESSAGE); 
				break;								
		}
	}
	
	public static JFAltaNuevo getInstance() {
		return instancia;
	}

	private static JFAltaNuevo instancia = null;
	

	private JTextField jtfNombre;
	private JTextField jtfApellido;
	private JTextField jtfDni;
	private JTextField jtfCelda;
	private JTextArea jtaCondena;
	
}
