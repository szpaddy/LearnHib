package com.paddy.learn.hibernate.oneToMany;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Ignore;
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

			Classes classes = new Classes();
			classes.setName("access");

			Student st1 = new Student();
			st1.setSname("jason");
			st1.setClasses(classes);
			session.save(st1);

			Student st2 = new Student();
			st2.setSname("hwj");
			st2.setClasses(classes);
			session.save(st2);
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}