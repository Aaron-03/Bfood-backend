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
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Negocio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "negocioId")
	private int id;
	@Column(name = "negocioRuc")
	private String ruc;
	private String razon;
	private String nombComercial;
	private String direccion;
	
	@NotNull
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "solicitud_negocio", joinColumns = @JoinColumn(name = "negocioId"),
    inverseJoinColumns = @JoinColumn(name = "solicitud_id"))
    private Set<Solicitud> solicitud;
	

}
