package com.irina.xcep.model;

import java.util.List;

public class Lista {

	String nome;
	
	double idSupermercado;
	//FIXME CopyOnWriteArrayList
	List<Producto> productos;
	
	/**
	 * Agrega un producto a la lista
	 * @param producto Producto que se agrega a la lista
	 */
	public void engadirProducto(Producto producto){
		
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getIdSupermercado() {
		return idSupermercado;
	}

	public void setIdSupermercado(double idSupermercado) {
		this.idSupermercado = idSupermercado;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
	
	//TODO agregar el resto de metodos.
}
