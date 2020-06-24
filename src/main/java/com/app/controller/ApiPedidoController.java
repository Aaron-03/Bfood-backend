package com.app.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Consumidor;
import com.app.entity.Pedido;
import com.app.service.ConsumidorService;
import com.app.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/bfood/pedido")
public class ApiPedidoController {

	private ObjectMapper obm = new ObjectMapper();

	private JSONArray getObjectsJSON(List<?> listItems) throws JsonProcessingException {

		String jsonstring = obm.writeValueAsString(listItems);
		JSONArray jrr = new JSONArray(jsonstring);
		return jrr;
	}

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ConsumidorService conServ;
	
	@GetMapping(path = "/list",produces = "application/jason")
	public String lstPedidos() throws JsonProcessingException{
		 
		return null;
		 
	}

	@PostMapping(path = "/add/{id}", consumes = "application/json", produces = "application/json")
	public String Registrar(@RequestBody Integer id, Pedido pedido) throws Exception {

		Consumidor nuevoConsumidor = conServ.get(id);

		Pedido p = new Pedido();

		p.setConsumidor(nuevoConsumidor);
		p.setFecha(pedido.getFecha());
		p.setDetalles(pedido.getDetalles());
		p.setTotal(pedido.getTotal());
		p.setStatus(pedido.getStatus());

		// pedidoService.registrarPedido(p);
		pedidoService.save(p);

		JSONObject res = new JSONObject();

		String message = "Pedido añadido correctamente";

		res.put("ok", true);
		res.put("message", message);

		return res.toString();
	}
	
	

}
