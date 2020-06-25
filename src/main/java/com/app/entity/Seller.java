package com.app.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Seller implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendedorId")
	private int id;
	private String ruc;
	private String razon;
	private String nombComercial;
	private String direccion;
	
	
	private String telefono;
	private String web;
	private String nomContacto;
	private String logo;
	private String email;
	
	@NotNull
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "solicitud_vendedor", joinColumns = @JoinColumn(name = "vendedorId"),
    inverseJoinColumns = @JoinColumn(name = "solicitud_id"))
    private Set<Solicitud> solicitud;
	
	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "cuenta_vendedor", joinColumns = @JoinColumn(name = "vendedorId"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Usuario usuario;	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor", fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Producto> products;


	
}
