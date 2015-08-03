package com.irina.xcep;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.irina.xcep.model.Supermercado;
import com.parse.ParseException;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;


public class AddMarketActivity extends Activity{
	
	ButtonRectangle btncancel, btnacept;
	protected static final int CAMERA_REQUEST = 0;
	protected static final int GALLERY_PICTURE = 1;
	Bitmap bitmap;
	ImageView img_logo;
    String selectedImagePath;
    private Intent pictureActionIntent = null;
   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		setContentView(R.layout.activity_new_market);
		
		btncancel = (ButtonRectangle) findViewById(R.id.cancel_new_market);
		btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		btnacept = (ButtonRectangle) findViewById(R.id.add_new_market);
		btnacept.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Engadimos a nova lista a BD
				
				engadirmarket();
				
				
			}
		});
		
		ImageView img_logo;
		img_logo= (ImageView) findViewById(R.id.image_view_market);
		img_logo.setOnClickListener(new OnClickListener() {
		        public void onClick(View v) {
		            //openImageIntent();
		        	 startDialog();
		            
		        }

		    });
		
	}
	
	private void startDialog() {
	    AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
	    myAlertDialog.setTitle("Upload Pictures Option");
	    myAlertDialog.setMessage("How do you want to set your picture?");

	    myAlertDialog.setPositiveButton("Gallery",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                    pictureActionIntent = new Intent(
	                            Intent.ACTION_GET_CONTENT, null);
	                    pictureActionIntent.setType("image/*");
	                    pictureActionIntent.putExtra("return-data", true);
	                    startActivityForResult(pictureActionIntent,
	                            GALLERY_PICTURE);
	                }
	            });

	    myAlertDialog.setNegativeButton("Camera",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                    pictureActionIntent = new Intent(
	                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	                    startActivityForResult(pictureActionIntent,
	                            CAMERA_REQUEST);

	                }
	            });
	    myAlertDialog.show();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		ImageView fotomarket = (ImageView) findViewById(R.id.image_view_market);

	    super.onActivityResult(requestCode, resultCode, data);
	    if (requestCode == GALLERY_PICTURE) {
	        if (resultCode == RESULT_OK) {
	            if (data != null) {
	                // our BitmapDrawable for the thumbnail
	                BitmapDrawable bmpDrawable = null;
	                // try to retrieve the image using the data from the intent
	                Cursor cursor = getContentResolver().query(data.getData(),
	                        null, null, null, null);
	                if (cursor != null) {

	                    cursor.moveToFirst();

	                    int idx = cursor.getColumnIndex(ImageColumns.DATA);
	                    String fileSrc = cursor.getString(idx);
	                    bitmap = BitmapFactory.decodeFile(fileSrc); // load
	                                                                        // preview
	                                                                        // image
	                    bitmap = Bitmap.createScaledBitmap(bitmap,
	                            100, 100, false);
	                    // bmpDrawable = new BitmapDrawable(bitmapPreview);
	                    img_logo.setImageBitmap(bitmap);
	                } else {

	                    bmpDrawable = new BitmapDrawable(getResources(), data
	                            .getData().getPath());
	                    img_logo.setImageDrawable(bmpDrawable);
	                }

	            } else {
	                Toast.makeText(getApplicationContext(), "Cancelled",
	                        Toast.LENGTH_SHORT).show();
	            }
	        } else if (resultCode == RESULT_CANCELED) {
	            Toast.makeText(getApplicationContext(), "Cancelled",
	                    Toast.LENGTH_SHORT).show();
	        }
	    } else if (requestCode == CAMERA_REQUEST) {
	        if (resultCode == RESULT_OK) {
	            if (data.hasExtra("data")) {

	                // retrieve the bitmap from the intent
	                bitmap = (Bitmap) data.getExtras().get("data");


	 Cursor cursor = getContentResolver()
	                        .query(Media.EXTERNAL_CONTENT_URI,
	                                new String[] {
	                                        Media.DATA,
	                                        Media.DATE_ADDED,
	                                        MediaStore.Images.ImageColumns.ORIENTATION },
	                                Media.DATE_ADDED, null, "date_added ASC");
	                if (cursor != null && cursor.moveToFirst()) {
	                    do {
	                        Uri uri = Uri.parse(cursor.getString(cursor
	                                .getColumnIndex(Media.DATA)));
	                        selectedImagePath = uri.toString();
	                    } while (cursor.moveToNext());
	                    cursor.close();
	                }

	                Log.e("path of the image from camera ====> ",
	                        selectedImagePath);


	                bitmap = Bitmap.createScaledBitmap(bitmap, 100,
	                        100, false);
	                // update the image view with the bitmap
	                img_logo.setImageBitmap(bitmap);
	            } else if (data.getExtras() == null) {

	                Toast.makeText(getApplicationContext(),
	                        "No extras to retrieve!", Toast.LENGTH_SHORT)
	                        .show();

	                BitmapDrawable thumbnail = new BitmapDrawable(
	                        getResources(), data.getData().getPath());

	                // update the image view with the newly created drawable
	                img_logo.setImageDrawable(thumbnail);

	            }

	        } else if (resultCode == RESULT_CANCELED) {
	            Toast.makeText(getApplicationContext(), "Cancelled",
	                    Toast.LENGTH_SHORT).show();
	        }
	    }

	}
	
	public String getPath(Uri uri, Activity activity) {
	    String[] projection = { MediaColumns.DATA };
	    Cursor cursor = activity
	            .managedQuery(uri, projection, null, null, null);
	    int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
	    cursor.moveToFirst();
	    return cursor.getString(column_index);
	}
	
//	private Uri outputFileUri;
//
//	private void openImageIntent() {
//
//	// Determine Uri of camera image to save.
//	final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "MyDir" + File.separator);
//	root.mkdirs();
//	final String fname = File.createTempFile()
//			//"img_"+ System.currentTimeMillis() + ".jpg" ;
//			//Utils.getUniqueImageFilename();
//	final File sdImageMainDirectory = new File(root, fname);
//	outputFileUri = Uri.fromFile(sdImageMainDirectory);
//
//	    // Camera.
//	    final List<Intent> cameraIntents = new ArrayList<Intent>();
//	    final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//	    final PackageManager packageManager = getPackageManager();
//	    final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
//	    for(ResolveInfo res : listCam) {
//	        final String packageName = res.activityInfo.packageName;
//	        final Intent intent = new Intent(captureIntent);
//	        intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//	        intent.setPackage(packageName);
//	    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//	        cameraIntents.add(intent);
//	    }
//
//	    // Filesystem.
//	    final Intent galleryIntent = new Intent();
//	    galleryIntent.setType("image/*");
//	    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//
//	    // Chooser of filesystem options.
//	    final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
//
//	    // Add the camera options.
//	    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));
//
//	    startActivityForResult(chooserIntent, CAMERA_REQUEST);
//	}
//
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		ImageView fotomarket = (ImageView) findViewById(R.id.image_view_market);
//		Uri selectedImageUri = null;
//	    if (resultCode == RESULT_OK) {
//	        if (requestCode == CAMERA_REQUEST) {
//	            final boolean isCamera;
//	            if (data == null) {
//	                isCamera = true;
//	            } else {
//	                final String action = data.getAction();
//	                if (action == null) {
//	                    isCamera = false;
//	                } else {
//	                    isCamera = action.equals(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//	                }
//	            }
//
//	            
//	            if (isCamera) {
//	                selectedImageUri = outputFileUri;
//	            } else {
//	                selectedImageUri = data == null ? null : data.getData();
//	            }
//	           
//	           
//	            
//	        }
//	       
//	    }
//	    Log.i("URI", selectedImageUri + "");
//	    fotomarket.setImageURI(selectedImageUri);
//	}

	
	//Métodos empregados nesta clase
			/**
			 * Engade un supermercado a BD
			 * @param market: supermercado que se engade a BD
			 */
			public void engadirmarket(){
				
				
				String namemarketTxt ="";
				Supermercado addmarket = new Supermercado();
				
								
				//Nome
				 EditText nameMarket = (EditText) findViewById(R.id.text_name_market);
				 namemarketTxt = nameMarket.getText().toString();
				 addmarket.setNome(namemarketTxt);
							
				//Marca
				
				//foto
				 ImageView fotomarket = (ImageView) findViewById(R.id.image_view_market);
//				 addmarket.setUrlLogo(selectedImagePath);
//				 
//				 final ParseFile fileObject = selectedImagePath; 
//                 String urlBitmap = fileObject.getUrl(); 
                 Picasso.with(AddMarketActivity.this).load(selectedImagePath).into(fotomarket);
//				 String fotomarketoTxt = fotomarket.getText().toString();
//				 addmarket.setUrlImaxe(fotomarketoTxt);
			
			 
				 addmarket.saveInBackground(new SaveCallback() {
					
					@Override
					public void done(ParseException arg0) {
						if (arg0 == null){
							Toast.makeText(AddMarketActivity.this, "Engadimos O supermercado a BD ", Toast.LENGTH_SHORT).show();
							Log.i("market", "Engadimos O supermercado a BD ");
							finish();
							
						}else{
							Toast.makeText(AddMarketActivity.this, R.string.error_add_list+" " + arg0.getMessage(), Toast.LENGTH_SHORT).show();
							Log.e("market", "ERROR O ENGADIR NA BD ");
						}
					}
				});
			}
						
						
}



