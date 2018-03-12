package db;

import models.Book;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBHelper {
    private static Transaction transaction;
    private static Session session;

    public static void save(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getAll(String className){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        try{
            transaction = session.beginTransaction();
            String hql = "from " + className;
            results = session.createQuery(hql).list();
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static void update(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
        public static void delete(Object object){
            session = HibernateUtil.getSessionFactory().openSession();
            try{
                transaction =session.beginTransaction();
                session.delete(object);
                transaction.commit();
            } catch (HibernateException e){
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }

    public static List<Book> getBooks(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Book> employees = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Book WHERE author_id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            employees = query.list();
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    public static <T> List<T> getById(Object object, int id){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> result = null;
        try{
            transaction =session.beginTransaction();
            String hql = "From " + object + " where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            result = query.list();
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}



