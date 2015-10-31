/**
 * 
 */
package cn.eainfo.common.excel;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


 /**
 * <p>文件名称: CommonDateUtils.java </p>
 * <p>文件描述: &lt;描述&gt; </p>
 * <p>版权所有:  Copyright (c) qixin Coperation</p>
 * <p>公    司: 南京企信科技有限公司</p>
 * <p>内容摘要: 无</p>
 * <p>其他说明: 无</p>
 * <p>创建日期：2014-4-20</p>
 * <p>完成日期：2014-4-20</p>
 * <p>修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：Administrator
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 * @version 1.0
 * @author huxiangyu
 */
public class CommonDateUtils {
	
	//日志对象，用于记录日志
    private  Logger logger = Logger.getLogger(this.getClass().getName());
	
    
	/**
	 * 方法说明:获取当前日期
	 * 创建人:huxiangyu
	 * 创建时间:2013-11-29
	 * @param flag
	 * @return temp_str
	 */
	public static String getNowDate(String flag){
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat(flag);
	    String dateString = formatter.format(currentTime);
	    return dateString;
	       
	}
	/**
	 * 
	 * 方法说明:根据特定格式格式化日期，时间
	 * 创建人:huxiangyu
	 * 创建时间:2014-4-17
	 * @param myDate 当前时间
	 * @param pattern 日期格式（可以为"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyy-MM-dd"等）
	 * @return
	 */
	public static  String  dateToString(Date myDate, String pattern){
		String stringDate=DateFormatUtils.format(myDate, pattern);
		return stringDate;
	}
	
	/**
	 * 将date由thisPattern转化为toPattern格式
	 * @param date 
	 * @param thisPattern
	 * @param toPattern
	 * @return
	 */
	public static String dateToString(String strDate, String thisPattern, String toPattern){
		if(StringUtils.isBlank(strDate)){
			return "";
		}
		String returnDate = "";
		try {
			Date date = stringToDate(strDate, thisPattern);
			returnDate = dateToString(date, toPattern);
		} catch (Exception e) {
			
		}
		return returnDate;
	}
	
	/**
	 * 将字符串类型的日期转化为日期对象
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static  Date stringToDate(String strDate, String pattern){
		Date myDate = null;
		try {
			myDate = DateUtils.parseDate(strDate, new String[]{pattern});
		} catch (Exception e) {
			Logger.getLogger(CommonDateUtils.class).log(Level.ERROR,"将字符串转换为日期时间类型时出错"+e);
		}
		return myDate;
	}
	
	/**
	 * 
	 * 方法说明:将字符串转换为日期时间类型
	 * 创建人:huxiangyu
	 * 创建时间:2014-4-17
	 * @param stringDate
	 * @return
	 * @throws DateParseException
	 */
	public static Date stringToDate(String stringDate){
		String[] patterns={"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyy-MM-dd"};
		Date myDate=null;
		try {
			myDate = DateUtils.parseDate(stringDate, patterns);
		} catch (DateParseException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(CommonDateUtils.class).log(Level.ERROR,"将字符串转换为日期时间类型时出错"+e);
		}
		return myDate;
	}
	public static void main(String[] args) {
		/*String str = "";
		try {
			str = dateToString("20131218004557", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
			System.out.println(str);
		} catch (Exception e) {
			// TODO: handle exception
		}*/
	}
}
