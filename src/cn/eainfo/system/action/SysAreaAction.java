package cn.eainfo.system.action;

import java.io.InputStream;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;



import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.AjaxJsonListData;

import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.system.model.SysAreaModel;
import cn.eainfo.system.po.SysArea;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.service.SysAreaService;

 /**
 * <p>文件名称: SysAreaAction.java </p>
 * <p>文件描述: &lt;描述&gt; </p>
 * <p>版权所有:  Copyright (c) qixin Coperation</p>
 * <p>公    司: 南京企信科技有限公司</p>
 * <p>内容摘要: 无</p>
 * <p>其他说明: 无</p>
 * <p>创建日期：2014-4-27</p>
 * <p>完成日期：2014-4-27</p>
 * <p>修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：huxiangyu
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 * @version 1.0
 * @author huxiangyu
 */
public class SysAreaAction {
	// 地区管理接口
	private SysAreaService sysAreaService;

	/**
	 * 列表查询参数，page当前页码 rows是当前页的行数
	 */
	private long pageNum;// 当前页码
	private String name;
	private long pageSize;// 当前页条数
	private InputStream operateResultString = null;// 返回结果
	private long rows;//每页显示的记录数
	private long page;//当前第几页 
	private String jsonString;
	private String areaNo;
	private String enName;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	/**
	 * 获取地区信息
	 * @throws Exception
	 */
	public String ajaxGetBean() throws Exception {
		SysArea area = sysAreaService.getSysAreaBean(areaNo);
		jsonString = JSONObjectTool.getObjectJson(area);
		return "jsonString";
	}
	
	public String ajaxSaveOrUpdate() throws Exception {
		SysArea area = new SysArea();
		logger.log(Level.INFO,"ajaxSaveOrUpdate()地区信息更新开始");
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","失败","navigation");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		logger.log(Level.INFO,"ajaxSaveOrUpdate()地区信息更新结束");
		try{
			area.setAreaNo(areaNo);
			area.setEnName(enName);
			area.setName(name);
			//第一临时的地区对象来判断该地区是否已经存在数据库里面
			SysArea tempArea = sysAreaService.getSysAreaBean(areaNo);
			//若是不为空则表示此次更新为修改
			if(tempArea!=null){
				sysAreaService.edit(area);
			}
			else{
				area.setAreaCode("025");
				area.setTelCount( 8);
				area.setSuperArea("00");
				area.setBillCycle(1);
				area.setPowerLevel(2);
				area.setStatOrder(100);
				area.setStatOrder1(1);
				sysAreaService.insert(area);
			}
			ajaxBeanData = new AjaxBeanData("success","地区信息更新成功","navigation");
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		}catch(Exception e){
			logger.log(Level.ERROR,"ajaxSaveOrUpdate()地区信息更新出错"+e);
		}
		return "jsonString";
	}
	/**
	 * 列表查询
	 * 
	 * @throws Exception
	 */
	public String ajaxList() throws Exception {
		SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");		
		SysAreaModel sysAreaModel = new SysAreaModel();
		sysAreaModel.setName(name);
		sysAreaModel.setPowerLevel(-1);
		sysAreaModel.setPage(page);
		sysAreaModel.setPageSize(rows);
		sysAreaModel.setAreaNo(sysUser.getAreaNo());
		AjaxJsonListData ajaxJsonListData = sysAreaService.getSysArea(sysAreaModel);
		jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
		return "jsonString";
	}
	
	public SysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(SysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}

	public InputStream getOperateResultString() {
		return operateResultString;
	}

	public void setOperateResultString(InputStream operateResultString) {
		this.operateResultString = operateResultString;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
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

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
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
	
}
