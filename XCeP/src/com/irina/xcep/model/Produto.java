package com.irina.xcep.model;

import java.util.List;
import java.util.Map;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Product")
public class Produto extends ParseObject {

	
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
	}

	public String getDescripcion() {
		return getString("description");
	}

	public void setDescripcion(String descripcion) {
		put("description", descripcion);
	}

	public String getUrlImaxe() {
		return getString("icon");
	}

	public void setUrlImaxe(String urlImaxe) {
		put("icon", urlImaxe);
	}

	public String getMarca() {
		return getString("mark");
	}

	public void setMarca(String marca) {
		put("mark", marca);
	}

	public List<Map<String, Produto>> getPrezoPorSupermercado() {
		return getList("PrizeMarket");
	}

	public void setPrezoPorSupermercado(List<Map<String, Produto>> prezoPorSupermercado) {
		put("PrizeMarket", prezoPorSupermercado);
	}

	public List<String> getTags() {
		return getList("tags");
	}

	public void setTags(List<String> tags) {
		put("tags", tags);
	}
	
	
}
