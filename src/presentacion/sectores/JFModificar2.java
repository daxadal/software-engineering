package presentacion.sectores;

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
import javax.swing.JTextField;

import negocio.sectores.TransferSector;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFModificar2 extends JFrame{
	
	public class JBModificar extends JButton{	
		public class ALModificar implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = JFModificar2.this.jtfNombre.getText().trim();
				if (!nombre.equals("")) {
					JFModificar2.this.sector.setNombre(nombre);
					Controlador.getInstance().actualiza(Controlador.MODIFICAR_SECTOR, JFModificar2.this.sector);
				}
				else JOptionPane.showMessageDialog
							(JFModificar2.this, "Introduzca un nombre" , "Error",
							JOptionPane.ERROR_MESSAGE);		
			}
		}
		
		public JBModificar() {
			super("Modificar");
			this.addActionListener(new ALModificar());
		}
	}

	public JFModificar2(TransferSector sector) {
		super("Sector " + sector.getId() + ": " + sector.getNombre());
		setSize(530,210);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		
		this.sector = sector;
		instancia = this;
		
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
		
		JPMostrarDatos jpDatos = new JPMostrarDatos(true, jtfId, jtfNombre, jtfNoCeldas, listaCeldas);
		
		this.setLayout(new FlowLayout());
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
													(this, "Sector modificado con éxito", "Sector modificado",
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
	private JTextField jtfNoCeldas;
	
	private JList<Integer> listaCeldas;
	
	private TransferSector sector;
}
