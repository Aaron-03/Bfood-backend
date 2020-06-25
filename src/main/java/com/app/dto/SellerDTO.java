package com.app.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.app.entity.Solicitud;
import com.app.entity.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String ruc;
	private String razon;
	private String nombComercial;
	private String direccion;

	private String telefono;
	private String web;
	private String nomContacto;
	private String logo;
	private String email;

	private Set<Solicitud> solicitud;

	private Usuario usuario;
}
