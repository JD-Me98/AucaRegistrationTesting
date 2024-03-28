package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
public class StudentRegistration {
	@Id
	@Column(length = 100)
	private String regId;
	@ManyToOne
	@JoinColumn(name = "studentId")
	private Student studentId;
	@ManyToMany(mappedBy="studentReg", fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Course> courses=new HashSet<Course>();
	@ManyToOne
	private Semester semester;
	@ManyToOne
	private AcademicUnit academicUnit;
	public StudentRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Student getStudentId() {
		return studentId;
	}
	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}

	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public Semester getSemester() {
		return semester;
	}
	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	public AcademicUnit getAcademicUnit() {
		return academicUnit;
	}
	public void setAcademicUnit(AcademicUnit academicUnit) {
		this.academicUnit = academicUnit;
	}
	
	
	
}
