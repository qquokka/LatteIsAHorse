package com.latte.help;

import java.io.Serializable;

public class NumberResult implements Serializable {

	private static final long serialVersionUID = 3194364515720322284L;
	private String name;
	private int count;
	private String state = "true"; // false

	public NumberResult() {
		super();
	}

	public NumberResult(String name, int count, String state) {
		super();
		this.name = name;
		this.count = count;
		this.state = state;
	}

	@Override
	public String toString() {
		return "NumberResult [name=" + name + ", count=" + count + ", state=" + state + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
