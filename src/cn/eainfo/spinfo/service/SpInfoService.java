package cn.eainfo.spinfo.service;

import java.util.List;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.spinfo.model.SpInfoModel;
import cn.eainfo.spinfo.po.SpInfo;

/**
 * SP信息管理
 * 
 */
public interface SpInfoService {

	
		/**
		 * 根据条件分页查询
		 * 
		 * @param spInfoModel查询条件的模型
		 * @return AjaxListData 列表对象
		 */
		public AjaxJsonListData getSpInfoPage(SpInfoModel spInfoModel);

		/**
		 * 新增
		 * 
		 * @param spInfo角色对象
		 * @return void
		 */
		public void insertSpInfo(SpInfo spInfo);

		/**
		 * 修改
		 * 
		 * @param sysUser角色对象
		 * @return void
		 */
		public void updateSpInfo(SpInfo spInfo);

		/**
		 * 根据id获取对象
		 * 
		 * @param spId
		 *            id
		 * @return SpInfo对象
		 */
		public SpInfo getSpInfoBean(long spId);

		/**
		 * 获取所有
		 * 
		 * @return List列表
		 */
		public List getAll();
		
		/**
		 * 
		 * 获取所有spinfo信息的 spid 和 spname
		 * 
		 */
		public List getSpIdAndSpName();
	}
