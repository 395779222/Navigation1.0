package cn.eainfo.spinfo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.PageBean;
import cn.eainfo.common.SystemDate;
import cn.eainfo.spinfo.model.SpInfoModel;
import cn.eainfo.spinfo.po.SpInfo;
import cn.eainfo.spinfo.service.SpInfoService;

public class SpInfoServiceImpl implements SpInfoService {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	public AjaxJsonListData getSpInfoPage(SpInfoModel spInfoModel) {
		// 获取总记录数
		long count = (Long) sqlMapClientTemplate.queryForObject("SpInfo.getSpInfoCount", spInfoModel);
		// 初始化分页对象，获取记录开始和结束行
		PageBean page = new PageBean(spInfoModel.getPage(),
				spInfoModel.getPageSize(), count);
		spInfoModel.setStart(page.getStart());
		spInfoModel.setEnd(page.getEnd());
		// 查询数据
		List pageList = sqlMapClientTemplate.queryForList(
				"SpInfo.getSpInfoRecord", spInfoModel);
		for(int i=0;i<pageList.size();i++){
			SpInfo spInfo = (SpInfo)pageList.get(i);
			if(spInfo.getSpcreateTime()!=null&&!"".equals(spInfo.getSpcreateTime())){
				Date createDate = spInfo.getSpcreateTime();
				spInfo.setCreateDate(SystemDate.formatDate(createDate));
			}
			if(spInfo.getContractEffectiveDate()!=null&&!"".equals(spInfo.getContractEffectiveDate())){
				Date beginDate = spInfo.getContractEffectiveDate();
				spInfo.setBeginDate(SystemDate.formatDate(beginDate));
			}
			if(spInfo.getContractExpireDate()!=null&&!"".equals(spInfo.getContractExpireDate())){
				Date endDate = spInfo.getContractExpireDate();
				spInfo.setEndDate(SystemDate.formatDate(endDate));
			}
			
		}
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(count);
		ajaxJsonListData.setRows(pageList);
		return ajaxJsonListData;
	}

	public void insertSpInfo(SpInfo spInfo) {
		sqlMapClientTemplate.insert("SpInfo.insert", spInfo);
	}

	public void updateSpInfo(SpInfo spInfo) {
		sqlMapClientTemplate.update("SpInfo.update", spInfo);
	}

	public SpInfo getSpInfoBean(long spId) {
		SpInfo spInfo = (SpInfo)sqlMapClientTemplate.queryForObject("SpInfo.getBean",spId);
		if(spInfo.getSpcreateTime()!=null&&!"".equals(spInfo.getSpcreateTime())){
			Date createDate = spInfo.getSpcreateTime();
			spInfo.setCreateDate(SystemDate.formatDate(createDate));
		}
		if(spInfo.getContractEffectiveDate()!=null&&!"".equals(spInfo.getContractEffectiveDate())){
			Date beginDate = spInfo.getContractEffectiveDate();
			spInfo.setBeginDate(SystemDate.formatDate(beginDate));
		}
		if(spInfo.getContractExpireDate()!=null&&!"".equals(spInfo.getContractExpireDate())){
			Date endDate = spInfo.getContractExpireDate();
			spInfo.setEndDate(SystemDate.formatDate(endDate));
		}
		return spInfo;
	}

	public List getAll() {
		return sqlMapClientTemplate.queryForList("SpInfo.getAll");
	}

	public List getSpIdAndSpName() {
		return sqlMapClientTemplate.queryForList("SpInfo.getSpIdAndSpName");
	}

}
