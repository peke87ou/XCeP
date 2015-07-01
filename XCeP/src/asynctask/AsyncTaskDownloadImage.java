package asynctask;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.irina.xcep.model.Supermercado;
import com.parse.ParseException;
import com.parse.ParseFile;

public class AsyncTaskDownloadImage extends AsyncTask<Object, Void, Bitmap>{
	
	private WeakReference<ImageView> imageViewWeakReference;
	
	public AsyncTaskDownloadImage(ImageView imView){
		imageViewWeakReference = new WeakReference<ImageView>(imView);
	}

	@Override
	protected Bitmap doInBackground(Object... params) {
		ParseFile fileObject = (ParseFile) params[0];
		Supermercado superRelacionado = (Supermercado) params[1];
		
		Bitmap bmp = null;
		
		try {
    		 bmp = BitmapFactory.decodeByteArray(fileObject.getData(), 0, fileObject.getData().length);
    		 //if(bmp != null) mImagenes.put(superRelacionado.getObjectId(), bmp);
    	 } catch (ParseException ee) {
    		 ee.printStackTrace();
    	 }
		
		return bmp;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		if(imageViewWeakReference != null && result != null){
			final ImageView imageView = imageViewWeakReference.get();
			imageView.setImageBitmap(result);
		}
		super.onPostExecute(result);
	}
	
}