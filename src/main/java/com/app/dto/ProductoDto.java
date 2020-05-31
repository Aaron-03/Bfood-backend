package com.app.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ProductoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank
	private String nombre;
	@Min(0)
	private Float precio;

}
