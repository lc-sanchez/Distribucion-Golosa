package models;

import java.io.Serializable;
import java.util.ArrayList;

public class CentroDeDistribucion implements Comparable<CentroDeDistribucion>, Serializable {

	private static final long serialVersionUID = 1L;
	private String _nombre;
	private double _latitud;
	private double _longitud;
	private double _sumaDeDistanciasConClientes;
	private double _promedioDistanciaConClientes;
	private double _distanciaConClienteTemporal;
	private int _cantClientesElegidos;
	
	public CentroDeDistribucion(String nombre, double latitud, double longitud) {
		setNombre(nombre);
		setLatitud(latitud);
		setLongitud(longitud);
		set_cantClientesElegidos(0);
	}

	public double calcularDistanciaConCliente(Cliente cliente) {
		int radio_Terrestre= 6371;
		
		double dLat= Math.toRadians((cliente.getLatitud()-getLatitud()));
		double dLong= Math.toRadians(cliente.getLongitud()-getLongitud());
		
		double radianLat1= Math.toRadians(getLatitud());
		double radianLat2=Math.toRadians(cliente.getLatitud());
		
		double a= haversin(dLat)+Math.cos(radianLat1)* Math.cos(radianLat2)* haversin(dLong);
		double c= 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return radio_Terrestre *c; //Distancia en km
		
	}
	
	 private  double haversin(double val) 
	 {
	    return Math.pow(Math.sin(val / 2), 2);
	 }
	 
	public void sumaDeDistanciasConClientes(ArrayList<Cliente> clientes) {
		if(clientes==null) {
			throw new RuntimeException("Array de clientes vacio");
		}
		else {
			double sumaTotalDistancias=0.0;
			for(int i=0;i<clientes.size();i++) {
				sumaTotalDistancias=sumaTotalDistancias+ calcularDistanciaConCliente(clientes.get(i));
			}
			_sumaDeDistanciasConClientes=sumaTotalDistancias;
		}
			
	}
	
	public void promedioDeDistanciasConClientes(int cantidadClientes) {
		_promedioDistanciaConClientes=_sumaDeDistanciasConClientes/cantidadClientes;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CentroDeDistribucion))
			return false;
		CentroDeDistribucion other = (CentroDeDistribucion) obj;
		if (_latitud != other._latitud)
			return false;
		if (_longitud != other._longitud)
			return false;
		if (_nombre == null) {
			if (other._nombre != null)
				return false;
		} else if (!_nombre.equals(other._nombre))
			return false;
		if (Double.doubleToLongBits(_sumaDeDistanciasConClientes) != Double
				.doubleToLongBits(other._sumaDeDistanciasConClientes))
			return false;
		return true;
	}

	@Override
	public int compareTo(CentroDeDistribucion otroCentro) {
		//Se comparan los centros de distribucion por la suma total de las distancias con todos los clientes
		//Utilizando la formula de Haversine para poder calcular la distancia en linea recta
		//Como esta en double las distancias, lo tengo que castear en int
		if(get_cantClientesElegidos()<otroCentro.get_cantClientesElegidos()) {
			return 1;
		}
		else if(get_cantClientesElegidos()>otroCentro.get_cantClientesElegidos()) {
			return -1;
		}
		else {
			return 0;
		}
		
		
	}
	
	@Override
	public String toString() {
		return "[nombre=" + _nombre + ", sumaDeDistanciasConClientes="
				+ _sumaDeDistanciasConClientes + "]";
	}
	
	//:::::::::::::::::::::::::::: Setters y Getters :::::::::::::::::::::::::::::::::::::::
	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		if(nombre==null || nombre=="") {
			throw new RuntimeException("Nombre null o vacio no permitido");
		}else {
			_nombre = nombre;
		}
		
	}

	public double getLatitud() {
		return _latitud;
	}

	public void setLatitud(double latitud) {
		_latitud = latitud;
	}

	public double getLongitud() {
		return _longitud;
	}

	public void setLongitud(double longitud) {
		_longitud = longitud;
	}
	
	public double getSumaDeDistanciasConClientes() {
		return _sumaDeDistanciasConClientes;
	}
	
	public double getPromedioDistanciasConClientes() {
		return _promedioDistanciaConClientes;
	}

	public void calcularDistanciaConCliente() {
		// TODO Auto-generated method stub
		
	}

	public double get_distanciaConClienteTemporal() {
		return _distanciaConClienteTemporal;
	}

	public void set_distanciaConClienteTemporal(double _distanciaConClienteTemporal) {
		this._distanciaConClienteTemporal = _distanciaConClienteTemporal;
	}

	public int get_cantClientesElegidos() {
		return _cantClientesElegidos;
	}

	public void set_cantClientesElegidos(int _cantClientesElegidos) {
		this._cantClientesElegidos = _cantClientesElegidos;
	}
	

	
}
