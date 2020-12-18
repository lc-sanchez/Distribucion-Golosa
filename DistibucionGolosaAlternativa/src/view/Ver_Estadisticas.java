package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logic.DistribucionGolosa;
import models.CentroDeDistribucion;

public class Ver_Estadisticas extends JFrame{
	

	private static final long serialVersionUID = 1L;
	DistribucionGolosa distribucion;
	private JTable table;

	public Ver_Estadisticas(DistribucionGolosa distribucionTraida) {
		distribucion = distribucionTraida;
	}
	
	public void initialize() {
		this.setSize(730,500);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTitle("Estadisticas");
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 680, 400);
		this.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		// Se crea el modelo de la tabla
		DefaultTableModel modelo = new DefaultTableModel();
		
		// Se crean las columnas
		modelo.addColumn("Nombre");
		modelo.addColumn("Suma de distancias");
		modelo.addColumn("Promedio de distancias");
		modelo.addColumn("Latitud");
		modelo.addColumn("Longitud");
		
		// Se crean las filas
		for (CentroDeDistribucion centro : distribucion.getCentrosDeDistribucionElegidos()) 
		{
			modelo.addRow(new String[] 
				{
					// Se trae por fila el nombre del centro y el promedio de distancia
					centro.getNombre(),
					Double.toString(centro.getSumaDeDistanciasConClientes()),
					String.valueOf(distribucion.getPromedioDeCentro(centro)),
					Double.toString(centro.getLatitud()),
					Double.toString(centro.getLongitud())
				});
		}
		
		// Al final se incorpora el modelo a la tabla
		table.setModel(modelo);
	}
}
