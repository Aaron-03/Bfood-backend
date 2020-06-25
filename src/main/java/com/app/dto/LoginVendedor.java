package com.app.dto;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVendedor {
	    @NotBlank
	    private String ruc;
	    @NotBlank
	    private String password;

}
