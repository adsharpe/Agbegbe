package com.ironrookcomputing.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ironrookcomputing.dao.SettingDAO;
import com.ironrookcomputing.hibernate.beans.Setting;
import com.ironrookcomputing.utils.HibernateUtil;
import com.ironrookcomputing.utils.LogUtil;

public class SettingHibernateDAO implements SettingDAO {

	@Override
	public Setting addSetting(Setting setting) {
		Session session = hibernateInstance.getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(setting);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			LogUtil.logException(ex, SettingHibernateDAO.class);
		} finally {
			session.close();
		}
		return setting;
	}

	@Override
	public Setting getSetting(Setting setting) {
		Session session = hibernateInstance.getSession();
		Setting newSetting = null;
		
		try {
			if(setting.getId() != 0) {
				newSetting = session.get(Setting.class, setting.getId());
			} else {
				String queryString = "from Setting s WHERE s.name=:name";
				Query<Setting> query = session.createQuery(queryString, Setting.class);
				query.setParameter("name", setting.getName());
				newSetting = query.uniqueResult();
				
				System.out.println("newSetting=" + newSetting);
			}
		} catch(HibernateException ex) {
			LogUtil.logException(ex, SettingHibernateDAO.class);
		} finally {
			session.close();
		}

		return newSetting;
	}

	@Override
	public Setting getSetting(String name) {
		Session session = hibernateInstance.getSession();
		Setting newSetting = null;
		
		try {
			String queryString = "from Setting s WHERE s.name=:name";
			Query<Setting> query = session.createQuery(queryString, Setting.class);
			query.setParameter("name", name);
			newSetting = query.uniqueResult();
			
			System.out.println("newSetting=" + newSetting);
		} catch(HibernateException ex) {
			LogUtil.logException(ex, SettingHibernateDAO.class);
		} finally {
			session.close();
		}

		return newSetting;
	}

	@Override
	public Setting updateSetting(Setting setting) {
		Session session = hibernateInstance.getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.update(setting);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			LogUtil.logException(ex, UserHibernateDAO.class);
		} finally {
			session.close();
		}
		return setting;
	}

	@Override
	public void deleteSetting(Setting setting) {
		Session session = hibernateInstance.getSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.delete(setting);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction != null) {
				transaction.rollback();
			}
			LogUtil.logException(ex, SettingHibernateDAO.class);
		} finally {
			session.close();
		}
	}
	
	private HibernateUtil hibernateInstance = HibernateUtil.getInstance();
}
