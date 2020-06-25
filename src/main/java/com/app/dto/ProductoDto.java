package com.app.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.app.entity.Categoria;
import com.app.entity.Seller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank
	private String nombre;
	@NotBlank
	private String descripcion;
	@Min(0)
	private Float precio;

	private int position;
	private Categoria categoria;
	private int stock;
	private String img;
	private boolean publish;
	private String status;
	private Seller vendedor;
}
