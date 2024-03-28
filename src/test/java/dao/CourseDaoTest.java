package dao;

import model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CourseDaoTest {

    //@Test
    public void createCourse() {
        SemesterDao semesterDao = new SemesterDao();
        CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();
        AcademicUnitDAO academicUnitDAO = new AcademicUnitDAO();

        AcademicUnit department = academicUnitDAO.getByCode("IM-123");
        CourseDefinition courseDefinition = courseDefinitionDAO.getCourseDefinitionById("INF5612");
        Semester semester = semesterDao.getSemesterById("SEM03-22/23");

        Course course = new Course();
        course.setSemester(semester);
        course.setCourseDefinition(courseDefinition);
        course.setAcademicUnit(department);

        CourseDao courseDao = new CourseDao();
        String res = courseDao.createCourse(course);
        assertEquals("success", res);
    }

    //@Test
    public void getCourseByDepartmentAndSemester() {
        CourseDao courseDao = new CourseDao();
        SemesterDao semesterDao = new SemesterDao();
        AcademicUnitDAO academicUnitDao = new AcademicUnitDAO();


        Semester semester = semesterDao.getSemesterById("SEM02-22/23");
        AcademicUnit department = academicUnitDao.getByCode("SE-123");


        System.out.println("Semester found " + semester.getName());
        System.out.println("Department found "+ department.getName());

        List<Course> courses = courseDao.getCourseByDepartmentAndSemester(department, semester);
        if (courses != null && !courses.isEmpty()) {
            System.out.println("courses for Semester ID "+semester.getId()+" and Department "+department.getName());
            System.out.println("-------------------------------------------------------");
            for (Course course : courses) {
                System.out.println(course.getCourseDefinition().getCode() +
                        " | " + course.getCourseDefinition().getName());
            }
            assertFalse(courses.isEmpty());
        } else {
            System.out.println("No course found");
            assertTrue(courses.isEmpty());
        }

    }

    @Test
    public void getCoursePerStudent() {
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.getStudentByRegNo("24377");

        StudentRegistrationDao regDao = new StudentRegistrationDao();
        SemesterDao semesterDao = new SemesterDao();

        StudentRegistration studentRegistration = regDao.getStudentRegistrationByStudent(student);

        CourseDao courseDao = new CourseDao();

        Set<Course> courses = courseDao.getCoursePerStudent(studentRegistration);
        if (courses != null && !courses.isEmpty()) {
            System.out.println("Courses for  "+student.getFirstName());
            System.out.println("--------------------------------");
            for (Course course  : courses) {
                System.out.println(course.getCourseDefinition().getCode() +
                        " | " + course.getCourseDefinition().getName());
            }
            assertFalse(courses.isEmpty());
        } else {
            System.out.println("No courses found ");
            assertTrue(courses.isEmpty());
        }

    }
}