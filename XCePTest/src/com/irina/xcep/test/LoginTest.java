package com.irina.xcep.test;

import com.gc.materialdesign.views.ButtonRectangle;
import com.irina.xcep.HomeActivity;
import com.irina.xcep.R;
import com.irina.xcep.LoginActivity;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Suppress;
import android.widget.EditText;

public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {
	
	private Solo solo;
	private LoginActivity login;
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
	}
	
	@Override
    protected void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
	
	@Suppress
	public void testLogin() {
		final EditText username  = (EditText) login.findViewById(R.id.username);
		final EditText password  = (EditText) login.findViewById(R.id.password);
		final ButtonRectangle buttonlogin = (ButtonRectangle) login.findViewById(R.id.login);
		solo.enterText(username, "irina");
		solo.enterText(password, "irina");
		solo.clickOnView(buttonlogin);
		solo.assertCurrentActivity("Actividad incorrecta", HomeActivity.class);

	}
	
	@Suppress
	public void testLoginFailed() {
		final EditText username  = (EditText) login.findViewById(R.id.username);
		final EditText password  = (EditText) login.findViewById(R.id.password);
		final ButtonRectangle buttonlogin = (ButtonRectangle) login.findViewById(R.id.login);
		solo.enterText(username, "irina");
		solo.enterText(password, "passmal");
		solo.clickOnView(buttonlogin);
		solo.searchText("Este usuario non existe, por favor rexistrese");
	}
}
