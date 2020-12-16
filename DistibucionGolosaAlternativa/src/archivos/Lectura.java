package archivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import models.CentroDeDistribucion;
import models.Cliente;

public class Lectura 
{
	
	// Retorna un arraylist con todos los clientes del archivo
	public static ArrayList<Cliente> obtenerClientes() throws IOException, ClassNotFoundException{
		
		ArrayList<Cliente> ret = new ArrayList<Cliente>();

		try 
		{
			FileInputStream fis = new FileInputStream("Clientes.txt");
			// Para leer objetos
			ObjectInputStream in = new ObjectInputStream(fis);
			
			// Se leen los clientes del archivo y se los agrega al arraylist que se retorna
			for (int i = 0; i < 6; i++) 
			{
				Cliente p1 = (Cliente) in.readObject();
				ret.add(p1);
			}
			in.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return ret;
	}
	
	// Retorna un arraylist con todos los centros del archivo
	public static ArrayList<CentroDeDistribucion> obtenerCentros() throws IOException, ClassNotFoundException{
			
		ArrayList<CentroDeDistribucion> ret = new ArrayList<CentroDeDistribucion>();
		
		try 
		{
			FileInputStream fis = new FileInputStream("Centros de distribucion.txt");
			
			// Para leer objetos
			ObjectInputStream in = new ObjectInputStream(fis);
			
			// Se leen los centros del archivo y se los agrega al arraylist que se retorna
			for (int i = 0; i < 3; i++) 
			{
				CentroDeDistribucion p1 = (CentroDeDistribucion) in.readObject();
				ret.add(p1);
			}
			in.close();
		}
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return ret;
	}

}
