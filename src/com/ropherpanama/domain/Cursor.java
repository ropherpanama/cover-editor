package com.ropherpanama.domain;

import java.util.List;

public class Cursor {
	private String resultCount;
	private List<Pages> pages;
	private String estimatedResultCount;
	private int currentPageIndex;
	private String moreResultsUrl;
	private String searchResultTime;

	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public List<Pages> getPages() {
		return pages;
	}

	public void setPages(List<Pages> pages) {
		this.pages = pages;
	}

	public String getEstimatedResultCount() {
		return estimatedResultCount;
	}

	public void setEstimatedResultCount(String estimatedResultCount) {
		this.estimatedResultCount = estimatedResultCount;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public String getMoreResultsUrl() {
		return moreResultsUrl;
	}

	public void setMoreResultsUrl(String moreResultsUrl) {
		this.moreResultsUrl = moreResultsUrl;
	}

	public String getSearchResultTime() {
		return searchResultTime;
	}

	public void setSearchResultTime(String searchResultTime) {
		this.searchResultTime = searchResultTime;
	}
}
