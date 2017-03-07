package presentacion.actividades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import negocio.actividades.TransferActividades;
import presentacion.JPAuxFecha;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFMostrarDePreso extends JFrame {
	public class JBMostrarDePreso extends JButton{	
		public class ALMostrarDePreso implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JFMostrarUno2 ventAnt: JFMostrarDePreso.this.abierto){
					ventAnt.setVisible(false);
					ventAnt.dispose();
				}
				try {
					int id = Integer.parseInt(JFMostrarDePreso.this.campo.getText());
					Controlador.getInstance().actualiza(Controlador.MOSTRAR_ACTIVIDADES_DE_PRESO, id);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog
							(JFMostrarDePreso.this, "Introduzca un entero" , "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public JBMostrarDePreso() {
			super("Cargar actividades");
			this.addActionListener(new ALMostrarDePreso());
		}
	}
	
	private class Modelo extends AbstractTableModel {

		public Modelo() {
			this.listaActividades = new ArrayList<TransferActividades>();
		}
		
		public String getColumnName(int column) {
			switch (column) {
				case 0: return "Id"; 
				case 1: return "Nombre"; 
				case 2: return "Fecha"; 
				default: return null;
			}
		}
		@Override
		public int getRowCount() {
			return this.listaActividades.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			TransferActividades actividad = this.listaActividades.get(rowIndex);
			Object ret = null;
			switch (columnIndex) {
				case 0: ret = actividad.getId(); break;
				case 1: ret = actividad.getNombre(); break;
				case 2: {
						Calendar fecha = actividad.getFecha();
						ret = JPAuxFecha.toString(fecha);
						} break;
			}
			return ret;
		}
		
		public void setData(List<TransferActividades> listaActividades) {
			this.listaActividades = listaActividades;
		}
		
		private List<TransferActividades> listaActividades;
		
	}

	public JFMostrarDePreso() {
		super("Muestra una Actividad");
		setSize(480,520);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		this.lista = null;
		this.abierto = new ArrayList<JFMostrarUno2>();
		initGUI();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent ev) {
				for (JFMostrarUno2 vent: JFMostrarDePreso.this.abierto){
					vent.setVisible(false);
					vent.dispose();
				}
			}
		});
	}

	private void initGUI() {
		JPanel jpTabla = new JPanel();
		jpTabla.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Actividades",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		this.add(jpTabla);
		
		//Modelo
		this.modelo = new Modelo();
		
		
		//TablaSectores
		this.tablaActividades = new JTable();
		this.tablaActividades.setModel(modelo);
		this.tablaActividades.setBackground(null);
		JScrollPane scTabla = new JScrollPane(this.tablaActividades);
		scTabla.setPreferredSize(new Dimension(scTabla.getPreferredSize().height, 300));
		jpTabla.add(scTabla, BorderLayout.CENTER);
		
		//Botones
		JPanel jpSur = new JPanel();
		jpSur.setLayout(new BorderLayout());
		this.add(jpSur, BorderLayout.SOUTH);
		JPanel jpVent = new JPanel();
		jpVent.setLayout(new BorderLayout());
		jpSur.add(jpVent, BorderLayout.CENTER);
		
		JPanel jpBotones = new JPanel();
		jpSur.add(jpBotones, BorderLayout.NORTH);
		JButton jbMostrar = new JButton("Mostrar seleccionados");
		jbMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] filas = tablaActividades.getSelectedRows();
				for (JFMostrarUno2 ventAnt: JFMostrarDePreso.this.abierto){
					ventAnt.setVisible(false);
					ventAnt.dispose();
				}
				JFMostrarDePreso.this.abierto.clear();
				for(int fila: filas) {
					JFMostrarUno2 sect = new JFMostrarUno2(lista.get(fila));
					JFMostrarDePreso.this.abrirVentana(sect);
				}
			}
		});
		
		jpBotones.add(jbMostrar);
		
		//Campo
		JPanel jpCampo = new JPanel();
		jpCampo.setLayout(new BorderLayout());
		jpCampo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		Border textB = BorderFactory.createEmptyBorder(10,10,10,10);
		JLabel texto = new JLabel("Introduzca el ID del preso.");
		texto.setFont(new Font("Courier New", Font.ITALIC, 12));
		texto.setBorder(textB);
		
		texto.setBackground(null);
		jpCampo.add(texto, BorderLayout.NORTH);
		JLabel nom = new JLabel("ID:");
		nom.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		jpCampo.add(nom, BorderLayout.WEST);
		this.campo = new JTextField();
		campo.setBorder(BorderFactory.createLoweredBevelBorder());
		jpCampo.add(this.campo, BorderLayout.CENTER);
		jpVent.add(jpCampo, BorderLayout.NORTH);
		
		JPanel jpMostrar = 	new JPanel();
		jpVent.add(jpMostrar, BorderLayout.CENTER);
		jpMostrar.add(new JBMostrarDePreso());
	}

	public void informar(int resultado, List<TransferActividades> listaActividades) {
		switch (resultado) {
			case Controlador.OPERACION_CORRECTA: this.lista = listaActividades;
												this.modelo.setData(listaActividades); 
												this.modelo.fireTableDataChanged();
												break;
			
			case Controlador.ACTIVIDAD_NO_EXISTENTE: JOptionPane.showMessageDialog
													(this, "No se han encontrado actividades", "Error",
													JOptionPane.ERROR_MESSAGE); break;
			case Controlador.PRESO_NO_EXISTENTE:JOptionPane.showMessageDialog
													(this, "Preso no existente", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFMostrarDePreso getInstance() {
		return instancia;
	}
	
	private void abrirVentana(JFMostrarUno2 vent) {
		this.abierto.add(vent);
		vent.setLocationRelativeTo(this);
		vent.setLocation(vent.getLocation().x, vent.getLocation().y - 100 + this.abierto.size()*25);
		vent.setVisible(true);
	}
	
	private static JFMostrarDePreso instancia = null;
	private JTextField campo;

	private List<TransferActividades> lista;
	private JTable tablaActividades;
	private Modelo modelo;
	private List<JFMostrarUno2> abierto;
}
