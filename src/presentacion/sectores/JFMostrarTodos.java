package presentacion.sectores;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import negocio.sectores.TransferSector;
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
				Controlador.getInstance().actualiza(Controlador.MOSTRAR_TODOS_SECTOR, null);
			}
		}
		
		public JBMostrarTodos() {
			super("Cargar sectores");
			this.addActionListener(new ALMostrarTodos());
		}
	}
	
	private class Modelo extends AbstractTableModel {

		public Modelo() {
			this.listaSectores = new Vector<TransferSector>();
		}
		
		public String getColumnName(int column) {
			switch (column) {
				case 0: return "Id"; 
				case 1: return "Nombre"; 
				case 2: return "Nº Celdas"; 
				default: return null;
			}
		}
		@Override
		public int getRowCount() {
			return this.listaSectores.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			TransferSector sector = this.listaSectores.get(rowIndex);
			Object ret = null;
			switch (columnIndex) {
				case 0: ret = sector.getId(); break;
				case 1: ret = sector.getNombre(); break;
				case 2: ret = sector.getNumCeldas(); break;
			}
			return ret;
		}
		
		public void setData(Vector<TransferSector> listaSectores) {
			this.listaSectores = listaSectores;
		}
		
		private Vector<TransferSector> listaSectores;
		
	}

	public JFMostrarTodos() {
		super("Muestra todos los Sectores");
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
		jpTabla.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Sectores",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		this.add(jpTabla);
		
		//Modelo
		this.modelo = new Modelo();
		
		
		//TablaSectores
		this.tablaSectores = new JTable();
		this.tablaSectores.setModel(modelo);
		this.tablaSectores.setBackground(null);
		JScrollPane scTabla = new JScrollPane(this.tablaSectores);
		scTabla.setPreferredSize(new Dimension(scTabla.getPreferredSize().height, 300));
		jpTabla.add(scTabla, BorderLayout.CENTER);
		
		//Botones
		JPanel jpBotones = new JPanel();
		this.add(jpBotones, BorderLayout.SOUTH);
		JButton jbMostrar = new JButton("Mostrar seleccionados");
		jbMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] filas = tablaSectores.getSelectedRows();
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

	public void informar(int resultado, Vector<TransferSector> listaSectores) {
		switch (resultado) {
			case Controlador.OPERACION_CORRECTA: this.lista = listaSectores;
												this.modelo.setData(listaSectores); 
												this.modelo.fireTableDataChanged();
												break;
			
			case Controlador.SECTOR_NO_EXISTENTE: JOptionPane.showMessageDialog
													(this, "No se han encontrado sectores", "Error",
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

	private Vector<TransferSector> lista;
	private JTable tablaSectores;
	private Modelo modelo;
	private List<JFMostrarUno2> abierto;
}