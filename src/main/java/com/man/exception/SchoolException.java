package com.man.exception;

public class SchoolException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String msg;
	private long code = 500L;

	public SchoolException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public SchoolException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public SchoolException(String msg, long code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public SchoolException(String msg, long code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}
}
