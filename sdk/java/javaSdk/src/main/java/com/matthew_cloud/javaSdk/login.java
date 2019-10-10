package com.matthew_cloud.javaSdk;
public class login {
	static String otp;
	static String  user;
	static String  passwd;
	otpMgr t = new otpMgr();
	public login(String username, String password){
		user = username;
		passwd = password;
		otpUpdate();
	}
	public static String getUser() 
	{
		return user;
	}
	public static String getPasswd() {
		return passwd;
	}
	public void otpUpdate() {
		t.run();
	}
}