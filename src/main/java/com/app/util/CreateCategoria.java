package com.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.entity.Categoria;
import com.app.service.CategoriaService;

@Component
public class CreateCategoria implements CommandLineRunner {

	@Autowired
	CategoriaService categoriaService;

	@Override
	public void run(String... args) throws Exception {
		Categoria c = categoriaService.get(1);

		if (c == null) {
			Categoria c1 = new Categoria("Sopas");
			Categoria c2 = new Categoria("Pastas");
			Categoria c3 = new Categoria("Entradas");
			categoriaService.save(c1);
			categoriaService.save(c2);
			categoriaService.save(c3);
		}

	}
}
