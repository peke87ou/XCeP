package com.irina.xcep.model;

import java.util.List;
import java.util.Map;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Product")
public class Produto extends ParseObject {

	//Declaramos as variables
//	double identificadorScan;
//	String nome;
//	String categoria;
//	String descripcion;
//	String urlImaxe;
//	String marca;
	//FIXME pensar como se hace
	// Prezo por supermercado
//	List<Map<String, Producto>> prezoPorSupermercado;
//	List<String> tags;
	
	public Produto() {
		
	}
	


	//Métodos empregados nesta clase
	
	/**
	 * Conxunto de getters e setters da clase
	 */
	
	public Number getIdentificadorScan() {
		return getNumber("idBarCode");
	}

	public void setIdentificadorScan(Number identificadorScan) {
		put("idBarCode", identificadorScan);
		//this.identificadorScan = identificadorScan;
	}

	public String getNome() {
		return getString("title");
	}

	public void setNome(String nome) {
		put("title", nome);
	}

	public String getCategoria() {
		return getString("categoria");
	}

	public void setCategoria(String categoria) {
		put("categoria", categoria);
		//this.categoria = categoria;
	}

	public String getDescripcion() {
		return getString("description");
	}

	public void setDescripcion(String descripcion) {
		put("description", descripcion);
		//this.descripcion = descripcion;
	}

	public String getUrlImaxe() {
		return getString("icon");
	}

	public void setUrlImaxe(String urlImaxe) {
		put("icon", urlImaxe);
		//this.urlImaxe = urlImaxe;
	}

	public String getMarca() {
		return getString("mark");
	}

	public void setMarca(String marca) {
		put("mark", marca);
		//this.marca = marca;
	}

	public List<Map<String, Produto>> getPrezoPorSupermercado() {
		return getList("PrizeMarket");
		//return prezoPorSupermercado;
	}

	public void setPrezoPorSupermercado(List<Map<String, Produto>> prezoPorSupermercado) {
		put("PrizeMarket", prezoPorSupermercado);
		//this.prezoPorSupermercado = prezoPorSupermercado;
	}

	public List<String> getTags() {
		return getList("tags");
	}

	public void setTags(List<String> tags) {
		put("tags", tags);
		//this.tags = tags;
	}
	
	
}
