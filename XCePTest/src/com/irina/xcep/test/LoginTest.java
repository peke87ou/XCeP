package com.irina.xcep.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.gc.materialdesign.views.ButtonRectangle;
import com.irina.xcep.HomeActivity;
import com.irina.xcep.LoginActivity;
import com.irina.xcep.R;
import com.irina.xcep.test.enums.LoginEnum;
import com.robotium.solo.Solo;

public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {
	
	private Solo solo;
	private LoginActivity login;
	private TestHelper helper;
	static int TIME_OUT_LOGIN = 30000;

	public LoginTest(Class<LoginActivity> activityClass) {
		super(activityClass);
	}

	public LoginTest() {
		this(LoginActivity.class);
	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo= new Solo(getInstrumentation(), getActivity());
		login = (LoginActivity) solo.getCurrentActivity();
		helper = new TestHelper(solo);
	}
	
	@Override
    protected void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
	
	
	public void testLogin() {
		
		helper.setLoginData(LoginEnum.CORRECTO);
		
		final EditText username  = (EditText) login.findViewById(R.id.username);
		final EditText password  = (EditText) login.findViewById(R.id.password);
		final ButtonRectangle buttonlogin = (ButtonRectangle) login.findViewById(R.id.login);
		solo.enterText(username, helper.getUser());
		solo.enterText(password, helper.getPass());
		solo.clickOnView(buttonlogin);
		solo.assertCurrentActivity("Actividad incorrecta", HomeActivity.class);

	}
	
	
	public void testLoginFailed() {
		
		helper.setLoginData(LoginEnum.INCORRECTO);
		
		final EditText username  = (EditText) login.findViewById(R.id.username);
		final EditText password  = (EditText) login.findViewById(R.id.password);
		final ButtonRectangle buttonlogin = (ButtonRectangle) login.findViewById(R.id.login);
		solo.enterText(username, helper.getUser());
		solo.enterText(password, helper.getPass());
		solo.clickOnView(buttonlogin);
		solo.searchText("Este usuario non existe, por favor rexistrese");
	}
}
