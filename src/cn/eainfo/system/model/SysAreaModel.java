package cn.eainfo.system.model;

import cn.eainfo.common.BaseModel;

/**
 * 地区管理
 */
public class SysAreaModel extends BaseModel{
	private String areaNo;
	private String name;
	private String areaCode;
	private Short telCount;
	private String superArea;
	private Integer billCycle;
	private Integer powerLevel;
	
	private Integer statOrder;
	private Integer statOrder1;
	
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Short getTelCount() {
		return telCount;
	}
	public void setTelCount(Short telCount) {
		this.telCount = telCount;
	}
	public String getSuperArea() {
		return superArea;
	}
	public void setSuperArea(String superArea) {
		this.superArea = superArea;
	}
	public Integer getBillCycle() {
		return billCycle;
	}
	public void setBillCycle(Integer billCycle) {
		this.billCycle = billCycle;
	}
	public Integer getPowerLevel() {
		return powerLevel;
	}
	public void setPowerLevel(Integer powerLevel) {
		this.powerLevel = powerLevel;
	}
	public Integer getStatOrder() {
		return statOrder;
	}
	public void setStatOrder(Integer statOrder) {
		this.statOrder = statOrder;
	}
	public Integer getStatOrder1() {
		return statOrder1;
	}
	public void setStatOrder1(Integer statOrder1) {
		this.statOrder1 = statOrder1;
	}
}
