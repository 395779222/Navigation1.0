package cn.eainfo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import cn.eainfo.system.po.LoginLog;
import cn.eainfo.system.service.LoginLogService;


/**
 * 用户管理
 */
public class LoginLogServiceImpl implements LoginLogService{
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}
	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	/**
	 * 新增
	 */
	public void insertLoginLog(LoginLog loginLog){
		sqlMapClientTemplate.insert("LoginLog.insert", loginLog);
	}

}
