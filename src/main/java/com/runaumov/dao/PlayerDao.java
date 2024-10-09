package com.runaumov.dao;

import com.runaumov.HibernateUtil;
import com.runaumov.entity.Player;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PlayerDao implements Dao<Player> {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Optional<Player> findByName(String name) {
        @Cleanup Session session = sessionFactory.openSession();
        Player player = session.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                .setParameter("name", name)
                .uniqueResult();

        return Optional.of(player);
    }

    @Override
    public List<Player> findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        return session.createQuery("SELECT p FROM Player p", Player.class)
                .getResultList();
    }

    // TODO: добавить rollback и чекнуть остальные методы в дао
    public void addPlayer(Player player) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(player);
        session.getTransaction().commit();
    }
}
