package cn.eainfo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.system.po.SysMaxId;
import cn.eainfo.system.service.SysMaxIdService;

public class SysMaxIdServiceImpl implements SysMaxIdService {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	public long getSysMaxId(String fieldName) {

		long maxId = 0;
		// 查询当前对象是否存在
		SysMaxId sysMaxId = (SysMaxId) sqlMapClientTemplate.queryForObject(
				"SysMaxId.getBean", fieldName);

		if (null != sysMaxId) {
			maxId = sysMaxId.getMaxValue() + 1;
			sysMaxId.setMaxValue(maxId);
			sqlMapClientTemplate.update("SysMaxId.update", sysMaxId);
		} else {
			sysMaxId = new SysMaxId();
			sysMaxId.setFieldName(fieldName);
			sysMaxId.setMaxValue(1000);
			sqlMapClientTemplate.insert("SysMaxId.insert", sysMaxId);
		}
		maxId = sysMaxId.getMaxValue();
		return maxId;
	}

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

}
