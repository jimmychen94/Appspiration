package com.appspiration.service;

public class Triple{

	private String title;
	private String op;
	private int votes;

	public Triple(String title, String op, int votes) {
		this.title   = title;
		this.op = op;
		this.votes= votes;
	}

	public String getTitle()   { return title; }

	public String getOp() { return op; }

	public int getVotes() { return votes; }

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

}
