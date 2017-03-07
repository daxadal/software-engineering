package presentacion.presos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.presos.TransferPresos;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFModificar2 extends JFrame{
	
	public class JBModificar extends JButton{	
		public class ALModificar implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = JFModificar2.this.jtfNombre.getText().trim();
				String apellido = JFModificar2.this.jtfApellido.getText().trim();
				String dni = JFModificar2.this.jtfDni.getText().trim();
				String condena = JFModificar2.this.jtaCondena.getText().trim();
				if(jtaSinSaltos(condena)) {
					if (!nombre.equals("") && !apellido.equals("") && !dni.equals("")) {
						TransferPresos preso = new TransferPresos(true, JFModificar2.this.preso.getId(), 
								nombre, apellido, dni, JFModificar2.this.preso.getCelda(), condena, JFModificar2.this.preso.getVisitas(), JFModificar2.this.preso.getActividades());
						Controlador.getInstance().actualiza(Controlador.MODIFICAR_PRESO, preso);
					}
					else JOptionPane.showMessageDialog
								(JFModificar2.this, "Introduzca un nombre, un apellido y un DNI válido" , "Error",
								JOptionPane.ERROR_MESSAGE);	
				}
				else {
					JOptionPane.showMessageDialog
					(JFModificar2.this, "El campo condena no admite saltos de linea" , "Error",
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
		
		public JBModificar() {
			super("Modificar");
			this.addActionListener(new ALModificar());
		}
	}

	public JFModificar2(TransferPresos preso) {
		super("Preso " + preso.getId() + ": " + preso.getApellido() + ", " + preso.getNombre());
		setSize(320,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		
		this.preso = preso;
		instancia = this;
		
		this.jtfId.setText(""+ preso.getId());
		this.jtfCelda.setText(""+ preso.getCelda());
		this.jtfNombre.setText(preso.getNombre());
		this.jtfApellido.setText(preso.getApellido());
		this.jtfDni.setText(preso.getDni());
		this.jtaCondena.setText(preso.getCondena());
		
		
		DefaultListModel<Integer> modelV = new DefaultListModel<Integer>();
		for (Integer vis: preso.getVisitas())
			modelV.addElement(vis);
		this.jlVisitas.setModel(modelV);
		
		DefaultListModel<Integer> modelA = new DefaultListModel<Integer>();
		for (Integer act: preso.getActividades())
			modelA.addElement(act);
		this.jlActividades.setModel(modelA);
	}

	private void initGUI() {
				
		jtfId = new JTextField(10);
		jtfCelda = new JTextField(10);
		jtfNombre = new JTextField();
		jtfApellido = new JTextField();
		jtfDni = new JTextField();
		jtaCondena = new JTextArea();
		jlVisitas = new JList<Integer>();
		jlActividades = new JList<Integer>();
		
		JPMostrarDatos jpDatos = new JPMostrarDatos(true, false, false, jtfId, jtfCelda, jtfNombre, jtfApellido, jtfDni, jtaCondena, jlVisitas, jlActividades);
		
		this.add(jpDatos);
		
		//Boton sur
		JPanel jpBoton = new JPanel();
		jpBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpBoton.add(new JBModificar());
		this.add(jpBoton, BorderLayout.SOUTH);
		
		
	}
	
	public void informar(int resultado) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
													(this, "Preso modificado con éxito", "Preso modificado",
													JOptionPane.INFORMATION_MESSAGE); break;
			case Controlador.ESCRITURA_INCORRECTA: JOptionPane.showMessageDialog
													(this, "Error en la escritura de los datos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.DATOS_INCORRECTOS: JOptionPane.showMessageDialog
													(this, "Datos incorrectos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFModificar2 getInstance() {
		return instancia;
	}
	
	private static JFModificar2 instancia = null;
	
	private JTextField jtfId;
	private JTextField jtfNombre;
	private JTextField jtfApellido;
	private JTextField jtfDni;
	private JTextField jtfCelda;
	private JTextArea jtaCondena;
	private JList<Integer> jlVisitas;
	private JList<Integer> jlActividades;
	
	private TransferPresos preso;
}
