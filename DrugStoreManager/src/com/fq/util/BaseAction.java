package com.fq.util;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware{

	public Map<String,Object> session;
	private ActionContext context=ActionContext.getContext();
	public static final String JSON="json";

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
}
