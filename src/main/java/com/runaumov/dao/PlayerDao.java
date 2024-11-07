package com.runaumov.dao;

import com.runaumov.util.HibernateUtil;
import com.runaumov.entity.Player;
import com.runaumov.exception.DatabaseAccessException;
import lombok.Cleanup;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Optional;

public class PlayerDao {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Optional<Player> findByName(String name) {
        @Cleanup Session session = sessionFactory.openSession();

        try {
            Player player = session.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResult();

            return Optional.ofNullable(player);
        } catch (HibernateException e) {
            throw new DatabaseAccessException("Database is not responding");
        }
    }

    // TODO: добавить rollback и чекнуть остальные методы в дао
    public void addPlayer(Player player) {
        @Cleanup Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new DatabaseAccessException("Adding a player is not available");
        }
    }
}
