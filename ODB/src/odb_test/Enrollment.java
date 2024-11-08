package odb_test;

import javax.persistence.*;

@Entity
public class Enrollment {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn (name = "student", nullable = false)
	private Student student;
	@ManyToOne
	@JoinColumn (name = "course", nullable = false)
	private Course course;
	private Double grade;

	public Enrollment() {}

	public Enrollment(Student student, Course course, Double grade) {
		this.student = student;
		this.course = course;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "\nid: " + id + "\n\nstudent:\n\tid:" + student.getId() + "\n\tname: "+ student.getName() + "\n\temail: " + student.getEmail() + "\n\ncourse:\n\tid:" + course.getId() + "\n\tname: " + course.getName() + "\n\tcredithours: " + course.getCreditHours() + "\n\ngrade: " + grade;
	}
	

}
