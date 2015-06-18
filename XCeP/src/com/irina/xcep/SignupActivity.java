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
import com.irina.xcep.utils.Utils;
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
				
				boolean allfilled = true;
				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();
				repasswordtxt = repassword.getText().toString();
				emailtxt = email.getText().toString();
				
				allfilled =  Utils.isNotEmpty(username, usernametxt);
				allfilled =  Utils.isNotEmpty(password, passwordtxt) && allfilled;
				allfilled =  Utils.isNotEmpty(repassword, repasswordtxt) && allfilled;
				allfilled =  Utils.isNotEmpty(email,emailtxt) && allfilled;
				if(!allfilled) return;
				
//				// Force user to fill up the form
//				if (usernametxt.equals("") || passwordtxt.equals("") || repasswordtxt.equals("") || email.equals("")) {
//					Toast.makeText(getApplicationContext(),
//							"Por favor complete o formulario de inscrición",
//							Toast.LENGTH_LONG).show();

//				}else 
				if (!passwordtxt.equals(repasswordtxt)){
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
								Intent intent = new Intent(
										SignupActivity.this,
										HomeActivity.class);
								startActivity(intent);
								Toast.makeText(getApplicationContext(),
										"Rexistrado Exitosamente ",
										Toast.LENGTH_LONG).show();
								finish();
							} else {
								String mensaje = "";
								switch (e.getCode()){
								case 125:
									mensaje = "O seu enderezo electrónico é inválido";
									break;
								case 202:
									mensaje = "O usuario que intenta rexistrar xa existe, Loguese!";
									break;
								case 203:
									mensaje = "O email que intenta rexistrar xa existe";
									break;
								default:
									mensaje = "Error no rexistro";
									break;
									
								}
								Toast.makeText(getApplicationContext(), mensaje
										, Toast.LENGTH_LONG)
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
