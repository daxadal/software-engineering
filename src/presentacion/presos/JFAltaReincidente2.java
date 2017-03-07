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
public class JFAltaReincidente2 extends JFrame{
	
	public class JBAltaReincidente2 extends JButton{	
		public class ALAltaReincidente implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String condena = JFAltaReincidente2.this.jtaCondena.getText().trim();
					int celda = Integer.parseInt(JFAltaReincidente2.this.jtfCelda.getText().trim());
					if (!condena.equals("")) {
						if(jtaSinSaltos(condena)) {
						TransferPresos preso = new TransferPresos(true, JFAltaReincidente2.this.preso.getId(),
								JFAltaReincidente2.this.preso.getNombre(), JFAltaReincidente2.this.preso.getApellido(),
								JFAltaReincidente2.this.preso.getDni(), celda, condena,
								JFAltaReincidente2.this.preso.getVisitas(), JFAltaReincidente2.this.preso.getActividades());
						Controlador.getInstance().actualiza(Controlador.ALTA_REINCIDENTE, preso);
						}
						else {
							JOptionPane.showMessageDialog
							(JFAltaReincidente2.this, "El campo condena no admite saltos de linea" , "Error",
							JOptionPane.ERROR_MESSAGE);
						}
					}
					
						
					else JOptionPane.showMessageDialog
								(JFAltaReincidente2.this, "Introduzca una condena" , "Error",
								JOptionPane.ERROR_MESSAGE);	
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFAltaReincidente2.this, "Introduzca un entero en el campo celda" , "Error",
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
		
		public JBAltaReincidente2() {
			super("Volver a dar de alta el Preso");
			this.addActionListener(new ALAltaReincidente());
		}
	}

	public JFAltaReincidente2(TransferPresos preso) {
		super("Preso " + preso.getId() + ": " + preso.getNombre());
		setSize(320,580);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		
		this.preso = preso;
		instancia = this;
		
		this.jtfId.setText("" + preso.getId());	
		this.jtfNombre.setText(preso.getNombre());
		this.jtfApellido.setText(preso.getApellido());
		this.jtfDni.setText(preso.getDni());
		this.jtfCelda.setText("" + preso.getCelda());
		this.jtaCondena.setText(preso.getCondena());
				
		
		DefaultListModel<Integer> modelV = new DefaultListModel<Integer>();
		for (Integer vis: preso.getVisitas())
			modelV.addElement(vis);
		this.listaVisitas.setModel(modelV);
		
		DefaultListModel<Integer> modelA = new DefaultListModel<Integer>();
		for (Integer act: preso.getActividades())
			modelA.addElement(act);
		this.listaActividades.setModel(modelA);
	}

	private void initGUI() {
				
		jtfId = new JTextField();
		jtfCelda = new JTextField(10);
		jtfNombre = new JTextField(10);
		listaVisitas = new JList<Integer>();
		listaActividades = new JList<Integer>();
		jtfApellido = new JTextField(10);
		jtfDni = new JTextField();
		jtaCondena = new JTextArea();

		JPMostrarDatos jpDatos = new JPMostrarDatos(false, true, true, jtfId, jtfCelda, jtfNombre,
									jtfApellido, jtfDni, jtaCondena, listaVisitas, listaActividades);
				
		this.add(jpDatos);
        
			//Boton sur
		JPanel jpBoton = new JPanel();
		jpBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpBoton.add(new JBAltaReincidente2());
		this.add(jpBoton, BorderLayout.SOUTH);
		
		
	}
	
	public void informar(int resultado) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
													(this, "Preso reactivado con éxito", "Preso reactivado",
													JOptionPane.INFORMATION_MESSAGE); break;
			case Controlador.ESCRITURA_INCORRECTA: JOptionPane.showMessageDialog
													(this, "Error en la escritura de los datos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.DATOS_INCORRECTOS: JOptionPane.showMessageDialog
													(this, "Datos incorrectos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFAltaReincidente2 getInstance() {
		return instancia;
	}
	
	private static JFAltaReincidente2 instancia = null;
	
	private JTextField jtfId;
	private JTextField jtfNombre;
	private JTextField jtfApellido;
	private JTextField jtfDni;
	private JTextField jtfCelda;
	private JTextArea jtaCondena;
	
	private JList<Integer> listaActividades;
	private JList<Integer> listaVisitas;
	
	
	private TransferPresos preso;
}
