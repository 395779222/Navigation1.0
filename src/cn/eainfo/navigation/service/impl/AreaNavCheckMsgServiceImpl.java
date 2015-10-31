
package cn.eainfo.navigation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.PageBean;
import cn.eainfo.navigation.model.AreaNavCheckMsgModel;
import cn.eainfo.navigation.po.AreaNavCheckMsg;
import cn.eainfo.navigation.po.Navigation;
import cn.eainfo.navigation.po.Release;
import cn.eainfo.navigation.po.Shortcuts;
import cn.eainfo.navigation.service.AreaNavCheckMsgService;
import cn.eainfo.system.po.SysArea;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AreaNavCheckMsgServiceImpl implements AreaNavCheckMsgService{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**地区审核信息查询 */
	public AjaxJsonListData getAreaNavCheckMsgPage (
			AreaNavCheckMsgModel areaNavCheckMsgModel)throws Exception {
		// 获取总记录数
		long count = (Long) sqlMapClientTemplate.queryForObject("AreaNavCheckMsg.getAreaNavCheckMsgCount", areaNavCheckMsgModel);
		// 初始化分页对象，获取记录开始和结束行
		PageBean page = new PageBean(areaNavCheckMsgModel.getPage(),areaNavCheckMsgModel.getPageSize(), count);
		areaNavCheckMsgModel.setStart(page.getStart());
		areaNavCheckMsgModel.setEnd(page.getEnd());
		// 查询数据
		@SuppressWarnings("rawtypes")
		List<AreaNavCheckMsg> pageList = sqlMapClientTemplate.queryForList("AreaNavCheckMsg.getAreaNavCheckMsgRecord", areaNavCheckMsgModel);
		if(pageList!=null&&pageList.size()>0){
			String cityName= "";
			for(AreaNavCheckMsg areaMsg :pageList){
				cityName = ((SysArea)sqlMapClientTemplate.queryForObject("SysArea.getBeanByAreaNo",areaMsg.getAreaNo())).getName();
				areaMsg.setCityEnName(cityName);
			}
		}
		// 初始化ajax分页，获取ajax对象
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(count);
		ajaxJsonListData.setRows(pageList);
		return ajaxJsonListData;
	}
	
	/** 导航审核 */
	public void updateAreaMsgAndNav(AreaNavCheckMsg areaCheckMsg) {
		Navigation nav = new Navigation();
		nav.setAreaNo(areaCheckMsg.getAreaNo());
		nav.setType(areaCheckMsg.getType());
		nav.setState(areaCheckMsg.getCheckState());
		Shortcuts shortcuts = new Shortcuts();
		shortcuts.setAreaNo(areaCheckMsg.getAreaNo());
		shortcuts.setType(areaCheckMsg.getType());
		//获取地区类型的导航数量
		long navNum =  (Long) sqlMapClientTemplate.queryForObject("Navigation.getAllNum",nav);
		//若是省和不同过或者待审核那么地区审核信息表里面IsToCheckNum里的值改该地区该类型导航数量所有值
		if(areaCheckMsg.getCheckState().equals("待审核")||areaCheckMsg.getCheckState().equals("审核不通过")){
			areaCheckMsg.setIsToCheckNum((int) navNum);
			areaCheckMsg.setIsNotToCheckNum(0);
		}
		//若是审核通过则变更对应的地区里面待审核的导航数量,而是审核不通过则
		else{
			areaCheckMsg.setIsNotToCheckNum((int) navNum);
			areaCheckMsg.setIsToCheckNum(0);
			long shortNotPassNum = (Long)sqlMapClientTemplate.queryForObject("Shortcuts.getCheckNum",shortcuts);
			if(shortNotPassNum!=0){
				areaCheckMsg.setCheckState("待审核");
			}
		}
		//更新地区导航审核信息表里
		sqlMapClientTemplate.update("AreaNavCheckMsg.update",areaCheckMsg); 
		//变更导航信息里面的数据
		sqlMapClientTemplate.update("Navigation.updateNavigationByCheck",nav);
		
	}
	/** 
	 * <快捷键审核>
	 * @param areaCheckMsg
	 * @see [类、类#方法、类#成员]
	 */
	public void updateAreaMsgAndShort(AreaNavCheckMsg areaCheckMsg){
		Navigation nav = new Navigation();
		nav.setAreaNo(areaCheckMsg.getAreaNo());
		nav.setType(areaCheckMsg.getType());
		Shortcuts shortcuts = new Shortcuts();
		shortcuts.setCheckState(areaCheckMsg.getCheckState());
		shortcuts.setAreaNo(areaCheckMsg.getAreaNo());
		shortcuts.setType(areaCheckMsg.getType());
		long shortNum =  (Long) sqlMapClientTemplate.queryForObject("Shortcuts.getAllNum",shortcuts);
		if(areaCheckMsg.getCheckState().equals("审核通过")){
			areaCheckMsg.setShortNotCheckNum((int) shortNum);
			areaCheckMsg.setShortCheckNum(0);
			//判断导航审核待审核的个数
			long navigationPassNum = (Long)sqlMapClientTemplate.queryForObject("Navigation.getToCheckNum",nav);
			//如果存在没有通过的导航则地区审核信息表审核状态还是待审核
			if(navigationPassNum!=0){
				areaCheckMsg.setCheckState("待审核");
			}
		}else{
			areaCheckMsg.setShortNotCheckNum(0);
			areaCheckMsg.setShortCheckNum((int)shortNum);
		}
		//更新地区导航审核信息表里
		sqlMapClientTemplate.update("AreaNavCheckMsg.updateShortCheckNum",areaCheckMsg); 
		//变更导航信息里面的数据
		sqlMapClientTemplate.update("Shortcuts.updateShortByCheck",shortcuts);
	}

}
