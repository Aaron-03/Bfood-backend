package com.app.dto;




import com.app.entity.Solicitud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NegocioDTO{
	private String ruc;
	private String razon;
	private String nombComercial;
	private String direccion;
    private Solicitud solicitud;
}
