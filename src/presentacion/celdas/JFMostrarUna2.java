package presentacion.celdas;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import negocio.celdas.TransferCeldas;

@SuppressWarnings("serial")
public class JFMostrarUna2 extends JFrame{

	public JFMostrarUna2(TransferCeldas celda) {
		super("Celda " + celda.getIdCelda() + ": Celda número " + celda.getNumCelda());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		pack();
		
		this.jtfId.setText(""+ celda.getIdCelda());	
		this.jtfNuCelda.setText("" + celda.getNumCelda());
		this.jtfCapacidad.setText(""+ celda.getCapacidad());
		this.jtfIdSector.setText("" + celda.getIdSector());
		
		DefaultListModel<Integer> model = new DefaultListModel<Integer>();
		for (Integer pres: celda.getPresos())
			model.addElement(pres);
		this.listaPresos.setModel(model);
	}

	private void initGUI() {
		jtfId = new JTextField(10);
		jtfNuCelda = new JTextField(10);
		jtfCapacidad = new JTextField(10);
		jtfIdSector = new JTextField(10);
		listaPresos = new JList<Integer>();
		
		JPMostrarDatos jpDatos = new JPMostrarDatos(JPMostrarDatos.MODO_NO, jtfId, jtfNuCelda, jtfCapacidad, jtfIdSector, listaPresos);
		this.add(jpDatos);
	}
	
	private JTextField jtfId;
	private JTextField jtfNuCelda;
	private JTextField jtfCapacidad;
	private JTextField jtfIdSector;
	
	private JList<Integer> listaPresos;
}
