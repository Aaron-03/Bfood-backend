package com.app.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vendedor")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idvendedor")
	private int id;
	private int user_id;
	private String nom_corto, razon_ven, ruc_ven, direccion, tele_ven, pag_web, contacto, logo, email;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy = "vendedor")
	private List<Producto> productos;


//	@ManyToMany(mappedBy="vendedor")
//	private List<Producto> productos;
	
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
}
