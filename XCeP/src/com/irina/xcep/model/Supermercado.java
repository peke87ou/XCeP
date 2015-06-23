package com.irina.xcep.model;

import java.util.List;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Market")
public class Supermercado extends ParseObject{
	
//	String nome;
//	String urlLogo;
//	List<Producto> productos;

	public Supermercado() {
		
	}

	public String getNome() {
		return getString("name");
	}


	public void setNome(String nome) {
		put("name", nome);
		//this.nome = nome;
	}


	public ParseFile getUrlLogo() {
		return getParseFile("Image");
	}


	public void setUrlLogo(ParseFile urlLogo) {
		put("Image", urlLogo);
		//this.urlLogo = urlLogo;
	}


	public List<Produto> getProductos() {
		return getList("Products");
	}


	public void setProductos(List<Produto> productos) {
		put("Products", productos);
		//this.productos = productos;
	}
	
	//TODO agregar el resto de metodos
	
	/**
	 * @param tags Lista de tags
	 * @return Devuelve una lista de producto que tienen este tag
	 */
	public List<Produto> getProductosPorTag(List<String> tags){
		
		return null;
	}
	
}
