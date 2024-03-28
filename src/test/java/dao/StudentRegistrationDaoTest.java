package dao;

import model.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StudentRegistrationDaoTest {

    //@Test
    public void saveStudentRegistration() {
        StudentRegistration studentRegistration = new StudentRegistration();

        StudentRegistrationDao registrationDao = new StudentRegistrationDao();
        CourseDao courseDao = new CourseDao();
        SemesterDao semesterDao = new SemesterDao();
        AcademicUnitDAO academicUnitDao = new AcademicUnitDAO();
        CourseDefinitionDAO courseDefinitionDao = new CourseDefinitionDAO();
        StudentDao studentDao = new StudentDao();


        CourseDefinition courseDefinition= courseDefinitionDao.getCourseDefinitionById("INF5612");
        AcademicUnit academicUnit = academicUnitDao.getByCode("IM-123");
        Semester semester = semesterDao.getSemesterById("SEM03-22/23");
        Course course = courseDao.getCourseByCode(courseDefinition);
        Student student = studentDao.getStudentByRegNo("25102");

        System.out.println("the course is " + course.getCourseDefinition().getName());

        studentRegistration.setAcademicUnit(academicUnit);
        studentRegistration.setStudentId(student);
        studentRegistration.setRegId("REG-25102");
        studentRegistration.getCourses().add(course);
        studentRegistration.setSemester(semester);


        course.getStudentReg().add(studentRegistration);


        String res=registrationDao.SaveStudentRegistration(studentRegistration);

        String test = courseDao.createCourse(course);
        System.out.println(test);

        assertEquals("success", res);
    }

    //@Test
    public void getStudentsBySemester() {
        StudentRegistrationDao regDao = new StudentRegistrationDao();
        SemesterDao semesterDao = new SemesterDao();

        Semester semester = semesterDao.getSemesterById("SEM03-22/23");

        System.out.println("Semester found " + semester.getName());

        List<StudentRegistration> students1 = regDao.getStudentsBySemester(semester);
        if (students1 != null && !students1.isEmpty()) {
            System.out.println("Students for Semester ID "+semester.getId());
            System.out.println("--------------------------------");
            for (StudentRegistration student : students1) {
                System.out.println(student.getStudentId().getRegNo() +
                        " | " + student.getStudentId().getFirstName() +
                        " | " + semester.getName());
            }
            assertFalse(students1.isEmpty());
        } else {
            System.out.println("No students found for Semester ID "+semester.getId());
            assertTrue(students1.isEmpty());
        }

    }

    //@Test
    public void getStudentsByDepartmentAndSemester() {
        StudentRegistrationDao regDao = new StudentRegistrationDao();
        SemesterDao semesterDao = new SemesterDao();
        AcademicUnitDAO academicUnitDao = new AcademicUnitDAO();

        Semester semester = semesterDao.getSemesterById("SEM02-22/23");
        AcademicUnit department = academicUnitDao.getByCode("SE-123");

        System.out.println("Semester found " + semester.getName());
        System.out.println("Department found "+ department.getName());

        List<StudentRegistration> students1 = regDao.getStudentsByDepartmentAndSemester(semester, department);
        if (students1 != null && !students1.isEmpty()) {
            System.out.println("Students for Semester ID "+semester.getId() +"and Department "+department.getName());
            System.out.println("-------------------------------------- ");
            for (StudentRegistration student : students1) {
                System.out.println(student.getStudentId().getRegNo() +
                        " | " + student.getStudentId().getFirstName()+
                        " | " + semester.getName() +
                        " | " + department.getName());
            }
            assertFalse(students1.isEmpty());
        } else {
            System.out.println("No Students Found for Semester ID "+semester.getId() +"and Department "+department.getName());
            assertTrue(students1.isEmpty());
        }

    }

    //@Test
    public void getStudentsByCourseAndSemester() {
        StudentRegistrationDao regDao = new StudentRegistrationDao();
        SemesterDao semesterDao = new SemesterDao();
        CourseDao courseDao = new CourseDao();
        CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();

        Semester semester = semesterDao.getSemesterById("SEM02-22/23");
        CourseDefinition courseDefinition = courseDefinitionDAO.getCourseDefinitionById("MATH816");
        Course course= courseDao.getCourseByCode(courseDefinition);

        List<StudentRegistration> students = regDao.getStudentsByCourseAndSemester(semester,course);

        if (students != null && !students.isEmpty()) {
            System.out.println("Students for Semester ID "+semester.getId() +" and Course "+course.getCourseDefinition().getName());
            System.out.println("-------------------------------------- ");
            for (StudentRegistration student : students) {
                System.out.println(student.getStudentId().getRegNo() +
                        " | " + student.getStudentId().getFirstName()+
                        " | " + semester.getName() +
                        " | " + course.getCourseDefinition().getName());
            }
            assertFalse(students.isEmpty());
        } else {
            System.out.println("No Students Found for Semester ID "+semester.getId() +"and Department "+course.getCourseDefinition().getName());
            assertTrue(students.isEmpty());
        }

    }

    //@Test
    public void addCourseToRegisteredStudent() {
        CourseDao courseDAO = new CourseDao();
        StudentRegistrationDao studentRegistrationDao = new StudentRegistrationDao();
        CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();
        StudentDao studentDao = new StudentDao();

        Student student = studentDao.getStudentByRegNo("24377");
        CourseDefinition courseDefinition =courseDefinitionDAO.getCourseDefinitionById("SENG8132");
        Course course = courseDAO.getCourseByCode(courseDefinition);

        StudentRegistration studentRegistration = studentRegistrationDao.getStudentRegistrationByStudent(student);
        studentRegistration.getCourses().add(course);

        String res = studentRegistrationDao.SaveStudentRegistration(studentRegistration);

        assertEquals("success", res);

    }
}