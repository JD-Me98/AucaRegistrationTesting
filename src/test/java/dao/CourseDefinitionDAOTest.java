package dao;

import model.Course;
import model.CourseDefinition;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseDefinitionDAOTest {

    @Test
    public void createCourse() {
        CourseDefinition courseDefinition = new CourseDefinition();
        courseDefinition.setName("Management Information System");
        courseDefinition.setCode("INF5612");
        courseDefinition.setDescription("Learn how to manage information");

        CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();
        String res = courseDefinitionDAO.createCourse(courseDefinition);
        assertEquals("success", res);
    }
}