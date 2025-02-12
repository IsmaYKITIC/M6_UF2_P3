package com.iticbcn.ismaelyounes.dao;

import com.iticbcn.ismaelyounes.model.Reserva;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ReservaDAO {
    private final SessionFactory sessionFactory;

    public ReservaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearReserva(Reserva reserva) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(reserva);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al guardar la Reserva: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Reserva obtenirReserva(long id) {
        Reserva reserva = null;
        try (Session session = sessionFactory.openSession()) {
            reserva = session.find(Reserva.class, id);
        } catch (HibernateException e) {
            System.err.println("Error al obtener la Reserva: " + e.getMessage());
        }
        return reserva;
    }

    public List<Reserva> obtenirTotesReserves() {
        List<Reserva> reserves = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Reserva> query = session.createQuery("from Reserva", Reserva.class);
            reserves = query.list();
        } catch (HibernateException e) {
            System.err.println("Error al obtener todas las Reservas: " + e.getMessage());
        }
        return reserves;
    }

    public void actualitzarReserva(Reserva reserva) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(reserva);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al actualizar la Reserva: " + e.getMessage());
        }
    }

    public void eliminarReserva(Reserva reserva) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(reserva);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al eliminar la Reserva: " + e.getMessage());
        }
    }
}
