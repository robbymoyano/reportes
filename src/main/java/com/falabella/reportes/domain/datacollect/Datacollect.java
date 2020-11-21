package com.falabella.reportes.domain.datacollect;

public class Datacollect {

	String head;
	String body;

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Datacollect(String head, String body) {
		super();
		this.head = head;
		this.body = body;
	}

	public Datacollect() {
		super();
	}

	@Override
	public String toString() {
		return "Datacollect [head=" + head + ", body=" + body + "]";
	}
	
}

