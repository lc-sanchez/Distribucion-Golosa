package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import logic.DistribucionGolosa;

public class Ventana_Principal extends JFrame{
	private static final long serialVersionUID = 1L;

	public Ventana_Principal() {
	}
	
	public void initialize() {
		this.setSize(350,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JMapViewer");
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JLabel texto = new JLabel("Ingrese el numero de centros que quiera abrir:");
		texto.setFont(new Font("Arial", Font.PLAIN, 15));
		texto.setBounds(20, 0, 350, 70);
		this.getContentPane().add(texto);
		
		// Se crea el area para rellenar con el numero de centros que se quieren abrir
		JTextArea txtArea = new JTextArea();
		txtArea.setTabSize(10);
		txtArea.setLineWrap(true);
		txtArea.setBounds(50, 70, 240, 30);
		this.getContentPane().add(txtArea);
		
		// Se crea el boton con el que se iniciara la ventana del mapa
		JButton verMapa = new JButton("Ver Mapa");
		verMapa.setBounds(50, 150, 242, 51);
		verMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					// Se trae el numero del area que se le pasara a la distribucion para setearlo
					int area = Integer.parseInt(txtArea.getText());
					
					DistribucionGolosa distribucion = new DistribucionGolosa(area);
					
					// Se traen los datos de los archivos y se los guarda en la distribucion
					distribucion.cargarDatos();
					
					// Se crea la vista del mapa
					Vista_Mapa vistaMapaVisual = new Vista_Mapa(distribucion) {
						public void dispose() {
							getFrame().setVisible(true);
							super.frame.dispose();
						}  
					};
					
					vistaMapaVisual.frame.setVisible(true);
					getFrame().dispose();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(getFrame(), "El numero no es un int");
				}
			}
		});
		this.getContentPane().add(verMapa);
	}
	
	public JFrame getFrame() {
		return this;
	}
}