package com.foomoo.auto.api.pojo;

public class Case {
	private String caseId;
	private String desc;
	private String apiId;
	private String params;
	private String validatorContent;
	private String validatorMethod;
	private String responseValidators;
	private String isPosition;
	private String isCookie;
	private String isCsrfToken;
	public Case(String caseId,String desc, String apiId, String params, String validatorContent, String validatorMethod,
			String responseValidators,String isPosition,String isCookie,String isCsrfToken) {
		super();
		this.caseId = caseId;
		this.desc = desc;
		this.apiId = apiId;
		this.params = params;
		this.validatorContent = validatorContent;
		this.validatorMethod = validatorMethod;
		this.responseValidators = responseValidators;
		this.isPosition = isPosition;
		this.isCsrfToken = isCsrfToken;
		this.isCsrfToken = isCsrfToken;
	}
	public Case() {
		super();
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getValidatorContent() {
		return validatorContent;
	}
	public void setValidatorContent(String validatorContent) {
		this.validatorContent = validatorContent;
	}
	public String getValidatorMethod() {
		return validatorMethod;
	}
	public void setValidatorMethod(String validatorMethod) {
		this.validatorMethod = validatorMethod;
	}
	public String getResponseValidators() {
		return responseValidators;
	}
	public void setResponseValidators(String responseValidators) {
		this.responseValidators = responseValidators;
	}
	
	
	public String getIsPosition() {
		return isPosition;
	}
	public void setIsPosition(String isPosition) {
		this.isPosition = isPosition;
	}
	
	
	public String getIsCookie() {
		return isCookie;
	}
	public void setIsCookie(String isCookie) {
		this.isCookie = isCookie;
	}
	public String getIsCsrfToken() {
		return isCsrfToken;
	}
	public void setIsCsrfToken(String isCsrfToken) {
		this.isCsrfToken = isCsrfToken;
	}
	@Override
	public String toString() {
		return "Case [caseId=" + caseId + ", desc=" + desc + ", apiId=" + apiId + ", params=" + params
				+ ", validatorContent=" + validatorContent + ", validatorMethod=" + validatorMethod
				+ ", responseValidators=" + responseValidators + "]";
	}
	
	
	

}
