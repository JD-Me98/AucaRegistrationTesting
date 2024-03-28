package dao;

import model.Student;
import org.junit.Test;

import java.util.Date;
import java.util.Scanner;

import static org.junit.Assert.*;

public class StudentDaoTest {
    Scanner scanner = new Scanner(System.in);
    @Test
    public void registerStudent() {

        Student student = new Student();

//        System.out.println("Enter Student Name: ");
//        String Name = scanner.next();
//        System.out.println("Enter student registration Number: ");
//        String regno = scanner.next();
//        System.out.println("Enter date of Birth with format 'YYYY-MM-DD'");
//        String dob = scanner.next();

        student.setFirstName("Ngabo");
        student.setRegNo("25102");
        student.setDateOfBirth("2004-12-2");

        StudentDao studentDao = new StudentDao();
        String res = studentDao.RegisterStudent(student);
        assertEquals("success", res);
    }
}