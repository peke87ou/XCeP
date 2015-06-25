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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.irina.xcep.adapters.AdapterGridAddShoppingList;
import com.irina.xcep.model.Lista;
import com.irina.xcep.model.Supermercado;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class AddShoppingListActivity extends Activity{
	
	ButtonRectangle btncancel, btnacept;
	ArrayList<Supermercado> supermercados = new ArrayList<Supermercado>();
	GridView grid;
	Supermercado market;
	AdapterGridAddShoppingList adapter;
	boolean click_item = false;
	private EditText nameList;
	private String nameListtxt;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_shopping_list);
		getActionBar().setTitle(R.string.title_action_bar_add_shopping_list);
		
		btncancel = (ButtonRectangle) findViewById(R.id.cancelar_new_list);
		btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		btnacept = (ButtonRectangle) findViewById(R.id.crear_new_list);
		btnacept.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Engadimos a nova lista a BD
				
				engadirLista();
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
	        	// Aqui cambio o fondo da fila seleccionada actualmente
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

	protected void engadirLista() {
		nameList = (EditText) findViewById(R.id.text_name_list);
		
		nameListtxt = nameList.getText().toString();
		
//		ParseObject addlist = new ParseObject("List");
//
//		addlist.put("name", nameListtxt);
		//addlist.put("idMarket","Mecadona");
		
//		ParseUser currentUser = ParseUser.getCurrentUser();
//		String struser = currentUser.getUsername().toString();
//		addlist.put("idUser", ParseUser.getCurrentUser());
		
		
		final Lista addlist = new Lista();
		
		addlist.setNome(nameListtxt);
		//addlist.setIdSupermercado(idSupermercado);
		addlist.setIdUser(ParseUser.getCurrentUser());
		Log.i("USER", ParseUser.getCurrentUser()+"");
		addlist.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException arg0) {
				
			}
		});
	}


	

}
