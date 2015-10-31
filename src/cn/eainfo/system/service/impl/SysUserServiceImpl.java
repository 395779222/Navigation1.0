package cn.eainfo.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.PageBean;
import cn.eainfo.common.SystemDate;
import cn.eainfo.navigation.po.CompanyInfo;
import cn.eainfo.system.model.SysUserModel;
import cn.eainfo.system.po.SysArea;
import cn.eainfo.system.po.SysRole;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.po.SysUserRoleRel;
import cn.eainfo.system.service.SysAreaService;
import cn.eainfo.system.service.SysMaxIdService;
import cn.eainfo.system.service.SysUserService;

public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	@Autowired
	private SysMaxIdService sysMaxIdService;
	@Autowired
	private SysAreaService sysAreaService;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	public SysMaxIdService getSysMaxIdService() {
		return sysMaxIdService;
	}

	public void setSysMaxIdService(SysMaxIdService sysMaxIdService) {
		this.sysMaxIdService = sysMaxIdService;
	}

	public AjaxJsonListData getSysUserPage(SysUserModel sysUserModel) {
		// 获取总记录数
		long count = (Long) sqlMapClientTemplate.queryForObject("SysUser.getSysUserCount", sysUserModel);
		// 初始化分页对象，获取记录开始和结束行
		PageBean page = new PageBean(sysUserModel.getPage(),sysUserModel.getPageSize(), count);
		sysUserModel.setStart(page.getStart());
		sysUserModel.setEnd(page.getEnd());
		// 查询数据
		List<SysUser> pageList = sqlMapClientTemplate.queryForList("SysUser.getSysUserRecord", sysUserModel);
		/*根据用户的id获取用户的角色名称
		 * */
		if(pageList!=null&&pageList.size()>0){
			for(int i = 0;i<pageList.size();i++){
				String tempRoleName = getUserRoleName(pageList.get(i).getUserId());
				pageList.get(i).setUserRoleName(tempRoleName);
			}
			String cityEnName = "";
			for(SysUser user :pageList){
				if(user.getAreaNo().equals("00")){
					user.setCityEnName("江苏省");	
				}
				else{
					cityEnName = ((SysArea)sqlMapClientTemplate.queryForObject("SysArea.getBeanByAreaNo",user.getAreaNo())).getName();
					user.setCityEnName(cityEnName);
				}
			}
			
		}
		
		// 初始化ajax分页，获取ajax对象
        if(pageList.size()>0){
        	for(int i=0;i<pageList.size();i++){
        		SysUser sysUser = (SysUser)pageList.get(i);
        		if(sysUser.getEffectTime()!=null&&!"".equals(sysUser.getEffectTime())){
        			Date effDate = sysUser.getEffectTime();
        			sysUser.setShenDate(SystemDate.formatDate(effDate));
        		}
        		if(sysUser.getExpireTime()!=null&&!"".equals(sysUser.getExpireTime())){
        			Date expDate = sysUser.getExpireTime();
        			sysUser.setShiDate(SystemDate.formatDate(expDate));
        		}
        		if(sysUser.getLoginBeginTime()!=null&&!"".equals(sysUser.getLoginBeginTime())){
        			Date logDate = sysUser.getLoginBeginTime();
        			sysUser.setLogDate(SystemDate.formatDate(logDate));
        		}
        		if(sysUser.getLoginEndTime()!=null&&!"".equals(sysUser.getLoginEndTime())){
        			Date endDate = sysUser.getLoginEndTime();
        			sysUser.setEndDate(SystemDate.formatDate(endDate));
        		}
        	}
        }
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(count);
		ajaxJsonListData.setRows(pageList);
		return ajaxJsonListData;
	}
	
	private String getUserRoleName(long userId) {
		SysRole role = (SysRole) sqlMapClientTemplate.queryForObject("SysUser.getUserRoleName",userId);
		return role.getRoleName();
	}

	public SysUser getSysUserBean(long businessTypeId) {
		SysUser sysUser = (SysUser) sqlMapClientTemplate.queryForObject(
				"SysUser.getBean", businessTypeId);
		if(sysUser.getEffectTime()!=null&&!"".equals(sysUser.getEffectTime())){
			Date effDate = sysUser.getEffectTime();
			sysUser.setShenDate(SystemDate.formatDate(effDate));
		}
		if(sysUser.getExpireTime()!=null&&!"".equals(sysUser.getExpireTime())){
			Date expDate = sysUser.getExpireTime();
			sysUser.setShiDate(SystemDate.formatDate(expDate));
		}
		if(sysUser.getLoginBeginTime()!=null&&!"".equals(sysUser.getLoginBeginTime())){
			Date logDate = sysUser.getLoginBeginTime();
			sysUser.setLogDate(SystemDate.formatDate(logDate));
		}
		if(sysUser.getLoginEndTime()!=null&&!"".equals(sysUser.getLoginEndTime())){
			Date endDate = sysUser.getLoginEndTime();
			sysUser.setEndDate(SystemDate.formatDate(endDate));
		}
		return sysUser;
	}

	public void insertSysUser(SysUser sysUser) {
		sysUser.setUserId(sysMaxIdService.getSysMaxId("SysUser_userId"));
		sqlMapClientTemplate.insert("SysUser.insert", sysUser);
	}

	public void insertSysUser(SysUser sysUser, long[] roleIds) {
		sysUser.setUserId(sysMaxIdService.getSysMaxId("SysUser_userId"));
		sqlMapClientTemplate.insert("SysUser.insert", sysUser);
		if (roleIds != null && roleIds.length > 0) {
			for (int i = 0; i < roleIds.length; i++) {
				SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
				sysUserRoleRel.setRoleId(roleIds[i]);
				sysUserRoleRel.setUserId(sysUser.getUserId());
				sqlMapClientTemplate.insert("SysUserRoleRel.insert",
						sysUserRoleRel);
			}
		}

	}

	public void updateSysUser(SysUser sysUser) {
		sqlMapClientTemplate.update("SysUser.update", sysUser);
	}

	public void updateSysUser(SysUser sysUser, long[] roleIds) {
		sqlMapClientTemplate.update("SysUser.update", sysUser);
		sqlMapClientTemplate.delete("SysUserRoleRel.deleteByUserId",
				sysUser.getUserId());
		if (roleIds != null && roleIds.length > 0) {
			for (int i = 0; i < roleIds.length; i++) {
				SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
				sysUserRoleRel.setRoleId(roleIds[i]);
				sysUserRoleRel.setUserId(sysUser.getUserId());
				sqlMapClientTemplate.insert("SysUserRoleRel.insert",
						sysUserRoleRel);
			}
		}

	}

	public String getUserForRole(long roleId) {
		String selectUser = "";
		List sysUserRoleRelList = sqlMapClientTemplate.queryForList(
				"SysUserRoleRel.selectByRoleId", roleId);
		int selectUserCount = sysUserRoleRelList.size();
		for (int i = 0; i < selectUserCount; i++) {
			SysUserRoleRel sysUserRoleRel = (SysUserRoleRel) sysUserRoleRelList
					.get(i);
			if (i == 0) {
				selectUser = selectUser + ",";
			}
			selectUser = selectUser + sysUserRoleRel.getUserId() + ",";
		}
		List sysUserList = sqlMapClientTemplate.queryForList("SysUser.getAll");
		String selectUserReturn = "";
		int sysUserListCount = sysUserList.size();
		for (int i = 0; i < sysUserListCount; i++) {
			SysUser sysUser = (SysUser) sysUserList.get(i);
			long userId = sysUser.getUserId();
			if (selectUser.indexOf("," + userId + ",") > -1) {
				selectUserReturn = selectUserReturn
						+ "<input type='checkbox' name='userIds' value='"
						+ sysUser.getUserId() + "' checked='checked'>"
						+ sysUser.getUserName() + "&nbsp;&nbsp;&nbsp;";// 设置菜单选择框
			} else {
				selectUserReturn = selectUserReturn
						+ "<input type='checkbox' name='userIds' value='"
						+ sysUser.getUserId() + "'>" + sysUser.getUserName()
						+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";// 设置菜单选择框
			}
			if((i+1)%4==0){
				selectUserReturn = selectUserReturn+"<br />";
			}
		}
		selectUserReturn=selectUserReturn+"<br /><br /><br /><br />";
		return selectUserReturn;
	}

	public SysUser userLongin(String loginName, String passWord) {
		SysUserModel sysUserModel = new SysUserModel();
		sysUserModel.setLoginName(loginName);
		sysUserModel.setPassword(passWord);
		return (SysUser) sqlMapClientTemplate.queryForObject(
				"SysUser.userLongin", sysUserModel);
	}

	public List getRoleByUserId(long userId) {

		return sqlMapClientTemplate.queryForList(
				"SysUserRoleRel.selectByUserId", userId);
	}

	public List checkUser(String loginName) {
		return sqlMapClientTemplate
				.queryForList("SysUser.checkUser", loginName);
	}

}
