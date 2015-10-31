

package cn.eainfo.navigation.model;

import cn.eainfo.common.BaseModel;
 /**
 * <p>文件名称: AreaNavCheckMsgModel.java </p>
 * <p>文件描述: &lt;描述&gt; </p>
 * <p>版权所有:  Copyright (c) qixin Coperation</p>
 * <p>公    司: 南京企信科技有限公司</p>
 * <p>内容摘要: 无</p>
 * <p>其他说明: 无</p>
 * <p>创建日期：2014-4-18</p>
 * <p>完成日期：2014-4-18</p>
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
public class AreaNavCheckMsgModel extends BaseModel{
	private long id;
	private String areaNo;
	private String enName;
	private Integer isToCheckNum;
	private Integer isNotToCheckNum;
	private String checkText;
	private String checkState;
	private String type;
	/**
	 * @return 返回 id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param 对id进行赋值
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return 返回 isToCheckNum
	 */
	public Integer getIsToCheckNum() {
		return isToCheckNum;
	}
	/**
	 * @param 对isToCheckNum进行赋值
	 */
	public void setIsToCheckNum(Integer isToCheckNum) {
		this.isToCheckNum = isToCheckNum;
	}
	/**
	 * @return 返回 isNotToCheckNum
	 */
	public Integer getIsNotToCheckNum() {
		return isNotToCheckNum;
	}
	/**
	 * @param 对isNotToCheckNum进行赋值
	 */
	public void setIsNotToCheckNum(Integer isNotToCheckNum) {
		this.isNotToCheckNum = isNotToCheckNum;
	}
	/**
	 * @return 返回 checkText
	 */
	public String getCheckText() {
		return checkText;
	}
	/**
	 * @param 对checkText进行赋值
	 */
	public void setCheckText(String checkText) {
		this.checkText = checkText;
	}
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
	 * @return 返回 enName
	 */
	public String getEnName() {
		return enName;
	}
	/**
	 * @param 对enName进行赋值
	 */
	public void setEnName(String enName) {
		this.enName = enName;
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
