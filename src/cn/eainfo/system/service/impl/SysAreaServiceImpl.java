package cn.eainfo.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.PageBean;
import cn.eainfo.navigation.po.AreaNavCheckMsg;
import cn.eainfo.system.model.SysAreaModel;
import cn.eainfo.system.po.SysArea;
import cn.eainfo.system.service.SysAreaService;

/**
 * @author huxiangyu
 *
 */
/**
 * @author huxiangyu
 *
 */
public class SysAreaServiceImpl implements SysAreaService {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SysArea getSysAreaBean(String areaNo) {
		return (SysArea) sqlMapClientTemplate.queryForObject("SysArea.getBean",
				areaNo);
	}

	public List getSysAreaByPow(SysAreaModel sysAreaModel) {
		List sysAreaList = sqlMapClientTemplate.queryForList(
				"SysArea.getSysAreaBypow", sysAreaModel);
		return sysAreaList;
	}

	public List getSysAreaList(SysAreaModel sm) {
		List sysAreaList = sqlMapClientTemplate
				.queryForList("SysArea.getAll",sm);
		if (sysAreaList.size() < 1) {
			sysAreaList = new ArrayList();
		}
		return sysAreaList;
	}

	public AjaxJsonListData getSysArea(SysAreaModel sysAreaModel) {

		// 获取总记录数
		long count = (Long) sqlMapClientTemplate.queryForObject(
				"SysArea.getSysAreaCount", sysAreaModel);
		// 初始化分页对象，获取记录开始和结束行
		PageBean page = new PageBean(sysAreaModel.getPage(),
				sysAreaModel.getPageSize(), count);
		sysAreaModel.setStart(page.getStart());
		sysAreaModel.setEnd(page.getEnd());
		// 查询数据
		List pageList = sqlMapClientTemplate.queryForList(
				"SysArea.getSysAreaRecord", sysAreaModel);
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(count);
		ajaxJsonListData.setRows(pageList);
		return ajaxJsonListData;
	}

	public List getBySeparatorSysArea(String areaNo, String areaValue) {
		List temp = new ArrayList();
		List tempList = sqlMapClientTemplate.queryForList(
				"SysArea.getByAreaNo", areaNo);
		int count = tempList.size();
		for (int i = 0; i < count; i++) {
			SysArea sysArea = (SysArea) tempList.get(i);
			String areaNoTemp = sysArea.getAreaNo();
			if (areaValue != null && !"".equals(areaValue)) {
				if (areaValue.indexOf("'" + areaNoTemp + "'") > -1) {
					if (!"86".equals(areaNoTemp)) {
						String areaName = sysArea.getName();
						sysArea.setName(getSeparator(areaNoTemp) + areaName);
						temp.add(sysArea);
					}
				}
			} else {

				if (!"86".equals(areaNoTemp)) {
					String areaName = sysArea.getName();
					sysArea.setName(getSeparator(areaNoTemp) + areaName);
					temp.add(sysArea);
				}
			}

		}
		return temp;
	}

	public List getBySeparatorSysArea(String areaNo) {
		List tempList = sqlMapClientTemplate.queryForList(
				"SysArea.getByAreaNo", areaNo);
		int count = tempList.size();
		for (int i = 0; i < count; i++) {
			SysArea sysArea = (SysArea) tempList.get(i);
			String areaNoTemp = sysArea.getAreaNo();
			String name = sysArea.getName();
			sysArea.setName(getSeparator(areaNoTemp) + name);
		}
		return tempList;
	}

	/**
	 * 根据地区编码获取字符前缀
	 */
	private String getSeparator(String areaNo) {
		String backString = "";
		String separator = "---";
		int length = areaNo.length();
		for (int i = 0; i < (length / 2) - 1; i++) {
			backString = backString + separator;
		}
		return backString;
	}

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	public List getAll() {
		return sqlMapClientTemplate.queryForList("SysArea.getAll");
	}

	/**
	 * 根据地区编码获取前缀
	 */
	private String getSeparatorForRole(String areaNo) {
		String backString = "";
		String separator = "&nbsp;&nbsp;&nbsp;&nbsp;";
		int length = areaNo.length();
		for (int i = 0; i < (length / 2) - 1; i++) {
			backString = backString + separator;
		}
		return backString;
	}

	public String getLoginArea(long userId) {
		List areaList = sqlMapClientTemplate.queryForList(
				"SysArea.getLoginArea", userId);
		String returnValue = "";
		if (areaList != null && areaList.size() > 0) {
			int count = areaList.size();
			for (int i = 0; i < count; i++) {
				SysArea sysArea = (SysArea) areaList.get(i);
				String areaNo = sysArea.getAreaNo();
				if (i == count - 1) {
					returnValue = returnValue + "'" + areaNo + "'";
				} else {
					returnValue = returnValue + "'" + areaNo + "',";
				}
			}
		}
		return returnValue;
	}

	public List getAreaByUserId(long userId) {
		return sqlMapClientTemplate.queryForList("SysArea.getAreaByUserId",
				userId);
	}

	public List getBySysArea(String areaNo, String areaValue) {

		List temp = new ArrayList();
		List tempList = sqlMapClientTemplate.queryForList(
				"SysArea.getByAreaNo", areaNo);
		int count = tempList.size();
		for (int i = 0; i < count; i++) {
			SysArea sysArea = (SysArea) tempList.get(i);
			String areaNoTemp = sysArea.getAreaNo();
			if (areaValue.indexOf("'" + areaNoTemp + "'") > -1) {
				if (!"86".equals(areaNoTemp)) {
					temp.add(sysArea);
				}
			}
		}
		return temp;

	}

	/** {@inheritDoc} */
	 
	public List getAreaNoAndName(String areaNo) {
		return sqlMapClientTemplate.queryForList("SysArea.getAreaIdAndName",
				areaNo);
	}

	public void edit(SysArea area) {
		sqlMapClientTemplate.update("SysArea.update",area);
		
	}

	public void insert(SysArea area) {
		//插入地区表
		sqlMapClientTemplate.insert("SysArea.insert",area);
		AreaNavCheckMsg areaNavCheckMsgHd = getInsertAreaNavCheckMsgByArea(area,"hd");
		sqlMapClientTemplate.insert("AreaNavCheckMsg.insert",areaNavCheckMsgHd);
		AreaNavCheckMsg areaNavCheckMsgSd= getInsertAreaNavCheckMsgByArea(area,"sd");
		sqlMapClientTemplate.insert("AreaNavCheckMsg.insert",areaNavCheckMsgSd);
	}
	
	/** 
	 * <新增地区信息时>
	 * @param area
	 * @param type
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private AreaNavCheckMsg getInsertAreaNavCheckMsgByArea(SysArea area,String type){
		AreaNavCheckMsg areaNavCheckMsg = new AreaNavCheckMsg();
		areaNavCheckMsg.setAreaNo(area.getAreaNo());
		areaNavCheckMsg.setCheckState("待审核");
		areaNavCheckMsg.setIsNotToCheckNum(0);
		areaNavCheckMsg.setIsToCheckNum(0);
		areaNavCheckMsg.setShortCheckNum(0);
		areaNavCheckMsg.setShortNotCheckNum(0);
		areaNavCheckMsg.setType(type);
		//areaNavCheckMsg.setCityEnName(area.getEnName());
		return areaNavCheckMsg;
	}
}
