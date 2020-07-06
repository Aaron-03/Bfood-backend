package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginConsumidor {

	@NotBlank
    private String username;
    @NotBlank
    private String password;

}
