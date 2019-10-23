package com.foomoo.api.auto.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.log4j.spi.LoggerFactory;
import org.testng.ITestContext;

import com.foomoo.auto.api.pojo.Api;
import com.foomoo.auto.api.pojo.Case;
import com.foomoo.auto.api.pojo.TestData;
import com.sun.istack.internal.logging.Logger;

public class DataUtil {
	private static Logger logger= Logger.getLogger(DataUtil.class);
	//数据初始化时保存的登陆的csrf_token
	public static String csrfToken=null;
	//数据初始化时保存的登陆cookie
	public static String cookie=null;
	public static Object[][] getProviderDatas(String apiId, ITestContext context) {
		//根据用例编号，上下文缓存获得用例对象集合
		List<Case> cases = getWantedCaseList(apiId, context);			
		//获得接口
		Api api = getWantedApi(apiId, context);
		//把用例及接口数据保存到测试数据
		String url= api.getUrl();
		String isAccessControled=api.getIsAccessControled();
		String requestMethod=api.getRequestMethod();
		String paramsFormat = api.getParamsFormat();
		Object[][] datas = new Object[cases.size()][1];
		for(int i=0;i<cases.size();i++){			
			Case cs = cases.get(i);
			String caseId=cs.getCaseId();
			String params=cs.getParams();
			String validatorContent = cs.getValidatorContent();
			String validatorMethod = cs.getValidatorMethod();
			String responseValidators=cs.getResponseValidators();
			String isPosition = cs.getIsPosition();
			String isCookie = cs.getIsCookie();
			String isCsrfToken = cs.getIsCsrfToken();
			String desc=cs.getDesc();
			TestData testData=new TestData(apiId, caseId, url, requestMethod, paramsFormat, isAccessControled,desc, params, validatorContent, validatorMethod, responseValidators,isPosition,isCookie,isCsrfToken);
			datas[i][0]=testData;
//			logger.info("=======获得用例数据（get the cases data)：======"+testData);
		}
		return datas;
	}
	public static List<Case> getWantedCaseList(String apiId, ITestContext context) {
		List<Case> cases = (List<Case>) context.getAttribute("cases");
		List<Case> wantedCaseList= new ArrayList();

		for(Case cs:cases){
			if(apiId.equals(cs.getApiId())){
				wantedCaseList.add(cs);
			}		
		}
		return wantedCaseList;
	}

	public static Api getWantedApi(String apiId, ITestContext context) {
		List<Api> apis = (List<Api>) context.getAttribute("apis");
		for(Api api:apis){
			if(apiId.equals(api.getApiId())){
				return api;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
//		getProviderDatas(1,)
	}
	
	public static void setCsrfToken(String result) {
		String regex = "hidden\" value=\".+";		
		Pattern pattern = Pattern.compile (regex);
		Matcher match = pattern.matcher (result);
		while (match.find ())
		{
		    String group = match.group();
			String[] strs=group.split("\"");
			logger.info("======================获得请求的csrt_token供下个接口：【【【"+strs[2]+"】】】");
			csrfToken=strs[2];			 
		}
	}


	public static void setCookies(Header[] headers) {
		for (Header header : headers) {
			if(header.toString().contains("Set-Cookie")){
				String[] strs = header.toString().split(";");
				String s1=strs[0];
				String[] cs=s1.split(":");
				logger.info("======================获得请求的cookie供下个接口用：【【【"+cs[1]+"】】】");
				cookie=cs[1];
			}
		}
	}

}
