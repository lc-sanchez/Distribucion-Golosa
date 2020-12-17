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
		
		// Se recorren todos los centros por un cliente y se busca cual es el centro con menor distancia al cliente
		// Se guarda esa referencia a ese centro en una variable min
		for(int i=0;i<_cantCentrosDistribucion;i++) {
			
			// Se calcula la distancia del centro actual con el cliente y se la guarda en el centro
			_centrosDeDistribucion.get(i).set_distanciaConClienteTemporal
				(_centrosDeDistribucion.get(i).calcularDistanciaConCliente(cliente));
			
			// Se pregunta si ya se guardo al menos una vez en el min alguna referencia para comparar
			if(min==null) {
				min = _centrosDeDistribucion.get(i);
			}
			
			// En caso de que si se haya guardado, se procede a comparar si el centro actual 
			// tiene menor distancia al cliente que el centro guardado en min
			// En caso de que si sea menor, se reemplaza la referencia de min al centro actual
			else if(_centrosDeDistribucion.get(i).get_distanciaConClienteTemporal()<min.get_distanciaConClienteTemporal()) {
				min = _centrosDeDistribucion.get(i);
			}
			
			// Se calcula la suma de distancias de los clientes hasta el momento con el centro 
			// y el promedio de distancias de todos los clientes con el centro
			// Se guardan los valores en el centro desde donde se llaman los metodos
			_centrosDeDistribucion.get(i).sumaDeDistanciasConClientes(_clientes);
			_centrosDeDistribucion.get(i).promedioDeDistanciasConClientes(_cantClientes);
		}

		// Se vincula el cliente con el centro que este a menor distancia
		cliente.set_centroElegido(min);
		
		// Se incrementa un contador de clientes relacionados en el centro de menor distancia
		// Para luego elejir que centro abrir
		min.set_cantClientesElegidos(min.get_cantClientesElegidos()+1);
	}
	
	// Lo mismo que el metodo anterior, pero para clientes que se desvinculan de un centro que se decidio no abrir
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
