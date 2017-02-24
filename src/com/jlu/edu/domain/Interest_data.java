package com.jlu.edu.domain;

import java.util.List;

public class Interest_data {

	private int Interestid;
	private String Interestname;
	private String Interestportrait;
	private String Interestnumber;
	private String Interestsex;
	private String Interestcontent;
	private String Interestimage;
	private long Interesttime;
	private List<Interest_comment> list;

	public int getInterestid() {
		return Interestid;
	}

	public void setInterestid(int interestid) {
		Interestid = interestid;
	}

	public String getInterestnumber() {
		return Interestnumber;
	}

	public void setInterestnumber(String interestnumber) {
		Interestnumber = interestnumber;
	}

	public String getInterestcontent() {
		return Interestcontent;
	}

	public void setInterestcontent(String interestcontent) {
		Interestcontent = interestcontent;
	}

	public String getInterestimage() {
		return Interestimage;
	}

	public void setInterestimage(String interestimage) {
		Interestimage = interestimage;
	}

	public long getInteresttime() {
		return Interesttime;
	}

	public void setInteresttime(long interesttime) {
		Interesttime = interesttime;
	}

	public String getInterestname() {
		return Interestname;
	}

	public void setInterestname(String interestname) {
		Interestname = interestname;
	}

	public String getInterestportrait() {
		return Interestportrait;
	}

	public void setInterestportrait(String interestportrait) {
		Interestportrait = interestportrait;
	}

	public List<Interest_comment> getList() {
		return list;
	}

	public void setList(List<Interest_comment> list) {
		this.list = list;
	}

	public String getInterestsex() {
		return Interestsex;
	}

	public void setInterestsex(String interestsex) {
		Interestsex = interestsex;
	}

	@Override
	public String toString() {
		return "Interest_data [Interestid=" + Interestid + ", Interestname="
				+ Interestname + ", Interestportrait=" + Interestportrait
				+ ", Interestnumber=" + Interestnumber + ", Interestsex="
				+ Interestsex + ", Interestcontent=" + Interestcontent
				+ ", Interestimage=" + Interestimage + ", Interesttime="
				+ Interesttime + ", list=" + list + "]";
	}

}
