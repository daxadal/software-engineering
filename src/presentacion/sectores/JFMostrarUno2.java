package presentacion.sectores;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import negocio.sectores.TransferSector;

@SuppressWarnings("serial")
public class JFMostrarUno2 extends JFrame{

	public JFMostrarUno2(TransferSector sector) {
		super("Sector " + sector.getId() + ": " + sector.getNombre());
		setSize(500,150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		
		this.jtfId.setText(""+ sector.getId());	
		this.jtfNombre.setText(sector.getNombre());
		this.jtfNoCeldas.setText(""+ sector.getNumCeldas());
		
		DefaultListModel<Integer> model = new DefaultListModel<Integer>();
		for (Integer cell: sector.getCeldas())
			model.addElement(cell);
		this.listaCeldas.setModel(model);
	}

	private void initGUI() {
		jtfId = new JTextField(10);
		jtfNombre = new JTextField(10);
		jtfNoCeldas = new JTextField(10);
		listaCeldas = new JList<Integer>();
		
		JPMostrarDatos jpDatos = new JPMostrarDatos(false, jtfId, jtfNombre, jtfNoCeldas, listaCeldas);
		this.add(jpDatos);
	}
	
	private JTextField jtfId;
	private JTextField jtfNombre;
	private JTextField jtfNoCeldas;
	
	private JList<Integer> listaCeldas;
}
