package com.foomoo.api.auto.cases;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import com.foomoo.api.auto.util.AssertionResult;
import com.foomoo.api.auto.util.AuthorizationUtil;
import com.foomoo.api.auto.util.ExcelUtil;
import com.foomoo.api.auto.util.MyRequestUtil;

import com.foomoo.auto.api.pojo.Api;
import com.foomoo.auto.api.pojo.Case;

import com.foomoo.auto.api.pojo.TestData;



public class BaseCase {
	Logger logger = Logger.getLogger(BaseCase.class);
	@BeforeSuite
	public void initData(ITestContext context){
		logger.info("===start=<<<<<<<<<<===执行用例套件前获得用例信息=====>>>>>>>>>>====\n");
		logger.info("===start====开始执行保存用例数据=========");
		//获得接口对象集合
		List<Api> apis=ExcelUtil.readPojos(Api.class,0);
		//获得用例对象集合
		List<Case> cases=ExcelUtil.readPojos(Case.class,1);
		//保存对象集合到testng上下文缓存中
		context.setAttribute("apis", apis);
		context.setAttribute("cases", cases);
		logger.info("===start====结完成保存用例数据=========\n");
		
		logger.info("===start====开始执行保存登陆状态数据=========");
		//保存登陆相关鉴权数据	
		AuthorizationUtil.storeAuthorization(context);
		logger.info("==============完成保存登陆状态数据保存========\n");
		logger.info("===start==<<<<<<<<====完成执行用例套件前获得用例信息=====>>>>>>>>>>====\n\n");
	}
	
	
	@Test(dataProvider="datas")
	public void foomooTest(TestData testData){
		logger.info("<<<<<<<<<<=========开始执行用例："+testData.getCaseId()+"、"+testData.getDesc()+"=========>>>>>>>>>>>>>>>>>>>>>>=====");
		//发送接口请求
		Map<String, Object> resultMap = MyRequestUtil.Call(testData);
		//数据校验
		AssertionResult.validateResponseData(testData, resultMap);
		logger.info("====<<<<<<<<====结束执行用例："+testData.getCaseId()+"、"+testData.getDesc()+"======>>>>>>>>>>>>>======\n\n\n\n");
	}


	

}
