package com.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Entity
public class Sucursal implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@ManyToOne
	@JoinColumn(name = "negocio_id")
	private Negocio negocio;
	
	private String direccion;
	private double lat;
	private double lng;
	private int estado;
	
	public Sucursal(Negocio negocio, String direccion, double lat, double lng, int estado) {
		super();
		this.negocio = negocio;
		this.direccion = direccion;
		this.lat = lat;
		this.lng = lng;
		this.estado = estado;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the negocio
	 */
	@JsonIgnore
	public Negocio getNegocio() {
		return negocio;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}
	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	
		
	
	
}
