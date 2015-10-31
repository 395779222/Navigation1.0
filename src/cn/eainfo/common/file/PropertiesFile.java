package cn.eainfo.common.file;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * <p>文件名称: PropertiesFile.java </p>
 * <p>文件描述: &lt;描述&gt; </p>
 * <p>版权所有:  Copyright (c) qixin Coperation</p>
 * <p>公    司: 南京企信科技有限公司</p>
 * <p>内容摘要: 无</p>
 * <p>其他说明: 无</p>
 * <p>创建日期：2014-5-7</p>
 * <p>完成日期：2014-5-7</p>
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
public class PropertiesFile {
	/** 
	 * <根据所给的名称获取properties里面的属性值>
	 * @param name(属性值名称)
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getPropertiesValueByName(String name){
		PropertiesFile aa  = new PropertiesFile();	
		//获取文件时需要将空格转化
		File pf = new File(aa.getClass().getResource("/").getPath().toString().replaceAll("%20", " ")+"systemParamter.properties");  
	   // 生成文件输入流  
	    FileInputStream inpf = null;  
	    try {  
	    	inpf = new FileInputStream(pf);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	  
        // 生成properties对象  
        Properties p = new Properties();  
        try {  
            p.load(inpf);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return p.getProperty(name);
	}
		
 }
