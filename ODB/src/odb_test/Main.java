package odb_test;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		DB.getStudentCount();
		
		System.out.println("\nList of students:");
		System.out.println("----------------------------------");
		List<Student> students = DB.getAllStudents();

		if (students.isEmpty()) {
			System.out.println("No items found.");
		} else {
			for (Student student : students) {
				System.out.println(student);
			}
		}

		System.out.println("\n----------------------------------");
		
		
		System.out.println("\nList of courses:");
		System.out.println("----------------------------------");
		List<Course> courses = DB.getAllCourses(); 
		
		if (courses.isEmpty()) {
			System.out.println("No items found.");
		} else {
			for (Course course : courses) {
				System.out.println(course);
			}
		}

		System.out.println("\n----------------------------------");
		
		
		System.out.println("\nList of Enrollments:");
		System.out.println("----------------------------------");
		List<Enrollment> enrollments = DB.getAllEnrollments(); 
		
		if (enrollments.isEmpty()) {
			System.out.println("No items found.");
		} else {
			for (Enrollment enrollment : enrollments) {
				System.out.println(enrollment);
			}
		}
		
		System.out.println("\n----------------------------------");
		
		
	}

}