package cn.eainfo.spinfo.action;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.spinfo.model.SpInfoModel;
import cn.eainfo.spinfo.po.SpInfo;
import cn.eainfo.spinfo.service.SpInfoService;

public class SpInfoAction {

	private SpInfoService spInfoService;

	private InputStream operateResultString = null;
	private long rows;//每页显示的记录数
	private long page;//当前第几页 
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

	private String beginDate;
	private String endDate;
	private String spEName;// sp英文名称
	private Integer type;// 类型
	private String SPTel;// SP客服电话
	private String descriptionCN;// 公司中文描述
	private String descriptionEN;// 公司英文描述
	private String provinceId;// 省份ID
	private Integer roamProperty;// 漫游地业务
	private String companyAddress;// 公司地址
	private String legalRepresentative;// sp法人代表
	private String princiPalEmail;// 总负责人email
	private String serviceManager;// 业务联系人
	private String serviceManagerTel;// 业务联系人电话
	private String serviceManagerEmail;// 业务联系人邮箱
	private String serviceManagerAddr;// 业务联系人地址
	private String serviceManagerPC;// 业务联系人邮政编码
	private String serviceManagerFax;// 业务联系人传真
	private String license;// SP营业执照
	private String accessNo;// 接入号
	private Integer settlementCycle;// 结算周期：0：按月 1：按季度 2：按年
	private Integer settlementPayType;// 结算付费方式：0：一次性付清 1：分批付款
	private String settlementPercent;// 结算百分比
	private Date startDate;//
	private Date overDate;//
	private String statusName;
	private String jsonString;
	/**
	 * 列表查询
	 * 
	 * @throws Exception
	 */
	public String ajaxList() throws Exception {
		SpInfoModel spInfoModel = new SpInfoModel();
		spInfoModel.setSpName(spName);
		spInfoModel.setStatus(status);
		spInfoModel.setPage(page);
		spInfoModel.setPageSize(rows);
		spInfoModel.setBeginDate(startDate);
		spInfoModel.setEndDate(overDate);
		AjaxJsonListData ajaxJsonListData = spInfoService.getSpInfoPage(spInfoModel);
		jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
		return "jsonString";
	}

	/**
	 * 修改
	 * 
	 * @throws Exception
	 */
	public String ajaxSaveOrUpdate() throws Exception {
		if (spId != 0) {
			// 修改
			spInfo = spInfoService.getSpInfoBean(spId);
			spInfo.setSpName(spName);
			spInfo.setBeginDate(beginDate);
			spInfo.setEndDate(endDate);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 将接收到的字符串转化为Date类型
			spInfo.setPrinciPal(princiPal);
			spInfo.setPrinciPaltel(princiPaltel);
			Date beginDates = df.parse(spInfo.getBeginDate());
			spInfo.setContractEffectiveDate(beginDates);
			Date endDates = df.parse(spInfo.getEndDate());
			spInfo.setContractExpireDate(endDates);
			spInfo.setCsWebsite(csWebsite);
			spInfo.setStatus(status);
			spInfo.setInterfaceName(interfaceName);
			spInfo.setReMark(reMark);
			spInfo.setAccessNo(accessNo);
			spInfo.setCompanyAddress(companyAddress);
			spInfo.setDescriptionCN(descriptionCN);
			spInfo.setDescriptionEN("spEnDES"+descriptionCN);
			spInfo.setLegalRepresentative(princiPal);
			spInfo.setLicense(license);
			spInfo.setPrinciPalEmail(princiPalEmail);
			spInfo.setProvinceId("8632");
			spInfo.setRoamProperty(roamProperty);
			spInfo.setServiceManager(princiPal);
			spInfo.setServiceManagerAddr(serviceManagerAddr);
			spInfo.setServiceManagerEmail(princiPalEmail);
			spInfo.setServiceManagerFax(serviceManagerFax);
			spInfo.setServiceManagerPC(serviceManagerPC);
			spInfo.setServiceManagerTel(princiPaltel);
			spInfo.setSettlementCycle(settlementCycle);
			spInfo.setSettlementPayType(settlementPayType);
			spInfo.setSettlementPercent(settlementPercent);
			spInfo.setSpEName("spEnName"+spName);
			spInfo.setSPTel(princiPaltel);
			spInfoService.updateSpInfo(spInfo);
		} else {
			// 新增
			spInfo = new SpInfo();
			spInfo.setSpName(spName);
			spInfo.setPrinciPal(princiPal);
			spInfo.setPrinciPaltel(princiPaltel);
			spInfo.setBeginDate(beginDate);
			spInfo.setEndDate(endDate);

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 将接收到的字符串转化为Date类型
			Date beginDates = df.parse(spInfo.getBeginDate());
			spInfo.setContractEffectiveDate(beginDates);
			Date endDates = df.parse(spInfo.getEndDate());
			spInfo.setContractExpireDate(endDates);
			spInfo.setCsWebsite(csWebsite);
			spInfo.setStatus(0);
			spInfo.setInterfaceName(interfaceName);
			spInfo.setReMark(reMark);
			spInfo.setAccessNo(accessNo);
			spInfo.setCompanyAddress(companyAddress);
			spInfo.setDescriptionCN(descriptionCN);
			spInfo.setDescriptionEN("spEnDES"+descriptionCN);
			spInfo.setLegalRepresentative(princiPal);
			spInfo.setLicense(license);
			spInfo.setPrinciPalEmail(princiPalEmail);
			spInfo.setProvinceId("8632");
			spInfo.setRoamProperty(roamProperty);
			spInfo.setServiceManager(princiPal);
			spInfo.setServiceManagerAddr(serviceManagerAddr);
			spInfo.setServiceManagerEmail(princiPalEmail);
			spInfo.setServiceManagerFax(serviceManagerFax);
			spInfo.setServiceManagerPC(serviceManagerPC);
			spInfo.setServiceManagerTel(princiPaltel);
			spInfo.setSettlementCycle(settlementCycle);
			spInfo.setSettlementPayType(settlementPayType);
			spInfo.setSettlementPercent(settlementPercent);
			spInfo.setSpEName("spEnName"+spName);
			spInfo.setSpcreateTime(new Date());
			spInfo.setSPTel(princiPaltel);
			spInfoService.insertSpInfo(spInfo);
		}

		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("spInfo");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";

	}
	
	/**
	 * 新增
	 * 
	 * @throws Exception
	 */
	public String ajaxInert() throws Exception {
		spInfo = new SpInfo();
		spInfo.setSpName(spName);
		spInfo.setPrinciPal(princiPal);
		spInfo.setPrinciPaltel(princiPaltel);
		spInfo.setContractEffectiveDate(contractEffectiveDate);
		spInfo.setContractExpireDate(contractExpireDate);
		spInfo.setCsWebsite(csWebsite);
		spInfo.setStatus(0);
		spInfo.setInterfaceName(interfaceName);
		spInfo.setReMark(reMark);
		spInfo.setAccessNo(accessNo);
		spInfo.setCompanyAddress(companyAddress);
		spInfo.setDescriptionCN(descriptionCN);
		spInfo.setDescriptionEN("spEnDES"+descriptionCN);
		spInfo.setLegalRepresentative(princiPal);
		spInfo.setLicense(license);
		spInfo.setPrinciPalEmail(princiPalEmail);
		spInfo.setProvinceId("8632");
		spInfo.setRoamProperty(roamProperty);
		spInfo.setServiceManager(princiPal);
		spInfo.setServiceManagerAddr(serviceManagerAddr);
		spInfo.setServiceManagerEmail(princiPalEmail);
		spInfo.setServiceManagerFax(serviceManagerFax);
		spInfo.setServiceManagerPC(serviceManagerPC);
		spInfo.setServiceManagerTel(princiPaltel);
		spInfo.setSettlementCycle(settlementCycle);
		spInfo.setSettlementPayType(settlementPayType);
		spInfo.setSettlementPercent(settlementPercent);
		spInfo.setSpEName("spEnName"+spName);
		spInfo.setSpcreateTime(contractEffectiveDate);
		spInfo.setSPTel(princiPaltel);
		spInfoService.insertSpInfo(spInfo);
		String result = "<Response><ProdOfferSyncToVSOPResp><StreamingNo>2013042715000000000000000000013</StreamingNo><ResultCode>0</ResultCode><ResultDesc>同步成功</ResultDesc></ProdOfferSyncToVSOPResp></Response>";
		if (result.contains("<ResultCode>0</ResultCode>")) {
			AjaxBeanData ajaxBeanData = new AjaxBeanData();
			ajaxBeanData.setResult("sucess");
			ajaxBeanData.setResume("成功");
			ajaxBeanData.setLabel("spInfo");
			operateResultString = JSONObjectTool.getJson(ajaxBeanData);
		} else {
			AjaxBeanData ajaxBeanData = new AjaxBeanData();
			ajaxBeanData.setResult("error");
			ajaxBeanData.setResume("失败");
			ajaxBeanData.setLabel("spInfo");
			operateResultString = JSONObjectTool.getJson(ajaxBeanData);
		}
		return "ajaxData";
	}

	/**
	 * 获取
	 * 
	 * @throws Exception
	 */
	public String ajaxGetBean() throws Exception {		
		spInfo = spInfoService.getSpInfoBean(spId);
		if(spInfo.getStatus()==0){
			spInfo.setStatusName("正常");
		}
		if(spInfo.getStatus()==1){
			spInfo.setStatusName("注销");
		}
		jsonString = JSONObjectTool.getObjectJson(spInfo);
		return "jsonString";
	}

	/**
	 * 转到修改
	 * 
	 */
	public String forwordUpdate() {
		
		return "forwordUpdate";
	}

	/**
	 * 
	 * 转到显示
	 * 
	 */
	public String forwordShow() {
		return "forwordShow";
	}

	/**
	 * 修改
	 * 
	 * @throws Exception
	 */
	public String ajaxUpdate() throws Exception {
		spInfo = spInfoService.getSpInfoBean(spId);
		spInfo.setSpName(spName);
		spInfo.setBeginDate(beginDate);
		spInfo.setEndDate(endDate);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 将接收到的字符串转化为Date类型

		spInfo.setPrinciPal(princiPal);
		spInfo.setPrinciPaltel(princiPaltel);

		Date beginDates = df.parse(spInfo.getBeginDate());
		spInfo.setContractEffectiveDate(beginDates);

		Date endDates = df.parse(spInfo.getEndDate());
		spInfo.setContractExpireDate(endDates);

		spInfo.setCsWebsite(csWebsite);
		spInfo.setStatus(status);
		spInfo.setInterfaceName(interfaceName);
		spInfo.setReMark(reMark);
		spInfoService.updateSpInfo(spInfo);
		return this.ajaxList();
	}

	/**
	 * 删除
	 * 
	 * @throws Exception
	 */
	public String ajaxDelete() throws Exception {
		spInfo = spInfoService.getSpInfoBean(spId);
		spInfo.setStatus(1);
		spInfoService.updateSpInfo(spInfo);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("spInfo");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";
	}

	/**
	 * 
	 * @Title: EXCELImport
	 * @Description: 导出EXCEL
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	// public void EXCELImport() throws Exception {
	// String result = "";
	// try {
	// List spinfoList = spInfoService.getAll();
	//
	// XLSUtil<SpInfo> ex = new XLSUtil<SpInfo>();
	// String[] headers = { "SP编号 ", "SP名称 ", "创建日期  ", "负责人", "负责人电话 ",
	// "SP合同开始时间 ", "SP合同结束时间 ", "客服网址", "状态 ", "SP和号百接口信息", "备注 " };
	// OutputStream out = new FileOutputStream("D://spinfot.xls");//
	// C:\\Users\\Administrator\\Desktop\\spinfo.xls
	// ex.exportExcel(headers, spinfoList, out);
	// out.close();
	// JOptionPane.showMessageDialog(null, " 导出成功 !");
	// System.out.println("excel 导出成功！ ");
	// result = "succe";
	// } catch (Exception e) {
	// e.printStackTrace();
	// result = "erro";
	// }
	// // return result;
	// }

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

	public String getCsWebsite() {
		return csWebsite;
	}

	public void setCsWebsite(String csWebsite) {
		this.csWebsite = csWebsite;
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

	public SpInfoService getSpInfoService() {
		return spInfoService;
	}

	public void setSpInfoService(SpInfoService spInfoService) {
		this.spInfoService = spInfoService;
	}

	public InputStream getOperateResultString() {
		return operateResultString;
	}

	public void setOperateResultString(InputStream operateResultString) {
		this.operateResultString = operateResultString;
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

	public String getSpEName() {
		return spEName;
	}

	public void setSpEName(String spEName) {
		this.spEName = spEName;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSPTel() {
		return SPTel;
	}

	public void setSPTel(String sPTel) {
		SPTel = sPTel;
	}

	public String getDescriptionCN() {
		return descriptionCN;
	}

	public void setDescriptionCN(String descriptionCN) {
		this.descriptionCN = descriptionCN;
	}

	public String getDescriptionEN() {
		return descriptionEN;
	}

	public void setDescriptionEN(String descriptionEN) {
		this.descriptionEN = descriptionEN;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	public Integer getRoamProperty() {
		return roamProperty;
	}

	public void setRoamProperty(Integer roamProperty) {
		this.roamProperty = roamProperty;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getPrinciPalEmail() {
		return princiPalEmail;
	}

	public void setPrinciPalEmail(String princiPalEmail) {
		this.princiPalEmail = princiPalEmail;
	}

	public String getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(String serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String getServiceManagerTel() {
		return serviceManagerTel;
	}

	public void setServiceManagerTel(String serviceManagerTel) {
		this.serviceManagerTel = serviceManagerTel;
	}

	public String getServiceManagerEmail() {
		return serviceManagerEmail;
	}

	public void setServiceManagerEmail(String serviceManagerEmail) {
		this.serviceManagerEmail = serviceManagerEmail;
	}

	public String getServiceManagerAddr() {
		return serviceManagerAddr;
	}

	public void setServiceManagerAddr(String serviceManagerAddr) {
		this.serviceManagerAddr = serviceManagerAddr;
	}

	public String getServiceManagerPC() {
		return serviceManagerPC;
	}

	public void setServiceManagerPC(String serviceManagerPC) {
		this.serviceManagerPC = serviceManagerPC;
	}

	public String getServiceManagerFax() {
		return serviceManagerFax;
	}

	public void setServiceManagerFax(String serviceManagerFax) {
		this.serviceManagerFax = serviceManagerFax;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getAccessNo() {
		return accessNo;
	}

	public void setAccessNo(String accessNo) {
		this.accessNo = accessNo;
	}
	
	public Integer getSettlementCycle() {
		return settlementCycle;
	}

	public void setSettlementCycle(Integer settlementCycle) {
		this.settlementCycle = settlementCycle;
	}

	public Integer getSettlementPayType() {
		return settlementPayType;
	}

	public void setSettlementPayType(Integer settlementPayType) {
		this.settlementPayType = settlementPayType;
	}

	public String getSettlementPercent() {
		return settlementPercent;
	}

	public void setSettlementPercent(String settlementPercent) {
		this.settlementPercent = settlementPercent;
	}
	
	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getOverDate() {
		return overDate;
	}

	public void setOverDate(Date overDate) {
		this.overDate = overDate;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
