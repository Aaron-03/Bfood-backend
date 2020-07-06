package com.app.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.app.enums.SolicitudEstado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SolicitudDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fecha;
	@Enumerated(EnumType.STRING)
	private SolicitudEstado estado;
}
