package cn.eainfo.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.system.model.CheckphoneModel;
import cn.eainfo.system.model.SysUserModel;
import cn.eainfo.system.po.Checkphone;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.service.CheckphoneService;

public class CheckphoneServiceImpl implements CheckphoneService {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	public void insertCheckphone(Checkphone checkphone) {
		String accountid = checkphone.getAccountid();
		this.updateCheckphonebyAccount(accountid, new Long(2));
		sqlMapClientTemplate.insert("Checkphone.insert", checkphone);
	}

	public void updateCheckphonebyAccount(String accountid, Long crrstate) {
		Checkphone checkphone = new Checkphone();
		checkphone.setAccountid(accountid);
		checkphone.setCrrstate(crrstate);
		sqlMapClientTemplate.update("Checkphone.update", checkphone);
	}

	public void updateCheckphone(Checkphone checkphone) {
		sqlMapClientTemplate.update("Checkphone.update", checkphone);

	}

	public Checkphone getCheckphoneBean(CheckphoneModel checkphoneModel) {
		Checkphone backcheckphone = null;
		List list = (List) sqlMapClientTemplate.queryForList(
				"Checkphone.getCheckphone", checkphoneModel);
		if (list != null && list.size() > 0) {
			backcheckphone = (Checkphone) list.get(0);
		}
		return backcheckphone;
	}

}
