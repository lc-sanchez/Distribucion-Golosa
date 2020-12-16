package models;

import java.io.Serializable;

public class Cliente implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String _nombre;
	private double _latitud;
	private double _longitud;
	private CentroDeDistribucion _centroElegido;
	
	public Cliente (String nombre, double latitud, double longitud) {
		setNombre(nombre);
		setLatitud(latitud);
		setLongitud(longitud);
	}
	
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
	
	public CentroDeDistribucion get_centroElegido() {
		return _centroElegido;
	}

	public void set_centroElegido(CentroDeDistribucion _centroElegido) {
		this._centroElegido = _centroElegido;
	}
	
	@Override
	public String toString() {
		return "Cliente [nombre=" + getNombre() + ", latitud=" + getLatitud() + ", longitud=" + getLongitud() + "]";
	}
	
}
