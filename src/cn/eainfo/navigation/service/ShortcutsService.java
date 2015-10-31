/*
 * 文 件 名:  ShortcutsService.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-6
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.navigation.model.ShortcutsModel;
import cn.eainfo.navigation.po.Shortcuts;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ShortcutsService {

	/** 
	 * <功能详细描述>
	 * @param shortcutsModel
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	AjaxJsonListData getShortPage(ShortcutsModel shortcutsModel);

	/** 
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	void deleteShort(long id);

	/** 
	 * <功能详细描述>
	 * @param shortcuts
	 * @see [类、类#方法、类#成员]
	 */
	void add(Shortcuts shortcuts);

	/** 
	 * <功能详细描述>
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	Shortcuts getShortBean(long id);

	/** 
	 * <功能详细描述>
	 * @param shortcuts
	 * @see [类、类#方法、类#成员]
	 */
	void edit(Shortcuts shortcuts);

	String getShortIsHavePssed(Shortcuts shortcuts);

	String keyIsExist(Shortcuts shortcuts);

}
