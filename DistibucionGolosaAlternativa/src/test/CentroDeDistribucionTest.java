package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import models.CentroDeDistribucion;
import models.Cliente;

public class CentroDeDistribucionTest {


	@Test
	public void calcularDistanciaConClienteTest() {
		//SetUp
		Cliente c1= new Cliente("Maria",3,9);
		CentroDeDistribucion cd1= new CentroDeDistribucion("Charlone",10,20);
		
		double esperado= 1442.4950610215733;
		
		//Execute
		double calculo= cd1.calcularDistanciaConCliente(c1);
		
		//Verify
		assertTrue(esperado==calculo);
	}
	
	@Test
	public void sumaDeDistanciasConClientesTest() {
		//SetUp
		Cliente c1= new Cliente("Maria",3,9);
		Cliente c2= new Cliente("Juan",11,18);
		Cliente c3= new Cliente("Lila",10,7);
		Cliente c4= new Cliente("Kia",7,6);
		
		CentroDeDistribucion cd1= new CentroDeDistribucion("Charlone",10,20);
		
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		clientes.add(c4);
		
		double esperado= 4686.374356064969;
		
		//Execute
		cd1.sumaDeDistanciasConClientes(clientes);
		
		//Verify
		assertTrue(esperado==cd1.getSumaDeDistanciasConClientes());
	}
	
	@Test
	public void compareToTest() {
		//SetUp
		Cliente c1= new Cliente("Maria",3,9);
		Cliente c2= new Cliente("Juan",11,18);
		Cliente c3= new Cliente("Lila",10,7);
		Cliente c4= new Cliente("Kia",7,6);
		
		CentroDeDistribucion cd1= new CentroDeDistribucion("Charlone",10,20);
		CentroDeDistribucion cd2= new CentroDeDistribucion("Pena",5,10);
		
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		clientes.add(c4);
		
		//Execute
		cd1.sumaDeDistanciasConClientes(clientes);
		cd2.sumaDeDistanciasConClientes(clientes);
		
		//verify
		//cd1 es mayor que cd2
		assertTrue(cd1.compareTo(cd2)==0); 
	}
	
	@Test
	public void promedioDeDistanciasConClientes() {
		//Set Up
		Cliente c1= new Cliente("Maria",3,9);
		Cliente c2= new Cliente("Juan",11,18);
		
		CentroDeDistribucion cd1= new CentroDeDistribucion("Charlone",10,20);
		
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		clientes.add(c1);
		clientes.add(c2);
		
		double promedioEsperado=843.9031396283136;
		
		//Execute
		cd1.sumaDeDistanciasConClientes(clientes);
		cd1.promedioDeDistanciasConClientes(2);
		
		//Verify
		assertTrue(promedioEsperado==cd1.getPromedioDistanciasConClientes());
		
	}
	
	@Test
	public void equalsDistintosTest() {
		//Set Up
		CentroDeDistribucion cd1= new CentroDeDistribucion("Charlone",10,20);
		CentroDeDistribucion cd2= new CentroDeDistribucion("Pena",5,10);
		
		//Verify
		assertFalse(cd1.equals(cd2));
		
	}
	
	@Test
	public void equalsIgualesTest() {
		//Set Up
		CentroDeDistribucion cd1= new CentroDeDistribucion("Charlone",10,20);
		CentroDeDistribucion cd2= new CentroDeDistribucion("Charlone",10,20);
				
		//Verify
		assertTrue(cd1.equals(cd2));
	}

}
