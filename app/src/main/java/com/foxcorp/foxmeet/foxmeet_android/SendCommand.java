package com.foxcorp.foxmeet.foxmeet_android;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jake on 1/17/15.
 */
public class SendCommand extends AsyncTask<String, Void, String>{
	@Override
	protected String doInBackground(String... params) {
		try {
			Socket sock = new Socket("www.iamyourintern.com", 1080);
			PrintWriter pw = new PrintWriter(sock.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pw.println(params[0]);
			pw.flush();
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			System.out.println(sb.toString());
			return sb.toString();
		} catch (Exception hotmom) {
			hotmom.printStackTrace();
		}
		return null;
	}
}
