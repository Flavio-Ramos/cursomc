package com.flavioramos.cursomc.resources.exeption;

import java.io.Serializable;

public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String mgs;
	private Long timeStamp;
	
	
	
	public StandardError(Integer status, String mgs, Long timeStamp) {
		super();
		this.status = status;
		this.mgs = mgs;
		this.timeStamp = timeStamp;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getMgs() {
		return mgs;
	}


	public void setMgs(String mgs) {
		this.mgs = mgs;
	}


	public Long getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
