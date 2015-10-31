/*
 * 文 件 名:  RealseNavigationService.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-4
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service;

import java.io.IOException;
import java.util.List;

import cn.eainfo.navigation.po.Natest;
import cn.eainfo.navigation.po.Navigation;
import cn.eainfo.navigation.po.Release;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ReleaseNavigationService {
	public boolean generateIndexJsp(String areaNo) throws IOException;

	/** 
	 * <功能详细描述>
	 * @param areaNo
	 * @see [类、类#方法、类#成员]
	 */
	public boolean JspToHtmlByURL(String u,String path);

	/** 
	 * <功能详细描述>
	 * @param string
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Release> getServerList(String string);

	/** 
	 * <功能详细描述>
	 * @param release
	 * @see [类、类#方法、类#成员]
	 */
	
	public List<String> getCityName(String areaNo);

	/** 
	 * <功能详细描述>
	 * @param na
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Navigation> getNavigationRealse(Navigation na);

	/** 
	 * <功能详细描述>
	 * @param type
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Natest> getTest(String type);

	

}
