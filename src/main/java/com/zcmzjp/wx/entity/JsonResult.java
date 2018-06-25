package com.zcmzjp.wx.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装操作结果JSON数据
 * 
 * @author zhao
 * 
 */
public class JsonResult {

	private boolean success;

	private Object resultText;

	private String message;

	private Integer error;

	private String url;

	private int errorCode;

	private Serializable resultId;

	private String forwardUrl;

	private List<String> details;

	private Map<String, Object> data = new HashMap<String, Object>();
	
	public JsonResult() {
	}

	public JsonResult(boolean success) {
		this.success = success;
	}

	public JsonResult(boolean success, String resultText) {
		super();
		this.success = success;
		this.resultText = resultText;
	}

	public JsonResult(boolean success, String resultText,String message,String resultId) {
		super();
		this.success = success;
		this.resultText = resultText;
		this.message = message;
		this.resultId = resultId;
	}
	public JsonResult(Integer error,String message,String url){
		super();
		this.error = error;
		this.message = message;
		this.url = url;
	}

	public JsonResult(boolean success,Object obj){
		super();
		this.success = success;
		this.resultText = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	public JsonResult resultText(String resultText) {
		this.resultText = resultText;
		return this;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public JsonResult setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public JsonResult errorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public Serializable getResultId() {
		return resultId;
	}

	public JsonResult setResultId(Serializable resultId) {
		this.resultId = resultId;
		return this;
	}

	public JsonResult resultId(Serializable resultId) {
		this.resultId = resultId;
		return this;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public JsonResult forwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
		return this;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public void putObj(String key, Object obj) {
		data.put(key, obj);
	}

	public void remove(String key) {
		data.remove(key);
	}

	public Map<String, Object> getData() {
		return data;
	}

	public static JsonResult getFailInstance(String msg) {
		return new JsonResult(false, msg);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



/*public JsonResult(boolean success, String resultText) {
		super(success, resultText);
	}

	public static JsonResult getSuccessInstance(HttpServletRequest request,
												String key, String... args) {
		String msg = MessageUtil.getMesssage(request, key, args);
		return new JsonResult(true, msg);
	}

	public static JsonResult getFailInstance(HttpServletRequest request,
											 String key, String... args) {
		String msg = MessageUtil.getMesssage(request, key, args);
		return new JsonResult(false, msg);
	}*/

}
