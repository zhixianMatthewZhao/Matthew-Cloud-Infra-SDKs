package com.matthew_cloud.javaSdk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class otpMgr implements Runnable{
	String user = login.getUser();
	String passwd = login.getPasswd();
	String otp;
	public void run() {
		while(true) {
			try {
				URL getotp = new URL("http://localhost:3001/login/api/getotp");
				HttpURLConnection conn = (HttpURLConnection) getotp.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				StringBuffer query = new StringBuffer();
				query.append("username=");
				query.append(user);
				query.append("password=");
				query.append(passwd);
				OutputStream out = conn.getOutputStream();
				out.write(query.toString().getBytes());
				out.flush();
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				otp = in.readLine();
				if (otp.intern() == "None") {
					System.exit(1);
					throw new IllegalArgumentException("incorrect identifier");
				}
				login.otp = otp;
			}
			catch (Exception e) {
				throw new IllegalArgumentException("something went wrong, please reinstall the SDK");
			}
			try {
				Thread.sleep(200000);
			} catch (InterruptedException e) {
			}
		}
	}

}
