package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class AcademicUnit {
    @Id
	@Column(length = 100)
    private String code;
    private String name;
    private String type;

    @OneToMany(mappedBy = "academicUnit", fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Course> courses;

    @OneToMany(mappedBy = "academicUnit", fetch =FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<StudentRegistration> studentRegistration = new HashSet<StudentRegistration>();

    @ManyToOne
    private AcademicUnit parent;

    public AcademicUnit() {
        super();
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}


	public Set<StudentRegistration> getStudentRegistration() {
		return studentRegistration;
	}

	public void setStudentRegistration(Set<StudentRegistration> studentRegistration) {
		this.studentRegistration = studentRegistration;
	}

	public AcademicUnit getParent() {
		return parent;
	}

	public void setParent(AcademicUnit parent) {
		this.parent = parent;
	}

}
