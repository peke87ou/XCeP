package com.irina.xcep.test;

import com.irina.xcep.R;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;

import com.gc.materialdesign.views.ButtonRectangle;
import com.irina.xcep.HomeActivity;
import com.irina.xcep.test.enums.LoginEnum;
import com.irina.xcep.test.enums.SignUpEnum;
import com.robotium.solo.Solo;

public class TestHelper {
	
	private Solo solo;
	private LoginEnum loginData;
	private SignUpEnum registerData;
	
	public TestHelper(Solo s) {
		solo= s;
	}
	
	public void setLoginData(LoginEnum loginData){
		this.loginData = loginData;
	}

	public String getUser(){
		return this.loginData.getUser();
	}
	
	public String getPass(){
		return this.loginData.getPass();
	}
	
	public void setSignData(SignUpEnum SignData){
		this.registerData = SignData;
	}
	public String getUserSign(){
		return this.registerData.getUser();
	}
	
	public String getPassSing(){
		return this.registerData.getPass();
	}
	public String getRePassSign(){
		return this.registerData.getRepass();
	}
	
	public String getEmailSign(){
		return this.registerData.getMail();
	}
	
	public void logout() {
		if(solo.waitForActivity(HomeActivity.class)){
			solo.assertCurrentActivity("Actividad incorrecta", HomeActivity.class);
			HomeActivity home = (HomeActivity) solo.getCurrentActivity();
			final ButtonRectangle buttondesconectar =  (ButtonRectangle) home.findViewById(R.id.logout);
			solo.clickOnView(buttondesconectar);
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				Log.e(SignUpTest.class.getName(),"Interrupted Exception");
			}
		}
	}
}
