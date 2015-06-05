package com.irina.xcep.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.irina.xcep.R;
import com.irina.xcep.model.Lista;

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
       
       
       
       return celdaView;
   }
}
