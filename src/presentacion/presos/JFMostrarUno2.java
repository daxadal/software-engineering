package presentacion.presos;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.presos.TransferPresos;


@SuppressWarnings("serial")
public class JFMostrarUno2 extends JFrame{

	public JFMostrarUno2(TransferPresos preso) {
		super("Preso " + preso.getId() + ": " + preso.getApellido() + ", " + preso.getNombre());
		setSize(320,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		
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
		
		JPMostrarDatos jpDatos = new JPMostrarDatos(false, false, false, jtfId, jtfCelda, jtfNombre, jtfApellido, jtfDni, jtaCondena, jlVisitas, jlActividades);
		this.add(jpDatos);
	}
	
	private JTextField jtfId;
	private JTextField jtfCelda;
	private JTextField jtfNombre;
	private JTextField jtfApellido;
	private JTextField jtfDni;
	private JTextArea jtaCondena;
	private JList<Integer> jlVisitas;
	private JList<Integer> jlActividades;
}
