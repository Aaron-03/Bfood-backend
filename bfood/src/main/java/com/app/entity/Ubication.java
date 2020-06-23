package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "localizacion")
public class Ubication {

	@Id
	private int id_loc;
	private int id_ven;
	private String latitud, longitud, ruc_ven; 
	
	public Ubication() {
		super();
	}	
	
	public Ubication(int id_loc, int id_ven, String latitud, String longitud, String ruc_ven) {
		super();
		this.id_loc = id_loc;
		this.id_ven = id_ven;
		this.latitud = latitud;
		this.longitud = longitud;
		this.ruc_ven = ruc_ven;
	}

	public int getId_loc() {
		return id_loc;
	}

	public void setId_loc(int id_loc) {
		this.id_loc = id_loc;
	}

	public int getId_ven() {
		return id_ven;
	}

	public void setId_ven(int id_ven) {
		this.id_ven = id_ven;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getRuc_ven() {
		return ruc_ven;
	}

	public void setRuc_ven(String ruc_ven) {
		this.ruc_ven = ruc_ven;
	}	
}
