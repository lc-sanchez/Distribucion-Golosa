package archivos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import models.CentroDeDistribucion;
import models.Cliente;

public class Escritura {
	
	public static void main(String[] args) {
		
		// Se crean los clientes de ejemplo
		Cliente cliente1= new Cliente("Maria",-34.5199,-58.7206);
		Cliente cliente2= new Cliente("Juan",-34.5161,-58.7177);
		Cliente cliente3= new Cliente("Lila",-34.522,-58.719);
		Cliente cliente4= new Cliente("Kia",-34.5177,-58.7231);
		Cliente cliente5 = new Cliente("Mailen",-34.5201,-58.7246);
		Cliente cliente6 = new Cliente("Anahi",-34.5156,-58.721);
		
		// Se crean los centros de distribucion de ejemplo
		CentroDeDistribucion centro1 = new CentroDeDistribucion("Axion",-34.5225,-58.722);
		CentroDeDistribucion centro2 = new CentroDeDistribucion("YPF",-34.5187,-58.717);
		CentroDeDistribucion centro3 = new CentroDeDistribucion("Shell",-34.5168,-58.72);
		
		try
		{
			
			FileOutputStream fos = new FileOutputStream("Clientes.txt");
			
			// Para sacar un texto de objetos
			ObjectOutputStream out = new ObjectOutputStream(fos);
			
			// Escribiendo cada cliente en el txt
			out.writeObject(cliente1);
			out.writeObject(cliente2);
			out.writeObject(cliente3);
			out.writeObject(cliente4);
			out.writeObject(cliente5);
			out.writeObject(cliente6);
//			out.writeObject(cliente7);
//			out.writeObject(cliente8);
//			out.writeObject(cliente9);
//			out.writeObject(cliente10);

			out.close();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			FileOutputStream fos = new FileOutputStream("Centros de distribucion.txt");
			
			// Para sacar un texto de objetos
			ObjectOutputStream out = new ObjectOutputStream(fos);
			
			// Escribiendo cada centro en el txt
			out.writeObject(centro1);
			out.writeObject(centro2);
			out.writeObject(centro3);
//			out.writeObject(centro4);
//			out.writeObject(centro5);

			out.close();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void escrituraDeSolucion(ArrayList<CentroDeDistribucion> centros) throws IOException {
		FileOutputStream fos = new FileOutputStream("Solucion de Centros.txt");
		
		OutputStreamWriter out = new OutputStreamWriter(fos);
		
		for (CentroDeDistribucion centroDeDistribucion : centros) {
			//out.write("===========================\n");
			out.write(centroDeDistribucion.getNombre()+"\n");
			out.write(Integer.toString((int) centroDeDistribucion.getLatitud())+"\n");
			out.write(Integer.toString((int) centroDeDistribucion.getLongitud())+"\n");
			out.write("===========================\n");
		}
		out.close();
	}

}
