package presentacion.celdas;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import negocio.celdas.TransferCeldas;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFCrear extends JFrame{
	
	public class JBCrear extends JButton{
		
		public class ALCrear implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String idSector = JFCrear.this.jtfIdSector.getText().trim();
				String numCelda = JFCrear.this.jtfNuCelda.getText().trim();
				String capacidad = JFCrear.this.jtfCapacidad.getText().trim();
				if (!numCelda.equals("") && !idSector.equals("") && !capacidad.equals("") ) 
				{
					try 
					{
						int numCeldaInt = Integer.parseInt(numCelda);
						int idSectorInt = Integer.parseInt(idSector);
						int capacidadInt = Integer.parseInt(capacidad);
						TransferCeldas celda = new TransferCeldas(true, -1, numCeldaInt, idSectorInt, capacidadInt, new Vector<Integer>());
						Controlador.getInstance().actualiza(Controlador.CREAR_CELDA, celda);
					}
					catch (NumberFormatException ex)
					{
						JOptionPane.showMessageDialog
						(JFCrear.this, "Todos los campos deben ser números" , "Error",
						JOptionPane.ERROR_MESSAGE);
					}
				}
				else JOptionPane.showMessageDialog
							(JFCrear.this, "Los campos no pueden estar vacíos" , "Error",
							JOptionPane.ERROR_MESSAGE);				
			}
		}
		
		public JBCrear() {
			super("Crear");
			this.addActionListener(new ALCrear());
		}
	}

	public JFCrear() {
		super("Crear una Celda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		initGUI();
		pack();
	}

	private void initGUI() {
		JPanel jpCampo = new JPanel();
		jpCampo.setLayout(new BorderLayout());
		jpCampo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca los datos de la Celda a crear.");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		JTextField jtfId = new JTextField();
		jtfNuCelda = new JTextField();
		jtfCapacidad = new JTextField();
		jtfIdSector = new JTextField();
		JPMostrarDatos datos = new JPMostrarDatos(JPMostrarDatos.MODO_NUEVO, jtfId, jtfNuCelda, jtfCapacidad, jtfIdSector, null);
		
		add(datos);
		JPanel jpBoton = new JPanel();
		jpBoton.add(new JBCrear());
		this.add(jpBoton, BorderLayout.SOUTH);
		
		
	}

	public void informar(int resultado, int id) {
		switch (resultado ) {
			case Controlador.OPERACION_CORRECTA: JOptionPane.showMessageDialog
													(this, "Celda creada con éxito.\nID: " + id, "Celda creada",
													JOptionPane.INFORMATION_MESSAGE); break;
			case Controlador.ESCRITURA_INCORRECTA: JOptionPane.showMessageDialog
													(this, "Error en la escritura de los datos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.DATOS_INCORRECTOS: JOptionPane.showMessageDialog
													(this, "Datos incorrectos", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.CELDA_EXISTENTE: JOptionPane.showMessageDialog
													(this, "Celda ya existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFCrear getInstance() {
		return instancia;
	}
	
	private static JFCrear instancia = null;

	
	JTextField jtfNuCelda;
	JTextField jtfCapacidad;
	JTextField jtfIdSector;
	
}