package presentacion.actividades;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.actividades.TransferActividades;
import presentacion.JPAuxFecha;

@SuppressWarnings("serial")
public class JFMostrarUno2 extends JFrame{

	public JFMostrarUno2(TransferActividades transferActividades) {
		super("Actividad " + transferActividades.getId() + ": " + transferActividades.getNombre());
		setSize(630,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		
		this.jtfId.setText(""+ transferActividades.getId());	
		this.jtfNombre.setText(transferActividades.getNombre());
		this.jtaDescripcion.setText(transferActividades.getDescripcion());
		this.jtfAforo.setText(""+ transferActividades.getAforo());
		this.jtfNoPresos.setText(""+ transferActividades.getNumPresos());
		this.jpFecha.setFecha(transferActividades.getFecha());
		
		DefaultListModel<Integer> model = new DefaultListModel<Integer>();
		for (Integer cell: transferActividades.getPresos())
			model.addElement(cell);
		this.listaPresos.setModel(model);
	}

	private void initGUI() {
		jtfId = new JTextField(10);
		jtfNombre = new JTextField(10);
		jtaDescripcion = new JTextArea(2, 10);
		jtfAforo = new JTextField(10);
		jtfNoPresos = new JTextField(10);
		listaPresos = new JList<Integer>();
		jpFecha = new JPAuxFecha(false);
		
		JPMostrarDatos jpDatos = new JPMostrarDatos(false, jtfId, jtfNombre,jtaDescripcion, jtfAforo, jtfNoPresos, jpFecha, listaPresos);
		this.add(jpDatos);
	}
	
	private JTextField jtfId;
	private JTextField jtfNombre;
	private JTextArea jtaDescripcion;
	private JTextField jtfAforo;
	private JTextField jtfNoPresos;
	private JPAuxFecha jpFecha;
	
	private JList<Integer> listaPresos;
}
