package com.coderhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.dao.DaoFactory;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;

@SpringBootApplication
public class Clase08Application implements CommandLineRunner {
	
	@Autowired
	private DaoFactory dao;

	public static void main(String[] args) {
		SpringApplication.run(Clase08Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			
			Cliente cliente1 = new Cliente("Luis","Ortiz");
			Cliente cliente2 = new Cliente("Pancho","Barraza");
			Cliente cliente3 = new Cliente("Fredy","Mercury");
			
			Producto producto1 = new Producto("Papas","54gr",15);
			Producto producto2 = new Producto("Refresco","600ml",20);
			Producto producto3 = new Producto("yogurt lala","50ml",25);
			
			
			dao.createCliente(cliente1);
			dao.createCliente(cliente2);
			dao.createCliente(cliente3);

			dao.createProducto(producto1);
			dao.createProducto(producto2);
			dao.createProducto(producto3);
			
			dao.venta(cliente1.getId(), producto1.getId());
			dao.venta(cliente2.getId(), producto2.getId());
			dao.venta(cliente3.getId(), producto3.getId());

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}