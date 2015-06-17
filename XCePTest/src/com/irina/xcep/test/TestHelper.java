package com.irina.xcep.test;

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
	
	
}
