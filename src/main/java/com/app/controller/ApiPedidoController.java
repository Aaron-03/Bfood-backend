package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Consumidor;
import com.app.entity.Pedido;
import com.app.service.ConsumidorService;
import com.app.service.PedidoService;


@RestController
@RequestMapping("/bfood/pedido")
public class ApiPedidoController {

	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ConsumidorService conServ;
	
	/*@PostMapping("/add")
	public String addPedido(@Valid @RequestBody Pedido p) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		
		p.setFecha(fecha);
		System.out.println(p);
		
		pedidoService.save(p);
		
		JSONObject res = new JSONObject();
		
		String message = "Pedido añadido correctamente";
		
		res.append("ok", true);
		res.append("message", message);
		
		return res.toString();
	}*/
	
	@PostMapping(path = "/add", consumes = "application/json",produces = "application/json")
	public String Registrar(Pedido pedido ) throws Exception{
		
		
		Consumidor nuevoConsumidor = conServ.get(1);
		
		Pedido p = new Pedido();
		
		p.setConsumidor(nuevoConsumidor);
		p.setFecha(pedido.getFecha());
		p.setDetalles(pedido.getDetalles());
		p.setTotal(pedido.getTotal());
		p.setStatus(pedido.getStatus());
					
	    //pedidoService.registrarPedido(p);
		pedidoService.save(p);
	    
	    JSONObject res = new JSONObject();
		
		String message = "Pedido añadido correctamente";
		
		res.put("ok", true);
		res.put("message", message);
		
		return res.toString();
	}
}
