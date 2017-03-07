/**
 * 
 */
package presentacion.actividades;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import negocio.actividades.TransferActividades;
import presentacion.JPAuxFecha;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFCrear extends JFrame{
	
	public class JBCrear extends JButton{
		
		public class ALCrear implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = JFCrear.this.campoNombre.getText().trim();
					String descripcion = JFCrear.this.campoDescrip.getText();
					int aforo = Integer.parseInt(JFCrear.this.campoAforo.getText());
					Calendar fecha = JFCrear.this.campoFecha.getFecha();
					if(jtaSinSaltos(descripcion)) {
						if (!nombre.equals("")) {
							TransferActividades actividad = new TransferActividades(
									true, -1, nombre, descripcion, aforo, 
									fecha, 0, new Vector<Integer>());
							Controlador.getInstance().actualiza(Controlador.CREAR_ACTIVIDAD, actividad);
						}
						else JOptionPane.showMessageDialog
									(JFCrear.this, "Introduzca un nombre" , "Error",
									JOptionPane.ERROR_MESSAGE);	
					}
					else {
						JOptionPane.showMessageDialog
						(JFCrear.this, "El campo descripción no admite saltos de linea" , "Error",
						JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFCrear.this, "Los campos asociados a aforo y fecha deben contener enteros" , "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException ex){
					JOptionPane.showMessageDialog
							(JFCrear.this, "La fecha indicada no es correcta" , "Error",
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
		
		public JBCrear() {
			super("Crear");
			this.addActionListener(new ALCrear());
		}
	}
	public JFCrear() {
		super("Crear una Actividad");
		setSize(400,325);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		initGUI();
	}

	private void initGUI() {
		
		//Panel de todos los campos
		JPanel jpTodosCampos = new JPanel();
		jpTodosCampos.setLayout(new BorderLayout());
		this.add(jpTodosCampos, BorderLayout.NORTH);
		
		JPanel jpSubTodosCampos = new JPanel();
		jpSubTodosCampos.setLayout(new BorderLayout());
		jpTodosCampos.add(jpSubTodosCampos, BorderLayout.NORTH);
		
		
		//Panel nombre
		JPanel jpCampoNombre = new JPanel();
		jpCampoNombre.setLayout(new BorderLayout());
		jpCampoNombre.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca el nombre de la Actividad a crear.");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		jpCampoNombre.add(texto, BorderLayout.NORTH);
		JLabel nom = new JLabel("Nombre:", JLabel.RIGHT);
		nom.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampoNombre.add(nom, BorderLayout.WEST);
		this.campoNombre = new JTextField();
		campoNombre.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampoNombre.add(this.campoNombre, BorderLayout.CENTER);
		jpSubTodosCampos.add(jpCampoNombre, BorderLayout.NORTH);
		
		//Panel descripcion
		JPanel jpCampoDescrip = new JPanel();
		jpCampoDescrip.setLayout(new BorderLayout());
		jpCampoDescrip.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textBDescrip = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel textodescrip = new JLabel("Introduzca la descripcion de la Actividad.");
		textodescrip.setFont(new Font("Courier New", Font.ITALIC, 12));
		textodescrip.setBorder(textBDescrip);
		
		textodescrip.setBackground(null);
		jpCampoDescrip.add(textodescrip, BorderLayout.NORTH);
		JLabel nomDescrip = new JLabel("Descripcion:", JLabel.RIGHT);
		nomDescrip.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampoDescrip.add(nomDescrip, BorderLayout.WEST);
		this.campoDescrip = new JTextArea();
		campoDescrip.setBorder(BorderFactory.createLoweredBevelBorder());
		JScrollPane scrollDescrip = new JScrollPane(this.campoDescrip);
		scrollDescrip.setPreferredSize(new Dimension(200,35));
		scrollDescrip.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollDescrip.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jpCampoDescrip.add(scrollDescrip, BorderLayout.CENTER);
		jpSubTodosCampos.add(jpCampoDescrip, BorderLayout.CENTER);
		
		//Panel Aforo
		JPanel jpCampoAforo = new JPanel();
		jpCampoAforo.setLayout(new BorderLayout());
		jpCampoAforo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textBAforo = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel textoAforo = new JLabel("Introduzca el aforo de la Actividad.");
		textoAforo.setFont(new Font("Courier New", Font.ITALIC, 12));
		textoAforo.setBorder(textBAforo);
		
		textoAforo.setBackground(null);
		jpCampoAforo.add(textoAforo, BorderLayout.NORTH);
		JLabel nomAforo = new JLabel("Aforo:", JLabel.RIGHT);
		nomAforo.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampoAforo.add(nomAforo, BorderLayout.WEST);
		this.campoAforo = new JTextField();
		campoAforo.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampoAforo.add(this.campoAforo, BorderLayout.CENTER);
		jpSubTodosCampos.add(jpCampoAforo, BorderLayout.SOUTH);
		
		//Panel Fecha
		JPanel jpCampoFecha = new JPanel();
		jpCampoFecha.setLayout(new BorderLayout());
		jpCampoFecha.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textBFecha = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel textoFecha = new JLabel("Introduzca la fecha de la Actividad.");
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
		Dimension dimNom = nomDescrip.getPreferredSize();
		nom.setPreferredSize(dimNom);
		nomFecha.setPreferredSize(dimNom);
		nomAforo.setPreferredSize(dimNom);
		
		
	}

	public void informar(int resultado, int id) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
													(this, "Actividad creada con éxito.\nID: " + id, "Actividad creada",
													JOptionPane.INFORMATION_MESSAGE); break;
			case Controlador.ESCRITURA_INCORRECTA: JOptionPane.showMessageDialog
													(this, "Error en la escritura de los datos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.DATOS_INCORRECTOS: JOptionPane.showMessageDialog
													(this, "Datos incorrectos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.ACTIVIDAD_EXISTENTE: JOptionPane.showMessageDialog
													(this, "Actividad ya existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFCrear getInstance() {
		return instancia;
	}
	
	private static JFCrear instancia = null;

	
	private JTextField campoNombre;
	private JTextArea campoDescrip;
	private JTextField campoAforo;
	private JPAuxFecha campoFecha;

	
}