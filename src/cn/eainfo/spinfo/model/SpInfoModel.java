package cn.eainfo.spinfo.model;

import java.util.Date;

import cn.eainfo.common.BaseModel;

public class SpInfoModel extends BaseModel {
	
	private String spName;
	private Date contractEffectiveDate;
	private Date contractExpireDate;
	private long status;
	private Date beginDate;
	private Date endDate;
	
	private String reMark;
	
	
	
	public String getReMark() {
		return reMark;
	}
	public void setReMark(String reMark) {
		this.reMark = reMark;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public Date getContractEffectiveDate() {
		return contractEffectiveDate;
	}
	public void setContractEffectiveDate(Date contractEffectiveDate) {
		this.contractEffectiveDate = contractEffectiveDate;
	}
	public Date getContractExpireDate() {
		return contractExpireDate;
	}
	public void setContractExpireDate(Date contractExpireDate) {
		this.contractExpireDate = contractExpireDate;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
}
