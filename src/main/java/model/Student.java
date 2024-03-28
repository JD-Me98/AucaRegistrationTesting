package model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
	@Id
	@Column(length = 100)
	private String regNo;
	private String firstName;
	private String DateOfBirth;
	@OneToMany(mappedBy = "studentId")
	private Set<StudentRegistration> studentReg;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public Set<StudentRegistration> getStudentReg() {
		return studentReg;
	}

	public void setStudentReg(Set<StudentRegistration> studentReg) {
		this.studentReg = studentReg;
	}
}
