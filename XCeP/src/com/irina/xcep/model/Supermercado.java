package com.irina.xcep.model;

import java.util.List;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Market")
public class Supermercado extends ParseObject{
	
	String nome;
	String urlLogo;
	List<Producto> productos;


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getUrlLogo() {
		return urlLogo;
	}


	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	//TODO agregar el resto de metodos
	
	/**
	 * @param tags Lista de tags
	 * @return Devuelve una lista de producto que tienen este tag
	 */
	public List<Producto> getProductosPorTag(List<String> tags){
		
		return null;
	}
	
}
