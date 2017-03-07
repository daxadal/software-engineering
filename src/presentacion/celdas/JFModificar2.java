package presentacion.celdas;

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

import negocio.celdas.TransferCeldas;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFModificar2 extends JFrame{
	public class JBModificar extends JButton{	
		public class ALModificar implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{
				int numCelda = Integer.parseInt(JFModificar2.this.jtfNuCelda.getText().trim());
				TransferCeldas celda = new TransferCeldas(true, JFModificar2.this.celda.getIdCelda(), numCelda,
							JFModificar2.this.celda.getIdSector(), JFModificar2.this.celda.getCapacidad(), JFModificar2.this.celda.getPresos());
					Controlador.getInstance().actualiza(Controlador.MODIFICAR_CELDA, celda);
				}
				catch (NumberFormatException ex)
				{
				JOptionPane.showMessageDialog
							(JFModificar2.this, "Introduzca un nombre" , "Error",
													JOptionPane.ERROR_MESSAGE);	
				}
			}
		}
		
		public JBModificar() {
			super("Modificar");
			this.addActionListener(new ALModificar());
		}
	}

	public JFModificar2(TransferCeldas celda) {
		super("Celda " + celda.getIdCelda() + ": Celda número " + celda.getNumCelda());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		pack();
		this.celda =celda;
		
		this.jtfId.setText(""+ celda.getIdCelda());	
		this.jtfNuCelda.setText("" + celda.getNumCelda());
		this.jtfCapacidad.setText(""+ celda.getCapacidad());
		this.jtfIdSector.setText("" + celda.getIdSector());
		
		DefaultListModel<Integer> model = new DefaultListModel<Integer>();
		for (Integer pres: celda.getPresos())
			model.addElement(pres);
		this.listaPresos.setModel(model);
		instancia = this;
	}

	private void initGUI() {
				
		jtfId = new JTextField(10);
		jtfNuCelda = new JTextField(10);
		jtfCapacidad = new JTextField(10);
		jtfIdSector = new JTextField(10);
		listaPresos = new JList<Integer>();
		
		JPMostrarDatos jpDatos = new JPMostrarDatos(JPMostrarDatos.MODO_MODIFICAR, jtfId, jtfNuCelda, jtfCapacidad, jtfIdSector, listaPresos);
		this.add(jpDatos);
		
		this.setLayout(new BorderLayout());
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
													(this, "Celda modificada con éxito", "Celda modificada",
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
	private JTextField jtfNuCelda;
	private JTextField jtfCapacidad;
	private JTextField jtfIdSector;
	
	private JList<Integer> listaPresos;
	
	private TransferCeldas celda;
}
