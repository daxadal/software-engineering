/**
 * 
 */
package presentacion.visitantes;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import negocio.visitantes.TransferVisitantes;
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
				Controlador.getInstance().actualiza(Controlador.MOSTRAR_TODOS_VISITANTE, null);
			}
		}
		
		public JBMostrarTodos() {
			super("Cargar visitantes");
			this.addActionListener(new ALMostrarTodos());
		}
	}
	
	private class Modelo extends AbstractTableModel {

		public Modelo() {
			this.listaVisitantes = new ArrayList<TransferVisitantes>();
		}
		
		public String getColumnName(int column) {
			switch (column) {
				case 0: return "Id"; 
				case 1: return "Apellido"; 
				case 2: return "Nombre";
				case 3: return "DNI"; 
				default: return null;
			}
		}
		@Override
		public int getRowCount() {
			return this.listaVisitantes.size();
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			TransferVisitantes visitante = this.listaVisitantes.get(rowIndex);
			Object ret = null;
			switch (columnIndex) {
				case 0: ret = visitante.getId(); break;
				case 1: ret = visitante.getApellido(); break;
				case 2: ret = visitante.getNombre(); break;
				case 3: ret = visitante.getDni(); break;
			}
			return ret;
		}
		
		public void setData(List<TransferVisitantes> listaVisitantes) {
			this.listaVisitantes = listaVisitantes;
		}
		
		private List<TransferVisitantes> listaVisitantes;
		
	}

	
	public JFMostrarTodos() {
		super("Muestra un Visitante");
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
		jpTabla.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Visitantes",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		this.add(jpTabla);
		
		//Modelo
		this.modelo = new Modelo();
		
		
		//TablaSectores
		this.tablaVisitantes = new JTable();
		this.tablaVisitantes.setModel(modelo);
		this.tablaVisitantes.setBackground(null);
		JScrollPane scTabla = new JScrollPane(this.tablaVisitantes);
		scTabla.setPreferredSize(new Dimension(scTabla.getPreferredSize().height, 300));
		jpTabla.add(scTabla, BorderLayout.CENTER);
		
		//Botones
		JPanel jpBotones = new JPanel();
		this.add(jpBotones, BorderLayout.SOUTH);
		JButton jbMostrar = new JButton("Mostrar seleccionados");
		jbMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] filas = tablaVisitantes.getSelectedRows();
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

	public void informar(int resultado, List<TransferVisitantes> listaVisitantes) {
		switch (resultado) {
			case Controlador.OPERACION_CORRECTA: this.lista = listaVisitantes;
												this.modelo.setData(listaVisitantes); 
												this.modelo.fireTableDataChanged();
												break;
			
			case Controlador.VISITANTE_NO_EXISTENTE: JOptionPane.showMessageDialog
													(this, "No se han encontrado visitantes", "Error",
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

	private List<TransferVisitantes> lista;
	private JTable tablaVisitantes;
	private Modelo modelo;
	private List<JFMostrarUno2> abierto;
}