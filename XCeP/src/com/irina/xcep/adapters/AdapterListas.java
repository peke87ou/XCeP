package com.irina.xcep.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.irina.xcep.R;
import com.irina.xcep.model.Lista;
import com.irina.xcep.model.Supermercado;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

public class AdapterListas extends ArrayAdapter<Lista> {
    
	public AdapterListas(Context context, ArrayList<Lista> lista) {
       super(context, 0, lista);
    }

    @Override
    public View getView(int position, View celdaView, ViewGroup parent) {
       
    	// Get the data item for this position
       Lista lista = getItem(position);    
       
       // Check if an existing view is being reused, otherwise inflate the view
       if (celdaView == null) {
          celdaView = LayoutInflater.from(getContext()).inflate(R.layout.item_shopping_list, parent, false);
       }
       
       // Lookup view for data population
       ((TextView) celdaView.findViewById(R.id.name_list)).setText(lista.getNome());

       final ImageView imageView = (ImageView)celdaView.findViewById(R.id.imageMarketList);
       ParseRelation<ParseObject> relation = lista.getRelation("idMarket");
       ParseQuery<ParseObject> query = relation.getQuery();
       
       query.findInBackground(new FindCallback<ParseObject>() {
    	   public void done(List<ParseObject> list, ParseException e) {
    	       if (e == null) {
    	           for (ParseObject object : list) {

    	              Supermercado superRelacionado = ((Supermercado)object);
    	              System.out.println("Supermercado encontrado " + superRelacionado.getNome());
    	              
    	              ParseFile fileObject = superRelacionado.getUrlLogo(); 
    	              Bitmap bmp = null;
    	 try {
    		 bmp = BitmapFactory.decodeByteArray(fileObject.getData(), 0, fileObject.getData().length);
    	 } catch (ParseException ee) {
    		 ee.printStackTrace();
    	 }
    	         
    	imageView.setImageBitmap(bmp);
    	           }
    	       } 
    	   }
    	});
     
       
       return celdaView;
   }
}
