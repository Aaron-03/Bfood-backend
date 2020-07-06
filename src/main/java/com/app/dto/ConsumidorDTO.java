package com.app.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.app.entity.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumidorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String dni;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String fechaNac;
	private String sexo;

	private Usuario usuario;

}
