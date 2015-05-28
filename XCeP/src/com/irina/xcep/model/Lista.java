package com.irina.xcep.model;

import java.util.List;

public class Lista {

	//Declaramos as variables
	String nome;
	double idSupermercado;
	//FIXME CopyOnWriteArrayList
	List<Producto> productos;
	
	
	//Métodos empregados nesta clase
	/**
	 * Engade un producto a lista
	 * @param producto: Producto que se engade a lista
	 */
	public void engadirProducto(Producto producto){
		//TODO
		
	}

	/**
	 * Quita un producto da lista
	 * @param producto: Producto que se quita da lista
	 */
	public void quitarProducto(Producto producto){
		//TODO
		
	}
	
	
	/**
	 * Conxunto de getters e setters da clase
	 *
	 */
	
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
