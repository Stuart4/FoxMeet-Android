package com.foxcorp.foxmeet.foxmeet_android;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.LinkedList;


public class NewEvent extends Activity {

	LinkedList<String> email = new LinkedList<>();
	ArrayAdapter<String> adapter;
	ListView listView;
	EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event);
		listView = (ListView) findViewById(R.id.newEventListView);
		editText = (EditText) findViewById(R.id.newEventEditText);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, email);
		listView.setAdapter(adapter);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_new_event, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void addEmail (View v) {
		if (editText.getText().equals(""))
			return;
		email.add(editText.getText().toString());
		editText.setText("");
		adapter.notifyDataSetChanged();
	}


	public void getContact (View v) {
		Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
		pickContactIntent.setType(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE); // Show user only contacts w/ phone numbers
		startActivityForResult(pickContactIntent, 1);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				Uri contactUri = data.getData();
				String[] projection = {ContactsContract.CommonDataKinds.Email.ADDRESS};
				Cursor cursor = getContentResolver()
						.query(contactUri, projection, null, null, null);
				cursor.moveToFirst();

				int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS);
				String address = cursor.getString(column);
				editText.setText(address);
			}
		}
	}
}
