package com.app.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PedidoResult;
import com.app.entity.Consumidor;
import com.app.entity.DetallePedido;
import com.app.entity.Pedido;
import com.app.entity.Producto;
import com.app.entity.Seller;
import com.app.entity.Sucursal;
import com.app.service.ConsumidorService;
import com.app.service.PedidoService;
import com.app.service.ProductoService;
import com.app.service.SellerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/bfood/pedido")
public class ApiPedidoController {

	//private ObjectMapper obm = new ObjectMapper();

//	private JSONArray getObjectsJSON(List<?> listItems) throws JsonProcessingException {
//
//		String jsonstring = obm.writeValueAsString(listItems);
//		JSONArray jrr = new JSONArray(jsonstring);
//		return jrr;
//	}

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ConsumidorService conServ;
	
	@Autowired 
	private SellerService sellerService;
	
	@Autowired 
	private  ProductoService productoService;
	
	@Autowired
	private  DetallePedido detallePedido;

	private List<PedidoResult> pedidoResults;
	
	@GetMapping(path = "/list", produces = "application/json")
	public List<PedidoResult> lstPedidos(){
		int param=1;
		Seller seller= sellerService.get(1);
		//Producto producto = productoService.get(1);
		Consumidor consumidor=conServ.get(1);
		
		List<Pedido> lst = pedidoService.read().stream().filter(x -> x.getConsumidor() == consumidor)
				.collect(Collectors.toList());
		
		List<DetallePedido> detallePedidos= new ArrayList<DetallePedido>();
		
		//detallePedidos= DetallePedidoService.read();
		
		pedidoResults = null;
		
		for (Pedido pedido : lst) {
		
			for (DetallePedido detallePedido : detallePedidos) {
				if(seller.getId()==param) {
					pedidoResults.add(new PedidoResult(seller, pedido,detallePedido));
				}
			}
		}
		
		return pedidoResults;
		 
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
