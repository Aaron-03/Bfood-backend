package com.app.jwts;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.entity.Usuario;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUsuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUsuario(String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {

		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static JwtUsuario build(Usuario usuario) {
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
		return new JwtUsuario(usuario.getUsername(), usuario.getEmail(), usuario.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getEmail() {
		return email;
	}
}
