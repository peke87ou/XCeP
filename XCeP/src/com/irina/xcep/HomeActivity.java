package com.irina.xcep;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.irina.xcep.adapters.AdapterListas;
import com.irina.xcep.model.Lista;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class HomeActivity extends Activity {
	
	// Declare Variable
	ButtonRectangle logout;
	ListView list;
	List<ParseObject> ob;
	AdapterListas adapter;
	ArrayList<Lista> misListas = new ArrayList<Lista>();
	ImageButton addlist;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.activity_home);
		
		// Retrieve current user from Parse.com
		ParseUser currentUser = ParseUser.getCurrentUser();
		
		// Convert currentUser into String
		String struser = currentUser.getUsername().toString();
		
		// Locate TextView in welcome.xml
		TextView txtuser = (TextView) findViewById(R.id.txtuser);

		// Set the currentUser String into TextView
		txtuser.setText("Has iniciado sesión como " + struser);
		
		// Locate Button in welcome.xml
		logout = (ButtonRectangle) findViewById(R.id.logout);

		// Logout Button Click Listener
		logout.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Logout current user
				ParseUser.logOut();
				finish();
			}
		});
		
		list = (ListView) findViewById(R.id.lista_list);
		adapter = new AdapterListas(HomeActivity.this, misListas);//ArrayAdapter<Lista>(HomeActivity.this,R.layout.item_shopping_list);
		list.setAdapter(adapter);
		
		//Supermercado market =  new Supermercado();
		//market.setNome("Mercadona");
		
		ParseQuery<Lista> query = ParseQuery.getQuery(Lista.class);
		query.include("Market");
		query.findInBackground(new FindCallback<Lista>() {
			
			@Override
			public void done(List<Lista> objects, ParseException e) {
				

				misListas = (ArrayList<Lista>) objects;
				
				adapter.clear();

				if(misListas != null){
					adapter.addAll(misListas);
				}else{
					Toast.makeText(HomeActivity.this, "Listas vacía", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
		addlist = (ImageButton) findViewById(R.id.add_list);
		
		addlist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Ir a páxina engadir unha lista
				Intent intent = new Intent(HomeActivity.this, AddShoppingListActivity.class);
				startActivity(intent);
				
			}
		});

		
		
	
	}
}