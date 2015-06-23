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
	
	// Declaración de variables
	ButtonRectangle logout;
	ListView list;
	List<ParseObject> ob;
	AdapterListas adapter;
	ArrayList<Lista> misListas = new ArrayList<Lista>();
	ImageButton addlist;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		// Solicitar usuario actual do Parse.com
		ParseUser currentUser = ParseUser.getCurrentUser();
		
		// Convertir currentUser en String
		String struser = currentUser.getUsername().toString();
		TextView txtuser = (TextView) findViewById(R.id.txtuser);
		txtuser.setText(R.string.text_login_home_user + struser);
		
		logout = (ButtonRectangle) findViewById(R.id.logout);
		logout.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// Desconectar o current user
				ParseUser.logOut();
				finish();
			}
		});
		
		list = (ListView) findViewById(R.id.lista_list);
		adapter = new AdapterListas(HomeActivity.this, misListas);
		list.setAdapter(adapter);
		
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
					Toast.makeText(HomeActivity.this, R.string.empty_list, Toast.LENGTH_LONG).show();
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