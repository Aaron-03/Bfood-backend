package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Seller;
import com.app.entity.Usuario;
import com.app.jwt.JwtAutentication;
import com.app.jwt.JwtDto;
import com.app.jwt.JwtProvider;
import com.app.service.SellerService;
import com.app.service.UserDetailsImpl;
import com.app.service.UserDetailsServiceImpl;
import com.app.service.UsuarioService;

@RestController
@RequestMapping("/bfood")
public class ApiAdminController {

	@Autowired
	private SellerService sellerservice;

	@GetMapping("/prueba")
	public String miPage() {
		return "Hola Mundo";
	}

	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public String registerSeller(@RequestBody Seller seller) {

		sellerservice.registerSeller(seller);

		return "Registro exitoso";
	}

	
	// ===================== start rest Login resource
	@Autowired
	private UsuarioService usuarioServicio;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncode;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtProvider jwtProvider;

	// ===================== end rest Login resource

	
	@PostMapping(path = "/register-acount", consumes = "application/json", produces = "application/json")
	public String crearcuenta(@Valid @RequestBody Usuario usuario) {
		String pwd = usuario.getPassword();
		String encriptar = bCryptPasswordEncode.encode(pwd);
		usuario.setPassword(encriptar);
		usuarioServicio.save(usuario);
		return ":) user add success!";
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody JwtAutentication loginUsuario,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}

	@GetMapping("/details")
	public UserDetailsImpl getAuthenticatedUser(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtProvider.getNombreUsuarioFromToken(token);
		UserDetailsImpl user = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(username);
		return user;
	}
}
