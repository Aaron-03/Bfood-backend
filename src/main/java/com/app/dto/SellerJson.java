package com.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class SellerJson {
	private String ruc;
	private String razon_social;
	private String ciiu;
	private String contribuyente_condicion;
	private String contribuyente_tipo;
	private String contribuyente_estado;
	private String nombre_comercial;
	private String domicilio_fiscal;

}
