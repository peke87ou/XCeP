package com.irina.xcep;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.irina.xcep.adapters.AdapterGridAddShoppingList;
import com.irina.xcep.model.Supermercado;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class AddShoppingListActivity extends Activity{
	
	ButtonRectangle btncancel;
	ArrayList<Supermercado> supermercados = new ArrayList<Supermercado>();
	GridView grid;
	Supermercado market;
	AdapterGridAddShoppingList adapter;
	boolean click_item = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.activity_add_shopping_list);
		getActionBar().setTitle(R.string.title_action_bar_add_shopping_list);
		
		btncancel = (ButtonRectangle) findViewById(R.id.cancelar_new_list);
		btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		adapter = new AdapterGridAddShoppingList(AddShoppingListActivity.this, supermercados);
		
		grid=(GridView)findViewById(R.id.grid_logo_market);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	
              
//        	// Esta parte la uso para poder quitar el fondo cambiado a la fila seleccionada anteriormente
//        	if(grid != null){
//        		if(!grid.equals(view)){
////        			view.getBackground();
////        	        grid.setBackgroundDrawable(view.getBackground());
//        	      
//        	        Log.i("1", "por aki");
//        	    }                            
//        	 }   
        	if(!click_item){
	        	// Aqui cambio el fondo de la fila seleccionada actualmente
	        	view.setBackgroundColor(Color.argb(200,208,245,169));  
	        	click_item = true;
	        	Log.i("2", parent.getItemAtPosition(position).toString());
        	}else{
        		view.setBackgroundColor(Color.TRANSPARENT); 
        		click_item = false;
        	}
        	//   grid = view;
        	Toast.makeText(AddShoppingListActivity.this, "You Clicked at " + supermercados.get(position).getNome(), Toast.LENGTH_SHORT).show();
             }
         });

        ParseQuery<Supermercado> query = ParseQuery.getQuery(Supermercado.class);
		query.findInBackground(new FindCallback<Supermercado>() {
			
			@Override
			public void done(List<Supermercado> objects, ParseException e) {
				

				supermercados = (ArrayList<Supermercado>) objects;
				
				adapter.clear();
				adapter.addAll(supermercados);
				
			}
		});
	}


	

}
