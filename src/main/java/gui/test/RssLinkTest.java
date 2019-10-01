package gui.test;

import gui.model.RssLink;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class RssLinkTest {
    SessionFactory sessionFactory  = null;
        private Session session = null;

    public RssLinkTest() {
        this.session = createHibernateSession();
      //clearTable();
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        /**
         * Процедура создания сессии
         * @return org.hibernate.Session
         */
        private void clearTable(){
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = session.beginTransaction();
                Query query = session.createSQLQuery("DELETE FROM RSS_LINK");
                query.executeUpdate();
                //session.flush();
                tx.commit();
            }
        }
        private Session createHibernateSession()
        {
            ServiceRegistry serviceRegistry = null;
            try {
                try {
                    Configuration cfg = new Configuration().
                            addResource("rsslink.hbm.xml").configure();
                    serviceRegistry = new StandardServiceRegistryBuilder().
                            applySettings(cfg.getProperties()).build();
                    sessionFactory = cfg.buildSessionFactory(serviceRegistry);
                } catch (Throwable e) {
                    System.err.println("Failed to create sessionFactory object." + e);
                    throw new ExceptionInInitializerError(e);
                }
                session = sessionFactory.openSession();
                System.out.println("Создание сессии");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return session;
        }
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        /**
         * Процедура добавления записей в таблицу
         */
        public void recordsAdd(RssLink link)
        {
            try (Session session = sessionFactory.openSession()) {
                System.out.println("Добавление записи в таблицу БД");
                Transaction tx = session.beginTransaction();
               // Session session = sessionFactory.openSession();
                session.save(link);

                tx.commit();
                //session.close();
                System.out.println("\tЗаписи добавлены");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        /**
         * Процедура чтения записей
         */
        public List<RssLink> recordsRead()
        {
            try(Session session = sessionFactory.openSession()) {

                System.out.println("\nЧтение записей таблицы");
                String query = "select p from " + RssLink.class.getSimpleName() + " p";

                @SuppressWarnings("unchecked")
                List<RssLink> list = (List<RssLink>) session.createQuery(query).list();

                return list;
            }
        }
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        /**
         * Процедура поиска записи
         */
        private void recordFind(final int id)
        {
            System.out.println("\nЧтение записи таблицы по ID");
            RssLink link = (RssLink) session.load(RssLink.class, id);
            System.out.println(link);
        }
        public void close(){
            session.close();
        }
}
