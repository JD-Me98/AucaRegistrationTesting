package dao;

import model.Course;
import model.CourseDefinition;
import model.EQualification;
import model.Teacher;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeacherDaoTest {

    @Test
    public void registerTeacher() {
        Teacher teacher = new Teacher();
        teacher.setNames("Munezero");
        teacher.setCode("TR-004");
        teacher.setQualification(EQualification.PHD.name());

        CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();
        CourseDefinition courseDefinition = courseDefinitionDAO.getCourseDefinitionById("INF5612");

        CourseDao courseDao = new CourseDao();
        Course course = courseDao.getCourseByCode(courseDefinition);

        teacher.setCourses(course);

        TeacherDao teacherDao = new TeacherDao();
        String res = teacherDao.RegisterTeacher(teacher);

        assertEquals("success", res);
    }
}