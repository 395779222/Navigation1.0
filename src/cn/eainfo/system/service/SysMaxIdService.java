package cn.eainfo.system.service;


/**
 * 系统id管理
 */
public interface SysMaxIdService {
	/**
	 * 根据表名称和字段名称 得到系统id
	 * 
	 * @param fieldName表名称加字段名称
	 * @return SysMaxId对象
	 */
	public long getSysMaxId(String fieldName);
}
