package com.iticbcn.ismaelyounes.dao;

import com.iticbcn.ismaelyounes.model.Restaurant;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class RestaurantDAO {
    private final SessionFactory sessionFactory;

    public RestaurantDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void crearRestaurant(Restaurant restaurant) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(restaurant);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al guardar el Restaurant: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Restaurant obtenirRestaurant(long id) {
        Restaurant restaurant = null;
        try (Session session = sessionFactory.openSession()) {
            restaurant = session.find(Restaurant.class, id);
        } catch (HibernateException e) {
            System.err.println("Error al obtener el Restaurant: " + e.getMessage());
        }
        return restaurant;
    }

    public List<Restaurant> obtenirTotsRestaurants() {
        List<Restaurant> restaurants = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Restaurant> query = session.createQuery("from Restaurant", Restaurant.class);
            restaurants = query.list();
        } catch (HibernateException e) {
            System.err.println("Error al obtener todos los Restaurants: " + e.getMessage());
        }
        return restaurants;
    }

    public void actualitzarRestaurant(Restaurant restaurant) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(restaurant);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al actualizar el Restaurant: " + e.getMessage());
        }
    }

    public void eliminarRestaurant(Restaurant restaurant) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(restaurant);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error al eliminar el Restaurant: " + e.getMessage());
        }
    }
}
