package com.jlu.edu.domain;

public class Emails {
     private String teachername;
     private String teachernumber;
     private String emailname;
     private String emailpwd;
     private int account;
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getEmailname() {
		return emailname;
	}
	public void setEmailname(String emailname) {
		this.emailname = emailname;
	}
	public String getEmailpwd() {
		return emailpwd;
	}
	public void setEmailpwd(String emailpwd) {
		this.emailpwd = emailpwd;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public String getTeachernumber() {
		return teachernumber;
	}
	public void setTeachernumber(String teachernumber) {
		this.teachernumber = teachernumber;
	}
	@Override
	public String toString() {
		return "Emails [teachername=" + teachername + ", teachernumber="
				+ teachernumber + ", emailname=" + emailname + ", emailpwd="
				+ emailpwd + ", account=" + account + "]";
	}
     
}
