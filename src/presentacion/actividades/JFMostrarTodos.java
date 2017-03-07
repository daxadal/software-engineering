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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import negocio.actividades.TransferActividades;
import presentacion.JPAuxFecha;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFMostrarTodos extends JFrame{
	
	public class JBMostrarTodos extends JButton{	
		
		public class ALMostrarTodos implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JFMostrarUno2 ventAnt: JFMostrarTodos.this.abierto){
					ventAnt.setVisible(false);
					ventAnt.dispose();
				}
				Controlador.getInstance().actualiza(Controlador.MOSTRAR_TODAS_ACTIVIDADES, null);
			}
		}
		
		public JBMostrarTodos() {
			super("Cargar actividades");
			this.addActionListener(new ALMostrarTodos());
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

	public JFMostrarTodos() {
		super("Muestra todas las Actividades");
		setSize(480,420);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		this.lista = null;
		this.abierto = new ArrayList<JFMostrarUno2>();
		initGUI();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent ev) {
				for (JFMostrarUno2 vent: JFMostrarTodos.this.abierto){
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
		JPanel jpBotones = new JPanel();
		this.add(jpBotones, BorderLayout.SOUTH);
		JButton jbMostrar = new JButton("Mostrar seleccionados");
		jbMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] filas = tablaActividades.getSelectedRows();
				for (JFMostrarUno2 ventAnt: JFMostrarTodos.this.abierto){
					ventAnt.setVisible(false);
					ventAnt.dispose();
				}
				JFMostrarTodos.this.abierto.clear();
				for(int fila: filas) {
					JFMostrarUno2 sect = new JFMostrarUno2(lista.get(fila));
					JFMostrarTodos.this.abrirVentana(sect);
				}
			}
		});
		jpBotones.add(new JBMostrarTodos());
		jpBotones.add(jbMostrar);
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
		}
	}
	
	public static JFMostrarTodos getInstance() {
		return instancia;
	}
	
	private void abrirVentana(JFMostrarUno2 vent) {
		this.abierto.add(vent);
		vent.setLocationRelativeTo(this);
		vent.setLocation(vent.getLocation().x, vent.getLocation().y - 100 + this.abierto.size()*25);
		vent.setVisible(true);
	}
	
	private static JFMostrarTodos instancia = null;

	private List<TransferActividades> lista;
	private JTable tablaActividades;
	private Modelo modelo;
	private List<JFMostrarUno2> abierto;
}