package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import logic.DistribucionGolosa;
import logic.Solver;
import models.CentroDeDistribucion;
import models.Cliente;

public class DistribucionGolosaTest {

	@Test
	public void resolverDistribucion() throws ClassNotFoundException, IOException {
		//Set up
		DistribucionGolosa distribucion= new DistribucionGolosa(1);
		
		CentroDeDistribucion centro = new CentroDeDistribucion("Axion",10,20);
		Cliente cliente= new Cliente("Maria",3,9);
		
		distribucion.agregarCentroDeDistribucion(centro);
		distribucion.agregarCliente(cliente);
		//Execute
		distribucion.resolverDistribucion();
		
		//Verify
		assertTrue(distribucion.getCantCentrosDeDistribucionElegidos()==1);
		assertTrue(distribucion.getCentrosDeDistribucionElegidos().get(0).equals(centro));
		
	}
	
	@Test
	public void agregarCentroElegidoTest() throws ClassNotFoundException, IOException {
		//Set up
		DistribucionGolosa distribucion= new DistribucionGolosa(1);
		
		CentroDeDistribucion centro = new CentroDeDistribucion("Axion",10,20);
		Cliente cliente= new Cliente("Maria",3,9);
		
		distribucion.agregarCentroDeDistribucion(centro);
		distribucion.agregarCliente(cliente);
		
		
		Solver solver= new Solver(distribucion);
		solver.resolverDistribucion();
		
		ArrayList<CentroDeDistribucion> centrosObtenidos= distribucion.getCentrosDeDistribucionElegidos();
		
		assertTrue(centrosObtenidos.size()==1);
		assertTrue(centrosObtenidos.get(0).equals(centro));
	}
	
	@Test
	public void getCostoTotalSolucion() throws ClassNotFoundException, IOException {
		//Set up
		DistribucionGolosa distribucion= new DistribucionGolosa(1);
				
		CentroDeDistribucion centro = new CentroDeDistribucion("Axion",10,20);
		Cliente cliente= new Cliente("Maria",3,9);
				
		distribucion.agregarCentroDeDistribucion(centro);
		distribucion.agregarCliente(cliente);
		
		double resultadoEsperado=1442.4950610215733; 
		
		//Execute
		distribucion.resolverDistribucion();
				
		//Verify
		assertTrue(resultadoEsperado==distribucion.getCostoTotalSolucion());
	}
	
	

}
