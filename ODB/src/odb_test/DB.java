package odb_test;

import java.util.*;
import javax.persistence.*;

//CRUD Operations
public class DB {

	// Create

	public static void addStudent(Student s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}

	public static void addCourse(Course c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}

	public static void addEnrollment(Enrollment e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}

	// Retrieve (Lists w/ Queries)

	public static List<Student> getAllStudents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Student> query = em.createNamedQuery("Student.findall", Student.class);

		List<Student> students = query.getResultList();

		em.close();
		emf.close();
		return students;
	}

	public static List<Course> getAllCourses() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();

		em.close();
		emf.close();
		return courses;
	}

	public static List<Enrollment> getAllEnrollments() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		List<Enrollment> enrollments = em.createQuery("SELECT e FROM Enrollment e", Enrollment.class).getResultList();

		em.close();
		emf.close();
		return enrollments;
	}

	// Aggregate Query
	
	public static void getStudentCount() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Integer> query = em.createNamedQuery("Student.count", Integer.class);
		
		System.out.println("Number of Students: " + query.getSingleResult());
		
		em.close();
		emf.close();
	}
	
	// Retrieve (Single Objects w/ EntityManager)

	public static Student getStudentById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		Student s = em.find(Student.class, id);

		em.close();
		emf.close();
		return s;
	}

	public static Course getCourseById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		Course c = em.find(Course.class, id);

		em.close();
		emf.close();
		return c;
	}

	public static Enrollment geEnrollmentById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		Enrollment e = em.find(Enrollment.class, id);

		em.close();
		emf.close();
		return e;
	}

	// Update

	public static void updateStudent(int id, String newName, String newEmail) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		Student s = em.find(Student.class, id);

		try {
			if (s != null) {
				if (newName != null) {
					em.getTransaction().begin();
					s.setName(newName);
					em.getTransaction().commit();
				}
				if (newEmail != null) {
					em.getTransaction().begin();
					s.setEmail(newEmail);
					em.getTransaction().commit();
				}

			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

	}
	
	public static void updateCourse(int id, String newName, Integer newCred) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		
		Course c = em.find(Course.class, id);	
		
		try {
			if (c != null) {
				if(newName != null) {
					em.getTransaction().begin();
					c.setName(newName);
					em.getTransaction().commit();
				}
				if (newCred != null) {
					em.getTransaction().begin();
					c.setCreditHours(newCred);
					em.getTransaction().commit();
				}
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void updateEnrollment(int id, Student s, Course c, Double newGrade) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();
		
		Enrollment en = em.find(Enrollment.class, id);
		
		try {
			if (en != null) {
				if(s != null) {
					em.getTransaction().begin();
					en.setStudent(s);
					em.getTransaction().commit();
				}
				if (c != null) {
					em.getTransaction().begin();
					en.setCourse(c);
					em.getTransaction().commit();
				}
				if (newGrade != null) {
					em.getTransaction().begin();
					en.setGrade(newGrade);
					em.getTransaction().commit();
				}
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		
	}


	//Delete
	
	public static void deleteStudent(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		Student s = em.find(Student.class, id);
		
		if (s != null) {
			em.getTransaction().begin();
			em.remove(s);
			System.out.printf("SUCCESS: Student with id : \"%d\" deleted successfully.", id);
			em.getTransaction().commit();
		} else {
			System.out.printf("ERROR: There's no student with id : \"%d\" in the database.", id);
		}
		em.close();
		emf.close();
	}
	
	public static void deleteCourse(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		Course c = em.find(Course.class, id);
		
		if (c != null) {
			em.getTransaction().begin();
			em.remove(c);
			System.out.printf("SUCCESS: Course with id : \"%d\" deleted successfully.", id);
			em.getTransaction().commit();
		} else {
			System.out.printf("ERROR: There's no course with id : \"%d\" in the database.", id);
		}
		em.close();
		emf.close();
	}
	
	public static void deleteEnrollment(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/odbtest.odb");
		EntityManager em = emf.createEntityManager();

		Enrollment e = em.find(Enrollment.class, id);
		
		if (e != null) {
			em.getTransaction().begin();
			em.remove(e);
			System.out.printf("SUCCESS: Enrollment with id : \"%d\" deleted successfully.", id);
			em.getTransaction().commit();
		} else {
			System.out.printf("ERROR: There's no enrollment with id : \"%d\" in the database.", id);
		}
		em.close();
		emf.close();
	}
}
