package com.irina.xcep.test;

import com.gc.materialdesign.views.ButtonRectangle;
import com.irina.xcep.HomeActivity;
import com.irina.xcep.R;
import com.irina.xcep.LoginActivity;
import com.irina.xcep.SignupActivity;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Suppress;
import android.widget.EditText;

public class SignUpTest extends ActivityInstrumentationTestCase2<SignupActivity> {
	
	private Solo solo;
	private SignupActivity singup;
	static int TIME_OUT_SINGUP = 30000;

	public SignUpTest(Class<SignupActivity> activityClass) {
		super(activityClass);
	}

	public SignUpTest() {
		this(SignupActivity.class);
	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo= new Solo(getInstrumentation(), getActivity());
		singup = (SignupActivity) solo.getCurrentActivity();
	}
	
	@Override
    protected void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
	
	@Suppress
	public void testSingup() {
		final EditText username  = (EditText) singup.findViewById(R.id.username);
		final EditText password  = (EditText) singup.findViewById(R.id.password);
		final ButtonRectangle buttonlogin = (ButtonRectangle) singup.findViewById(R.id.login);
		solo.enterText(username, "irina");
		solo.enterText(password, "irina");
		solo.clickOnView(buttonlogin);
		solo.assertCurrentActivity("Actividad incorrecta", HomeActivity.class);

	}
	
	@Suppress
	public void testSingupFailed() {
		final EditText username  = (EditText) singup.findViewById(R.id.username);
		final EditText password  = (EditText) singup.findViewById(R.id.password);
		final ButtonRectangle buttonlogin = (ButtonRectangle) singup.findViewById(R.id.login);
		solo.enterText(username, "irina");
		solo.enterText(password, "passmal");
		solo.clickOnView(buttonlogin);
		solo.searchText("Este usuario non existe, por favor rexistrese");
	}
}
