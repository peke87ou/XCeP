package com.irina.xcep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends Activity {
	//Declaramos as variables
	ButtonRectangle loginbutton;
	ButtonRectangle signup;
	String usernametxt;
	String passwordtxt;
	EditText password;
	EditText username;

	/** Chamase cando creouse por primera vez a actividad */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from main.xml
		setContentView(R.layout.loginsignup);

		// Locate EditTexts in main.xml
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		// Locate Buttons in main.xml
		loginbutton = (ButtonRectangle) findViewById(R.id.login);
		signup = (ButtonRectangle) findViewById(R.id.signup);

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
		// Sign up Button Click Listener
		signup.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {

				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();
				
				// Force user to fill up the form
				if (usernametxt.equals("") && passwordtxt.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Por favor complete o formulario de inscrici�n",
							Toast.LENGTH_LONG).show();

				} else {
					// Save new user data into Parse.com Data Storage
					ParseUser user = new ParseUser();
					user.setUsername(usernametxt);
					user.setPassword(passwordtxt);
					user.signUpInBackground(new SignUpCallback() {
						public void done(ParseException e) {
							if (e == null) {
								// Show a simple Toast message upon successful registration
								Toast.makeText(getApplicationContext(),
										"Con �xito rexistrado, por favor conectarse .",
										Toast.LENGTH_LONG).show();
							} else {
								Toast.makeText(getApplicationContext(),
										"Error no rexistro", Toast.LENGTH_LONG)
										.show();
							}
						}
					});
				}

			}
		});

	}
}