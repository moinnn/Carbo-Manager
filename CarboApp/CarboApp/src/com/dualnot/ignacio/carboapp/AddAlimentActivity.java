package com.dualnot.ignacio.carboapp;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.dualnot.ignacio.carboapp.data.AlimentsDataSource;

public class AddAlimentActivity extends ActionBarActivity {

	NumberPicker MyNumPicker1;
	TextView gramsRationTextView, rationsPictureTextView;
	AlertDialog alertdialog;
	ImageView imageView1;
	private Uri fileUri;
	public static final int MAXGRAMSPERRATIONVALUE = 350;
	public static final int MINGRAMSPERRATIONVALUE = 0;
	public static final int MAXRATIONSINPICVALUE = 10;
	public static final int MINRATIONSINPICVALUE = 0;
	public static final int CAMERA_CODE = 0;
	public static final int GALLERY_CODE = 1;
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
	public static final String APP_NAME = "Carbo App";

	
	
	public void choosePicture(View view){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(
				AddAlimentActivity.this);

		builder.setTitle(R.string.pick_picture);
		builder.setItems(R.array.picture_sources, 
			new DialogInterface.OnClickListener() {
				

				@Override
				public void onClick(DialogInterface dialog,
							int which) {
					switch (which){
					case CAMERA_CODE: 
						Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
						//fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
					    //intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
						startActivityForResult(intent, CAMERA_CODE);//zero can be replaced with any action code
						break;
					case GALLERY_CODE: 
						Intent pickPhoto = new Intent(Intent.ACTION_PICK,
						           android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(pickPhoto , GALLERY_CODE);//second arg can be replaced with any action code
						break;
					default:
						break;
					}
				}
			}
		);
		
		alertdialog = builder.create();
		alertdialog.show();

	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 
		switch(requestCode) {
		case CAMERA_CODE:
		    if(resultCode == RESULT_OK){ 
		    	//no deja imagen en galeria ... carga la foto directamente de la camara al imageView
		    	//Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
		    	//imageView1 = (ImageView) findViewById(R.id.imageView1);
		        //imageView1.setImageBitmap(photo);
		    	//Uri uri = (Uri) imageReturnedIntent.getBundleExtra(MediaStore.EXTRA_OUTPUT);
		    	//imageView1 = (ImageView) findViewById(R.id.imageView1);
		        //imageView1.setImageURI(uri);
		        
		        
		    }
		break; 
		case GALLERY_CODE:
		    if(resultCode == RESULT_OK){  
		        Uri selectedImage = imageReturnedIntent.getData();
		        //imageView1 = (ImageView) findViewById(R.id.imageView1);
		        imageView1.setImageURI(selectedImage);
		    }
		break;
		}
	}
	
	@Override
	protected void onStop(){
		super.onStop();  // Always call the superclass method first

	    // Save aliment
		AlimentsDataSource alimentDS = new AlimentsDataSource(this);
		EditText name_et = (EditText) findViewById(R.id.edit_aliment_name);
		String name = name_et.getText().toString();
		TextView gramsPerRation_tv = (TextView) findViewById(R.id.grams_picker);
		Integer grams_per_ration = Integer.parseInt(gramsPerRation_tv.getText().toString());
		Integer rations_in_picture = 0;
		alimentDS.open();
		alimentDS.createAliment(name, grams_per_ration, rations_in_picture);
		alimentDS.close();
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_add_aliment);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		//why this fails? R.id.container not found? WTF!
		//if (savedInstanceState == null) {
			//getSupportFragmentManager().beginTransaction() .add(R.id.container,
			//new PlaceholderFragment()).commit(); }
		 

		// beginning number picker grams
		gramsRationTextView = (TextView) findViewById(R.id.grams_picker);
		gramsRationTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View v1 = inflater.inflate(R.layout.numpicker, null);
				MyNumPicker1 = (NumberPicker) v1
						.findViewById(R.id.MyNunPicker1);

				MyNumPicker1.setMaxValue(MAXGRAMSPERRATIONVALUE);
				MyNumPicker1.setMinValue(MINGRAMSPERRATIONVALUE);
				MyNumPicker1.setValue(Integer.parseInt(gramsRationTextView.getText()
						.toString()));
				MyNumPicker1.setWrapSelectorWheel(true);

				AlertDialog.Builder builder = new AlertDialog.Builder(
						AddAlimentActivity.this);

				builder.setView(v1);
				builder.setTitle(R.string.grams_per_ration_label);
				builder.setPositiveButton(R.string.set,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								gramsRationTextView.setText(Integer.toString(MyNumPicker1
										.getValue()));
							}
						});

				builder.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								alertdialog.dismiss();

							}
						});

				alertdialog = builder.create();
				alertdialog.show();

			}
		});

		// end number picker grams
		
		
		// beginning number pickers rations
				/*rationsPictureTextView = (TextView) findViewById(R.id.rations_picker);
				rationsPictureTextView.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						View v1 = inflater.inflate(R.layout.numpicker, null);
						MyNumPicker1 = (NumberPicker) v1
								.findViewById(R.id.MyNunPicker1);

						MyNumPicker1.setMaxValue(MAXRATIONSINPICVALUE);
						MyNumPicker1.setMinValue(MINRATIONSINPICVALUE);
						MyNumPicker1.setValue(Integer.parseInt(rationsPictureTextView.getText()
								.toString()));
						MyNumPicker1.setWrapSelectorWheel(true);

						AlertDialog.Builder builder = new AlertDialog.Builder(
								AddAlimentActivity.this);

						builder.setView(v1);
						builder.setTitle(R.string.rations_in_picture);
						builder.setPositiveButton(R.string.set,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										rationsPictureTextView.setText(Integer.toString(MyNumPicker1
												.getValue()));
									}
								});

						builder.setNegativeButton(R.string.cancel,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										alertdialog.dismiss();

									}
								});

						alertdialog = builder.create();
						alertdialog.show();

					}
				});
*/
		// end number picker rations
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_aliment, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()){
		case R.id.action_settings:
			return true;
		case R.id.action_new_picture:
			choosePicture(item.getActionView());
			//TODO: A number picker popup to set rations in picture 
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_add_aliment,
					container, false);
			return rootView;
		}
	}
	
	/** Create a file Uri for saving an image or video */
	/*private static Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}*/

	/** Create a File for saving an image or video */
	/*private static File getOutputMediaFile(int type){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), APP_NAME );
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("MyCameraApp", "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else if(type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}*/

}
