package presentacion.visitantes;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import negocio.visitantes.TransferVisitantes;


@SuppressWarnings("serial")
public class JFMostrarUno2 extends JFrame{

	public JFMostrarUno2(TransferVisitantes visitante) {
		super("Visitante " + visitante.getId() + " (" + visitante.toString() + "): " + visitante.getApellido() + ", " + visitante.getNombre());
		setSize(420,350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI(visitante);
		
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

	private void initGUI(TransferVisitantes visitante) {
		jtfId = new JTextField(4);
		jtfNombre = new JTextField();
		jtfApellido = new JTextField();
		jtfDni = new JTextField();
		jtfEspec = new JTextField(8);
		jlHistorialVisitas = new JList<Integer>();
		String opcion = visitante.toString();
		
		JComboBox<String> jcbTipo = new JComboBox<String>();
		switch (opcion) {
		case "Familiar": 	jcbTipo.addItem("Familiar"); 
							break;
		case "Profesional": jcbTipo.addItem("Profesional"); 
							break;
		}
		jcbTipo.setSelectedIndex(0);
		jcbTipo.setEditable(false);
		
		JPMostrarDatos jpDatos = new JPMostrarDatos(false, jtfId, jtfNombre, 
												jtfApellido, jtfDni, jcbTipo, 
												new JLabel(visitante.getNombreCampo()),
												jtfEspec, jlHistorialVisitas);
		
		this.add(jpDatos);
	}
	
	private JTextField jtfId;
	private JTextField jtfNombre;
	private JTextField jtfApellido;
	private JTextField jtfDni;
	private JList<Integer> jlHistorialVisitas;
	private JTextField jtfEspec;
}
