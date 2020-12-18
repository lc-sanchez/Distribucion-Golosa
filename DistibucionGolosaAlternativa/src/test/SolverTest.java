package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Ignore;
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
		
		Solver solver= new Solver(distribucion);
		
		ArrayList<CentroDeDistribucion> centros= distribucion.getCentrosDeDistribucion();
		ArrayList<CentroDeDistribucion> centrosElegidos=distribucion.getCentrosDeDistribucionElegidos();
		
		//Execute	
		solver.resolverDistribucion();	
		
		//Verify
		assertEquals(2,distribucion.getCantCentrosDeDistribucionElegidos());
		assertEquals(centros.get(2),centrosElegidos.get(0));
		assertEquals(centros.get(0),centrosElegidos.get(1));
	}

	@Test
	public void getCostoTotalSolucion() throws ClassNotFoundException, IOException {
		//Set Up
		DistribucionGolosa distribucion= initDistribucionGolosa();
		
		Solver solver= new Solver(distribucion);
				
		//Execute
		solver.resolverDistribucion();
		
		double resultadoEsperado=1.7618657007687524;
		assertEquals(resultadoEsperado,solver.getCostoTotalSolucion(),12);
	}
	
		
	private DistribucionGolosa initDistribucionGolosa() throws ClassNotFoundException, IOException {
		//SetUp
		DistribucionGolosa distribucion= new DistribucionGolosa(2);
		
		Cliente cliente1= new Cliente("Maria",-34.5199,-58.7206);
		Cliente cliente2= new Cliente("Juan",-34.5161,-58.7177);
		Cliente cliente3= new Cliente("Lila",-34.520,-58.718);
		Cliente cliente4= new Cliente("Kia",-34.5177,-58.7231);
		Cliente cliente5 = new Cliente("Mailen",-34.5201,-58.7246);
		Cliente cliente6 = new Cliente("Anahi",-34.5156,-58.721);
		
		// Se crean los centros de distribucion de ejemplo
		CentroDeDistribucion centro1 = new CentroDeDistribucion("Axion",-34.5225,-58.722);
		CentroDeDistribucion centro2 = new CentroDeDistribucion("YPF",-34.5187,-58.717);
		CentroDeDistribucion centro3 = new CentroDeDistribucion("Shell",-34.5168,-58.72);
								
		//Se llena nuestra distribucion
		distribucion.agregarCentroDeDistribucion(centro1);
		distribucion.agregarCentroDeDistribucion(centro2);
		distribucion.agregarCentroDeDistribucion(centro3);
						
		distribucion.agregarCliente(cliente1);
		distribucion.agregarCliente(cliente2);
		distribucion.agregarCliente(cliente3);
		distribucion.agregarCliente(cliente4);
		distribucion.agregarCliente(cliente5);
		distribucion.agregarCliente(cliente6);
		
		distribucion.setearCentrosMasCercanos();
		distribucion.actualizarValoresComparativos();
		
		return distribucion;
	}
	
}
