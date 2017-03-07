package integracion.actividades;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Vector;

import negocio.actividades.TransferActividades;

public class DAOActividadesImpl implements DAOActividades {

	private static String generarNombreArchivo(int id) {
		return "actividad" + id + ".txt";
	}

	public TransferActividades leerActividad(int id) {
		TransferActividades actividad = new TransferActividades();
		String arch = generarNombreArchivo(id);
		try {
			Scanner entrada = new Scanner(new FileReader(arch));
			int activo = entrada.nextInt();
			if (activo == TRUE)
				actividad.setActivo(true);
			else
				actividad.setActivo(false);
			entrada.nextLine();
			actividad.setId(id);
			actividad.setNombre(entrada.nextLine());
			actividad.setDescripcion(entrada.nextLine());
			Calendar fecha =  new GregorianCalendar(entrada.nextInt(), entrada.nextInt(), entrada.nextInt(), entrada.nextInt(), entrada.nextInt());
			fecha.set(Calendar.SECOND, 0);
			fecha.set(Calendar.MILLISECOND, 0);
			actividad.setFecha(fecha);
			actividad.setAforo(entrada.nextInt());
			
			int numPresos = entrada.nextInt();
			actividad.setNumPresos(numPresos);
			
			Vector<Integer> presos = new Vector<Integer>(numPresos);
			for(int i = 0; i < numPresos; i++){
				presos.addElement(entrada.nextInt());
			}
			actividad.setPresos(presos);
			
			entrada.close();
		} catch (FileNotFoundException e) {
			actividad = null;
		}
		return actividad;
	}

	public Vector<TransferActividades> leerTodosActividad() {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			Vector<TransferActividades> actividades = new Vector<TransferActividades>();
			
			for(int i = 1; i <= ultimoid; i++){
				TransferActividades act = leerActividad(i);
				if(act.getActivo())
					actividades.addElement(act);
			}
			entrada.close();
			return actividades;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@Override
	public int crearActividad(TransferActividades actividad) {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			actividad.setId(ultimoid+1);
			entrada.close();
			FileWriter salida = new FileWriter(fileID);
			salida.write("" + (ultimoid+1));
			salida.close();
			return this.actualizarActividad(actividad);
		} catch (IOException e) {
			return -1;
		}
	}
	
	public int actualizarActividad(TransferActividades actividad) {
		String arch = generarNombreArchivo(actividad.getId());
		try {
			FileWriter salida = new FileWriter(arch);
			boolean activo = actividad.getActivo();
			if (activo)
				salida.write(TRUE + "\n");
			else
				salida.write(FALSE + "\n");
			salida.write(actividad.getNombre() + "\n");
			salida.write(actividad.getDescripcion() + "\n");
			Calendar fecha = actividad.getFecha();
			salida.write(fecha.get(Calendar.YEAR)+ " " + fecha.get(Calendar.MONTH) + " " + fecha.get(Calendar.DAY_OF_MONTH) + " " + fecha.get(Calendar.HOUR_OF_DAY) + " " + fecha.get(Calendar.MINUTE) + "\n");
			salida.write(actividad.getAforo() + "\n");
			
			int numPresos = actividad.getNumPresos();
			salida.write(numPresos + "\n");
			
			Vector<Integer> presos = actividad.getPresos();
			for (int i=0; i< numPresos; i++)
				salida.write("" + presos.elementAt(i) +" ");
			
			salida.close();
			return actividad.getId();
		} catch (IOException e) {
			return -1;
		}
	}
	
	public TransferActividades buscarPorNombreYFecha(String nombre, Calendar fecha){
		int i = 1; 
		boolean esta = false;
		TransferActividades actividad = null;
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			
			while(!esta && i <= ultimoid){
				actividad = leerActividad(i);
				i++;
				if(actividad.getNombre().equals(nombre) && actividad.getFecha().compareTo(fecha) == 0)
					esta = true;
			}
			entrada.close();
			if(esta) return actividad;
			return null;
		} catch (FileNotFoundException e) {
			return null;
		}	
	}

	private final int TRUE = 1;
	private final int FALSE = 0;
	private final String fileID = "ultimoIdActividades.txt";
}