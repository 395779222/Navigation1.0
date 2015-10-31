
package cn.eainfo.navigation.action;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.navigation.model.CompanyInfoModel;
import cn.eainfo.navigation.po.CompanyInfo;
import cn.eainfo.navigation.service.CompanyInfoService;
import cn.eainfo.system.model.SysAreaModel;
import cn.eainfo.system.po.SysArea;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.service.SysAreaService;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CompanyInfoAction {
	private Logger logger = Logger.getLogger(this.getClass().getName());
    private long rows;//每页显示的记录数
	private long page;//当前第几页 
	private String areaString;
	private String areaStrings;
	private String jsonString;
	private SysAreaService sysAreaService;
	private CompanyInfoService companyInfoService;
	private long id;
	private String districtCode;
	private String districtName;
	private String areaNo;
	private List areaList;
	private CompanyInfo companyInfo;
	/** 
     * <设置返回的地区字符串>
     * @param SysAreaModel
     * @see [类、类#方法、类#成员]
     */
    public void setPageAreaString(SysAreaModel SysAreaModel){
    	areaList = sysAreaService.getSysAreaList(SysAreaModel);
		String menu = "<select id='areaNo' name='areaNo' class='easyui-combobox' editable='false'>";
		for (int i = 0; i < areaList.size(); i++) {
			SysArea sysArea = (SysArea) areaList.get(i);
			areaString = areaString + "<option value='"+sysArea.getAreaNo()+"'>"+sysArea.getName()+"</option>";
		}
		areaString = menu + areaString + "</select>";
		String menus = "<select id='areaNos' name='areaNos' class='easyui-combobox' editable='false'>";
		for (int i = 0; i < areaList.size(); i++) {
			SysArea sysArea = (SysArea) areaList.get(i);
			areaStrings = areaStrings + "<option value='"+sysArea.getAreaNo()+"'>"+sysArea.getName()+"</option>";
		}
		areaStrings = menus + areaStrings + "</select>";
    }
	/** 
	 * <查询页面list>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String forwordList() throws Exception{
		SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		SysAreaModel sm = new SysAreaModel();
		if(sysUser!=null){
			sm.setAreaNo(sysUser.getAreaNo());
			setPageAreaString(sm);
		}
		//入过登陆超时则直接跳转
		else{
			return "timeOut";
		}
		return "forwordList";
	}
	/** 
	 * <服务器的查询>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxList() throws Exception {
		logger.log(Level.INFO, "ajaxList()分公司信息查询开始");
		//SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		CompanyInfoModel companyInfoModel = new CompanyInfoModel();
		companyInfoModel.setAreaNo(areaNo);
		companyInfoModel.setDistrictName(districtName);
		companyInfoModel.setPage(page);
		companyInfoModel.setPageSize(rows);
		AjaxJsonListData ajaxJsonListData =  companyInfoService.getCompanyInfoPage(companyInfoModel);
		jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
		logger.log(Level.INFO, "ajaxList()分公司信息查询结束");
		return "jsonString";
	}
	/** 
	 * <服务器的信息删除>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxDelete() throws Exception {
		logger.log(Level.INFO, "ajaxDelete()分公司信息查询删除开始");
		companyInfoService.deleteCompanyInfo(id);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysUser");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		logger.log(Level.INFO, "ajaxDelete()分公司信息查询删除结束");
		return "jsonString";
	}
	/** 
	 * <服务器的信息删除>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxGetBean() throws Exception {
		companyInfo = companyInfoService.ajaxGetBean(id);
		jsonString = JSONObjectTool.getObjectJson(companyInfo);
		return "jsonString";
	}
	/** 
	 * <服务器的信息新增>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxSaveOrUpdate() throws Exception{
		logger.log(Level.INFO, "ajaxDelete()分公司信息查询更新开始");
		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setAreaNo(areaNo);
		companyInfo.setId(id);
		companyInfo.setDistrictName(districtName);
		companyInfo.setDistrictCode(districtCode);
		if(id!=0){
			companyInfoService.update(companyInfo);
		}
		else{
			companyInfoService.insert(companyInfo);
		}
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysUser");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		logger.log(Level.INFO, "ajaxDelete()分公司信息查询删除结束");
		return "jsonString";
	}
	/**
	 * @return 返回 rows
	 */
	public long getRows() {
		return rows;
	}
	/**
	 * @param 对rows进行赋值
	 */
	public void setRows(long rows) {
		this.rows = rows;
	}
	/**
	 * @return 返回 page
	 */
	public long getPage() {
		return page;
	}
	/**
	 * @param 对page进行赋值
	 */
	public void setPage(long page) {
		this.page = page;
	}
	/**
	 * @return 返回 areaString
	 */
	public String getAreaString() {
		return areaString;
	}
	/**
	 * @param 对areaString进行赋值
	 */
	public void setAreaString(String areaString) {
		this.areaString = areaString;
	}
	/**
	 * @return 返回 areaStrings
	 */
	public String getAreaStrings() {
		return areaStrings;
	}
	/**
	 * @param 对areaStrings进行赋值
	 */
	public void setAreaStrings(String areaStrings) {
		this.areaStrings = areaStrings;
	}
	/**
	 * @return 返回 jsonString
	 */
	public String getJsonString() {
		return jsonString;
	}
	/**
	 * @param 对jsonString进行赋值
	 */
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	/**
	 * @return 返回 sysAreaService
	 */
	public SysAreaService getSysAreaService() {
		return sysAreaService;
	}
	/**
	 * @param 对sysAreaService进行赋值
	 */
	public void setSysAreaService(SysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}
	/**
	 * @return 返回 companyInfoService
	 */
	public CompanyInfoService getCompanyInfoService() {
		return companyInfoService;
	}
	/**
	 * @param 对companyInfoService进行赋值
	 */
	public void setCompanyInfoService(CompanyInfoService companyInfoService) {
		this.companyInfoService = companyInfoService;
	}
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
	 * @return 返回 districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}
	/**
	 * @param 对districtCode进行赋值
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	/**
	 * @return 返回 districtName
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * @param 对districtName进行赋值
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
	 * @return 返回 areaList
	 */
	public List getAreaList() {
		return areaList;
	}
	/**
	 * @param 对areaList进行赋值
	 */
	public void setAreaList(List areaList) {
		this.areaList = areaList;
	}
	/**
	 * @return 返回 companyInfo
	 */
	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}
	/**
	 * @param 对companyInfo进行赋值
	 */
	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}
	
	
}
