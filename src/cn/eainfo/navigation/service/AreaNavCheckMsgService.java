/*
 * 文 件 名:  AreaNavCheckMsgService.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-18
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.navigation.model.AreaNavCheckMsgModel;
import cn.eainfo.navigation.po.AreaNavCheckMsg;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AreaNavCheckMsgService {

	/** 
	 * <功能详细描述>
	 * @param navigationrModel
	 * @return
	 * @throws Exception 
	 * @see [类、类#方法、类#成员]
	 */
	AjaxJsonListData getAreaNavCheckMsgPage(
			AreaNavCheckMsgModel areaNavCheckMsg) throws Exception;

	void updateAreaMsgAndNav(AreaNavCheckMsg areaCheckMsg);

	void updateAreaMsgAndShort(AreaNavCheckMsg areaCheckMsg);

}
