package com.irina.xcep.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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
//       ((TextView) celdaView.findViewById(R.id.products_list)).setText(lista.getProductos().size()+ R.string.products);
       //((ImageView) celdaView.findViewById(R.id.imageMarketList)).set(lista.getIdSupermercado().getQuery().getClass().getMethods().);
//      ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Market");
       
////       ParseRelation<ParseObject> relation = lista.getRelation("Market");
//       ParseQuery<ParseObject> query = lista.getIdSupermercado().getQuery().orderByAscending(lista.getObjectId());
//       ((ImageView) celdaView.findViewById(R.id.imageMarketList)).set(query.get(lista.getObjectId()).getParseFile(Lista.));
//
       ParseRelation<ParseObject> relation = lista.getRelation("Market");
       
       
       ParseQuery<ParseObject> query = relation.getQuery();
       query.whereEqualTo("idMarket", lista.getIdSupermercado());
       try {
		Log.i("idmarket",   query.whereEqualTo("idMarket", lista.getIdSupermercado()).find() +"");
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//       ((ImageView) celdaView.findViewById(R.id.imageMarketList)).set(query.whereEqualTo("idMarket", lista.getIdSupermercado()));
       
//       query.findInBackground(new FindCallback<ParseObject>() {
//    	    public void done(List<ParseObject> list, ParseException e) {
//    	        if (e == null) {
//    	            for (ParseObject object : list) {
//
//    	                ParseFile image = (ParseFile) object.get("images_column_name");
//
//    	            }
//    	        } 
//    	    }
//    	});
//       
       
       return celdaView;
   }
}
