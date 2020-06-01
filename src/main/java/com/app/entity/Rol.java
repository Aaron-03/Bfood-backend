package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.app.enums.RolName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Rol {

	@Id
	@GeneratedValue
	@Column(name = "rol_id")
	private int id;

	@Column(name = "rol_nombre")
	@Enumerated(EnumType.STRING)
	private RolName rolnombre;
}
