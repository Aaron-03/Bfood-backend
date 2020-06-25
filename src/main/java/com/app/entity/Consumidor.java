package com.app.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@Entity
public class Consumidor{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "consumidorId")
	private int id;
	@NotNull
    @Column(unique = true)
	private String dni;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String fechaNac;
	private String sexo;
	
	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "cuenta_consumidor", joinColumns = @JoinColumn(name = "consumidorId"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Usuario usuario;
		
}
