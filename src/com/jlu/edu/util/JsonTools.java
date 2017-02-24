package com.jlu.edu.util;


import net.sf.json.JSONObject;

public class JsonTools {
	  public JsonTools(){
		  
	  }
	  
	  /**
	   * 
	   * @param key : JSON 名值对中的的名字
	   * @param value : JSON 名值对中的值,值可以有多种类型 
	   * @return
	   */
	  public static String createJsonString(String key, Object value){  
	      JSONObject jsonObject = new JSONObject();  
	      jsonObject.put(key, value);  
	      return jsonObject.toString(); //就可以转换成Json数据格式  
	  }  
}