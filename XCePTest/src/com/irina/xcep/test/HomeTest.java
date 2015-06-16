package com.irina.xcep.test;

import com.gc.materialdesign.views.ButtonRectangle;
import com.irina.xcep.HomeActivity;
import com.irina.xcep.R;
import com.irina.xcep.LoginActivity;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Suppress;
import android.widget.EditText;

public class HomeTest extends ActivityInstrumentationTestCase2<HomeActivity> {
	
	private Solo solo;
	private HomeActivity home;
	static int TIME_OUT_LOGIN = 30000;

	public HomeTest(Class<HomeActivity> activityClass) {
		super(activityClass);
	}

	public HomeTest() {
		this(HomeActivity.class);
	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo= new Solo(getInstrumentation(), getActivity());
		home = (HomeActivity) solo.getCurrentActivity();
	}
	
	@Override
    protected void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
	
	
	public void testDesconectar() {
		final EditText username  = (EditText) home.findViewById(R.id.username);
		final EditText password  = (EditText) home.findViewById(R.id.password);
		final ButtonRectangle buttonlogin = (ButtonRectangle) home.findViewById(R.id.login);
		solo.enterText(username, "irina");
		solo.enterText(password, "irina");
		solo.clickOnView(buttonlogin);
		solo.assertCurrentActivity("Actividad incorrecta", HomeActivity.class);

	}
	
	
	public void testLoginFailed() {
		final EditText username  = (EditText) home.findViewById(R.id.username);
		final EditText password  = (EditText) home.findViewById(R.id.password);
		final ButtonRectangle buttonlogin = (ButtonRectangle) home.findViewById(R.id.login);
		solo.enterText(username, "irina");
		solo.enterText(password, "passmal");
		solo.clickOnView(buttonlogin);
		solo.searchText("Este usuario non existe, por favor rexistrese");
	}
}
