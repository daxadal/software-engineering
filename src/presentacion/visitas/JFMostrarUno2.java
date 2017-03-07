package presentacion.visitas;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import negocio.visitas.TransferVisitas;
import presentacion.JPAuxFecha;

@SuppressWarnings("serial")
public class JFMostrarUno2 extends JFrame{

	public JFMostrarUno2(TransferVisitas transferVisitas) {
		super("Visita " + transferVisitas.getId());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		pack();
		
		this.jtfId.setText(""+ transferVisitas.getId());	
		this.jpFecha.setFecha(transferVisitas.getFecha());
		this.jtfPreso.setText(""+ transferVisitas.getPreso());
		this.jtfNoVisitantes.setText(""+ transferVisitas.getNumVisitantes());
		
		
		DefaultListModel<Integer> model = new DefaultListModel<Integer>();
		for (Integer vistte: transferVisitas.getVisitantes())
			model.addElement(vistte);
		this.listaVisitantes.setModel(model);
	}

	private void initGUI() {
		jtfId = new JTextField(10);
		jtfPreso = new JTextField(10);
		jtfNoVisitantes = new JTextField(10);
		listaVisitantes = new JList<Integer>();
		jpFecha = new JPAuxFecha(false);
		
		JPMostrarDatos jpDatos = new JPMostrarDatos(false, jtfId, jtfPreso, jtfNoVisitantes, jpFecha, listaVisitantes);
		this.add(jpDatos);
	}
	
	private JTextField jtfId;
	private JTextField jtfPreso;
	private JTextField jtfNoVisitantes;
	private JPAuxFecha jpFecha;
	
	private JList<Integer> listaVisitantes;
}
