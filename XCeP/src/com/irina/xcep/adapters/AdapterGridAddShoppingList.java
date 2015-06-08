package com.irina.xcep.adapters;

import java.util.ArrayList;
import java.util.List;

import com.irina.xcep.R;
import com.irina.xcep.model.Supermercado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterGridAddShoppingList extends ArrayAdapter<Supermercado>{
	
   
      public AdapterGridAddShoppingList(Context context, int resource,
			int textViewResourceId, List<Supermercado> objects) {
		super(context, resource, textViewResourceId, objects);
	}

	@Override
      public View getView(int position, View convertView, ViewGroup parent) {
          View grid;
          LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          
          if (convertView == null) {

              grid = new View(mContext);
              grid = inflater.inflate(R.layout.grid_add_shopping_list, null);
              TextView textView = (TextView) grid.findViewById(R.id.title_market_add_list);
              ImageView imageView = (ImageView)grid.findViewById(R.id.image_market_add_list);
              textView.setText(objects);
              imageView.setImageResource(Imageid[position]);
          } else {
              grid = (View) convertView;
          }

          return grid;
      }

}
