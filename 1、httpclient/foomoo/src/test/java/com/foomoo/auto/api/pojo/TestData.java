package com.foomoo.auto.api.pojo;

public class TestData {
	private String apiId;
	private String caseId;
	private String url;
	private String requestMethod;
	private String paramFormat;
	private String isAccessControled;
	private String desc;
	private String params;
	private String validatorContent;
	private String validatorMethod;
	private String responseValidators;
	private String isPosition;
	private String isCookie;
	private String isCsrfToken;
	
	public TestData() {
		super();
	}
	
	
	

	
	
	
	public TestData(String apiId, String caseId, String url, String requestMethod, String paramFormat,
			String isAccessControled, String desc, String params, String validatorContent, String validatorMethod,
			String responseValidators, String isPosition, String isCookie, String isCsrfToken) {
		super();
		this.apiId = apiId;
		this.caseId = caseId;
		this.url = url;
		this.requestMethod = requestMethod;
		this.paramFormat = paramFormat;
		this.isAccessControled = isAccessControled;
		this.desc = desc;
		this.params = params;
		this.validatorContent = validatorContent;
		this.validatorMethod = validatorMethod;
		this.responseValidators = responseValidators;
		this.isPosition = isPosition;
		this.isCookie = isCookie;
		this.isCsrfToken = isCsrfToken;
	}







	public String getDesc() {
		return desc;
	}







	public void setDesc(String desc) {
		this.desc = desc;
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



	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
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

	public String getParamFormat() {
		return paramFormat;
	}

	public void setParamFormat(String paramFormat) {
		this.paramFormat = paramFormat;
	}

	public String getIsAccessControled() {
		return isAccessControled;
	}

	public void setIsAccessControled(String isAccessControled) {
		this.isAccessControled = isAccessControled;
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







	@Override
	public String toString() {
		return "TestData [apiId=" + apiId + ", caseId=" + caseId + ", url=" + url + ", requestMethod=" + requestMethod
				+ ", paramFormat=" + paramFormat + ", isAccessControled=" + isAccessControled + ", desc=" + desc
				+ ", params=" + params + ", validatorContent=" + validatorContent + ", validatorMethod="
				+ validatorMethod + ", responseValidators=" + responseValidators + ", isPosition=" + isPosition
				+ ", isCookie=" + isCookie + ", isCsrfToken=" + isCsrfToken + "]";
	}


	



	
	
}
