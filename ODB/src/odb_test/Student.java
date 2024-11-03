package odb_test;

import java.util.*;
import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "Student.findall", query = "Select s From Student s"),
	@NamedQuery(name = "Student.findByID", query = "Select s From Student s where s.id = :id"),
	@NamedQuery(name = "Student.count", query = "Select count(s) From Student s"),
})
public class Student {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Enrollment> enrollments;

	public Student() {
	}

	public Student(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "\nid: " + id + "\nname: " + name + "\nemail: " + email;
	}

}
