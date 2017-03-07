package presentacion.visitantes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import presentacion.JFPrincipal;

@SuppressWarnings("serial")
public class JPMostrarDatos extends JPanel{

	public JPMostrarDatos(boolean editable, JTextField jtfId, JTextField jtfNombre, 
			JTextField jtfApellido,  JTextField jtfDni, JComboBox<String> jcbTipo, 
			JLabel nomEspec, JTextField jtfEspec, JList<Integer> jlVisitas)
	{
		initGUI(editable, jtfId, jtfNombre, jtfApellido, jtfDni, jcbTipo, nomEspec,
				jtfEspec, jlVisitas);
	}
	private void initGUI(boolean editable, JTextField jtfId, JTextField jtfNombre,
			JTextField jtfApellido,  JTextField jtfDni, JComboBox<String> jcbTipo, 
			JLabel nomEspec, JTextField jtfEspec, JList<Integer> jlVisitas)
	{	
		 	this.nomDato = nomEspec;
		 	this.dato = jtfEspec;
		this.setLayout(new BorderLayout());
		JPanel jpCentro = new JPanel(new GridLayout(1,2,10,10));
		this.add(jpCentro);
		//PARTE IZQUIERDA (FOTO, ID Y DATOS PRINCIPALE)
		JPanel jpLeft = new JPanel(new GridLayout(2,1,10,10));
		jpCentro.add(jpLeft);
		
			 //Parte Foto y ID
				//Foto
				JPanel jpUp = new JPanel();
				jpUp.setLayout(new BorderLayout());
				URL icon = JFPrincipal.class.getResource("iconos/face.png");
				JLabel face = new JLabel(new ImageIcon(icon));
				face.setSize(20, 20);
				jpUp.add(face, BorderLayout.WEST);
				 //ID
				JPanel jpCID = new JPanel(new FlowLayout(FlowLayout.LEADING, 15, 20));
				JPanel jpID = new JPanel(new BorderLayout());
				jpCID.add(jpID);
				jpID.add(new JLabel("ID: ", JLabel.RIGHT), BorderLayout.WEST);
				jpID.add(jtfId);
				jtfId.setBorder(BorderFactory.createLoweredBevelBorder());
				jtfId.setEditable(false);
				jpUp.add(jpCID);
			jpLeft.add(jpUp);
	
			//Parte Datos
		
			JPanel jpDPersonales = new JPanel();
			jpDPersonales.setLayout(new BorderLayout());
			jpDPersonales.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Datos personales",
												TitledBorder.CENTER, TitledBorder.TOP,
												new Font("Courier New", Font.TRUETYPE_FONT, 14), 
												Color.BLACK));
			
			JPanel jpEtiquetasDP = new JPanel();
			JPanel jpTextosDP = new JPanel();
			jpEtiquetasDP.setLayout(new GridLayout(3, 1, 10, 0));
			jpTextosDP.setLayout(new GridLayout(3, 1, 0, 10));
			jpDPersonales.setSize(100, 100);
			jpDPersonales.add(jpEtiquetasDP, BorderLayout.WEST);
			jpDPersonales.add(jpTextosDP, BorderLayout.CENTER);
			jpEtiquetasDP.add(new JLabel("Nombre: ", JLabel.RIGHT));
			jpTextosDP.add(jtfNombre);
			jtfNombre.setBorder(BorderFactory.createLoweredBevelBorder());
			jtfNombre.setEditable(editable);
			jpEtiquetasDP.add(new JLabel("Apellido: ", JLabel.RIGHT));
			jpTextosDP.add(jtfApellido);
			jtfApellido.setBorder(BorderFactory.createLoweredBevelBorder());
			jtfApellido.setEditable(editable);
			jpEtiquetasDP.add(new JLabel("DNI: ", JLabel.RIGHT));
			jpTextosDP.add(jtfDni);
			jtfDni.setBorder(BorderFactory.createLoweredBevelBorder());
			jtfDni.setEditable(editable);
			jpLeft.add(jpDPersonales);
				
		//PARTE DERECHA (VISITA)
		JPanel jpRight = new JPanel(new GridLayout(1,2,10,10));
		JPanel jpVisitas = new JPanel();
		jpRight.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Visitas",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		if (jlVisitas != null)
		{
			jlVisitas.setBackground(null);
			DefaultListCellRenderer rendererV =  (DefaultListCellRenderer)jlVisitas.getCellRenderer();  
			rendererV.setHorizontalAlignment(JLabel.CENTER);
			jpVisitas.add(jlVisitas, BorderLayout.CENTER);
		}
		JScrollPane sPVis = new JScrollPane
				(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sPVis.setViewportView(jpVisitas);
		sPVis.setBorder(null);
		jpRight.add(sPVis);
		
		
		jpCentro.add(jpRight);
		
	//PARTE ABAJO (PROFESIONAL/PERSONAL)
	if(jcbTipo.getItemCount() != 0)
	{
		JPanel jpSur = new JPanel(new GridLayout(1,2,10,10));
		jpSur.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jpSur.setMinimumSize(new Dimension(50,200));
		JPanel jpDat = new JPanel(new BorderLayout(10,0));
		JPanel jpTipo = new JPanel(new BorderLayout(10,0));
		jpTipo.add(new JLabel("Tipo: "), BorderLayout.WEST);
		if(editable)
		{
			jpTipo.add(jcbTipo);
			jcbTipo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			        @SuppressWarnings("rawtypes")
					JComboBox cb = (JComboBox)e.getSource();
			        String selected = (String)cb.getSelectedItem();
			       switch(selected)
			       {
			       		case "Profesional": nomDato.setText("Email: ");
			       							dato.setEditable(true);			break;
			       		case "Familiar": 	nomDato.setText("Parentesco: ");
			       							dato.setEditable(true);			break;
			       		default: 			nomDato.setText(""); 
											dato.setEditable(false);		break;
			       }
			       JPMostrarDatos.this.repaint();
				}
			});
			jpDat.add(nomDato, BorderLayout.WEST);
			jpDat.add(dato, JLabel.CENTER);
			dato.setBorder(BorderFactory.createLoweredBevelBorder());
		}
		else 
		{
			JLabel jlTipo = new JLabel(jcbTipo.getItemAt(0), JLabel.CENTER);
			jlTipo.setBorder(BorderFactory.createLoweredBevelBorder());
			jlTipo.setFont(new Font(jlTipo.getFont().getName(), Font.ITALIC,12));
			jpTipo.add(jlTipo);
			jpDat.add(nomEspec, BorderLayout.WEST);
			dato.setBorder(BorderFactory.createLoweredBevelBorder());
			jpDat.add(dato);
			dato.setEditable(false);
		}
		jpSur.add(jpTipo);
		jpSur.add(jpDat);
		JPanel jpSurAux = new JPanel();
		jpSurAux.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY),"Tipo de Visitante",
				TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Courier New", Font.TRUETYPE_FONT, 14), 
				Color.BLACK));
		jpSurAux.add(jpSur);
		this.add(jpSurAux, BorderLayout.SOUTH);	
	}
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		

	}
	private JLabel nomDato;
	private JTextField dato;
}
