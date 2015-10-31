package cn.eainfo.system.model;

import java.util.Date;

/**
 * Checkphone entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CheckphoneModel{

	// Fields

	private String id;
	private String accountid;
	private String checkphonepwd;
	private String sendmessage;
	private Date sendtime;
	private Long sendstate;
	private Long crrstate;
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountid() {
		return this.accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getCheckphonepwd() {
		return this.checkphonepwd;
	}

	public void setCheckphonepwd(String checkphonepwd) {
		this.checkphonepwd = checkphonepwd;
	}

	public String getSendmessage() {
		return this.sendmessage;
	}

	public void setSendmessage(String sendmessage) {
		this.sendmessage = sendmessage;
	}

	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Long getSendstate() {
		return this.sendstate;
	}

	public void setSendstate(Long sendstate) {
		this.sendstate = sendstate;
	}

	public Long getCrrstate() {
		return this.crrstate;
	}

	public void setCrrstate(Long crrstate) {
		this.crrstate = crrstate;
	}

}