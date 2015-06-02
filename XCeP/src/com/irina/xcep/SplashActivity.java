package com.irina.xcep;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.gc.materialdesign.views.ButtonRectangle;


public class SplashActivity extends Activity implements OnClickListener {

	ButtonRectangle btnSignIn;
	ButtonRectangle btnSignUp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        btnSignIn = (ButtonRectangle) findViewById(R.id.btnSingIn);
        btnSignUp = (ButtonRectangle) findViewById(R.id.btnSignUp);
        
        
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        
        
    }

	public void onClick(View v) {
		Intent i = null;
		switch(v.getId()){
			case R.id.btnSingIn:
				i = new Intent(this,LoginActivity.class);
				break;
			case R.id.btnSignUp:
				i = new Intent(this,SignupActivity.class);
				break;
		}
		startActivity(i);
		
	}

	
}
