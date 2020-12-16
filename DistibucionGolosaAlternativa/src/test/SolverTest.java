package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import logic.DistribucionGolosa;

import logic.Solver;
import models.CentroDeDistribucion;
import models.Cliente;

public class SolverTest {

	@Test
	public void resolverDistribucionTest() throws ClassNotFoundException, IOException {
		//SetUp
		DistribucionGolosa distribucion= initDistribucionGolosa();
		
		//Armamos nuestro comparador 
		CentroDeDistribucion centro1 = new CentroDeDistribucion("Axion",10,20);
		CentroDeDistribucion centro2 = new CentroDeDistribucion("YPF",5,10);
		CentroDeDistribucion centro3 = new CentroDeDistribucion("Shell",3,7);
		
		Cliente cliente1= new Cliente("Maria",3,9);
		Cliente cliente2= new Cliente("Juan",11,18);
		Cliente cliente3= new Cliente("Lila",10,7);
		Cliente cliente4= new Cliente("Kia",7,6);
	
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		
		centro1.sumaDeDistanciasConClientes(clientes);
		centro2.sumaDeDistanciasConClientes(clientes);
		centro3.sumaDeDistanciasConClientes(clientes);
		
		Solver solver= new Solver(distribucion);
		
		//Execute
		solver.resolverDistribucion();
		
		//Tenemos que obtener 2 valores
		CentroDeDistribucion centroObtenido1=distribucion.getCentrosDeDistribucionElegidos().get(0);
		CentroDeDistribucion centroObtenido2=distribucion.getCentrosDeDistribucionElegidos().get(1);
		
		//Verify
		assertEquals(2,distribucion.getCantCentrosDeDistribucionElegidos());
		assertTrue(centroObtenido1.equals(centro2) && centroObtenido2.equals(centro3));
	}

	@Test
	public void getCostoTotalSolucion() throws ClassNotFoundException, IOException {
		//Set Up
		DistribucionGolosa distribucion= initDistribucionGolosa();
		
		Solver solver= new Solver(distribucion);
				
		//Execute
		solver.resolverDistribucion();
		
		double resultadoEsperado=5458.137005394175;
		assertEquals(resultadoEsperado,solver.getCostoTotalSolucion(),12);
	}
	
	private DistribucionGolosa initDistribucionGolosa() throws ClassNotFoundException, IOException {
		//SetUp
		DistribucionGolosa distribucion= new DistribucionGolosa(2);
						
		CentroDeDistribucion centro1 = new CentroDeDistribucion("Axion",10,20);
		CentroDeDistribucion centro2 = new CentroDeDistribucion("YPF",5,10);
		CentroDeDistribucion centro3 = new CentroDeDistribucion("Shell",3,7);
						
						
		Cliente cliente1= new Cliente("Maria",3,9);
		Cliente cliente2= new Cliente("Juan",11,18);
		Cliente cliente3= new Cliente("Lila",10,7);
		Cliente cliente4= new Cliente("Kia",7,6);
						
		//Se llena nuestra distribucion
		distribucion.agregarCentroDeDistribucion(centro1);
		distribucion.agregarCentroDeDistribucion(centro2);
		distribucion.agregarCentroDeDistribucion(centro3);
						
		distribucion.agregarCliente(cliente1);
		distribucion.agregarCliente(cliente2);
		distribucion.agregarCliente(cliente3);
		distribucion.agregarCliente(cliente4);
		
		return distribucion;
	}
	
}
