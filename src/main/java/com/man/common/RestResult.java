package com.man.common;

import java.util.HashMap;
import java.util.Map;

/**
 * json串返回共用实体
 * @date 2019-12-10 18:06
 */
public class RestResult extends HashMap<String,Object> {
	private static final long serialVersionUID = 1L;
	public static final String CODE = "CODE";
	public static final String MESSAGE = "MESSAGE";
	public static final String DATA = "DATA";

	public RestResult(){
		put(CODE,200);
		put(MESSAGE,"SUCCESS");
	}
	@Override
	public RestResult put(String key ,Object value){
		super.put(key,value);
		return this;
	}
	public static RestResult ok(){
		RestResult restResult = new RestResult();
		return restResult;
	}
	public static RestResult ok(String msg){
		return ok().put(MESSAGE,msg);
	}
	public static RestResult ok(Map<String,Object> map){
		RestResult restResult = ok();
		restResult.putAll(map);
		return restResult;
	}
	public static RestResult error(int code,String message){
		return ok().put(CODE,code).put(MESSAGE,message);
	}
	public static RestResult error(){
		return error(500,"未知错误，请联系管理员");
	}
	public static RestResult error(String message){
		return error().put(MESSAGE,message);
	}
}
