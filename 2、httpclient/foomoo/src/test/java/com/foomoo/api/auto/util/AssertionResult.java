package com.foomoo.api.auto.util;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.foomoo.auto.api.pojo.TestData;

public class AssertionResult {
	private static Logger logger = Logger.getLogger(AssertionResult.class);
	public static void validateResponseData(TestData testData, Map<String, Object> resultMap){
		Boolean assertResult=null;
		//对响应信息断言
		if("responseMessage".equals(testData.getValidatorContent())){
			String responseMessage=(String) resultMap.get("result");
			//断言方法为contains
			if("contains".equals(testData.getValidatorMethod())){
				assertResult=responseMessage.contains(testData.getResponseValidators());
			//断言方法为not contains	
			}else if("not".equals(testData.getValidatorMethod())){
				assertResult=!(responseMessage.contains(testData.getResponseValidators()));
			}else{
				throw new RuntimeException("====没有找到断言方法：："+testData.getValidatorMethod());									
			}
		}else{
			throw new RuntimeException("====未定义断言指定内容方法：："+testData.getValidatorContent());
		}
		
		logger.info("=========断言结果："+assertResult);
			Assert.assertTrue(assertResult);
	}

}
