package com.iticbcn.ismaelyounes.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import java.util.List;
import org.hibernate.query.Query;

import com.iticbcn.ismaelyounes.model.Client;

public class ClientDAO {
    private SessionFactory sessionFactory;

    public ClientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearClient(Client client) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(client);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al guardar el Client: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Client obtenirClient(long id) {
        Client client = null;
        try (Session session = sessionFactory.openSession()) {
            client = session.find(Client.class, id);
        } catch (HibernateException e) {
            System.err.println("Error al obtenir el Client: " + e.getMessage());
        }
        return client;
    }

    public List<Client> obtenirTotsClients() {
        List<Client> clients = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Client> query = session.createQuery("from Client", Client.class);
            clients = query.list();
        } catch (HibernateException e) {
            System.err.println("Error al obtenir todos els Clients: " + e.getMessage());
        }
        return clients;
    }

    public void actualitzarClient(Client client) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(client);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al actualizar el Client: " + e.getMessage());
        }
    }

    public void eliminarClient(Client client) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(client);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al eliminar el Client: " + e.getMessage());
        }
    }
}
