package presentacion.visitantes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.visitantes.TransferVisitanteFamiliar;
import negocio.visitantes.TransferVisitanteProfesional;
import negocio.visitantes.TransferVisitantes;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFModificar2 extends JFrame{
	public class JBModificar extends JButton{	
		public class ALModificar implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				boolean activo = JFModificar2.this.visitante.getActivo();
				int id = JFModificar2.this.visitante.getId();
				String nombre = JFModificar2.this.jtfNombre.getText().trim();
				String apellido = JFModificar2.this.jtfApellido.getText().trim();
				String dni = JFModificar2.this.jtfDni.getText().trim();
				Vector<Integer> historialVisitas = JFModificar2.this.visitante.getHistorialVisitas();
				String espec = JFModificar2.this.jtfEspec.getText().trim();
				if (!nombre.equals("") && !apellido.equals("") && !dni.equals("")) {
					String opcion = JFModificar2.this.visitante.toString();
					switch (opcion) {
					case "Familiar": TransferVisitantes visitanteF = new TransferVisitanteFamiliar
											(activo, id, nombre, apellido, dni, historialVisitas , espec);
							Controlador.getInstance().actualiza(Controlador.MODIFICAR_VISITANTE, visitanteF);
							break;
					case "Profesional": TransferVisitantes visitanteP = new TransferVisitanteProfesional
											(activo, id, nombre, apellido, dni, historialVisitas , espec);
							Controlador.getInstance().actualiza(Controlador.MODIFICAR_VISITANTE, visitanteP);
							break;
					}
				}
				else JOptionPane.showMessageDialog
							(JBModificar.this, "Introduzca un nombre" , "Error",
							JOptionPane.ERROR_MESSAGE);		
			}
		}
		
		public JBModificar() {
			super("Modificar");
			this.addActionListener(new ALModificar());
		}
	}

	public JFModificar2(TransferVisitantes visitante) {
		super("Visitante " + visitante.getId() + "(" + visitante.toString() + "): " + visitante.getApellido() + ", " + visitante.getNombre());
		setSize(400,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.visitante = visitante;
		instancia = this;
		
		initGUI();
		
		
		this.jtfId.setText(""+ visitante.getId());	
		this.jtfNombre.setText(visitante.getNombre());
		this.jtfApellido.setText(visitante.getApellido());
		this.jtfDni.setText(visitante.getDni());
		this.jtfEspec.setText(visitante.getDatoCampo());
		
		
		DefaultListModel<Integer> modelV = new DefaultListModel<Integer>();
		for (Integer vis: visitante.getHistorialVisitas())
			modelV.addElement(vis);
		this.jlHistorialVisitas.setModel(modelV);
	}

	private void initGUI() {
				
		jtfId = new JTextField(10);
		jtfNombre = new JTextField();
		jtfApellido = new JTextField();
		jtfDni = new JTextField();
		jtfEspec = new JTextField(8);
		jlHistorialVisitas = new JList<Integer>();
		String opcion = JFModificar2.this.visitante.toString();
		
		JComboBox<String> jcbTipo = new JComboBox<String>();
		switch (opcion) {
		case "Familiar": 	jcbTipo.addItem("Familiar"); 
							break;
		case "Profesional": jcbTipo.addItem("Profesional"); 
							break;
		}
		jcbTipo.setSelectedIndex(0);
		jcbTipo.setEditable(false);
		
		JPMostrarDatos jpDatos = new JPMostrarDatos(true, jtfId, jtfNombre, 
												jtfApellido, jtfDni, jcbTipo, 
												new JLabel(visitante.getNombreCampo()),
												jtfEspec, jlHistorialVisitas);
		
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
													(this, "Visitante modificado con éxito", "Visitante modificado",
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
	private JList<Integer> jlHistorialVisitas;
	private JTextField jtfEspec;
	
	private TransferVisitantes visitante;
}
