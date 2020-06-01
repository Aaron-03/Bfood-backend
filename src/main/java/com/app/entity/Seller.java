package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendedor")
public class Seller {

	/*
	 * {
			"user_id": 2,
			"nom_corto": "Cualquiera",
			"razon_ven": "ECLIPSE SA",
			"ruc": "20136265474",
			"direccion": "direccion de prueba",
			"tele_ven": "987654321",
			"pag_web": "www.eclipse.com",
			"contacto": "321654987",
			"logo": "img.png",
			"email": "eclipse@gmail.com"
		}
	 * 
	 * */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_ven;
	private int user_id;
	private String nom_corto, razon_ven, ruc_ven, direccion, tele_ven, pag_web, contacto, logo, email;

	public Seller() {
		super();
	}	

	public Seller(int id_ven, int user_id, String nom_corto, String razon_ven, String ruc_ven, String direccion,
			String tele_ven, String pag_web, String contacto, String logo, String email) {
		super();
		this.id_ven = id_ven;
		this.user_id = user_id;
		this.nom_corto = nom_corto;
		this.razon_ven = razon_ven;
		this.ruc_ven = ruc_ven;
		this.direccion = direccion;
		this.tele_ven = tele_ven;
		this.pag_web = pag_web;
		this.contacto = contacto;
		this.logo = logo;
		this.email = email;
	}

	public int getId_ven() {
		return id_ven;
	}
	public void setId_ven(int id_ven) {
		this.id_ven = id_ven;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getNom_corto() {
		return nom_corto;
	}
	public void setNom_corto(String nom_corto) {
		this.nom_corto = nom_corto;
	}
	public String getRazon_ven() {
		return razon_ven;
	}
	public void setRazon_ven(String razon_ven) {
		this.razon_ven = razon_ven;
	}
	public String getRuc_ven() {
		return ruc_ven;
	}
	public void setRuc_ven(String ruc_ven) {
		this.ruc_ven = ruc_ven;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTele_ven() {
		return tele_ven;
	}
	public void setTele_ven(String tele_ven) {
		this.tele_ven = tele_ven;
	}
	public String getPag_web() {
		return pag_web;
	}
	public void setPag_web(String pag_web) {
		this.pag_web = pag_web;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}		
}
