package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Producto;
import com.app.service.ProductoService;


@RestController
@RequestMapping("/bfood/producto/")
public class ApiProductController {

	@Autowired
	private ProductoService productoService;

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public String addProduct(@Valid @RequestBody Producto producto) {
    	productoService.save(producto);

		return ":) user add success!";
	}
    
    @PostMapping(path = "/edit", consumes = "application/json", produces = "application/json")
	public String updProduct(@Valid @RequestBody Producto producto) {
    	
    	Producto p = productoService.get(producto.getId());
    	p = producto;
    	productoService.save(p);

		return ":) user add success!";
	}
    
    @PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	public String getProduct(@Valid @RequestBody String productId) {
    	
    	JSONObject jsonId = new JSONObject(productId);
    	
    	int id = (int) jsonId.get("id");
    	
    	Producto p = productoService.get(id);
    	
    	JSONObject json = new JSONObject(p);

		return json.toString();
	}


    @PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	public String lstProducts(@Valid @RequestBody String search) {
		
    	List<Producto> productos = productoService.read();
    	
    	String resultado = "";
    	
    	for(Producto p : productos) {
    		resultado += "Producto: " + p.getNombre() + "\n";
    		System.out.println("Producto: " + p.getNombre());
    	}

    	JSONObject json = new JSONObject();
    	
    	json.put("data", productos);

		return json.get("data").toString();
	}
    
    @PostMapping(path = "/dlt", consumes = "application/json", produces = "application/json")
	public String dltProduct(@Valid @RequestBody String productId) {
    	
    	System.out.println(productId);
    	JSONObject json = new JSONObject(productId.toString());
    	int id = (int) json.get("id");
    	Producto p = productoService.get(id);
		p.setStatus("D");
		productoService.save(p);

    	return "Eliminado con éxito";
	}


}
