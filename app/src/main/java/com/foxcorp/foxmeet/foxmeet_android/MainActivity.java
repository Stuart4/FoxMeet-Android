package com.foxcorp.foxmeet.foxmeet_android;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;


public class MainActivity extends Activity {
		ListView listView;
	static String personalEmail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Window win = getWindow();
		win.setNavigationBarColor(getResources().getColor(R.color.primaryDark));
		listView = (ListView) findViewById(R.id.eventsView);

		try {
//			new SendCommand(listView, this).execute("jacob@gmail.com").get();
		} catch (Exception exceptional) {
			exceptional.printStackTrace();
		}

		ArrayList<Event> events = new ArrayList<Event>();
		for (int i = 0; i < 15; i++) {
			Event e = new Event();
			e.title = "<html><b>Upcoming</b></html>";
			e.body = "something something something";
			events.add(e);
		}
		FancyAdapter fa = new FancyAdapter(this, events);
		listView.setAdapter(fa);

		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] accounts = AccountManager.get(this).getAccounts();
		for (Account account : accounts) {
			if (emailPattern.matcher(account.name).matches()) {
				personalEmail = account.name;
			}
		}

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
