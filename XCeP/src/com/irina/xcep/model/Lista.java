package com.irina.xcep.model;

import java.util.List;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;

@ParseClassName("List")
public class Lista extends ParseObject {

//	//Declaramos as variables
//	String nome;
//	double idSupermercado;
//	//FIXME CopyOnWriteArrayList
//	List<Producto> productos;
	
	public Lista() {
		
	}

	
	//M�todos empregados nesta clase
	/**
	 * Engade un producto a lista
	 * @param producto: Producto que se engade a lista
	 */
	public void engadirProducto(Produto producto){
		//TODO
		
	}

	/**
	 * Quita un producto da lista
	 * @param producto: Producto que se quita da lista
	 */
	public void quitarProducto(Produto producto){
		//TODO
		
	}
	
	
	/**
	 * Conxunto de getters e setters da clase
	 *
	 */
	
	public String getNome() {
		return getString("name");
	}

	public void setNome(String nome) {
		put("name", nome);
	}

	public ParseRelation<ParseObject> getIdSupermercado() {
		return getRelation("idMarket");
	}

	public void setIdSupermercado(double idSupermercado) {
		put("idMarket", idSupermercado);
		//this.idSupermercado = idSupermercado;
	}

	public List<Produto> getProductos() {
		return getList("Products");
	}

	public void setProductos(List<Produto> productos) {
		put("Products", productos);
		//this.productos = productos;
	}
	
	
	
	//TODO agregar el resto de metodos.
}
