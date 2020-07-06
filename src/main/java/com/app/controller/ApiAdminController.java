package com.app.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.dto.ConsumidorDTO;
import com.app.dto.LoginConsumidor;
import com.app.dto.LoginVendedor;
import com.app.dto.SellerJson;
import com.app.dto.SellerDTO;
import com.app.entity.Consumidor;
import com.app.entity.Rol;
import com.app.entity.Seller;
import com.app.entity.Usuario;
import com.app.enums.RolNombre;
import com.app.jwts.JwtDto;
import com.app.jwts.JwtProvider;
import com.app.service.ConsumidorService;
import com.app.service.SellerService;
import com.app.service.UsuarioService;
import com.app.util.DatosSunat;
import com.app.util.GobRestUtil;
import com.app.util.Mensaje;

@RestController
@RequestMapping("/bfood/users")
public class ApiAdminController {

	@Autowired
	private SellerService sellerservice;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncode;

	@Autowired
	private ConsumidorService consumidorService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	// start method register seller account
	// ========================================================================================

	@PostMapping("/registrar-solicitud")
	public ResponseEntity<?> registrarVendedor(@Valid @RequestBody SellerDTO dto) {
		try {

			if (StringUtils.isBlank(dto.getRuc()))
				return new ResponseEntity<>(new Mensaje(false, "ingrese ruc"), HttpStatus.BAD_REQUEST);

			if (valExistSeller(dto.getRuc()))
				return new ResponseEntity<>(new Mensaje(false, "el ruc ya fue registrado"), HttpStatus.BAD_REQUEST);

			if (usuarioService.existsByNombreUsuario(dto.getUsuario().getUsername()))
				return new ResponseEntity<>(new Mensaje(false, "nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);

			if (usuarioService.existsByEmail(dto.getUsuario().getEmail()))
				return new ResponseEntity<>(new Mensaje(false, "el correo ya esta registrado"), HttpStatus.BAD_REQUEST);

			String pwd = dto.getUsuario().getPassword();
			String passEncryptado = bCryptPasswordEncode.encode(pwd);

			Seller seller = new Seller();
			seller.setRuc(dto.getRuc());
			seller.setRazon(dto.getRazon());
			seller.setNombComercial(dto.getNombComercial());
			seller.setDireccion(dto.getDireccion());

			seller.setTelefono(dto.getTelefono());
			seller.setWeb(dto.getWeb());
			seller.setNomContacto(dto.getNomContacto());
			seller.setLogo(dto.getLogo());

			seller.setSolicitud(dto.getSolicitud());

			Usuario usuario = new Usuario();
			usuario.setUsername(dto.getUsuario().getUsername());
			usuario.setEmail(dto.getUsuario().getEmail());
			usuario.setPassword(passEncryptado);
			usuario.setEstado(dto.getUsuario().getEstado());
			usuario.setRoles(dto.getUsuario().getRoles());
			seller.setUsuario(usuario);

			sellerservice.save(seller);
			return new ResponseEntity<>(new Mensaje(true, "su solicitud fue registrada"), HttpStatus.OK);

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	boolean valExistSeller(String param) {
		List<Seller> lst = sellerservice.read();
		for (Seller seller : lst) {
			if (seller.getRuc().equals(param)) {
				return true;
			}
		}
		return false;
	}

	// end method register seller account
	// ========================================================================================

	// start method register customer account
	// ========================================================================================

	@PostMapping("/registrar-cuenta")
	public ResponseEntity<?> registrarConsumidor(@Valid @RequestBody ConsumidorDTO dto) {
		try {
			if (StringUtils.isBlank(dto.getDni()))
				return new ResponseEntity<>(new Mensaje(false, "ingrese dni"), HttpStatus.BAD_REQUEST);

			if (valExistdni(dto.getDni()))
				return new ResponseEntity<>(new Mensaje(false, "el dni ya fue registrado"), HttpStatus.BAD_REQUEST);

			if (usuarioService.existsByNombreUsuario(dto.getUsuario().getUsername()))
				return new ResponseEntity<>(new Mensaje(false, "el usuario ya existe"), HttpStatus.BAD_REQUEST);

			if (usuarioService.existsByEmail(dto.getUsuario().getEmail()))
				return new ResponseEntity<>(new Mensaje(false, "el correo esta siendo usado"), HttpStatus.BAD_REQUEST);

			String pwd = dto.getUsuario().getPassword();
			String passEncryptado = bCryptPasswordEncode.encode(pwd);

			Consumidor consumidor = new Consumidor();
			consumidor.setDni(dto.getDni());
			consumidor.setNombres(dto.getNombres());
			consumidor.setApellidos(dto.getApellidos());
			consumidor.setTelefono(dto.getTelefono());
			consumidor.setFechaNac(dto.getFechaNac());
			consumidor.setSexo(dto.getSexo());

			Usuario usuario = new Usuario();
			usuario.setUsername(dto.getUsuario().getUsername());
			usuario.setEmail(dto.getUsuario().getEmail());
			usuario.setPassword(passEncryptado);
			usuario.setEstado(dto.getUsuario().getEstado());
			usuario.setRoles(dto.getUsuario().getRoles());

			consumidor.setUsuario(usuario);

			consumidorService.save(consumidor);
			return new ResponseEntity<>(new Mensaje(true, "se registro correctamente"), HttpStatus.OK);

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	boolean valExistdni(String param) {
		List<Consumidor> list = consumidorService.read();
		for (Consumidor consumidor : list) {
			if (consumidor.getDni().equals(param)) {
				return true;
			}
		}
		return false;
	}

	// end of method register customer account
	// ========================================================================================

	// start method update customer account data
	// ========================================================================================

	@PutMapping("/actualizar-info/{id}")
	public ResponseEntity<?> actualizarCliente(@PathVariable("id") int id, @RequestBody ConsumidorDTO conDto) {
		Consumidor consumidor = consumidorService.get(id);
		if (consumidor == null)
			return new ResponseEntity<>(new Mensaje(false, "cuenta no registrada"), HttpStatus.NOT_FOUND);
		if (usuarioService.existsByNombreUsuario(conDto.getUsuario().getUsername())
				&& consumidorService.get(id) != null)
			return new ResponseEntity<>(new Mensaje(false, "el nombre de usaurio ya existe"), HttpStatus.BAD_REQUEST);

		String pwd = conDto.getUsuario().getPassword();
		String passEncryptado = bCryptPasswordEncode.encode(pwd);

		Usuario usuario = new Usuario();
		usuario.setEmail(conDto.getUsuario().getEmail());
		usuario.setPassword(passEncryptado);

		consumidor.setUsuario(usuario);
		consumidorService.save(consumidor);
		return new ResponseEntity<>(new Mensaje(true, "se actualizaron sus datos"), HttpStatus.OK);
	}

	// end method update customer account data
	// ========================================================================================

	// get token for access
	// ========================================================================================
	@PostMapping("/iniciar-sesion")
	public ResponseEntity<?> sesionCliente(@Valid @RequestBody LoginConsumidor usu) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usu.getUsername(), usu.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}

	@PostMapping("/acceso-cpanel")
	public ResponseEntity<?> sesionVendedor(@Valid @RequestBody LoginVendedor usu) {

		String rucIn = usu.getRuc();
		Usuario usuario = usuarioService.obtenerUsuario(rucIn);

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usu.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);

	}

	@Autowired
	private RestTemplate restTemplate;

	// validate
	// ========================================================================================
	@GetMapping("/sunatopcone/{ruc}")
	public ResponseEntity<?> validaRuc(@PathVariable("ruc") String ruc) {
		GobRestUtil gobRestUtil = new GobRestUtil();
		DatosSunat datosSunat = gobRestUtil.consultarSunat(ruc);

		List<Seller> lst = sellerservice.read();
		for (Seller seller : lst) {
			if (seller.getRuc().equals(ruc)) {
				return new ResponseEntity<>(new Mensaje(false, "Ruc ya esta registrado"), HttpStatus.NOT_FOUND);
			}
		}

		if (datosSunat == null) {
			return new ResponseEntity<>(new Mensaje(false, ":( Ruc no válido"), HttpStatus.NOT_FOUND);
		} else {

			return ResponseEntity.ok(datosSunat);
		}
	}

	@GetMapping(path = "/sunatopctwo/{ruc}", produces = "application/json")
	public ResponseEntity<?> consultarRuc(@PathVariable("ruc") String ruc) {
		ResponseEntity<SellerJson> response = restTemplate.getForEntity("https://api.sunat.cloud/ruc/" + ruc,
				SellerJson.class);
		SellerJson sellerJson = response.getBody();

		List<Seller> lst = sellerservice.read();
		for (Seller seller : lst) {
			if (seller.getRuc().equals(ruc)) {
				return new ResponseEntity<>(new Mensaje(false, "Ruc ya esta registrado"), HttpStatus.NOT_FOUND);
			}
		}
		if (sellerJson == null) {
			return new ResponseEntity<>(new Mensaje(false, ":( Ruc no válido"), HttpStatus.NOT_FOUND);

		} else {
			return ResponseEntity.ok(sellerJson);
		}
	}
}
