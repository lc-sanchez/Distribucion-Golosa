package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import archivos.Escritura;
import logic.DistribucionGolosa;

public class Vista_Mapa {
	public JFrame frame;
	private JMapViewer mapa;
	
	private DistribucionGolosa distribucion;
	private ArrayList<Color> colores;

	public Vista_Mapa(DistribucionGolosa dG) {
		distribucion=dG;
		colores = new ArrayList<Color>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.MAGENTA);
		colores.add(Color.GREEN);
		initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("JMapViewer");
		frame.setLocationRelativeTo(null);
		
		mapa = new JMapViewer();
		mapa.setZoomControlsVisible(false);
		
		// Creacion del menu Opciones
		JMenu menu = new JMenu("Opciones");
		// Items del menu
		JMenuItem menuItem = new JMenuItem("Ejecutar Algoritmo");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					// Se llama al solver que en resumidas seteara un arreglo con los centros elegidos
					distribucion.resolverDistribucion();
									
					// Se guarda un numero al azar que definira un color de camino para cada centro
					int numRandom = (int) (Math.random()*4);
						
					// Se recorren todos los clientes
					for (int j = 0; j < distribucion.getCantClientes(); j++) {
						agregarCamino(j,numRandom);
					}
					// Se obtienen los centros elegidos (solucion) y se los escribe en un archivo aparte
					Escritura.escrituraDeSolucion(distribucion.getCentrosDeDistribucionElegidos());
					JOptionPane.showMessageDialog(frame, "El costo total de abrir estos centros es: " + 
							distribucion.getCostoTotalSolucion());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, "El numero de centros se pasa de lo permitido");
				}
			}
		});
		
		menu.add(menuItem);
		
		JMenuItem menuItem2 = new JMenuItem("Mostrar Estadisticas");
		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Ver_Estadisticas estadisticas = new Ver_Estadisticas(distribucion);
					estadisticas.initialize();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		menu.add(menuItem2);
		
		JMenuItem menuItem3 = new JMenuItem("Volver");
		menuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		menu.add(menuItem3);
		
		// Creacion de la BARRA de menu
		JMenuBar barra = new JMenuBar();
		
		// Agrego los menus a la barra
		barra.add(menu);
		
		// Agrego la barra al menu
		frame.setJMenuBar(barra);
		
		// Se recorren todos los clientes guardados en distribucion y se los grafica
		for (int i = 0; i < distribucion.getCantClientes(); i++) {
			Coordinate coordenada = new Coordinate(distribucion.getClientes().get(i).getLatitud(),
					distribucion.getClientes().get(i).getLongitud());
			agregarMarcador(coordenada, Color.ORANGE,distribucion.getClientes().get(i).getNombre());
		}
		
		// Se recorren todos los centros de distribucion guardados en la clase distribucion y se los grafica
		for (int j = 0; j < distribucion.getCantCentrosDeDistribucion(); j++) {
			Coordinate coordenada = new Coordinate(distribucion.getCentrosDeDistribucion().get(j).getLatitud(),
					distribucion.getCentrosDeDistribucion().get(j).getLongitud());
			agregarMarcador(coordenada, Color.YELLOW,distribucion.getCentrosDeDistribucion().get(j).getNombre());
		}
		
		// Se guarda la primera coordenada de clientes (no antes guardada en esta clase)
		// Y se la utiliza para centrar la vista del mapa y el zoom
		Coordinate coordenada = new Coordinate(distribucion.getClientes().get(0).getLatitud(),
				distribucion.getClientes().get(0).getLongitud());
		mapa.setDisplayPosition(coordenada, 16);
		
		// Se agrega el mapa a la ventana
		frame.getContentPane().add(mapa);
	}
	
	// Se utiliza para agregar un camino entre un centro y un cliente
	private void agregarCamino(int numeroDeCliente,int numRandomElegido) {
		ArrayList<Coordinate> coordenas = new ArrayList<Coordinate>();
		coordenas.add(new Coordinate(distribucion.getClientes().get(numeroDeCliente).getLatitud(),
				distribucion.getClientes().get(numeroDeCliente).getLongitud()));
		coordenas.add(new Coordinate(distribucion.getClientes().get(numeroDeCliente).getLatitud(),
				distribucion.getClientes().get(numeroDeCliente).getLongitud()));
		coordenas.add(new Coordinate(distribucion.getClientes().get(numeroDeCliente).get_centroElegido().getLatitud(),
				distribucion.getClientes().get(numeroDeCliente).get_centroElegido().getLongitud()));
		
		MapPolygon poligono = new MapPolygonImpl(coordenas);
		poligono.getStyle().setColor(colores.get(numRandomElegido));
		mapa.addMapPolygon(poligono);
	}
	
	// Para graficar los vertices/puntos que representaran a los clientes y centros
	private void agregarMarcador(Coordinate coord, Color color,String nombre) {
		MapMarker marcador1 = new MapMarkerDot(nombre,coord);
		marcador1.getStyle().setBackColor(color);
		marcador1.getStyle().setColor(Color.ORANGE);
		mapa.addMapMarker(marcador1);
	}
	
	public void dispose() {
		
	}
}
