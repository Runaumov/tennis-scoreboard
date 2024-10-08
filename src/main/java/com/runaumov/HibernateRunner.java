package com.runaumov;

import com.runaumov.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateRunner {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Player playerKek = Player.builder()
                    //.id(3)
                    .name("Kek")
                    .build();

            session.save(playerKek);
            Player player2 = session.get(Player.class, 2);
            int a = 1;
            session.getTransaction().commit();
        }
    }
}