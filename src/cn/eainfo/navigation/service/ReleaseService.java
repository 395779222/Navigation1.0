/*
 * 文 件 名:  ReleaseService.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-6
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service;

import java.util.List;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.navigation.model.ReleaseModel;
import cn.eainfo.navigation.po.Release;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ReleaseService {

	/** 
	 * <功能详细描述>
	 * @param releaseModel
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	AjaxJsonListData getReleasePage(ReleaseModel releaseModel);

	/** 
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	void deleteRelease(long id);

	/** 
	 * <功能详细描述>
	 * @param release
	 * @see [类、类#方法、类#成员]
	 */
	void add(Release release);

	Release getShortBean(long id);

	void etid(Release release);

	List getAllRelease(Release release);

	void deleteAllRelease();

}
