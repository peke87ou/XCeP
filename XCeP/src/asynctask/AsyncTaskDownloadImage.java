package asynctask;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.irina.xcep.model.Supermercado;
import com.parse.ParseException;

public class AsyncTaskDownloadImage extends AsyncTask<Object, Void, Bitmap>{
	
	private WeakReference<ImageView> imageViewWeakReference;
	
	public AsyncTaskDownloadImage(ImageView imView){
		imageViewWeakReference = new WeakReference<ImageView>(imView);
	}

	@Override
	protected Bitmap doInBackground(Object... params) {
		String urlBitmap = (String) params[0];
		Supermercado superRelacionado = (Supermercado) params[1];
		
		Bitmap bmp = null;
		
		try {
			InputStream input = new URL(urlBitmap).openStream();
    		 bmp = BitmapFactory.decodeStream(input);
    		 //if(bmp != null) mImagenes.put(superRelacionado.getObjectId(), bmp);
    	 } catch(IOException e){
    		 Log.e("Error", "Error retrieving input stream");
    	 }
		
		return bmp;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		if(imageViewWeakReference != null && result != null){
				final ImageView imageView = imageViewWeakReference.get();
				if(imageView != null) imageView.setImageBitmap(result);
		}
		
	}
	
}