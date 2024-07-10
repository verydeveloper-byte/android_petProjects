package com.android.prprovincias;


public class Provincia {
	private String nombre;
	private String descripcion;
	private int imagen;

	public Provincia(String nombre, String descripcion, int imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getImagen() {
		return imagen;
	}

	public void setImagen(int imagen) {
		this.imagen = imagen;
	}


}
