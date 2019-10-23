package com.foomoo.api.auto.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.testng.ITestContext;

import com.foomoo.api.auto.cases.LoginCase;
import com.foomoo.auto.api.pojo.Api;
import com.foomoo.auto.api.pojo.Case;
import com.foomoo.auto.api.pojo.TestData;

public class AuthorizationUtil {
	public static Logger logger = Logger.getLogger(AuthorizationUtil.class);
	//保存获得csrf_token
	public static String csrfTokenLogin=null;
	public static String cookieLogin=null;

	
	//保存登陆相关鉴权数据
	public static void storeAuthorization(ITestContext context) {
		//获得登陆前的csrf_token与cookie
		LoginCase.getTokenBeforeLogin(context);				
		//获得登陆的鉴权数据数据
		List<Api> apis=(List<Api>) context.getAttribute("apis");
		List<Case> cases=(List<Case>) context.getAttribute("cases");
		logger.info("++++++++++开始获得登陆成功的csrftoken+++++++++++");
		
		String caseId="9";//这个值可进行参数化进行维护
		TestData testData=new TestData();
		String apiId="";
		for(Case cs:cases){
			//获得case
			if(caseId.equals(cs.getCaseId())){
				apiId=cs.getApiId();
				testData.setParams(cs.getParams());
				testData.setApiId(apiId);
				testData.setDesc(cs.getDesc());
				testData.setIsCookie(cs.getIsCookie());
				testData.setIsCsrfToken(cs.getIsCsrfToken());
				testData.setIsPosition(cs.getIsPosition());
				testData.setParams(cs.getParams());			
				testData.setCaseId(caseId);;			
				break;
				}
			//获得api
		}
		for(Api api:apis){
			if(apiId.equals(api.getApiId())){
				testData.setUrl(api.getUrl());
				testData.setParamFormat(api.getParamsFormat());
				testData.setRequestMethod(api.getRequestMethod());
				break;
			}
		}
				
		
		//请求接口获得响应数据
		Map<String,Object> mapResult = MyRequestUtil.Call(testData);
		//获得cookie数据保存起来
		Header[] heads= (Header[]) mapResult.get("headers");
		setCookie(heads);	
		//获得crsr_token数据保存起来
		AuthorizationUtil.csrfTokenLogin=DataUtil.csrfToken;
		logger.info("===============获得登陆后的cookie：【【【"+cookieLogin+"】】】");
		logger.info("===============获得登陆后的csrfToken：【【【"+csrfTokenLogin+"】】】");
		logger.info("============结束获得登陆成功的csrftoken和cookie==========/n/n");
		
	}

	//保存登陆cookie
	private static void setCookie(Header[] headers) {
		logger.info("===========开始保存登陆的cookie============");
		for (Header header : headers) {
			if(header.toString().contains("Set-Cookie")){
				String[] strs = header.toString().split(";");
				String s1=strs[0];
				String[] cs=s1.split(":");
				logger.info("===============获得登陆后的cookie：【【【"+cs[1]+"】】】");
				cookieLogin= cs[1];
			}
		}
	}
	
	


}
