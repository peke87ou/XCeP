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
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends Activity {
	//Declaramos as variables
	ButtonRectangle signup;
	EditText username;
	EditText password;
	EditText repassword;
	EditText email;
	
	String usernametxt;
	String passwordtxt;
	String repasswordtxt;
	String emailtxt;
	TextView linklogin;
	
	/** Chamase cando creouse por primera vez a actividad */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from main.xml
		setContentView(R.layout.activity_signup);
		
		getActionBar().setTitle(R.string.title_action_bar_signup);
		
		// Locate EditTexts in main.xml
		username = (EditText) findViewById(R.id.signup_username_input);
		password = (EditText) findViewById(R.id.signup_password_input);
		repassword = (EditText) findViewById(R.id.signup_confirm_password_input);
		email = (EditText) findViewById(R.id.signup_email_input);
		

		// Locate Buttons in main.xml
		signup = (ButtonRectangle) findViewById(R.id.create_account);
		linklogin = (TextView) findViewById(R.id.link_login);
		
		// Sign up Button Click Listener
		signup.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {

				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();
				repasswordtxt = repassword.getText().toString();
				emailtxt = email.getText().toString();
				
				// Force user to fill up the form
				if (usernametxt.equals("") || passwordtxt.equals("") || repasswordtxt.equals("") || email.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Por favor complete o formulario de inscrición",
							Toast.LENGTH_LONG).show();

				}else if (!passwordtxt.equals(repasswordtxt)){
					Toast.makeText(getApplicationContext(),
							"Las contraseñas no coinciden",
							Toast.LENGTH_LONG).show();
				}else {
					
				
					// Save new user data into Parse.com Data Storage
					ParseUser user = new ParseUser();
					user.setUsername(usernametxt);
					user.setPassword(passwordtxt);
					user.setEmail(emailtxt);
					user.signUpInBackground(new SignUpCallback() {
						public void done(ParseException e) {
							if (e == null) {
								// Show a simple Toast message upon successful registration
								Toast.makeText(getApplicationContext(),
										"Con éxito rexistrado, por favor conectarse .",
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
		
		linklogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Ir a páxina de rexistro
				Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
				startActivity(intent);
				
			}
		});

	}
}
