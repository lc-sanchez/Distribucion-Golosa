package logic;

import java.util.ArrayList;
import java.util.Collections;

import models.CentroDeDistribucion;
import models.Cliente;

public class Solver {
		
	private DistribucionGolosa _distribucion;
	private double _costoTotalSolucion;
	
	public Solver(DistribucionGolosa distribucion) {
		setDistribucionGolosa(distribucion);
		_costoTotalSolucion=distribucion.getCostoTotalSolucion();
		
	}
	
	// Se usa para ejecutar el algoritmo y solucionar que centros abrir
	public void resolverDistribucion() {
		if(_distribucion.getCantCentrosDeDistribucion()<_distribucion.getCantCentrosPermitidos()) {
			throw new RuntimeException("Los centros pedidos no puede ser menor a la cantidad de centros totales.");
		}
		else {
			//La solucion se va guardando en una variable centros de distribucion elegidos en distribucionGolosa
			//Recorremos el conjunto de centros ordenados de mayor a menor
			for(CentroDeDistribucion centro: centrosOrdenados()) {
				if(_distribucion.getCantCentrosDeDistribucionElegidos()< _distribucion.getCantCentrosPermitidos()) {
					_distribucion.agregarCentroElegido(centro);

					//Se va guardando el costo total de la solucion
					_costoTotalSolucion+=centro.getSumaDeDistanciasConClientes(); 
				}
				//Se desvinculan los clientes de los centros que no fueron elegidos
				else {
					centro.set_cantClientesElegidos(0);
					for(Cliente cliente : _distribucion.getClientes()) {
						if(cliente.get_centroElegido().equals(centro)) {
							_distribucion.setValoresComparativosYPromediosCentroElegido(cliente);
						}
					}
				}
			}
		}
	}
	// Se traen los centros ordenados por sort por la suma total de distancias entre el centro y los clientes
	private ArrayList<CentroDeDistribucion> centrosOrdenados() {
		//Se ordenan los centros de menor a mayor
		ArrayList<CentroDeDistribucion> centrosOrdenados= _distribucion.getCentrosDeDistribucion();
		Collections.sort(centrosOrdenados); //Implementacion de Mergesort
		return centrosOrdenados;
	}
	
	public double getCostoTotalSolucion() {
		return _costoTotalSolucion;
	}
	
	public void setDistribucionGolosa(DistribucionGolosa distribucion) {
		_distribucion=distribucion;
	}
	
}
