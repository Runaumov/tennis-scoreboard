package com.runaumov.dao;

import com.runaumov.util.HibernateUtil;
import com.runaumov.entity.Match;
import com.runaumov.entity.Player;
import com.runaumov.exception.DatabaseAccessException;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class MatchDao {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Match> findAllWithPagination(int offset, int pageSize) {
        @Cleanup Session session = sessionFactory.openSession();

        try {
            return session.createQuery("SELECT m FROM Match m", Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
        } catch (HibernateException e) {
            throw new DatabaseAccessException("Error fetching matches with pagination");
        }
    }

    public List<Match> findMatchByPlayerName(String name, int offset, int pageSize) {
        @Cleanup Session session = sessionFactory.openSession();

        try {
            Player player = session.createQuery(
                            "SELECT p FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResult();

            if (player == null) {
                return Collections.emptyList();
            }

            return session.createQuery(
                            "SELECT m FROM Match m WHERE m.player1Id = :player OR m.player2Id = :player", Match.class)
                    .setParameter("player", player)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .list();
        } catch (HibernateException e) {
            throw new DatabaseAccessException("Database is not responding");
        }
    }

    public void addMatch (Match match) {
        @Cleanup Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new DatabaseAccessException("Adding a match is not available");
        }
    }

    public boolean existsById (int id) {
        @Cleanup Session session = sessionFactory.openSession();

        Long count = session.createQuery("SELECT COUNT(m) FROM Match m WHERE m.id = :id", Long.class)
                .setParameter("id", id)
                .uniqueResult();
        return count > 0;
    }

    public long countByName(String playerName) {
        @Cleanup Session session = sessionFactory.openSession();

        try {
            Player player = session.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", playerName)
                    .uniqueResult();

            if (player == null) {
                return 0;
            }

            return session.createQuery("SELECT COUNT(m) FROM Match m WHERE m.player1Id = :player OR m.player2Id = :player", Long.class)
                    .setParameter("player", player)
                    .getSingleResult();
        } catch (HibernateException e) {
            throw new DatabaseAccessException("Database is not responding");
        }
    }

    public long countAll() {
        @Cleanup Session session = sessionFactory.openSession();

        try {
            return session.createQuery("SELECT COUNT(m) FROM Match m", Long.class)
                    .getSingleResult();
        } catch (HibernateException e) {
            throw new DatabaseAccessException("Database is not responding");
        }
    }
}
