package cn.eainfo.system.model;

import java.util.Date;

public class LoginLogModel {
	private Long logid;
	private Date createtime;
	private String operator;
	private Long operatid;
	private String ip;
	public Long getLogid() {
		return logid;
	}
	public void setLogid(Long logid) {
		this.logid = logid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Long getOperatid() {
		return operatid;
	}
	public void setOperatid(Long operatid) {
		this.operatid = operatid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
