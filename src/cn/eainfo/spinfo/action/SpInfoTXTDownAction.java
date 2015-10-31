package cn.eainfo.spinfo.action;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import cn.eainfo.common.SystemDate;
import cn.eainfo.spinfo.po.SpInfo;
import cn.eainfo.spinfo.service.SpInfoService;

import com.opensymphony.xwork2.ActionSupport;

public class SpInfoTXTDownAction extends ActionSupport{

	   
	/**  
	 * @Fields serialVersionUID : TODO  
	 */  
	    
	private static final long serialVersionUID = -9223240746376064639L;

	private SpInfoService spInfoService;

	private SpInfo spInfo;

	private long spId;
	private String spName;
	private Date spcreateTime;
	private String princiPal;
	private String princiPaltel;
	private Date contractEffectiveDate;
	private Date contractExpireDate;
	private String csWebsite;
	private long status;
	private String interfaceName;
	private String reMark;
	
	private String createDate;
	private String beginDate;
	private String endDate;

	// 导出TXT
	FileWriter fw = null;
	StringBuffer row = null;

	/**
	 * 
	 * @Title: TXTImport
	 * @Description: 导出TXT文件
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public String TXTExport() throws Exception {
		String result = "";
		try {
			List spinfoList = spInfoService.getAll();
			this.export(spinfoList, "D://spinfo.txt");
			result = "success";
		} catch (Exception e) {
			result = "error";
		}
		 return result;
	}

	public void export(List<SpInfo> dataSource, String file) {

		try {
			fw = new FileWriter(file);
			row = new StringBuffer();
			row.append("SP编号\t\t").append("SP名称\t\t").append("创建日期\t\t")
					.append("负责人\t\t").append("负责人" + "电话\t\t")
					.append("SP合同开始日期\t\t").append("SP合同结束日期\t\t")
					.append("客服网址\t\t").append("状" + "态\t\t")
					.append("SP与号百接口信息\t\t").append("备注\n\n");
			fw.write(row.toString());
			for (int i = 0; i < dataSource.size(); ++i) {
				row = new StringBuffer();
				spInfo = dataSource.get(i);
				spId = spInfo.getSpId();
				spName = spInfo.getSpName();
				
				Date createDates = spInfo.getSpcreateTime();
				spInfo.setCreateDate(SystemDate.formatDate(createDates));
				Date beginDates = spInfo.getContractEffectiveDate();
				spInfo.setBeginDate(SystemDate.formatDate(beginDates));
				Date endDates = spInfo.getContractExpireDate();
				spInfo.setEndDate(SystemDate.formatDate(endDates));
				
				createDate = spInfo.getCreateDate();
				
				princiPal = spInfo.getPrinciPal();
				princiPaltel = spInfo.getPrinciPaltel();
				
				beginDate = spInfo.getBeginDate();
				endDate = spInfo.getEndDate();
				
				csWebsite = spInfo.getCsWebsite();
				status = spInfo.getStatus();
				interfaceName = spInfo.getInterfaceName();
				reMark = spInfo.getReMark();
				row.append("\n\n"+spId + "\t\t").append(spName + "\t\t")
						.append(createDate + "\t\t")
						.append(princiPal + "\t\t")
						.append(princiPaltel + "\t\t")
						.append(beginDate + "\t\t")
						.append(endDate + "\t\t")
						.append(csWebsite + "\t\t").append(status + "\t\t")
						.append(interfaceName + "\t\t").append(reMark + "\n");
				fw.write(row.toString());
			}
			fw.flush();
		} catch (IOException e) {
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public SpInfoService getSpInfoService() {
		return spInfoService;
	}
	public void setSpInfoService(SpInfoService spInfoService) {
		this.spInfoService = spInfoService;
	}
	public SpInfo getSpInfo() {
		return spInfo;
	}
	public void setSpInfo(SpInfo spInfo) {
		this.spInfo = spInfo;
	}
	public long getSpId() {
		return spId;
	}
	public void setSpId(long spId) {
		this.spId = spId;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public Date getSpcreateTime() {
		return spcreateTime;
	}
	public void setSpcreateTime(Date spcreateTime) {
		this.spcreateTime = spcreateTime;
	}
	public String getPrinciPal() {
		return princiPal;
	}
	public void setPrinciPal(String princiPal) {
		this.princiPal = princiPal;
	}
	public String getPrinciPaltel() {
		return princiPaltel;
	}
	public void setPrinciPaltel(String princiPaltel) {
		this.princiPaltel = princiPaltel;
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
	public String getCsWebsite() {
		return csWebsite;
	}
	public void setCsWebsite(String csWebsite) {
		this.csWebsite = csWebsite;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getReMark() {
		return reMark;
	}
	public void setReMark(String reMark) {
		this.reMark = reMark;
	}
}
