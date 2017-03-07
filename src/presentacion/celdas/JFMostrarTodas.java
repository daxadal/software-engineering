package presentacion.celdas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import negocio.celdas.TransferCeldas;
import presentacion.controlador.Controlador;

@SuppressWarnings("serial")
public class JFMostrarTodas extends JFrame{
	
	public class JBMostrarTodas extends JButton{	
		
		public class ALMostrarTodas implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JFMostrarUna2 ventAnt: JFMostrarTodas.this.abierto){
					ventAnt.setVisible(false);
					ventAnt.dispose();
				}
				Controlador.getInstance().actualiza(Controlador.MOSTRAR_TODAS_CELDAS, null);
			}
		}
		
		public JBMostrarTodas() {
			super("Cargar Celdas");
			this.addActionListener(new ALMostrarTodas());
		}
	}
	
	private class Modelo extends AbstractTableModel {

		public Modelo() {
			this.listaCelda = new Vector<TransferCeldas>();
		}
		
		public String getColumnName(int column) {
			switch (column) {
				case 0: return "Id"; 
				case 1: return "Número de Celda"; 
				case 2: return "ID del Sector"; 
				default: return null;
			}
		}
		@Override
		public int getRowCount() {
			return this.listaCelda.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			TransferCeldas celda = this.listaCelda.elementAt(rowIndex);
			Object ret = null;
			switch (columnIndex) {
				case 0: ret = celda.getIdCelda(); break;
				case 1: ret = celda.getNumCelda(); break;
				case 2: ret = celda.getIdSector(); break;
			}
			return ret;
		}
		
		public void setData(Vector<TransferCeldas> listaCeldas) {
			this.listaCelda = listaCeldas;
		}
		
		private Vector<TransferCeldas> listaCelda;
		
	}

	public JFMostrarTodas() {
		super("Muestra todas las Celda");
		setSize(480,420);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		instancia = this;
		this.lista = null;
		this.abierto = new ArrayList<JFMostrarUna2>();
		initGUI();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent ev) {
				for (JFMostrarUna2 vent: JFMostrarTodas.this.abierto){
					vent.setVisible(false);
					vent.dispose();
				}
			}
		});
	}

	private void initGUI() {
		JPanel jpTabla = new JPanel();
		jpTabla.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Celdas",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		this.add(jpTabla);
		
		//Modelo
		this.modelo = new Modelo();
		
		
		
		//TablaCeldas
		this.tablaCeldas = new JTable();
		this.tablaCeldas.setModel(modelo);
		this.tablaCeldas.setBackground(null);
		JScrollPane scTabla = new JScrollPane(this.tablaCeldas);
		scTabla.setPreferredSize(new Dimension(scTabla.getPreferredSize().height, 300));
		jpTabla.add(scTabla, BorderLayout.CENTER);
		
		//Botones
		JPanel jpBotones = new JPanel();
		this.add(jpBotones, BorderLayout.SOUTH);
		JButton jbMostrar = new JButton("Mostrar seleccionados");
		jbMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] filas = tablaCeldas.getSelectedRows();
				for (JFMostrarUna2 ventAnt: JFMostrarTodas.this.abierto){
					ventAnt.setVisible(false);
					ventAnt.dispose();
				}
				JFMostrarTodas.this.abierto.clear();
				int i = 0;
				for(int fila: filas) {
					JFMostrarUna2 sect = new JFMostrarUna2(lista.elementAt(fila));
					sect.setLocation(0, i*10);
					JFMostrarTodas.this.abrirVentana(sect);
					i++;
				}
			}
		});
		jpBotones.add(new JBMostrarTodas());
		jpBotones.add(jbMostrar);
	}

	public void informar(int resultado, Vector<TransferCeldas> listaCeldas) {
		switch (resultado) {
			case Controlador.OPERACION_CORRECTA: this.lista = listaCeldas;
												this.modelo.setData(listaCeldas); 
												this.modelo.fireTableDataChanged();
												break;
			
			case Controlador.CELDA_NO_EXISTENTE: JOptionPane.showMessageDialog
													(this, "No se han encontrado celdas", "Error",
													JOptionPane.ERROR_MESSAGE); break;
		}
	}
	
	public static JFMostrarTodas getInstance() {
		return instancia;
	}
	
	private void abrirVentana(JFMostrarUna2 vent) {
		this.abierto.add(vent);
		vent.setLocationRelativeTo(this);
		vent.setLocation(vent.getLocation().x, vent.getLocation().y - 100 + this.abierto.size()*25);
		vent.setVisible(true);
	}
	
	private static JFMostrarTodas instancia = null;

	private Vector<TransferCeldas> lista;
	private JTable tablaCeldas;
	private Modelo modelo;
	private List<JFMostrarUna2> abierto;
}