package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import model.AcademicUnit;

public class AcademicUnitDAO {

	public String saveAcademicUnit(AcademicUnit academicUnit) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
        	tr = session.beginTransaction();
        	session.saveOrUpdate(academicUnit);
        	tr.commit();
            return "success";
        }catch(Exception ex) {
        	ex.printStackTrace();
        	return "failed";
        }

    }
	
	public AcademicUnit getByCode(String code) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            AcademicUnit academicUnit =(AcademicUnit) session
                    .createQuery("FROM AcademicUnit WHERE code = :code")
                    .setParameter("code", code)
                    .uniqueResult();
            session.close();
        return academicUnit;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
