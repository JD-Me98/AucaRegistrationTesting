package dao;

import model.Semester;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class SemesterDaoTest {

    @Test
    public void registerSemester() {
        Semester semester = new Semester();
        semester.setName("SEMESTER THREE");

        Date startDate = Date.valueOf("2023-07-02");
        Date endDate = Date.valueOf("2023-09-07");

        semester.setStartDate(startDate);
        semester.setEndDate(endDate);
        semester.setId("SEM03-22/23");

        SemesterDao semesterDao = new SemesterDao();
        String res = semesterDao.RegisterSemester(semester);
        assertEquals("success", res);

    }
}