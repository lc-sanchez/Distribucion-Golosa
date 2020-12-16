package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import models.CentroDeDistribucion;
import models.Cliente;
import archivos.Lectura;

//Nuestra Mochila
public class DistribucionGolosa {
	
	private int _cantClientes;
	private int _cantCentrosDistribucion;
	private int _cantCentrosPermitidos;
	
	private ArrayList<CentroDeDistribucion> _centrosDeDistribucion;
	private ArrayList<Cliente> _clientes;
	
	private ArrayList<CentroDeDistribucion> _centrosDeDistribucionElegidos;
	private int _cantCentrosDeDistribucionElegidos;
	private double _costoTotalSolucion;
	
	
	public DistribucionGolosa(int cantCentrosPermitidos) throws ClassNotFoundException, IOException {
		setCantCentrosPermitidos(cantCentrosPermitidos);
		
		//Se cargan los arrayList de la lectura
		_centrosDeDistribucion= new ArrayList<CentroDeDistribucion>();
		_clientes= new ArrayList<Cliente>();
		
		_cantCentrosDistribucion=_centrosDeDistribucion.size();
		_cantClientes=_clientes.size();
		
		_cantCentrosDeDistribucionElegidos=0;
		
		_centrosDeDistribucionElegidos= new ArrayList<CentroDeDistribucion>();
		
	}
	
	public void agregarCentroElegido(CentroDeDistribucion centro) {
		_centrosDeDistribucionElegidos.add(centro);
		_cantCentrosDeDistribucionElegidos++;
	}
	
	// Se setean los datos de clientes y centros traidos desde un archivo
	@SuppressWarnings("unused")
	public void cargarDatos() throws ClassNotFoundException, IOException {
		//Se cargan los arrayList de la lectura
		_centrosDeDistribucion= Lectura.obtenerCentros();
		_clientes= Lectura.obtenerClientes();
		
		_cantCentrosDistribucion=_centrosDeDistribucion.size();
		_cantClientes=_clientes.size();
		
		//Se setean los valores comparativos en los centros de distribucion
		for(Cliente cliente:getClientes()) {
			setValoresComparativosYPromedios(cliente);
		}
	}
	
	private void setValoresComparativosYPromedios(Cliente cliente) {
		CentroDeDistribucion min = null;
		
		for(int i=0;i<_cantCentrosDistribucion;i++) {
			_centrosDeDistribucion.get(i).set_distanciaConClienteTemporal
				(_centrosDeDistribucion.get(i).calcularDistanciaConCliente(cliente));
			
			if(i==0) {
				min = _centrosDeDistribucion.get(i);
			}
			else if(_centrosDeDistribucion.get(i).get_distanciaConClienteTemporal()<min.get_distanciaConClienteTemporal()) {
				min = _centrosDeDistribucion.get(i);
			}
			
			_centrosDeDistribucion.get(i).sumaDeDistanciasConClientes(_clientes);
			_centrosDeDistribucion.get(i).promedioDeDistanciasConClientes(_cantClientes);
		}

		cliente.set_centroElegido(min);
		
		min.set_cantClientesElegidos(min.get_cantClientesElegidos()+1);
	}
	
	public void setValoresComparativosYPromediosSinElCentro(Cliente cliente) {
		CentroDeDistribucion min = null;
		
		for(int i=0;i<_cantCentrosDeDistribucionElegidos;i++) {
			
			_centrosDeDistribucionElegidos.get(i).set_distanciaConClienteTemporal
			
				(_centrosDeDistribucionElegidos.get(i).calcularDistanciaConCliente(cliente));
			
			if(i==0) {
				min = _centrosDeDistribucionElegidos.get(i);
			}
			else if(_centrosDeDistribucionElegidos.get(i).get_distanciaConClienteTemporal()<min.get_distanciaConClienteTemporal()) {
				min = _centrosDeDistribucionElegidos.get(i);
			}
		}

		cliente.set_centroElegido(min);
		
		min.set_cantClientesElegidos(min.get_cantClientesElegidos()+1);
	}
	
	
	
	public void resolverDistribucion() {
		Solver solver= new Solver(this);
		solver.resolverDistribucion();
		
		_costoTotalSolucion=solver.getCostoTotalSolucion();
	}
	

	//:::::::::::::::::::::::::: FUNCIONES AUXILIARES PARA TESTEAR::::::::::::::::::::::::::::::::::::::::::::::
	public void agregarCliente(Cliente cliente) {
		_clientes.add(cliente);
		_cantClientes++;
		setValoresComparativosYPromedios(cliente);
	}
	
	public void agregarCentroDeDistribucion(CentroDeDistribucion centro) {
		_centrosDeDistribucion.add(centro);
		_cantCentrosDistribucion++;
	}
	
	//::::::::::::::::::::::::::::::::::::::::  Getters y Setters ::::::::::::::::::::::::::::::::::::::::::::
	public double getPromedioDeCentro(CentroDeDistribucion centro) {
		return centro.getPromedioDistanciasConClientes();
	}
	
	
	public double getCostoTotalSolucion() {
		return _costoTotalSolucion;
	}
	
	
	public int getCantClientes() {
		return _cantClientes;
	}
	
	public int getCantCentrosDeDistribucion() {
		return _cantCentrosDistribucion;
	}
	
	public int getCantCentrosPermitidos() {
		return _cantCentrosPermitidos;
	}
	
	public int getCantCentrosDeDistribucionElegidos() {
		return _cantCentrosDeDistribucionElegidos;
	}
	
	@SuppressWarnings("unchecked")//No se pueden modificar 
	public ArrayList<CentroDeDistribucion> getCentrosDeDistribucion(){
		return (ArrayList<CentroDeDistribucion>) _centrosDeDistribucion.clone();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Cliente> getClientes(){//No se pueden modificar
		return (ArrayList<Cliente>) _clientes.clone();
	}
	
	public ArrayList<CentroDeDistribucion> getCentrosDeDistribucionElegidos(){//Si se pueden modificar
		return _centrosDeDistribucionElegidos;
	}
	
	public void setCentrosDeDistribucion(ArrayList<CentroDeDistribucion> centros) {
		_centrosDeDistribucion=centros;
	}
	
	public void setClientes(ArrayList<Cliente> clientes) {
		_clientes=clientes;
	}
	
	public void setCantCentrosPermitidos(int cantCentrosPermitidos) {
		_cantCentrosPermitidos=cantCentrosPermitidos;
	}
	
}
