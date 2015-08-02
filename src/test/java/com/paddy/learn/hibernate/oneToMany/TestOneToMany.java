package com.paddy.learn.hibernate.oneToMany;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

public class TestOneToMany {

	@Test
	public void testOneToManyOK() throws SQLException {
		Session session = null;
		try {
			SessionFactory sf = new AnnotationConfiguration().configure()
					.buildSessionFactory();
			session = sf.openSession();
			Transaction tx = session.beginTransaction();

			Classes cls = (Classes) session.load(Classes.class, 1);
			Set<Student> stuSet = cls.getStudents();
			stuSet.iterator();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}