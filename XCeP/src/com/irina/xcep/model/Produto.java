package com.irina.xcep.model;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.irina.xcep.AddShoppingListActivity;
import com.irina.xcep.R;
import com.irina.xcep.utils.Utils;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

@ParseClassName("Products")
public class Produto extends ParseObject {

	Context mContext;
	//FIXME pensar como se hace
	// Prezo por supermercado
	//	List<Map<String, Producto>> prezoPorSupermercado;
	//	List<String> tags;
	
	 public Produto(Context context, Produto producto) {
			super();
			mContext = context;
		}
	
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

	public ParseRelation<Prezo> getPrezoPorSupermercado() {
		return getRelation("PrizeMarket");
	}

	public void setPrezoPorSupermercado(Prezo prezoPorSupermercado) {
		put("PrizeMarket", prezoPorSupermercado);
	}

	public ParseRelation<Tag> getTags() {
		return getRelation("tags");
	}

	public void setTags(Tag tags) {
		put("tags", tags);
	}
	
	
	//Métodos empregados nesta clase
		/**
		 * Engade un producto a BD
		 * @param producto: Producto que se engade a BD
		 */
		public void engadirProducto(){
			
			Produto addProduct = new Produto();
			
			//Nome
			 EditText nameProduto = (EditText) findViewById(R.id.text_name_product);
			 String nameProductTxt = nameProduto.toString();
			 addProduct.setNome(nameProductTxt);
						
			//Marca
			 EditText markProduto = (EditText) findViewById(R.id.text_mark_product);
			 String markProductTxt = markProduto.getText().toString();
			 addProduct.setMarca(markProductTxt);
			 
			//Descrición
			 EditText descriptionProduto = (EditText) findViewById(R.id.text_description_product);
			 String descriptionProdutoTxt = descriptionProduto.getText().toString();
			 addProduct.setDescripcion(descriptionProdutoTxt);
			 
			//foto
			 EditText fotoProducto = (EditText) findViewById(R.id.image_view_product);
//			 String fotoProductoTxt = fotoProducto.getText().toString();
//			 addProduct.setUrlImaxe(fotoProductoTxt);
			 
			//Prezo
			 EditText priceProduto = (EditText) findViewById(R.id.text_name_product);
//			 String priceProdutoTxt = priceProduto.getText().toString();
//			 addProduct.setPrezoPorSupermercado(priceProdutoTxt);
//			 
			 addProduct.saveInBackground(new SaveCallback() {
					@Override
					public void done(ParseException arg0) {
						if (arg0 == null){
							//Toast.makeText(g, "Engadimos O producto a BD ", Toast.LENGTH_SHORT).show();
							Log.i("Producto", "Engadimos O producto a BD ");
							
						}else{
							//Toast.makeText(, R.string.error_add_list+" " , Toast.LENGTH_SHORT).show();
							Log.e("Producto", "ERROR O ENGADIR NA BD ");
						}
					}
				});
			
		}

		private EditText findViewById(int textNameProduct) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	
}
