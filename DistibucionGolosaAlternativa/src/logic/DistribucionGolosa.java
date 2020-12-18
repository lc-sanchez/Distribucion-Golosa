package logic;

import java.io.IOException;
import java.util.ArrayList;

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
		_costoTotalSolucion = 0.0;
		
	}
	
	public void agregarCentroElegido(CentroDeDistribucion centro) {
		_centrosDeDistribucionElegidos.add(centro);
		_cantCentrosDeDistribucionElegidos++;
	}
	
	// Se setean los datos de clientes y centros traidos desde un archivo
	public void cargarDatos() throws ClassNotFoundException, IOException {
		//Se cargan los arrayList de la lectura
		_centrosDeDistribucion= Lectura.obtenerCentros();
		_clientes= Lectura.obtenerClientes();
		
		_cantCentrosDistribucion=_centrosDeDistribucion.size();
		_cantClientes=_clientes.size();
		
		//Se setean los centros mas cercanos a los clientes
		setearCentrosMasCercanos();

		//Se setean los valores comparativos
		actualizarValoresComparativos();
	}
	
	public void setearCentrosMasCercanos() {
		for(Cliente cliente:getClientes()) {
			setCentroMasCercano(cliente,_centrosDeDistribucion);
		}
	}
	
	public void setCentroMasCercano(Cliente cliente,ArrayList<CentroDeDistribucion> centros) {
		CentroDeDistribucion min = null;
		
		// Se recorren todos los centros por un cliente y se busca cual es el centro con menor distancia al cliente
		// Se guarda esa referencia a ese centro en una variable min
		for(int i=0;i<centros.size();i++) {
			
			// Se calcula la distancia del centro actual con el cliente y se la guarda en el centro
			centros.get(i).set_distanciaConClienteTemporal
				(centros.get(i).calcularDistanciaConCliente(cliente));
			
			// Se pregunta si ya se guardo al menos una vez en el min alguna referencia para comparar
			if(min==null) {
				min = centros.get(i);
			}
			
			// En caso de que si se haya guardado, se procede a comparar si el centro actual 
			// tiene menor distancia al cliente que el centro guardado en min
			// En caso de que si sea menor, se reemplaza la referencia de min al centro actual
			else if(centros.get(i).get_distanciaConClienteTemporal()<min.get_distanciaConClienteTemporal()) {
				min = centros.get(i);
			}
		}
		// Se vincula el cliente con el centro que este a menor distancia
		cliente.set_centroElegido(min);

		// Se incrementa un contador de clientes relacionados en el centro de menor distancia
		// Para luego elejir que centro abrir
		min.incrementarCantClientes();


	}

	public void actualizarValoresComparativos() {
		for(CentroDeDistribucion centro : getCentrosDeDistribucion()) {
			
			centro.sumaDeDistanciasConClientes(_clientes);
			centro.promedioDeDistanciasConClientes();
		}
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
		setCentroMasCercano(cliente,_centrosDeDistribucion);
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
