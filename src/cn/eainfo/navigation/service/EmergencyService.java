package cn.eainfo.navigation.service;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.navigation.model.EmergencyModel;
import cn.eainfo.navigation.po.Emergency;

public interface EmergencyService {

	AjaxJsonListData getEmergencyPage(EmergencyModel emergencyModel);

	void insert(Emergency emergency);

	void edit(Emergency emergency);

	Emergency getBeanById(long id);

	Emergency getBeanByTypeAndAreaNo(EmergencyModel emergencyModel);

	void deleteEmergence(long id);

}
