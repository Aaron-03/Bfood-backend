package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Categoria;
import com.app.entity.Producto;
import com.app.entity.Seller;
import com.app.service.ProductoService;
import com.app.service.SellerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/bfood/producto")
public class ApiProductController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private SellerService sellerService;

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public String addProduct(@Valid @RequestBody String producto) {
		
		JSONObject prod = new JSONObject(producto);

		System.out.println(prod.toString());
		System.out.println("Entró al agregar producto");
		
		Seller seller = sellerService.get(1);

		System.out.println(seller);

		Producto p = new Producto();
		p.setNombre(prod.getString("nombre"));
		p.setDescripcion(prod.getString("descripcion"));
		p.setPrecio(prod.getFloat("precio"));
		p.setCategoria(null);
		p.setImg(prod.getString("img"));
		p.setStock(prod.getInt("stock"));
		p.setStatus("A");

		p.setVendedor(seller);
		
		productoService.save(p);

		p.setVendedor(null);
		p.setCategoria(null);
		JSONObject response = new JSONObject();

		response.put("ok", true);
		response.put("message", "Producto creado correctamente");

		return response.toString();
	}

	@PostMapping(path = "/edit", consumes = "application/json", produces = "application/json")
	public String updProduct(@Valid @RequestBody String producto) {
		
		JSONObject prod = new JSONObject(producto);

		Producto p = new Producto();
		p.setId(prod.getInt("id"));
		p.setNombre(prod.getString("nombre"));
		p.setDescripcion(prod.getString("descripcion"));
		p.setPrecio(prod.getFloat("precio"));
		p.setCategoria(null);
		p.setImg(prod.getString("img"));
		p.setStock(prod.getInt("stock"));
		p.setStatus("A");
		
		Producto prd = productoService.get(prod.getInt("id"));
		p.setVendedor(prd.getVendedor());
		prd = p;
		productoService.save(prd);

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

		p.setVendedor(null);

		JSONObject json = new JSONObject(p);

		return json.toString();
	}

    
    @GetMapping(path = "/all", produces = "application/json")
	public String lstProducts() throws JsonProcessingException {
		ObjectMapper mp = new ObjectMapper();

    	List<Producto> products = productoService.read();
    	
    	for(Producto p : products) {
    		p.setVendedor(null);
    		p.setCategoria(null);
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
	public ResponseEntity<?> lstProductsByVendor(@RequestBody String vendorId) throws JsonProcessingException {
		
		JSONObject response = new JSONObject(vendorId);

    	int idvendedor = response.getInt("vendorId");
    	
    	Seller seller = sellerService.get(idvendedor);
    		
    	if(seller == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);	
    	}
    	
    	List<Producto> productos = (List<Producto>) seller.getProductos();

		for (Producto p : productos) {
			p.setVendedor(null);
			p.setCategoria(null);
		}

		return new ResponseEntity<>(productos, HttpStatus.OK);
	}

	@PutMapping(path = "/dlt", consumes = "application/json", produces = "application/json")
	public String dltProduct(@Valid @RequestBody String productId) {

		System.out.println(productId);
		JSONObject json = new JSONObject(productId);

		int id = json.getInt("id");
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

				return producto;
			}
		}

		return x;
	}

	@GetMapping(path="/listbycategory", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String lstProductsByCategory(@Valid @RequestBody String categoria) {

		System.out.println(categoria);
		JSONObject jsonCategoria = new JSONObject(categoria);
//		int dato_categoria = (int) jsonCategoria.get("categoria");

		return "" + categoria;
	}
}
