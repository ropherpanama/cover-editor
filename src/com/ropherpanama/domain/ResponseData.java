package com.ropherpanama.domain;

import java.util.List;

public class ResponseData {
	private List<Results> results;
	private Cursor cursor;

	public Cursor getCursor() {
		return cursor;
	}

	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}
}
