package com.app.entity;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Column(name = "user_name", length = 50, unique = true)
	@NotEmpty(message = "Por favor proporcione su nombre")
	private String username;

	@Column(name = "email")
	@Email(message = "Proporcione un correo electrónico válido")
	@NotEmpty(message = "Por favor proporcione un correo electrónico")
	private String email;

	@Column(name = "pass_word")
	@Length(min = 5, message = "Su contraseña debe tener al menos 5 caracteres.")
	@NotEmpty(message = "Por favor proporcione su contraseña")
	private String password;

	@Column(name = "estado")
	private int estado;
	

	@NotNull
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles;

	

	

}
