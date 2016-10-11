package com.fq.util;

import java.util.List;

public class PageModel<E> {
	//当前页
	private int currPage; 
	//每页的大小
	private int pageSize;
	//上一页
	private int perIndex;
	//下一页
	private int nextIndex;
	//总记录数
	private int total;
	//总页数
	private int totalPage;
	//查询关键字
	private String keyword;
	//查询出来的记录数
	private List<E> list;
	
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPerIndex() {
		return perIndex;
	}
	public void setPerIndex(int perIndex) {
		this.perIndex = perIndex;
	}
	public int getNextIndex() {
		return nextIndex;
	}
	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
}
