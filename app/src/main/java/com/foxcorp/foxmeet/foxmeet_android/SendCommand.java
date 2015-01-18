package com.foxcorp.foxmeet.foxmeet_android;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by jake on 1/17/15.
 */
public class SendCommand extends AsyncTask<String, Void, String>{
	ListView listView;
	Context c;

	@Override
	protected void onPostExecute(String s) {
		if (s.equals("okay")) {
			Toast.makeText(c, "Event is being scheduled...", Toast.LENGTH_SHORT).show();
			return;
		}
		ArrayList<Event> eventList = new ArrayList<>();
		String[] pieces = s.split(";");
		for (int i = 0; i < pieces.length; i++) {
			String[] individuals = pieces[i].split(",");
			String location = individuals[1];
			int crapIndex = Integer.parseInt(individuals[2]);
			if (crapIndex == 1) {
				int start = Integer.parseInt(individuals[3]);
				int end = Integer.parseInt(individuals[4]);
				int minutes = end - start * 1000 * 60;
				String name = individuals[5];
				ArrayList<String> attendees = new ArrayList<>();
				for (int j = 6; j < individuals.length; j++) {
					attendees.add(individuals[j]);
				}
				Event e = new Event();
				Calendar cal = new GregorianCalendar();
				cal.setTimeInMillis(start);
				e.title = String.format("<b>%s</b>", cal.getTime());
				StringBuilder emailsOfAttendees = new StringBuilder();
				for (int k = 0; k < attendees.size(); k++) {
					if (k == attendees.size() -1)
						emailsOfAttendees.append(attendees.get(k));
					else emailsOfAttendees.append(attendees.get(k) + "\n");
				}
				e.body = String.format("<u>%s for %d minutes</u><br>%s", location, minutes, emailsOfAttendees.toString());
			}
			else {
				//incomplete code below
				crapIndex = crapIndex * 2 + 3;
				String name = individuals[crapIndex++];
				ArrayList<String> attendees = new ArrayList<>();
				for (int j = crapIndex; j < individuals.length; j++) {
					attendees.add(individuals[j]);
				}
				Event e = new Event();
				e.title = String.format("<b>%s</b>", name);
				e.body = String.format("<u>%s @ %s</u><br>");
			}
		}
	}

	public SendCommand(ListView listView, Context c) {
		super();
		this.listView = listView;
		this.c = c;
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			Socket sock = new Socket("35.2.95.133", 1080);
			PrintWriter pw = new PrintWriter(sock.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pw.println(params[0]);
			pw.flush();
			return br.readLine();
		} catch (Exception hotmom) {
			hotmom.printStackTrace();
		}
		return null;
	}
}
