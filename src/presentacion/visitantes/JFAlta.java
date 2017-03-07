package presentacion.visitantes;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import negocio.visitantes.TransferVisitanteFamiliar;
import negocio.visitantes.TransferVisitanteProfesional;
import negocio.visitantes.TransferVisitantes;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFAlta extends JFrame{

	public class JBAlta extends JButton{
		
		public class ALAlta implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = JFAlta.this.jtfNombre.getText().trim();
				String apellido = JFAlta.this.jtfApellido.getText().trim();
				String dni = JFAlta.this.jtfDni.getText().trim();
				String espec = JFAlta.this.jtfEspec.getText().trim();
				if (!nombre.equals("") && !apellido.equals("") && !dni.equals("")) {
					int opcion = jcbTipo.getSelectedIndex();
					switch (opcion) {
					case 0: JOptionPane.showMessageDialog
							(JBAlta.this, "Seleccione el tipo de visitante" , "Error",
							JOptionPane.ERROR_MESSAGE);
							break;
					case 1: TransferVisitantes visitanteF = new TransferVisitanteFamiliar
											(true, -1, nombre, apellido, dni, new Vector<Integer>() , espec);
							Controlador.getInstance().actualiza(Controlador.ALTA_VISITANTE, visitanteF);
							break;
					case 2: TransferVisitantes visitanteP = new TransferVisitanteProfesional
											(true, -1, nombre, apellido, dni, new Vector<Integer>() , espec);
							Controlador.getInstance().actualiza(Controlador.ALTA_VISITANTE, visitanteP);
							break;
					}
				}
				else JOptionPane.showMessageDialog
							(JBAlta.this, "Introduzca un nombre" , "Error",
							JOptionPane.ERROR_MESSAGE);				
			}
		}
		
		public JBAlta() {
			super("Dar de Alta");
			this.addActionListener(new ALAlta());
		}
	}

	public JFAlta() {
		super("Dar de Alta un Visitante");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		initGUI();
	}
	private void initGUI() {
		JPanel jpCampo = new JPanel();
		jpCampo.setLayout(new BorderLayout());
		jpCampo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca los datos del visitante a crear.");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		JTextField jtfId = new JTextField(8);
		jtfNombre = new JTextField();
		jtfApellido = new JTextField();
		jtfDni = new JTextField();
		
		nomEspec = new JLabel();
		jtfEspec = new JTextField(8);
		jtfEspec.setEditable(false);
		
		jcbTipo = new JComboBox<String>();
		jcbTipo.addItem("(Seleccione uno)");
		jcbTipo.addItem("Familiar");
		jcbTipo.addItem("Profesional");
		jcbTipo.setSelectedIndex(0);
		JPMostrarDatos datos = new JPMostrarDatos(true, jtfId, jtfNombre, jtfApellido, jtfDni, jcbTipo, nomEspec,
				jtfEspec, null);
		
		add(datos);
		JPanel jpBoton = new JPanel();
		jpBoton.add(new JBAlta());
		this.add(jpBoton, BorderLayout.SOUTH);
		
		
	}
	
	public void informar(int resultado, int id) {
		switch (resultado ) {
		case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
												(this, "Visitante creado con éxito.\nID: " + id, "Visitante creado",
												JOptionPane.INFORMATION_MESSAGE); 	
												break;
		case Controlador.ESCRITURA_INCORRECTA:JOptionPane.showMessageDialog
												(this, "Escritura incorrecta", "Error",
												JOptionPane.ERROR_MESSAGE);  break;
		case Controlador.DATOS_INCORRECTOS: JOptionPane.showMessageDialog
												(this, "Datos incorrectos", "Error",
												JOptionPane.ERROR_MESSAGE); break;
		case Controlador.VISITANTE_EXISTENTE:JOptionPane.showMessageDialog
												(this, "Visitante ya existente", "Error",
												JOptionPane.ERROR_MESSAGE); 
												break;
		}
	}
	
	public static JFAlta getInstance() {
		return instancia;
	}

	private static JFAlta instancia = null;
	

	private JTextField jtfNombre;
	private JTextField jtfApellido;
	private JTextField jtfDni;
	
	private JLabel nomEspec;
	private JTextField jtfEspec;
	private JComboBox<String> jcbTipo;
	
}
