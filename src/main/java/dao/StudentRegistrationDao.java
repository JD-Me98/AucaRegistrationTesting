package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.AcademicUnit;
import model.Course;
import model.Semester;
import model.Student;
import model.StudentRegistration;

public class StudentRegistrationDao {

	public String SaveStudentRegistration(StudentRegistration studentRegistration) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
        	tr = session.beginTransaction();
        	session.saveOrUpdate(studentRegistration);
        	tr.commit();
            return "success";
        }catch(Exception ex) {
        	ex.printStackTrace();
        	return "failed";
        }
	}
	
	public List<StudentRegistration> getStudentsBySemester(Semester semester) {

        List<StudentRegistration> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql1 = "SELECT sr FROM StudentRegistration sr WHERE sr.semester = :code";
            Query<StudentRegistration> query = session.createQuery(hql1,StudentRegistration.class);
            query.setParameter("code", semester);
            students = query.list();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
       
	}
	
	public List<StudentRegistration> getStudentsByDepartmentAndSemester(Semester semester, AcademicUnit academicUnit) {

        List<StudentRegistration> students = null;
        try (Session session =HibernateUtil.getSessionFactory().openSession()){

            String hql1 = "FROM StudentRegistration WHERE semester = :sem AND academicUnit = :dep";
            Query query = session.createQuery(hql1);
            query.setParameter("sem", semester);
            query.setParameter("dep", academicUnit);
            students = query.list();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
       
	}
	
	public List<StudentRegistration> getStudentsByCourseAndSemester(Semester semester, Course course) {

        List<StudentRegistration> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            String hql1 ="SELECT sr " +
                    " FROM StudentRegistration sr " +
                    " JOIN sr.semester sem " +
                    "JOIN sr.courses c " +
                    "WHERE sem.id = :semesterId " +
                    "AND c.id = :courseId";
            Query query = session.createQuery(hql1);
            query.setParameter("semesterId", semester.getId());
            query.setParameter("courseId", course.getId());
            students = query.list();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
   
}
	
	public List<StudentRegistration> getAllStudentRegistrations() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.close();
            return session.createQuery("FROM StudentRegistration").list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public StudentRegistration getStudentRegistrationByStudent(Student student) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql1 = "SELECT sr FROM StudentRegistration sr WHERE sr.studentId = :code";
            StudentRegistration studentRegistration =(StudentRegistration) session.createQuery(hql1, StudentRegistration.class)
                    .setParameter("code", student)
                    .uniqueResult();
            
            return studentRegistration;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
	}
	
}
