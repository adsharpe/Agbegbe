package com.ironrookcomputing.dao;

import com.ironrookcomputing.hibernate.beans.Setting;

public interface SettingDAO {
	public Setting addSetting(Setting setting);
	public Setting getSetting(Setting setting);
	public Setting getSetting(String value);
	public Setting updateSetting(Setting setting);
	public void deleteSetting(Setting setting);
}
