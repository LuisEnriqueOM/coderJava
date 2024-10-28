package com.coderhouse.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class DaoFactory {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void createCliente(Cliente cliente) {
		em.persist(cliente);
	}

	@Transactional
	public void createProducto(Producto producto) {
		em.persist(producto);
	}
	
	@Transactional
	public List<Cliente> getAllClientes() {
		TypedQuery<Cliente> query = em.createQuery("SELECT a FROM Cliente a", Cliente.class);
		List<Cliente> clientes = query.getResultList();
		clientes.forEach(a -> Hibernate.initialize(a.getProductos()));
		return clientes;

	}
	
	@Transactional
	public List<Producto> getAllProductos() {
		TypedQuery<Producto> query = em.createQuery("SELECT c FROM Producto c", Producto.class);
		List<Producto> productos = query.getResultList();
		productos.forEach(c -> Hibernate.initialize(c.getClientes()));
		return productos;
	}
	
	@Transactional
	public Cliente getClienteById(Long clienteId) throws Exception {
		try {
			TypedQuery<Cliente> query = em.createQuery("SELECT a FROM Cliente a WHERE a.id = :id", Cliente.class);
			Cliente cliente = query.setParameter("id", clienteId).getSingleResult();
			Hibernate.initialize(cliente.getProductos());
			return cliente;
		} catch (Exception e) {
			throw new Exception("El curso no existe");
		}

	}
	
	@Transactional
	public Producto getProductoById(Long productoId) throws Exception {
		try {
			TypedQuery<Producto> query = em.createQuery("SELECT c FROM Producto c WHERE c.id = :id", Producto.class);
			return query.setParameter("id", productoId).getSingleResult();
		} catch (Exception e) {
			throw new Exception("El curso no existe");
		}
	}
	
	@Transactional
	public void venta(Long productoId, Long clienteId) throws Exception {
		Producto producto = getProductoById(productoId);
		Cliente cliente = getClienteById(clienteId);

		System.out.println(producto.toString());
		System.out.println(cliente.toString());
		
		Hibernate.initialize(producto.getClientes());
		Hibernate.initialize(cliente.getProductos());

		
		if (!producto.getClientes().contains(cliente)) {
			// Verifica si el alumno ya está inscrito en el curso.
		    // Si no está, lo agrega a la lista de alumnos del curso.
			producto.getClientes().add(cliente);
		}

		if (!cliente.getProductos().contains(producto)) {
			 // Verifica si el curso ya está en la lista de cursos del alumno.
		    // Si no está, lo agrega a la lista de cursos del alumno.
			cliente.getProductos().add(producto);
		}

		em.merge(producto);
		em.merge(cliente);

	}
	

}