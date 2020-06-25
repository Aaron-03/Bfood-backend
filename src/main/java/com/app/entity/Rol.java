package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.app.enums.RolNombre;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_id")
	private int id;

	@Column(name = "rol_nombre")
	@Enumerated(EnumType.STRING)
	private RolNombre rolNombre;

	
	
	
}
