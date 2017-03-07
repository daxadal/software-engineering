package presentacion;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JPAuxFecha extends JPanel {

	public JPAuxFecha(boolean longDisplay) {
		initGUI(true, longDisplay);
		setFecha("Año", "Mes", "Día", "Hora", "Min");
	}
		
	public JPAuxFecha(Calendar fecha, boolean editable, boolean longDisplay)
	{
		initGUI(editable, longDisplay);
		setFecha(fecha);
	}
	
	private void initGUI(boolean editable, boolean longDisplay) {
		this.campoMin = new JTextField();
		this.campoHora = new JTextField();
		this.campoDia = new JTextField();
		this.campoMes = new JTextField();
		this.campoAnyo = new JTextField();
		
		this.campoMin.setEditable(editable);
		this.campoHora.setEditable(editable);
		this.campoDia.setEditable(editable);
		this.campoMes.setEditable(editable);
		this.campoAnyo.setEditable(editable);
		
		campoMin.setBorder(BorderFactory.createLoweredBevelBorder());
		campoHora.setBorder(BorderFactory.createLoweredBevelBorder());
		campoDia.setBorder(BorderFactory.createLoweredBevelBorder());
		campoMes.setBorder(BorderFactory.createLoweredBevelBorder());
		campoAnyo.setBorder(BorderFactory.createLoweredBevelBorder());
		
		if (longDisplay) {
			campoMin.setPreferredSize(new Dimension(40, 20));
			campoHora.setPreferredSize(new Dimension(40, 20));
			campoDia.setPreferredSize(new Dimension(40, 20));
			campoMes.setPreferredSize(new Dimension(40, 20));
			campoAnyo.setPreferredSize(new Dimension(40, 20));
		}
		else {
			campoMin.setPreferredSize(new Dimension(20, 20));
			campoHora.setPreferredSize(new Dimension(20, 20));
			campoDia.setPreferredSize(new Dimension(20, 20));
			campoMes.setPreferredSize(new Dimension(20, 20));
			campoAnyo.setPreferredSize(new Dimension(40, 20));
		}
		
		this.add(this.campoHora);
		this.add(new JLabel(":"));
		this.add(this.campoMin);
		this.add(new JLabel(" - "));
		this.add(this.campoDia);
		this.add(new JLabel("/"));
		this.add(this.campoMes);
		this.add(new JLabel("/"));
		this.add(this.campoAnyo);
	}
	
	public void setFecha(String anyo, String mes, String dia, String hora, String min) {
		this.campoMin.setText(min);
		this.campoHora.setText(hora);
		this.campoDia.setText(dia);
		this.campoMes.setText(mes);
		this.campoAnyo.setText(anyo);	
	}
	
	public void setFecha(Calendar fecha) {
		this.campoMin.setText(""+ String.format("%02d", fecha.get(Calendar.MINUTE)));
		this.campoHora.setText(""+ String.format("%02d", fecha.get(Calendar.HOUR_OF_DAY)));
		this.campoDia.setText(""+ String.format("%02d", fecha.get(Calendar.DAY_OF_MONTH) + 1));
		this.campoMes.setText(""+ String.format("%02d", fecha.get(Calendar.MONTH)));
		this.campoAnyo.setText(""+ fecha.get(Calendar.YEAR));	
	}
	
	public Calendar getFecha() throws NumberFormatException, IllegalArgumentException{
		int min  = Integer.parseInt(this.campoMin.getText());
		int hora  = Integer.parseInt(this.campoHora.getText());
		int dia  = Integer.parseInt(this.campoDia.getText());
		int mes  = Integer.parseInt(this.campoMes.getText()) - 1;
		int anyo  = Integer.parseInt(this.campoAnyo.getText());
		
		GregorianCalendar fecha = new GregorianCalendar();
		fecha.setLenient(false);
		fecha.set(anyo, mes, dia, hora, min);
		fecha.set(Calendar.SECOND, 0);
		fecha.set(Calendar.MILLISECOND, 0);
		fecha.getTime();
		
		return fecha;
	}
	
	public static String toString(Calendar fecha) {
		return "" + String.format("%02d", fecha.get(Calendar.HOUR_OF_DAY)) + ":"
				+ String.format("%02d", fecha.get(Calendar.MINUTE)) + " - "
				+ String.format("%02d", fecha.get(Calendar.DAY_OF_MONTH)) + "/"
				+ String.format("%02d", (fecha.get(Calendar.MONTH)+1)) + "/"
				+ fecha.get(Calendar.YEAR);
	}
	
	public void setEditable(boolean editable) {
		this.campoMin.setEditable(editable);
		this.campoHora.setEditable(editable);
		this.campoDia.setEditable(editable);
		this.campoMes.setEditable(editable);
		this.campoAnyo.setEditable(editable);
	}
	
	private JTextField campoMin;
	private JTextField campoHora;
	private JTextField campoDia;
	private JTextField campoMes;
	private JTextField campoAnyo;
}
