package com.irina.xcep;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
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
import com.irina.xcep.utils.Utils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

@SuppressLint("NewApi")
public class AddShoppingListActivity extends Activity{
	
	ButtonRectangle btncancel, btnacept;
	ArrayList<Supermercado> supermercados = new ArrayList<Supermercado>();
	GridView grid;
	Supermercado market;
	AdapterGridAddShoppingList adapter;
	boolean click_item = false;
	private EditText nameList;
	private String nameListtxt;
	private Supermercado idSuper;
	
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
        
        ParseQuery<Supermercado> query = ParseQuery.getQuery(Supermercado.class);
		query.findInBackground(new FindCallback<Supermercado>() {
			
			@Override
			public void done(List<Supermercado> objects, ParseException e) {

				supermercados = (ArrayList<Supermercado>) objects;
				
				adapter.clear();
				adapter.addAll(supermercados);
				
			}
		});
        
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              
        	// Esta parte la uso para poder quitar el fondo cambiado a la fila seleccionada anteriormente
//        	if(grid != null){
//        		if(!grid.equals(view)){
////        			view.getBackground();
////        	        grid.setBackgroundDrawable(view.getBackground());
////        			if(!click_item){
////        	        	// Aqui cambio o fondo da fila seleccionada actualmente
////        	        	view.setBackgroundColor(Color.argb(200,208,245,169));  
////        	        	click_item = true;
////        	        	Log.i("2", parent.getItemAtPosition(position).toString());
////                	}else{
////                		view.setBackgroundColor(Color.TRANSPARENT); 
////                		click_item = false;
////                	}
//        	      //  Log.i("1", "por aki");
//        			grid.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
//        			 if (grid.getSelectedItem() != null) {
//        	                view.setBackgroundColor(Color.GREEN);
//        	            } else {
//        	                view.setBackgroundColor(Color.BLUE);
//        	            }
//        	    }                            
//        	 } else{
//        		 grid.setItemChecked(0, true);
//        		 click_item = true;
//        		
//        	 }
        	grid.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        	//view.setBackground(getResources().getDrawable(R.drawable.grid_selector));
        	//Toast.makeText(AddShoppingListActivity.this, "You Clicked at " + supermercados.get(position), Toast.LENGTH_SHORT).show();
        	idSuper = supermercados.get(position);
             }
         });

       
	}

	protected void engadirLista() {
			
		Lista addlist = new Lista();
		
		//Nome da lista
		nameList = (EditText) findViewById(R.id.text_name_list);
		nameListtxt = nameList.getText().toString();
		
		
		boolean allfilled = true;
		allfilled =  Utils.isNotEmpty(nameList, nameListtxt);
		if(!allfilled) return;
		
		addlist.setNome(nameListtxt);
		
		//Id supermercado seleccionado
		if (idSuper == null ){
			Toast.makeText(AddShoppingListActivity.this, "No ha seleccionado ning�n supermercado", Toast.LENGTH_SHORT).show();
		}else{
			addlist.setIdSupermercado(idSuper);
			//Id usuario logeuado
			addlist.setIdUser(ParseUser.getCurrentUser());
			
			addlist.saveInBackground(new SaveCallback() {
				@Override
				public void done(ParseException arg0) {
					Toast.makeText(AddShoppingListActivity.this, "Engadimos a nova lista " + nameListtxt, Toast.LENGTH_SHORT).show();
					finish();
				}
			});

		}
		
		
	}


	

}
