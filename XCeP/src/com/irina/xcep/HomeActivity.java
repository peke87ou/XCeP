package com.irina.xcep;

import java.util.List;

import org.w3c.dom.Comment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.irina.xcep.model.Lista;
import com.irina.xcep.model.Supermercado;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class HomeActivity extends Activity {
	
	// Declare Variable
	Button logout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.home);
		
		// Retrieve current user from Parse.com
		ParseUser currentUser = ParseUser.getCurrentUser();
		
		// Convert currentUser into String
		String struser = currentUser.getUsername().toString();
		
		// Locate TextView in welcome.xml
		TextView txtuser = (TextView) findViewById(R.id.txtuser);

		// Set the currentUser String into TextView
		txtuser.setText("Has iniciado sesión como " + struser);
		
		// Locate Button in welcome.xml
		logout = (Button) findViewById(R.id.logout);

		// Logout Button Click Listener
		logout.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Logout current user
				ParseUser.logOut();
				finish();
			}
		});
		
		//Supermercado market =  new Supermercado();
		//market.setNome("Mercadona");
		ParseQuery<Lista> query = ParseQuery.getQuery(Lista.class);
		query.findInBackground(new FindCallback<Lista>() {
			
			@Override
			public void done(List<Lista> objects, ParseException e) {
				// TODO Auto-generated method stub
				//market.getNome();
				TextView txtsupermarket = (TextView) findViewById(R.id.txtsupermarket);
				txtsupermarket.setText(objects.get(0).getNome());
				Log.i("Lista", objects.get(0).getIdSupermercado()+"");
				Log.i("Lista", objects.get(0).getProductos()+"");
//				Log.i("Supermercado", objects.get(0).getUrlLogo().+"");
			}
		});
		
				

	}
}