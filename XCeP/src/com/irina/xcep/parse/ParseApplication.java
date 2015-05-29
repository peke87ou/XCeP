package com.irina.xcep.parse;

import com.irina.xcep.model.Lista;
import com.irina.xcep.model.Supermercado;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;
import android.app.Application;
 
public class ParseApplication extends Application {
 
    @Override
    public void onCreate() {
        super.onCreate();
 
        // Add your initialization code here
        Parse.initialize(this, "wtEK7WzQx7cytFc1ogtpNxH5aPqDhipqlZQkFaPX", 		//YOUR_APPLICATION_ID
        		"WC1Xbw9HsZv17VyW0VXRjTC4To9PsGRDUKiwU4Mq");					//YOUR_CLIENT_KEY
        
        ParseObject.registerSubclass(Supermercado.class);
        ParseObject.registerSubclass(Lista.class);
        //ParseObject.registerSubclass(Producto.class);
        
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
 
        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
 
        ParseACL.setDefaultACL(defaultACL, true);
    }
 
}