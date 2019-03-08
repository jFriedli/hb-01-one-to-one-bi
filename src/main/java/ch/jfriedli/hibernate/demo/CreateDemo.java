package ch.jfriedli.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ch.jfriedli.hibernate.demo.entity.Instructor;
import ch.jfriedli.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			Instructor instructor = new Instructor("Jonas", "Friedli", "jonas.friedli@example.com");
			
			InstructorDetail instructorDetail = new InstructorDetail("jfriedli.com", "Spring configuration");
			
			instructor.setInstructorDetail(instructorDetail);
			
			session.beginTransaction();

			//also saves the instructorDetail object because of CascadeType.ALL
			session.save(instructor);
			
			session.getTransaction().commit();

			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
