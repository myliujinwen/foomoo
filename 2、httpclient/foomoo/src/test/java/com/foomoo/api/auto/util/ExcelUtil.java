package com.foomoo.api.auto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

import com.foomoo.auto.api.pojo.Api;
import com.foomoo.auto.api.pojo.Case;
import com.foomoo.auto.api.pojo.TestData;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	

	public static Map<String,Integer> rowIdentifiedAndIndexMapping=new HashMap<String,Integer>();
	//返回表头行标题及对应的列
	public static Map<String, Integer> cellNameAndIndexMapping=new HashMap<String,Integer>();
	
			
	
	
	public static Map<Integer, String> loadCellIndexAndCellNameMapping(Sheet sheet) {
		//获得表头行
		Row titleRow=sheet.getRow(0);
		//获取excel实际列数
		int lastCellnum=titleRow.getLastCellNum();
		Map<Integer, String> cellIndexAndCellNameMapping=new HashMap<Integer, String>();
		//获取标题行的每一列
		for(int i=0;i<lastCellnum;i++){
			//获得表头单元格
			Cell cell=titleRow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			//获得表头单元格的标题
			String title = cell.getStringCellValue();

			//获得比较规范的标题
			title = title.substring(0, title.indexOf("("));
			//把表头的名及对应的列号存入map中；
			cellIndexAndCellNameMapping.put(i, title);
			cellNameAndIndexMapping.put(title, i);
		}
		return cellIndexAndCellNameMapping;
	}
	
	/**
	 * 运用泛型、反射、封装
	 * @param clazz--表格需要转化的类
	 * @param sheetIndex--表格索引
	 * @return 表格数据反系列化成集合对象
	 */
	public static <T> List<T> readPojos(Class<T> clazz,int sheetIndex){		
		
		InputStream inputStream=null;
		//定义一个泛型集合，用与保存需要返回的数据
		List<T> t=new ArrayList<T>();
		try {
			//获得excle用例文件对象
			File file=new File("src/test/resources/foomoodebug.xls");
			//把文件对象转化为输入流
			inputStream=new FileInputStream(file);
			//excel输入流对象转发为wookbook工作簿对象
			Workbook workbook= WorkbookFactory.create(inputStream);
			//获得指定的表格对象
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			//获得列索引对应的列名保存到map中
			Map<Integer, String> loadCellIndexAndCellNameMapping = ExcelUtil.loadCellIndexAndCellNameMapping(sheet);
			//取出所有的数据行总数，最后一行
			int lastRownum=sheet.getLastRowNum();
			//获得第一行表头行
			Row rowTitle=sheet.getRow(0);
			//获得所有列的总数（需要减1）
			int lastCellnum=rowTitle.getLastCellNum();
			//对行操作
			for(int i=1;i<=lastRownum;i++){
				//定义行数据转换对象
				T obj = clazz.newInstance();
				//遍历每一行
				Row dataRow = sheet.getRow(i);
				//对列操作
				for(int j=0;j<lastCellnum;j++){
					//空行时跳过
					if(dataRow==null){
						break;
					}
					//遍历每个单元格
					Cell cell=dataRow.getCell(j,MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//把单元格数据设置为字符串
					cell.setCellType(CellType.STRING);
					//获得单元格值
					String cellValue = cell.getStringCellValue();
					//根据列索引拼接对应set的方法名
					String methodName="set"+loadCellIndexAndCellNameMapping.get(j);
					//获得方法对象
					Method method = clazz.getMethod(methodName, String.class);
					//执行方法
					method.invoke(obj, cellValue);
					if(j==0){
						rowIdentifiedAndIndexMapping.put(cellValue, i);
					}
					
				}
				//获得的行兑现增加到集合中
				t.add(obj);				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return t;
	}




}
