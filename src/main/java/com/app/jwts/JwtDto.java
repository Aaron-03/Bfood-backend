package com.app.jwts;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {

	private String token;
	private String bearer = "Bearer";
	private String username;
	private int id;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtDto(String token, String username, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.username = username;
		this.authorities = authorities;
	}
}
