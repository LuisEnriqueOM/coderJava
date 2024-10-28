package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.coderhouse.models.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String gramos;
	
	@Column(nullable = false)
	private int precio;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ventas", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
	private List<Cliente> clientes = new ArrayList<>();
	
	public Producto() {
		super();		
	}
	
	public Producto(String nombre, String gramos, int precio) {
		super();
		this.nombre = nombre;
		this.gramos = gramos;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGramos() {
		return gramos;
	}

	public void setGramos(String gramos) {
		this.gramos = gramos;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", gramos=" + gramos + ",precio=" + precio +"]";
	}

}
