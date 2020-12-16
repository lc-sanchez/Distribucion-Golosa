package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logic.DistribucionGolosa;

public class Ver_Estadisticas extends JFrame{
	

	private static final long serialVersionUID = 1L;
	DistribucionGolosa distribucion;
	private JTable table;

	public Ver_Estadisticas(DistribucionGolosa distribucionTraida) {
		distribucion = distribucionTraida;
	}
	
	public void initialize() {
		this.setSize(350,500);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTitle("Estadisticas");
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 300, 400);
		this.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		// Se crea el modelo de la tabla
		DefaultTableModel modelo = new DefaultTableModel();
		
		// Se crean las columnas
		modelo.addColumn("Nombre");
		modelo.addColumn("Promedio de distancias");
		
		// Se crean las filas
		for (int i = 0; i < distribucion.getCantCentrosDeDistribucionElegidos(); i++) 
		{
			modelo.addRow(new String[] 
				{
					// Se trae por fila el nombre del centro y el promedio de distancia
					distribucion.getCentrosDeDistribucionElegidos().get(i).getNombre(),
					String.valueOf(distribucion.getPromedioDeCentro(distribucion.getCentrosDeDistribucionElegidos().get(i)))
				});
		}
		
		// Al final se incorpora el modelo a la tabla
		table.setModel(modelo);
	}
}
