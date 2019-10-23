package com.foomoo.api.auto.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.foomoo.auto.api.pojo.TestData;

public class MyRequestUtil {

	public static Logger  logger =Logger.getLogger(MyRequestUtil.class);
	
	public static Map<String,Object> Call(TestData testData) {
		String method=testData.getRequestMethod();
		logger.info("获得测试数据"+testData);

		if("get".equals(method)){
			
			return doGet(testData);

		}else if("post".equals(method)){
			logger.info("=========执行post方法：==========执行用例数据为========:"+testData.getDesc());
			return doPost(testData);
		}
		return null;

	}
	
	public static Map<String,Object> doGet(TestData testData){
		logger.info("============开始执行get请求方法：======执行用例数据为========:"+testData.getDesc());	
		 String url=testData.getUrl();
		 String jsonString = testData.getParams();
		 HttpGet httpGet=null;
		 //请求是否有参数，有参数进入加请求参数
		 if(!("".equals(jsonString))&&(null!=jsonString)){
			 String params=generateQueryParam(testData);
			 httpGet = new HttpGet(url+"?"+params);			 
		 }
		 httpGet = new HttpGet(url);
				 
		 CloseableHttpClient httpClient = HttpClients.createDefault();
		//添加登陆cookie
		 if("1".equals(testData.getIsCookie())){

			String auth=AuthorizationUtil.cookieLogin;
			httpGet.addHeader("Cookie",auth );
				logger.info("==============增加登陆的cookie============:"+auth);
		//增加cookie
		}if("2".equals(testData.getIsCookie())){
			httpGet.addHeader("Cookie",DataUtil.cookie );
			logger.info("=========增加cookie:======="+DataUtil.cookie);
		}
		CloseableHttpResponse response=null;
		 try {
			response = httpClient.execute(httpGet);							
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //保存响应信息
		return SaveRequestResutlMap(testData, response);
	}


	

	private static Map<String,Object>  doPost(TestData testData) {
		logger.info("============开始执行post请求方法：======执行用例数据为========:"+testData.getDesc());	
		 Map<String,Object> resultMap=null;
		//1、获得httpposet并增加url
		String url=testData.getUrl();
		String paramFormat=testData.getParamFormat();
		HttpPost httpPost=new HttpPost(url);


		//增加请求头
		if("form".equals(paramFormat)){
			String params=generateQueryParam(testData);
			logger.info("=================获得参数："+params);
			httpPost.addHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded"));
			try {
				httpPost.setEntity(new StringEntity(params));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if("json".equals(paramFormat)){
			
		}
			//增加参数
		//带登陆态的cookie
		if("1".equals(testData.getIsCookie())){
			//登陆cookie
			String auth=AuthorizationUtil.cookieLogin;
			httpPost.addHeader("Cookie",auth );
			logger.info("==============增加登陆的cookie============:"+auth);
		//带上一个接口的cokkie
		}if("2".equals(testData.getIsCookie())){
			//可变cookie
			httpPost.addHeader("Cookie",DataUtil.cookie );
			logger.info("=========增加cookie:======="+DataUtil.cookie);

		}		
		//获得Http客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {
			CloseableHttpResponse response = httpClient.execute(httpPost);
			resultMap=SaveRequestResutlMap(testData, response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return resultMap;		
	}

	
	//保存响应数据
	private static Map<String,Object> SaveRequestResutlMap(TestData testData, CloseableHttpResponse response)  {
		logger.info("====start======开始处理保存响应数据=========");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		int code = response.getStatusLine().getStatusCode();
		Header[] allHeaders = response.getAllHeaders();
		String result=null;
		try {
			result = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultMap.put("code", code);
		resultMap.put("headers", allHeaders);
		resultMap.put("result",result);
//		logger.info("==== 请求===用例=="+testData.getCaseId()+"、"+testData.getDesc());
//		logger.info("================= 请求===结果==\n<<<<<<<<<<=========");
//		logger.info(result);
//		logger.info("==== >>>>>>>>>>>>>>=========");
//		for(Header header: allHeaders){
//			logger.info("/n/n】"+"header：【");
//			logger.info(header.toString());
//		}
		
		System.out.println("】====POST 请求===结束,获得结果结束=="+testData.getCaseId()+"、"+testData.getDesc());
		String newuri="";
		//302进行重定向
		if (code == 302) {
			logger.info("=====start=====返回状态码为302进行重定向发送请求========");
			DataUtil.setCookies(allHeaders);
			DataUtil.setCsrfToken(result);
            Header header = response.getFirstHeader("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
            newuri = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
            logger.info("-------获得重定向地址----"+newuri);
            System.out.println(code);
            TestData newTestData=new TestData();
            testData.setUrl(newuri);
            newTestData.setDesc("-----------------------重定向内容:::::::::::");
            testData.setRequestMethod("get");
            testData.setParams("");
            testData.setDesc(testData.getDesc()+"[【重定向】");
            testData.setIsCookie("2");
            //进行重定向get请求
            resultMap=doGet(testData);
            logger.info("=====end=====完成重定向发送请求========\n");
            
        }
		logger.info("====end======结束处理保存响应数据=========\n");
		return resultMap;
	}


	//json参数转换成表单格式参数
	public static String generateQueryParam(TestData testData) {
		logger.info("====start========开始对json参数转换成表单参数=========");
		
		// 通过fastJson框架把json字符串反系列化成一个map对象
		String jsonString=testData.getParams();
		Map<String,Object> map = (Map<String, Object>) JSONObject.parse(jsonString);
		Set<String> keySet = map.keySet();
		StringBuilder sBuffer=new StringBuilder();
		String [] keyArr=new String[keySet.size()];
		keySet.toArray(keyArr);
		for(int i=0;i<keyArr.length;i++){
			sBuffer.append(keyArr[i]).append("=").append(map.get(keyArr[i])).append("&");			
		}
		//增加登陆的csrf_token
		if("1".equals(testData.getIsCsrfToken())){
			
			logger.info("===========增加【登陆】的csrftoken===========:"+AuthorizationUtil.csrfTokenLogin);
			sBuffer.append("csrf_token").append("=").append(AuthorizationUtil.csrfTokenLogin).append("&");
		//增加csrf_token	
		}else if("2".equals(testData.getIsCsrfToken())){
			sBuffer.append("csrf_token").append("=").append(DataUtil.csrfToken).append("&");
			logger.info("===========增加csrftoken==========="+DataUtil.csrfToken);						
		}else{
			logger.info("============没有增加csrf_token============");
		}
		String paramsForm=sBuffer.substring(0, sBuffer.lastIndexOf("&"));
		logger.info("============完成json参数转换成表单参数，表单参数为：【 "+paramsForm+" 】============\n");
		return paramsForm;
	}
	
}
