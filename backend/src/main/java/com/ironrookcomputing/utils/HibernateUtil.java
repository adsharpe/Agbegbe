package com.ironrookcomputing.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private HibernateUtil() {
		super();
	}
	
	public static synchronized HibernateUtil getInstance() {
		if(thiz == null) {
			thiz = new HibernateUtil();
		}
		return thiz;
	}
	
	public synchronized SessionFactory getSessionFactory() {
		if(sessionFactory==null) {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
			Metadata meta = new MetadataSources(standardRegistry)
					.getMetadataBuilder()
					.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
					.build();
			sessionFactory = meta.getSessionFactoryBuilder()
					.build();
		}
		return sessionFactory;
	}
	
	public Session getSession() {
		return this.getSessionFactory().openSession();
	}
	
	private static HibernateUtil thiz = null;
	private SessionFactory sessionFactory = null;
}