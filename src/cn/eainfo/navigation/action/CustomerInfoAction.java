
package cn.eainfo.navigation.action;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.CustomerInfoWebService;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.navigation.po.CustomerInfo;
import cn.eainfo.navigation.service.CustomerInfoService;
import cn.eainfo.navigation.webService.QueryInfo;
import cn.eainfo.navigation.webService.ResultInfo;
import cn.eainfo.system.service.SysAreaService;



/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CustomerInfoAction {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private String areaString;
	private String areaStrings;
	private long rows;//每页显示的记录数
	private long page;//当前第几页 
	private List areaList;
	private String jsonString;
	private SysAreaService sysAreaService;
	private CustomerInfoService customerInfoService;

	private String userCityEnName;
	
	private Integer id;
	//用户分公司编码
	private String districtCode;
	//用户分公司名称
	private String districtName;
	//用户所属地区编号
	private String areaNo;
	//用户卡号
	private String cardNo;
	//客户端
	private  String nowTime;
	private  String domain;

	public String getUserCityName() {
		logger.log(Level.INFO, " getUserCityName()查询用户信息 开始 BEGIN");
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","emergency","navigation");
		try{
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			//根据卡号查询其所属地区编号
			//areaNo = customerInfoService.getAreaNoByCardNo(cardNo);
			CustomerInfoWebService customerInfoWebService = new CustomerInfoWebService();
			
			//若系统内没有该机顶盒用户则调webService接口查询
			//if(areaNo.equals("")||areaNo==null){
			//logger.log(Level.INFO, "卡号:"+cardNo+"用户不存在本地数据库！接口查询用户信息开始############");
			/*查询客户信息
			 * */
			QueryInfo queryInfo = new QueryInfo();
			if(cardNo==null){
				userCityEnName = "nanjing";
			}else{
				queryInfo.setSmartCardId(cardNo);
				logger.log(Level.INFO, "查询的卡号为："+cardNo);
				ResultInfo resultInfo = customerInfoWebService.getUserInfo(queryInfo);
				if(resultInfo!=null&&resultInfo.getUserInfo()!=null&&resultInfo.getUserInfo().getSmartCardId()!=null){
					districtCode = resultInfo.getUserInfo().getDistrictCode();
					logger.log(Level.INFO, "分公司编码："+districtCode);
					//给用户所属地区赋值
					userCityEnName = customerInfoService.getUserCityName(districtCode);
				}
				//若没有找到相关信息
				else{
					userCityEnName="无用户信息";
					logger.log(Level.INFO, "#################用户信息未找到############");
				}
				
			}
			if(userCityEnName==null){
				userCityEnName = "nanjing";
			}
			ajaxBeanData.setResult("success");
			ajaxBeanData.setLabel("");
			ajaxBeanData.setResume(userCityEnName);
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		}catch(Exception e){
			userCityEnName="查询用户信息异常";
		}
		//将获取的地区信息塞进session下次获取的时候在消除该session
		//ActionContext.getContext().getSession().put("userCityEnNameSession"+nowTime, userCityEnName);
		logger.log(Level.INFO, " getUserCityName()查询用户信息结束");
		return "jsonString";
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
	 * @return 返回 id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param 对id进行赋值
	 */
	public void setId(Integer id) {
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
	 * @return 返回 cradNo
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * @param 对cradNo进行赋值
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	 * @return 返回 customerInfoService
	 */
	public CustomerInfoService getCustomerInfoService() {
		return customerInfoService;
	}



	/**
	 * @param 对customerInfoService进行赋值
	 */
	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}



	/**
	 * @return 返回 userCityEnName
	 */
	public String getUserCityEnName() {
		return userCityEnName;
	}



	/**
	 * @param 对userCityEnName进行赋值
	 */
	public void setUserCityEnName(String userCityEnName) {
		this.userCityEnName = userCityEnName;
	}
	
	public  String getNowTime() {
		return nowTime;
	}
	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	

}
