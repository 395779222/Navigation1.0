package cn.eainfo.spinfo.po;

import java.util.Date;

public class SpInfo {
	/*
	 * 
	 * "SPID" NUMBER not null,
       "SPNAME" VARCHAR2(50) not null,
       "SPCREATETIME" DATE not null,
       "PRINCIPAL" VARCHAR2(50),
       "PRINCIPALTEL" VARCHAR2(50),
       "CONTRACTEFFECTIVEDATE" DATE not null,
       "CONTRACTEXPIREDATE" DATE not null,
       "CSWEBSITE" VARCHAR2(2000),
       "STATUS" NUMBER not null,
       "INTERFACENAME" VARCHAR2(2000) not null,
       "REMARK" VARCHAR2(2000),
       "RESERVED" NUMBER,
       "RESERVED1" VARCHAR2(200),
       "RESERVED2" VARCHAR2(2000),
       "RESERVED3" VARCHAR2(2000),
       "SPENAME" VARCHAR2(50 BYTE) NULL ,
"COMPANYDES" VARCHAR2(2000 BYTE) NULL ,
"COMPANYEDES" VARCHAR2(2000 BYTE) NULL ,
"TYPE" NUMBER NULL ,
"SPTEL" VARCHAR2(50 BYTE) NULL ,
"DESCRIPTIONCN" VARCHAR2(512 BYTE) NULL ,
"DESCRIPTIONEN" VARCHAR2(512 BYTE) NULL ,
"PROVINCEID" VARCHAR2(10 BYTE) NULL ,
"ROAMPROPERTY" NUMBER(3) NULL ,
"COMPANTADDRESS" VARCHAR2(512 BYTE) NULL ,
"LEGALREPRESENTATIVE" VARCHAR2(512 BYTE) NULL ,
"PRINCIPALEMAIL" VARCHAR2(512 BYTE) NULL ,
"SERVICEMANAGER" VARCHAR2(512 BYTE) NULL ,
"SERVICEMANAGERTEL" VARCHAR2(512 BYTE) NULL ,
"SERVICEMANAGEREMAIL" VARCHAR2(512 BYTE) NULL ,
"SERVICEMANAGERADDR" VARCHAR2(512 BYTE) NULL ,
"SERVICEMANAGERPC" VARCHAR2(512 BYTE) NULL ,
"SERVICEMANAGERFAX" VARCHAR2(512 BYTE) NULL ,
"LICENSE" VARCHAR2(512 BYTE) NULL ,
"ACCESSNO" VARCHAR2(21 BYTE) NULL ,
"SETTLEMENTCYCLE" NUMBER(1) NULL ,
"SETTLEMENTPAYTYPE" NUMBER(1) NULL ,
"SETTLEMENTPERCENT" VARCHAR2(10 BYTE) NULL ,
	 */
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
	private long reserved;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String statusName;
	
	private String createDate;
	private String beginDate;
	private String endDate;
	private String spEName;//sp英文名称
	private Integer type;//类型
	private String SPTel;//SP客服电话
	private String descriptionCN;//公司中文描述
	private String descriptionEN;//公司英文描述
	private String provinceId;//省份ID
	private Integer roamProperty;//漫游地业务
	private String companyAddress;//公司地址
	private String LegalRepresentative;//sp法人代表
	private String princiPalEmail;//总负责人email
	private String serviceManager;//业务联系人
	private String serviceManagerTel;//业务联系人电话
	private String serviceManagerEmail;//业务联系人邮箱
	private String serviceManagerAddr;//业务联系人地址
	private String serviceManagerPC;//业务联系人邮政编码
	private String serviceManagerFax;//业务联系人传真
	private String license;//SP营业执照
	private String accessNo;//接入号
	private Integer settlementCycle;//结算周期：0：按月 	1：按季度  2：按年 
	private Integer settlementPayType;//结算付费方式：0：一次性付清  1：分批付款
	private String settlementPercent;//结算百分比
	
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
	public long getReserved() {
		return reserved;
	}
	public void setReserved(long reserved) {
		this.reserved = reserved;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
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
		return LegalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		LegalRepresentative = legalRepresentative;
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
	
	
	
	
}
