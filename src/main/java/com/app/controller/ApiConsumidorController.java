package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Consumidor;
import com.app.jwt.JwtDto;
import com.app.jwt.JwtProvider;
import com.app.service.ConsumidorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/bfood/consumidor/")
public class ApiConsumidorController {

	private ObjectMapper obm = new ObjectMapper();
	
	// Transforma una lista a formato JSON y retorna un JSONArray
	private JSONArray getObjectsJSON(List<?> listItems) throws JsonProcessingException {
		
		String jsonstring = obm.writeValueAsString(listItems);
    	JSONArray jrr = new JSONArray(jsonstring);
		return jrr;
	}

	@Autowired
	private ConsumidorService consumidorService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	
	@PostMapping(path = "/login-consumidor", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String loginConsumidor(@RequestBody String customer) {
		
		
		return "";
	}

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public String addConsumidor(@Valid @RequestBody Consumidor consumidor) {

    	consumidorService.save(consumidor);
    	
    	JSONObject json = new JSONObject();
    	json.put("ok", "true");
    	json.put("message", "Registrado Correctamente");
    	
    	return json.toString();
    }

    @PutMapping(path = "/edit", consumes = "application/json", produces = "application/json")
	public String updConsumidor(@Valid @RequestBody Consumidor consumidor) {
    	
    	JSONObject json = new JSONObject();

    	Consumidor c = consumidorService.get(consumidor.getId());
    	
    	if(c == null) {
    		json.put("ok", false);
        	json.put("message", "Consumidor no existe");
    		return json.toString();
    	}

    	c = consumidor;

    	consumidorService.save(c);

    	
    	json.put("ok", true);
    	json.put("message", "Actualizado Correctamente");
    	
    	return json.toString();
    }

    @GetMapping(path = "/list", produces = "application/json")
	public String lstConsumidores() throws JsonProcessingException {

    	List<Consumidor> customers = consumidorService.read();

    	JSONArray jrr = getObjectsJSON(customers);

    	JSONObject json = new JSONObject();
    	json.put("ok", "true");
    	json.put("message", "Registrado Correctamente");
    	json.put("customers", jrr);

    	return json.toString();
    }

    @DeleteMapping(path = "/dlt", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String dltConsumidor(String idconsumidor) {

    	System.out.println(idconsumidor);

    	JSONObject json = new JSONObject();

    	int id = Integer.parseInt(idconsumidor);

    	Consumidor c = consumidorService.get(id);

    	if(c == null) {
    		json.put("ok", false);
        	json.put("message", "Consumidor no existe");
    		return json.toString();
    	}

    	consumidorService.delete(id);
    	
    	json.put("ok", "true");
    	json.put("message", "Eliminado Correctamente");

    	return json.toString();
    }
	
    @PostMapping(path = "/get", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public String getConsumidorById(String idconsumidor) {

    	JSONObject json = new JSONObject();

    	int id = Integer.parseInt(idconsumidor);
    	
    	Consumidor c = consumidorService.get(id);

    	if(c == null) {
    		json.put("ok", false);
        	json.put("message", "Consumidor no existe");
    		return json.toString();
    	}

    	JSONObject data = new JSONObject(c);

    	json.put("ok", "true");
    	json.put("message", "Se obtuvo correctamente");
    	json.put("data", data);

    	return json.toString();
    }
}
