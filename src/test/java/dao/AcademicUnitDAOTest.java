package dao;

import model.AcademicUnit;
import model.EAcademicUnit;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class AcademicUnitDAOTest {
    Scanner scanner = new Scanner(System.in);
    //@Test
    public void saveFaculty() {
        AcademicUnit academicUnit = new AcademicUnit();
        EAcademicUnit program=EAcademicUnit.FACULTY;
        academicUnit.setName("Information Technology");
        academicUnit.setType(program.name());
        academicUnit.setCode("IT-123");


        AcademicUnitDAO academicUnitDao=new AcademicUnitDAO();
        AcademicUnit existingAcademicUnit = academicUnitDao.getByCode("UG-001");


        System.out.println("Existing " + existingAcademicUnit.getName());

        academicUnit.setParent(existingAcademicUnit);

        String res = academicUnitDao.saveAcademicUnit(academicUnit);
        assertEquals("success",res);
    }
    //@Test
    public void saveProgramme(){
        AcademicUnit academicUnit = new AcademicUnit();
        academicUnit.setName("Undergraduate");
        academicUnit.setCode("UG-001");
        academicUnit.setType(EAcademicUnit.PROGRAMME.name());

        AcademicUnitDAO academicUnitDAO = new AcademicUnitDAO();
        String res = academicUnitDAO.saveAcademicUnit(academicUnit);
        assertEquals("success",res);

    }
    //@Test
    public void saveDepartment(){
        AcademicUnit academicUnit = new AcademicUnit();
        EAcademicUnit program=EAcademicUnit.DEPARTMENT;
        academicUnit.setName("Network and Communication System");
        academicUnit.setType(program.name());
        academicUnit.setCode("NC-123");

        AcademicUnitDAO academicUnitDao=new AcademicUnitDAO();
        AcademicUnit existingAcademicUnit = academicUnitDao.getByCode("IT-123");

        System.out.println("Existing " + existingAcademicUnit.getName());

        academicUnit.setParent(existingAcademicUnit);

        String res = academicUnitDao.saveAcademicUnit(academicUnit);
        assertEquals("success",res);
    }
}