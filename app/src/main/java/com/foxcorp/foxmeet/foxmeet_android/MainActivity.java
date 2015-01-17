package com.foxcorp.foxmeet.foxmeet_android;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;


public class MainActivity extends Activity {
		ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Window win = getWindow();
		win.setNavigationBarColor(getResources().getColor(R.color.primaryDark));

//		new SendCommadn().execute("stuart4@purdue.edu,events");

		listView = (ListView) findViewById(R.id.eventsView);
		ArrayList<Event> events = new ArrayList<Event>();
		for (int i = 0; i < 15; i++) {
			Event e = new Event();
			e.title = "<html><b>Upcoming</b></html>";
			e.body = "something something something";
			events.add(e);
		}
		FancyAdapter fa = new FancyAdapter(this, events);
		listView.setAdapter(fa);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
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

	public void openContacts(View v) {
		PackageManager pm = getApplicationContext().getPackageManager();
		Intent i = pm.getLaunchIntentForPackage("com.google.android.contacts");
		getApplicationContext().startActivity(i);
	}

	public void openGroups (View v) {
		Intent intent = new Intent(this, GroupActivity.class);
		startActivity(intent);
	}

	public void makeNewEvent (View v) {
		Intent intent = new Intent(this, NewEvent.class);
		startActivity(intent);
	}
}
