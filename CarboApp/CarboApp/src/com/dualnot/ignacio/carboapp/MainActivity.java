package com.dualnot.ignacio.carboapp;


import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dualnot.ignacio.carboapp.bean.Aliment;
import com.dualnot.ignacio.carboapp.data.AlimentsDataSource;

public class MainActivity extends ActionBarActivity {
	public static final String EXTRA_MESSAGE = "com.dualnot.ignacio.MESSAGE";
	
	public void searchAlimentByName(View view){
		/*Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);*/
		EditText alimentText = (EditText) findViewById(R.id.edit_aliment);
		String alimentName = alimentText.getText().toString();
		AlimentsDataSource alimentsData = new AlimentsDataSource(this);
		alimentsData.open();
		Aliment aliment = search(alimentName,alimentsData.getAllAliments());
		if (aliment == null){
			showAlimentNotFound(alimentName);
		} else {
			showAliment(aliment);
		}
	}
	private Aliment search(String alimentName,List<Aliment> aliments){
		for (Aliment aliment : aliments) {
			if (aliment.name.equalsIgnoreCase(alimentName)){
				return aliment;
			}
		}
		return null;
	}
	private void showAlimentNotFound(String alimentName){
		String text = String.format(
				getResources().getString(R.string.aliment_not_found), 
				alimentName);
		Toast toast = Toast.makeText(
				getApplicationContext(), 
				text, 
				Toast.LENGTH_LONG);
		toast.show();
	}
	private void showAliment(Aliment aliment){
		String gramsPerRation = String.format(
				getResources().getString(R.string.grams_per_ration), 
				aliment.getName(), aliment.getGrams_per_ration());
		TextView gramsPerRatioText = (TextView) findViewById(R.id.grams_per_ration);
		gramsPerRatioText.setText(gramsPerRation);
		String rationsInPicture = String.format(
				getResources().getString(R.string.rations_in_picture), 
				aliment.rations_in_picture);
		TextView rationsInPictureText = (TextView) findViewById(R.id.rations_in_picture);
		rationsInPictureText.setText(rationsInPicture);
		//A picture for aliment will be shown in case there is any.
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}*/
		switch (item.getItemId()) {
			case R.id.action_search: 
				openSearch();
				return true;
			case R.id.action_camera: 
				openCamera();
				return true;
			case R.id.action_new:
				//A new activity will request all data for new Aliment
				openNew();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}
	
	public void openSearch(){
		Toast.makeText(getApplicationContext(), "Search!", Toast.LENGTH_SHORT).show();
	}
	
	public void openCamera(){
		Toast.makeText(getApplicationContext(), "Camera!", Toast.LENGTH_SHORT).show();
	}
	
	public void openNew(){
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		startActivity(intent);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
