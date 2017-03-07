package presentacion.visitas;

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

import negocio.visitas.TransferVisitas;
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
				Controlador.getInstance().actualiza(Controlador.MOSTRAR_TODAS_VISITAS, null);
			}
		}
		
		public JBMostrarTodos() {
			super("Cargar visitas");
			this.addActionListener(new ALMostrarTodos());
		}
	}
	
	private class Modelo extends AbstractTableModel {

		public Modelo() {
			this.listaVisitas = new ArrayList<TransferVisitas>();
		}
		
		public String getColumnName(int column) {
			switch (column) {
				case 0: return "ID Visita"; 
				case 1: return "ID Preso"; 
				case 2: return "Fecha"; 
				default: return null;
			}
		}
		@Override
		public int getRowCount() {
			return this.listaVisitas.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			TransferVisitas actividad = this.listaVisitas.get(rowIndex);
			Object ret = null;
			switch (columnIndex) {
				case 0: ret = actividad.getId(); break;
				case 1: ret = actividad.getPreso(); break;
				case 2: {
						Calendar fecha = actividad.getFecha();
						ret = JPAuxFecha.toString(fecha);
						} break;
			}
			return ret;
		}
		
		public void setData(List<TransferVisitas> listaVisitas) {
			this.listaVisitas = listaVisitas;
		}
		
		private List<TransferVisitas> listaVisitas;
		
	}

	public JFMostrarTodos() {
		super("Muestra todas las Visita");
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
		jpTabla.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Visitas",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		this.add(jpTabla);
		
		//Modelo
		this.modelo = new Modelo();
		
		
		//TablaSectores
		this.tablaVisitas = new JTable();
		this.tablaVisitas.setModel(modelo);
		this.tablaVisitas.setBackground(null);
		JScrollPane scTabla = new JScrollPane(this.tablaVisitas);
		scTabla.setPreferredSize(new Dimension(scTabla.getPreferredSize().height, 300));
		jpTabla.add(scTabla, BorderLayout.CENTER);
		
		//Botones
		JPanel jpBotones = new JPanel();
		this.add(jpBotones, BorderLayout.SOUTH);
		JButton jbMostrar = new JButton("Mostrar seleccionados");
		jbMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] filas = tablaVisitas.getSelectedRows();
				for (JFMostrarUno2 ventAnt: JFMostrarTodos.this.abierto){
					ventAnt.setVisible(false);
					ventAnt.dispose();
				}
				JFMostrarTodos.this.abierto.clear();
				for(int fila: filas) {
					JFMostrarUno2 visita = new JFMostrarUno2(lista.get(fila));
					JFMostrarTodos.this.abrirVentana(visita);
				}
			}
		});
		jpBotones.add(new JBMostrarTodos());
		jpBotones.add(jbMostrar);
	}

	public void informar(int resultado, List<TransferVisitas> listaVisitas) {
		switch (resultado) {
			case Controlador.OPERACION_CORRECTA: this.lista = listaVisitas;
												this.modelo.setData(listaVisitas); 
												this.modelo.fireTableDataChanged();
												break;
			
			case Controlador.VISITA_NO_EXISTENTE: JOptionPane.showMessageDialog
													(this, "No se han encontrado visitas", "Error",
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

	private List<TransferVisitas> lista;
	private JTable tablaVisitas;
	private Modelo modelo;
	private List<JFMostrarUno2> abierto;
}