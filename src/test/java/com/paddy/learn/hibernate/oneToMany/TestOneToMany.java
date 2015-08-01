package com.paddy.learn.hibernate.oneToMany;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

public class TestOneToMany {

	//@Test
	public void testOneToManyOK() throws SQLException {
		try {
			SessionFactory sf = new AnnotationConfiguration().configure()
					.buildSessionFactory();
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			/*
			 * 因为mappedBy是定义在classes中,即classes类不负责维护级联关系.即维护者是student.所以,
			 * 1.要将clsses的数据,赋给student,即用student的setClasses()方法去捆定class数据;
			 * 2.在进行数据插入/更新session.save()/session.update()时,最后操作的是student.
			 */
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
		}
	}

	@Test
	public void testOneToManyError() throws SQLException {
		try {
			SessionFactory sf = new AnnotationConfiguration().configure()
					.buildSessionFactory();
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();

			/*
			 * 因为一端维护关系另一端不维护关系的原因，我们必须注意避免在应用中用不维护关系的类(class)建立关系，
			 * 因为这样建立的关系是不会在数据库中存储的。 如上的代码倒过来,则插入时,student的外键值为空.如下:
			 */
			Student st1 = new Student();
			st1.setSname("jason");
			session.save(st1);

			Student st2 = new Student();
			st2.setSname("hwj");
			session.save(st2);

			Set<Student> students = new HashSet<Student>();
			students.add(st1);
			students.add(st2);

			Classes classes = new Classes();
			classes.setName("access");
			classes.setStudents(students);
			session.save(classes);

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}