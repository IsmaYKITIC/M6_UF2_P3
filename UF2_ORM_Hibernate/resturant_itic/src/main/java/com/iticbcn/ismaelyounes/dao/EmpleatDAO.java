package com.iticbcn.ismaelyounes.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.iticbcn.ismaelyounes.model.Empleat;

public class EmpleatDAO {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public EmpleatDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearEmpleat(Empleat empleat) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(empleat);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al guardar el Empleat: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Empleat obtenirEmpleat(long id) {
        Empleat empleat = null;
        try (Session session = sessionFactory.openSession()) {
            empleat = session.find(Empleat.class, id);
        } catch (HibernateException e) {
            System.err.println("Error al obtenir l' Empleat: " + e.getMessage());
        }
        return empleat;
    }

    public List<Empleat> obtenirTotsEmpleats() {
        List<Empleat> empleats = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Empleat> query = session.createQuery("from Empleat", Empleat.class);
            empleats = query.list();
        } catch (HibernateException e) {
            System.err.println("Error al obtenir tots els Empleats: " + e.getMessage());
        }
        return empleats;
    }

    public void actualitzarEmpleat(Empleat empleat) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(empleat);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al actualizar l' Empleat: " + e.getMessage());
        }
    }

    public void eliminarEmpleat(Empleat empleat) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(empleat);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al eliminar l' Empelat: " + e.getMessage());
        }
    }
}
