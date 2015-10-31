/*
 * 文 件 名:  NavigationService.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-1
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service;

import java.util.List;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.navigation.model.NavigationModel;
import cn.eainfo.navigation.po.Navigation;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface NavigationService {

	/** 
	 * <功能详细描述>
	 * @param navigationrModel
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	AjaxJsonListData getNavigationPage(NavigationModel navigationrModel);

	/** 
	 * <功能详细描述>
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	Navigation getNavigationBean(long id);

	/** 
	 * <功能详细描述>
	 * @param navigation
	 * @see [类、类#方法、类#成员]
	 */
	void updateNavigation(Navigation navigation);

	/** 
	 * <功能详细描述>
	 * @param navigation
	 * @see [类、类#方法、类#成员]
	 */
	void deleteNavigation(long id,Navigation navigation);


	/** 
	 * <功能详细描述>
	 * @param navigation
	 * @see [类、类#方法、类#成员]
	 */
	void addNavigation(Navigation navigation);

	/** 
	 * <功能详细描述>
	 * @param navigationrModel
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("rawtypes")
	List getExportList(NavigationModel navigationrModel);

	/** 
	 * <功能详细描述>
	 * @param areaNo
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	boolean deleteNavigationListByAreaNo(Navigation navigation);

	Integer getMaxLoactionByAreaAndType(Navigation navigation);

	String getNavigationIsHavePssed(Navigation nav);

	

	

}
