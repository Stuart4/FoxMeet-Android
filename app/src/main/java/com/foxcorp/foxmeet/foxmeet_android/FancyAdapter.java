package com.foxcorp.foxmeet.foxmeet_android;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jake on 1/17/15.
 */
public class FancyAdapter extends ArrayAdapter<Event> {
	public FancyAdapter(Context context, ArrayList<Event> events) {
		super(context, 0, events);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Event e = getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.listlayout, parent, false);
		}

		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView body = (TextView) convertView.findViewById(R.id.body);

		title.setText(Html.fromHtml(e.title));
		body.setText(Html.fromHtml(e.body));
		return convertView;
	}
}
