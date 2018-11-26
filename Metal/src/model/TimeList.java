package model;

import java.io.Serializable;

public class TimeList implements Comparable<TimeList>, Serializable {
	
	private static final long serialVersionUID = 1L;
	private TimeList next;
	private TimeList prev;
	private long data;

	public TimeList(long playedTime) {
		this.data = playedTime;
		next = null;
		prev = null;
	}

	public TimeList getNext() {
		return next;
	}

	public void setNext(TimeList next) {
		this.next = next;
	}

	public TimeList getPrev() {
		return prev;
	}

	public void setPrev(TimeList prev) {
		this.prev = prev;
	}

	public long getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int compareTo(TimeList tl) {
		if (data == tl.getData()) {
			return 0;
		} else if (data > tl.getData()) {
			return 1;
		} else {
			return -1;
		}
	}

}
