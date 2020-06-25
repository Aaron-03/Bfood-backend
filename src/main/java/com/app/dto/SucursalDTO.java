package com.app.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.app.entity.Seller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SucursalDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Seller seller;
	@NotBlank
	private String nombre;
	private String direccion;
	private double lat;
	private double lng;
	private int estado;
}
