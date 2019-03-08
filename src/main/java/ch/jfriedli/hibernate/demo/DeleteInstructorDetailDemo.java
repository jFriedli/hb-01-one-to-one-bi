package ch.jfriedli.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ch.jfriedli.hibernate.demo.entity.Instructor;
import ch.jfriedli.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			int id = 3;
			
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			
			//remove the associated object reference
			//break bi directional link
			instructorDetail.getInstructor().setInstructorDetail(null);
			
			
			System.out.println("Delete " + instructorDetail);
			session.delete(instructorDetail);
			
			session.getTransaction().commit();

			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
