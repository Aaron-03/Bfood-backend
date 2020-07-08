package com.app.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PedidoResult;
import com.app.dto.PedidoResultCons;
import com.app.entity.Consumidor;
import com.app.entity.DetallePedido;
import com.app.entity.Pedido;
import com.app.entity.Producto;
import com.app.entity.Seller;
import com.app.entity.Sucursal;
import com.app.service.ConsumidorService;
import com.app.service.DetallePedidoService;
import com.app.service.PedidoService;
import com.app.service.ProductoService;
import com.app.service.SellerService;
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

	@Autowired
	private SellerService sellerService;

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private DetallePedidoService detallePedidoService;

	private List<PedidoResult> pedidoResults;
	
	private List<DetallePedido> pedidoResultsCons;

//	@GetMapping(path = "/list", produces = "application/json")
//	public List<PedidoResult> lstPedidos() {
//		int param = 1;
//		Seller seller = sellerService.get(1);
//		// Producto producto = productoService.get(1);
//		Consumidor consumidor = conServ.get(1);
//
//		List<Pedido> lst = pedidoService.read().stream().filter(x -> x.getConsumidor() == consumidor)
//				.collect(Collectors.toList());
//
//		List<DetallePedido> detallePedidos = new ArrayList<DetallePedido>();
//
//		// detallePedidos= DetallePedidoService.read();
//
//		pedidoResults = null;
//
//		for (Pedido pedido : lst) {
//
//			for (DetallePedido detallePedido : detallePedidos) {
//				if (seller.getId() == param) {
//					pedidoResults.add(new PedidoResult(seller, pedido, detallePedido));
//				}
//			}
//		}
//
//		return pedidoResults;
//	}
	
	@GetMapping(path = "/vendor", produces = "application/json")
	public ResponseEntity<?> lstDetailsOrders() {
		
		try {
			
			int vendorId = 1;
			
			List<Pedido> pedidos = pedidoService.read();
			
			
			
			return new ResponseEntity<>(pedidos, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>("No funcionó", HttpStatus.OK);
		}
	}
	
	// Aquí hirá la capa de seguridad
	@GetMapping(path = "/customer", produces = "application/json")
	public ResponseEntity<?> lstOrdersCustomer() {
		
		try {
			
			// Dato en duro - provicional
			int customerId = 1;
			
			
			List<Pedido> pedidos = pedidoService.read();
			
			for(Pedido pd : pedidos) {
				if(pd.getConsumidor().getId() != customerId) {
					pedidos.remove(pd);
				}
			}

			return new ResponseEntity<>(pedidos, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>("No funcionó", HttpStatus.OK);
		}
	}
	

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public String Registrar(@RequestBody Pedido pedido) throws Exception {

		Consumidor nuevoConsumidor = conServ.get(pedido.getConsumidor().getId());

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

	@GetMapping(path = "/list", consumes = "application/json", produces = "application/json")
    public List<PedidoResult> lstPedidos(@Valid @RequestBody int id, int xy) {
        //int param = 1;
        Seller seller = sellerService.get(id);
        // Producto producto = productoService.get(1);
        Consumidor consumidor = conServ.get(xy);

        List<Pedido> lst = pedidoService.read().stream().filter(x -> x.getConsumidor() == consumidor)
                .collect(Collectors.toList());

        List<DetallePedido> detallePedidos = detallePedidoService.read();

        pedidoResults = new ArrayList<PedidoResult>();

        for (Pedido pedido : lst) {

            for (DetallePedido detallePedido : detallePedidos) {
                if (seller.getId() == id) {
                    pedidoResults.add(new PedidoResult(seller, pedido, detallePedido));
                }
            }
        }

        return pedidoResults;
    }


    @GetMapping(path = "/listCons", produces = "application/json")
    public List<PedidoResultCons> lstPedidosConsumidor(@Valid @RequestBody int id) {
        //int param = 1;
        Consumidor consumidor = conServ.get(id);
        
        System.out.println("Dentro de listas pedidos por consumidor");

        List<Pedido> lst = pedidoService.read().stream().filter(x -> x.getConsumidor() == consumidor)
                .collect(Collectors.toList());

        System.out.println(lst.size());

        List<DetallePedido> detallePedidos = detallePedidoService.read();

        List<PedidoResultCons> pedidoResultsCons = new ArrayList<PedidoResultCons>();

        for (Pedido pedido : lst) {

            for (DetallePedido detallePedido : detallePedidos) {
                if (consumidor.getId() == id) {
                    pedidoResultsCons.add(new PedidoResultCons(consumidor, pedido, detallePedido));
                }
            }
        }

        return pedidoResultsCons;
    }

}
