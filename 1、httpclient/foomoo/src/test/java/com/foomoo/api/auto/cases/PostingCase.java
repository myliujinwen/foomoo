package com.foomoo.api.auto.cases;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.foomoo.api.auto.util.AuthorizationUtil;
import com.foomoo.api.auto.util.DataUtil;

public class PostingCase extends BaseCase{
	@DataProvider
	public Object [][] datas(ITestContext context){
		//根据及接口号，上下文缓存，获得测试数据
		Object[][] providerDatas = DataUtil.getProviderDatas("2",context);			
		return providerDatas;		
	}
	
	
}
