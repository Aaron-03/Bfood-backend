package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Producto;
import com.app.entity.Seller;
import com.app.service.ProductoService;
import com.app.service.SellerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/bfood/producto/")
public class ApiProductController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private SellerService sellerService;

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public String addProduct(@Valid @RequestBody Producto producto) {
    	
    	System.out.println("Entró al agregar producto");
    	Seller seller = sellerService.get(producto.getVendedor().getId());
    	
    	producto.setVendedor(seller);
    	
    	productoService.save(producto);

    	producto.setVendedor(null);

    	JSONObject response = new JSONObject();

    	response.put("ok", true);
    	response.put("message", "Producto creado correctamente");

    	return response.toString();
	}
    
    @PostMapping(path = "/edit", consumes = "application/json", produces = "application/json")
	public String updProduct(@Valid @RequestBody Producto producto) {
    	
    	Producto p = productoService.get(producto.getId());
    	p = producto;
    	productoService.save(p);
    	
    	JSONObject json = new JSONObject();

    	json.put("ok", true);
    	json.put("message", "Producto actualizado correctamente");
    	
		return json.toString();
	}
    
    @PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	public String getProduct(@Valid @RequestBody String productId) {

    	System.out.println(productId);
    	JSONObject jsonId = new JSONObject(productId.toString());

    	int id = (int) jsonId.get("id");

    	Producto p = productoService.get(id);

    	p.setVendedor(null);

    	JSONObject json = new JSONObject(p);

		return json.toString();
	}
    
    @GetMapping(path = "/all", consumes = "application/json", produces = "application/json")
	public String lstProducts() throws JsonProcessingException {

    	ObjectMapper mp = new ObjectMapper();

    	List<Producto> products = productoService.read();
    	
    	for(Producto p : products) {
    		p.setVendedor(null);
    	}
 
    	String json = mp.writeValueAsString(products);
    	
    	JSONArray prods = new JSONArray(json);

    	JSONObject response = new JSONObject();
    	
    	response.put("ok", true);
    	response.put("products", prods);
//    	response.put("page", page);

    	return response.toString();
	}


    @PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	public String lstProductsByVendor(@Valid @RequestBody String vendorId) throws JsonProcessingException {
    	
    	System.out.println(vendorId);
    	JSONObject jsonId = new JSONObject(vendorId);

    	int idvendedor = jsonId.getInt("id");

    	System.out.println("Vendedor: " + idvendedor);
    	
    	Seller seller = sellerService.get(idvendedor);
    	
    	List<Producto> productos = (List<Producto>) seller.getProductos();
    	
    	ArrayList<Producto> products = new ArrayList<Producto>();

    	for(Producto p : productos) {
    		p.setVendedor(null);
    		products.add(p);
    	}
    	
    	JSONObject json = new JSONObject();

    	json.put("ok", true);
    	json.put("data", products);
    	
    	return json.toString();
	}
    
    @PostMapping(path = "/dlt", consumes = "application/json", produces = "application/json")
	public String dltProduct(@Valid @RequestBody String productId) {
    	
    	System.out.println(productId);
    	JSONObject json = new JSONObject(productId.toString());
    	int id = (int) json.get("id");
    	Producto p = productoService.get(id);
		p.setStatus("D");
		productoService.save(p);
		
		JSONObject res = new JSONObject();
		
		res.put("ok", true);
		res.put("message", "Eliminado correctamente");

    	return res.toString();
	}
   

    @GetMapping(path = "/q={name}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> encontrarproducto(@PathVariable("name") String name) {

		Producto producto = getProductoByName(name);
		if (producto == null) {
			return new ResponseEntity<>(":( Producto no encontrado", HttpStatus.NOT_FOUND);
		} else {

			return ResponseEntity.ok(producto);
		}
	}
    

	Producto getProductoByName(String param) {
		
		Producto x = null;
		List<Producto> lst = productoService.read();
		for (Producto producto : lst) {
			if (producto.getNombre().equals(param)) {
				
				return  producto;
			}
		}

		return x;
	}


}
