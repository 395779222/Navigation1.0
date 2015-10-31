package cn.eainfo.navigation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.PageBean;
import cn.eainfo.navigation.model.AreaNavCheckMsgModel;
import cn.eainfo.navigation.model.EmergencyModel;
import cn.eainfo.navigation.po.AreaNavCheckMsg;
import cn.eainfo.navigation.po.Emergency;
import cn.eainfo.navigation.service.EmergencyService;
import cn.eainfo.system.po.SysArea;

/**
 * <p>文件名称: EmergencyServiceImpl.java </p>
 * <p>文件描述: &lt;描述&gt; </p>
 * <p>版权所有:  Copyright (c) qixin Coperation</p>
 * <p>公    司: 南京企信科技有限公司</p>
 * <p>内容摘要: 无</p>
 * <p>其他说明: 无</p>
 * <p>创建日期：2014-5-6</p>
 * <p>完成日期：2014-5-6</p>
 * <p>修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：Administrator
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 * @version 1.0
 * @author huxiangyu
 */
public class EmergencyServiceImpl implements EmergencyService{
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**地区审核信息查询 */
	public AjaxJsonListData getEmergencyPage (EmergencyModel emergencyModel) {
		// 获取总记录数
		long count = (Long) sqlMapClientTemplate.queryForObject("Emergency.getEmergencyCount", emergencyModel);
		// 初始化分页对象，获取记录开始和结束行
		PageBean page = new PageBean(emergencyModel.getPage(),emergencyModel.getPageSize(), count);
		emergencyModel.setStart(page.getStart());
		emergencyModel.setEnd(page.getEnd());
		// 查询数据
		@SuppressWarnings("rawtypes")
		List<Emergency> pageList = sqlMapClientTemplate.queryForList("Emergency.getEmergencyRecord", emergencyModel);
		if(pageList!=null&&pageList.size()>0){
			String cityName= "";
			for(Emergency emergency :pageList){
				cityName = ((SysArea)sqlMapClientTemplate.queryForObject("SysArea.getBeanByAreaNo",emergency.getAreaNo())).getName();
				emergency.setCityChName(cityName);
			}
		}
		// 初始化ajax分页，获取ajax对象
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(count);
		ajaxJsonListData.setRows(pageList);
		return ajaxJsonListData;
	}
	public void insert(Emergency emergency) {
		sqlMapClientTemplate.insert("Emergency.insert",emergency);
	}
	public void edit(Emergency emergency) {
		sqlMapClientTemplate.update("Emergency.update",emergency);
		
	}
	public Emergency getBeanById(long id) {
		return (Emergency) sqlMapClientTemplate.queryForObject("Emergency.getBean",id);
	}
	public Emergency getBeanByTypeAndAreaNo(EmergencyModel emergencyModel) {
		List<Emergency> list = sqlMapClientTemplate.queryForList("Emergency.getBeanByTypeAndAreaNo",emergencyModel);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	public void deleteEmergence(long id) {
		sqlMapClientTemplate.delete("Emergency.delete",id);
		
	}
}
