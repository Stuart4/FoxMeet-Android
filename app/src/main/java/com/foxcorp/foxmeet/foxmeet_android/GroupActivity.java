package com.foxcorp.foxmeet.foxmeet_android;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


public class GroupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		Window win = getWindow();
		win.setNavigationBarColor(getResources().getColor(R.color.primaryDark));

		ArrayList<HashMap<String, String>> list = new DBTools(this).getGroups();

		Event[] groups = new Event[0];
		try {
			groups = new Event[list.size()];
		} catch (Exception e) {
		}

		for (int i = 0; i < list.size(); i++) {
			groups[i] = new Event();
			groups[i].title = "<b>" + list.get(i).get("name") + "</b>";
			String body = list.get(i).get("emails");
			body = body.replaceAll(",", ",<br>");
			groups[i].body = body;
		}

		ListView listView = (ListView) findViewById(R.id.listView);
		ArrayList<Event> groupList = new ArrayList<>();
		for (Event e : groups) {
			groupList.add(e);
		}
		FancyAdapter fa = new FancyAdapter(this, groupList);
		listView.setAdapter(fa);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_group, menu);
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

	public void createGroup(View v) {
		Intent intent = new Intent(this, CreateGroup.class);
		startActivity(intent);
	}
}
