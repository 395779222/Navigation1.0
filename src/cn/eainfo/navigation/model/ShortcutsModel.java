/*
 * 文 件 名:  ShortcutsModel.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-6
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.model;

import cn.eainfo.common.BaseModel;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ShortcutsModel extends BaseModel{
	private String type;
	private String areaNo;
	private String checkState;
	
	
	/**
	 * @return 返回 checkState
	 */
	public String getCheckState() {
		return checkState;
	}

	/**
	 * @param 对checkState进行赋值
	 */
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	/**
	 * @return 返回 areaNo
	 */
	public String getAreaNo() {
		return areaNo;
	}

	/**
	 * @param 对areaNo进行赋值
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	/**
	 * @return 返回 type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param 对type进行赋值
	 */
	public void setType(String type) {
		this.type = type;
	}
}
