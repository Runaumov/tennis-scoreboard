package com.runaumov.dao;

import com.runaumov.HibernateUtil;
import com.runaumov.entity.Match;
import com.runaumov.entity.Player;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class MatchesDao implements Dao<Match> {

    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    @Override
    public List<Match> findAll() {

        @Cleanup Session session = sessionFactory.openSession();
        return session.createQuery("SELECT m FROM Match m", Match.class)
                .getResultList();
    }

    @Override
    public List<Match> findAllWithPagination(int offset, int pageSize) {
        @Cleanup Session session = sessionFactory.openSession();
        return session.createQuery("SELECT m FROM Match m", Match.class)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public List<Match> findByName(String name, int offset, int pageSize) {
        @Cleanup Session session = sessionFactory.openSession();

        Player player = session.createQuery(
                        "SELECT p FROM Player p WHERE p.name = :name", Player.class)
                .setParameter("name", name)
                .uniqueResult();

        // Проверяем, найден ли игрок
        if (player == null) {
            return Collections.emptyList(); // Если игрок не найден, возвращаем пустой список
        }

        return session.createQuery(
                "SELECT m FROM Match m WHERE m.player1Id = :player OR m.player2Id = :player", Match.class)
                .setParameter("player", player)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .list();
    }

    public long countByName(String playerName) {

        @Cleanup Session session = sessionFactory.openSession();
        Player player = session.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                .setParameter("name", playerName)
                .uniqueResult();

        if (player == null) {
            return 0;
        }

        return session.createQuery("SELECT COUNT(m) FROM Match m WHERE m.player1Id = :player OR m.player2Id = :player", Long.class)
                .setParameter("player", player)
                .getSingleResult();
    }

    public long countAll() {
        @Cleanup Session session = sessionFactory.openSession();
        return session.createQuery("SELECT COUNT(m) FROM Match m", Long.class)
                .getSingleResult();
    }
}
