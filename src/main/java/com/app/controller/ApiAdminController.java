package com.app.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Negocio;
import com.app.entity.Producto;
import com.app.entity.Seller;
import com.app.entity.Usuario;
import com.app.jwt.JwtAutentication;
import com.app.jwt.JwtDto;
import com.app.jwt.JwtProvider;
import com.app.service.NegocioService;
import com.app.service.ProductoService;
import com.app.service.SellerService;
import com.app.service.UserDetailsImpl;
import com.app.service.UserDetailsServiceImpl;
import com.app.service.UsuarioService;
import com.app.util.DatosSunat;
import com.app.util.GobRestUtil;


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

		sellerservice.save(seller);

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

	// =================code 21/05/2020
	@Autowired
	private NegocioService negocioService;

//	@Autowired
//	private SolicitudService solicitudService;

//	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/sunat/{ruc}")
	public ResponseEntity<?> validaRuc(@PathVariable("ruc") String ruc) {
		GobRestUtil gobRestUtil = new GobRestUtil();
		DatosSunat datosSunat = gobRestUtil.consultarSunat(ruc);
		List<Negocio> lst = negocioService.read();
		for (Negocio negocio : lst) {
			if (negocio.getRuc().contains(ruc)) {
				return new ResponseEntity<>("Ruc ya esta registrado", HttpStatus.NOT_FOUND);
			}
		}

		if (datosSunat == null) {
			return new ResponseEntity<>(":( Ruc no válido", HttpStatus.NOT_FOUND);
		} else {

			return ResponseEntity.ok(datosSunat);
		}
	}

	@PostMapping(path = "/register-solicitud", consumes = "application/json", produces = "application/json")
	public String registro(@RequestBody Negocio negocio) {
//		Negocio negocio = new Negocio();
//		negocio.setRuc(val.getRuc());
//		negocio.setRazon(val.getRazon());
//		negocio.setNombComercial(val.getNombComercial());
//		negocio.setDireccion(val.getDireccion());
//		negocio.setSolicitud(val.getSolicitud());
		System.out.println(negocio);

		negocioService.save(negocio);
		
		return ":) bussines add success!";
	}

	@GetMapping(path = "/negocio", produces = "application/json")
	public List<Negocio> listarNegocio() {
		return negocioService.read();
	}

	Negocio getNegocio(String param) {
		Negocio x = null;
		List<Negocio> lst = negocioService.read();
		for (Negocio negocio : lst) {
			if (negocio.getRuc().equals(param)) {
				
				return  negocio;
			}
		}

		return x;
	}

	@GetMapping(path = "/findnegocio/{name}", produces = "application/json")
	public ResponseEntity<?> encontrarnegocio(@PathVariable("name") String name) {

		Negocio negocio = getNegocio(name);
		if (negocio == null) {
			return new ResponseEntity<>(":( negocio no encontrado", HttpStatus.NOT_FOUND);
		} else {

			return ResponseEntity.ok(negocio);
		}

	}
}
