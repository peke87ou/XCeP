package com.irina.xcep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {
	//Declaramos as variables
	ButtonRectangle loginbutton;
	String usernametxt;
	String passwordtxt;
	EditText password;
	EditText username;
	TextView linksingup;
	
	/** Chamase cando creouse por primera vez a actividad */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from main.xml
		setContentView(R.layout.loginsignup);
		
		getActionBar().setTitle(R.string.title_action_bar_login);
		
		// Locate EditTexts in main.xml
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		// Locate Buttons in main.xml
		loginbutton = (ButtonRectangle) findViewById(R.id.login);
		linksingup = (TextView) findViewById(R.id.link_signup);

		// Login Button Click Listener
		loginbutton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();

				// Send data to Parse.com for verification
				ParseUser.logInInBackground(usernametxt, passwordtxt,
						new LogInCallback() {
							public void done(ParseUser user, ParseException e) {
								if (user != null) {
									// If user exist and authenticated, send user to Welcome.class
									Intent intent = new Intent(
											LoginActivity.this,
											HomeActivity.class);
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Conectado Exitosamente ",
											Toast.LENGTH_LONG).show();
									finish();
								} else {
									Toast.makeText(
											getApplicationContext(),
											"Este usuario non existe, por favor rexistrese",
											Toast.LENGTH_LONG).show();
								}
							}
						});
			}
		});
		
		linksingup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Ir a páxina de rexistro
				Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
				startActivity(intent);
				
			}
		});

	}
}
