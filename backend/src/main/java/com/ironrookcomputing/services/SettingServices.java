package com.ironrookcomputing.services;

import org.springframework.stereotype.Service;

import com.ironrookcomputing.dao.SettingDAO;
import com.ironrookcomputing.hibernate.beans.Setting;
import com.ironrookcomputing.hibernate.dao.SettingHibernateDAO;

@Service
public class SettingServices {
	public Setting addSetting(Setting setting) {
		return userDao.addSetting(setting);
	}
	
	public Setting getSetting(Setting setting) {
		return userDao.getSetting(setting);
	}
	
	public Setting getSetting(String name) {
		return userDao.getSetting(name);
	}
	
	public Setting updateSetting(Setting setting) {
		return userDao.updateSetting(setting);
	}

	public void deleteSetting(Setting setting) {
		userDao.deleteSetting(setting);
	}
	
	private SettingDAO userDao = new SettingHibernateDAO();
}
