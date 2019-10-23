package com.foomoo.auto.api.pojo;

public class Api {
	private String apiId;
	private String apiName;
	private String url;
	private String requestMethod;
	private String paramsFormat;
	private String isAccessControled;
	
	
	public Api() {
		super();
	}
	public Api(String apiId, String apiName, String url, String requestMethod, String paramsFormat,
			String isAccessControled) {
		super();
		this.apiId = apiId;
		this.apiName = apiName;
		this.url = url;
		this.requestMethod = requestMethod;
		this.paramsFormat = paramsFormat;
		this.isAccessControled = isAccessControled;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public String getParamsFormat() {
		return paramsFormat;
	}
	public void setParamsFormat(String paramsFormat) {
		this.paramsFormat = paramsFormat;
	}
	public String getIsAccessControled() {
		return isAccessControled;
	}
	public void setIsAccessControled(String isAccessControled) {
		this.isAccessControled = isAccessControled;
	}
	
	
	
}
