package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(length = 100)
	private long id;
	@OneToOne
	@JoinColumn(name="course_code")
	private CourseDefinition courseDefinition;
	@ManyToOne
	private AcademicUnit academicUnit;
	@OneToMany(mappedBy = "courses", fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Teacher> teachers= new HashSet<Teacher>();
	@ManyToOne
	private Semester semester;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="student_course")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<StudentRegistration> studentReg= new HashSet<StudentRegistration>();
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CourseDefinition getCourseDefinition() {
		return courseDefinition;
	}
	public void setCourseDefinition(CourseDefinition courseDefinition) {
		this.courseDefinition = courseDefinition;
	}
	public AcademicUnit getAcademicUnit() {
		return academicUnit;
	}
	public void setAcademicUnit(AcademicUnit academicUnit) {
		this.academicUnit = academicUnit;
	}
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	public Semester getSemester() {
		return semester;
	}
	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	public Set<StudentRegistration> getStudentReg() {
		return studentReg;
	}
	public void setStudentReg(Set<StudentRegistration> studentReg) {
		this.studentReg = studentReg;
	}
	
	
}
