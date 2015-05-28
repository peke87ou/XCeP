package com.irina.xcep.model;

import java.util.List;
import java.util.Map;

public class Producto {

	double identificadorScan;
	
	String nome;
	
	String categoria;
	
	String descripcion;
	
	String urlImaxe;
	
	String marca;
	
	//FIXME pensar como se hace
	List<Map<String, Producto>> prezoPorSupermercado;
	
	List<String> tags;

	
	
	public double getIdentificadorScan() {
		return identificadorScan;
	}

	public void setIdentificadorScan(double identificadorScan) {
		this.identificadorScan = identificadorScan;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlImaxe() {
		return urlImaxe;
	}

	public void setUrlImaxe(String urlImaxe) {
		this.urlImaxe = urlImaxe;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public List<Map<String, Producto>> getPrezoPorSupermercado() {
		return prezoPorSupermercado;
	}

	public void setPrezoPorSupermercado(
			List<Map<String, Producto>> prezoPorSupermercado) {
		this.prezoPorSupermercado = prezoPorSupermercado;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	
}
