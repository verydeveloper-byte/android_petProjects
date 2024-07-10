package com.android.prprovinciasapimaps.provincia;

import android.os.Parcel;
import android.os.Parcelable;

public class Provincia implements Parcelable {
	private String nombre;
	private String descripcion;
	private int imagen;
	private double[] coord;

	public Provincia(String nombre, String descripcion, int imagen, double[] coord) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.coord = coord;
	}
	
	/*
	 * Ctor para crear el objeto a partir de un Parcelable.
	 * En el caso de que haya mas de un campo, se recuperan en el 
	 * mismo orden que se introducen en el writeParcel (FIFO).
	 */
	public Provincia(Parcel in) {
		this.nombre = in.readString();
		this.descripcion = in.readString();
		this.imagen = in.readInt();
		coord = in.createDoubleArray();
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
	
	public void setCoordenadas(double[] coord) {
		this.coord[0] = coord[0];
		this.coord[1] = coord[1];
	}
	
	public double[] getCoordenadas() {
		return coord;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	/*
	 * Guardar el estado del objeto Provincia en el parcel dest, 
	 * como no hay pares clave-valor, el orden de escritura es
	 * por convencion el mismo que el orden de declaracion.
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nombre);
		dest.writeString(descripcion);
		dest.writeInt(imagen);
		dest.writeDoubleArray(new double[] {coord[0], coord[1]});
		// dest.writeStringArray(new String[] {this.nombre, this.descripcion, String.valueOf(this.imagen)});
	}
	
	/*
	 * Hay que crear siempre un objeto CREATOR mediante Parcelable.Creator
	 * para poder regenerar instancias de la clase Parcelable Provincia 
	 * desde otro Parcel. Es necesario para recrear objetos en otros 
	 * Parcelables distintos (como Bundle).
	 */
	public static final Parcelable.Creator<Provincia> CREATOR = new Parcelable.Creator<Provincia>() {

		@Override
		public Provincia createFromParcel(Parcel source) {
			return new Provincia(source);
		}

		@Override
		public Provincia[] newArray(int size) {
			return new Provincia[size];
		}
		
	};
}
