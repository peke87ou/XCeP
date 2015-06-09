package com.irina.xcep.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.irina.xcep.R;
import com.irina.xcep.model.Supermercado;
import com.parse.ParseException;
import com.parse.ParseFile;

public class AdapterGridAddShoppingList extends ArrayAdapter<Supermercado>{
	 Context mContext;
   
      public AdapterGridAddShoppingList(Context context, List<Supermercado> lista) {
		super(context,0, lista);
		mContext = context;
	}

	@Override
      public View getView(int position, View convertView, ViewGroup parent) {
          View grid;
          LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          Supermercado market = getItem(position);  
          
          if (convertView == null) {

              grid = new View(mContext);
              grid = inflater.inflate(R.layout.item_grid_add_shopping_list, null);
              TextView textView = (TextView) grid.findViewById(R.id.title_market_add_list);
              ImageView imageView = (ImageView)grid.findViewById(R.id.image_market_add_list);
              textView.setText(market.getNome());

              ParseFile fileObject = market.getUrlLogo(); 
              Bitmap bmp = null;
			  try {
				  bmp = BitmapFactory.decodeByteArray(fileObject.getData(), 0, fileObject.getData().length);
			  } catch (ParseException e) {
					e.printStackTrace();
			  }
	          
			  imageView.setImageBitmap(bmp);
          
          } else {
        	  grid = (View) convertView;
	      }
          return grid;
      }

}
