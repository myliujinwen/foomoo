package com.foomoo.api.auto.cases;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.foomoo.api.auto.util.AssertionResult;
import com.foomoo.api.auto.util.AuthorizationUtil;
import com.foomoo.api.auto.util.DataUtil;
import com.foomoo.api.auto.util.MyRequestUtil;
import com.foomoo.auto.api.pojo.TestData;

import sun.security.krb5.internal.AuthorizationData;


public class LoginCase extends BaseCase{
	private static Logger logger= Logger.getLogger(LoginCase.class);
	//获得登陆的crsftoken和cookie;
	@BeforeMethod
	public void getCsrftoken(ITestContext context){ 
		getTokenBeforeLogin(context);
	}

	public static void getTokenBeforeLogin(ITestContext context) {
		logger.info("======start=======执行获得登陆的前置条件：开始获得登陆前的csrftoken=============");
		Object[][] providerDatas = DataUtil.getProviderDatas("0",context);
		TestData testData= (TestData) providerDatas[0][0];
		Map<String, Object> resultMap = MyRequestUtil.Call(testData);
		String  result = (String) resultMap.get("result");
		Header[] headers=(Header[]) resultMap.get("headers");
//		System.out.println(result);
		DataUtil.setCookies(headers);		
		DataUtil.setCsrfToken(result);
			
		logger.info("=====end========结束执行获得登陆的前置条件：开始获得登陆前的csrftoken=============");
	}	


	@DataProvider
	public Object [][] datas(ITestContext context){
		//根据及接口号，上下文缓存，获得测试数据
		Object[][] providerDatas = DataUtil.getProviderDatas("1",context);			
		return providerDatas;		
	}	

}
