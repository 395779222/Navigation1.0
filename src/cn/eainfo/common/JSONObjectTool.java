package cn.eainfo.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONObjectTool {
	public static String ENCONDING = "utf-8";

	/**
	 * 获取json数据,返回结果
	 * 
	 * @throws Exception
	 */
	public static InputStream getJson(Object object) throws Exception {
		String temp = JSONObject.fromObject(object).toString();
		InputStream operateResultString = new ByteArrayInputStream(
				temp.getBytes(ENCONDING));
		return operateResultString;
	}

	/**
	 * 获取集合的json数据,返回字符串
	 * 
	 * @throws Exception
	 */
	public static String getJson(JSONArray jsonArray) throws Exception {
		String temp = JSONArray.fromObject(jsonArray).toString();
		return temp;
	}

	/**
	 * 获取json数据,返回结果
	 * 
	 * @throws Exception
	 */
	public static String getObjectJson(Object object) throws Exception {
		String temp = JSONObject.fromObject(object).toString();
		return temp;
	}

}
