package dao;

import configuration.SessionFactoryUtil;
import entity.Transports;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class TransportsDAO {

    public static void saveTransports(Transports transports) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(transports);
            transaction.commit();
        }
    }

    public static void saveOrUpdateTransports(Transports transports) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(transports);
            transaction.commit();
        }
    }

    public static Transports getTransports(long id) {
        Transports transports;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transports= session.get(Transports.class, id);
            transaction.commit();
        }
        return transports;
    }

    public static void deleteTransports(Transports transports) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(transports);
            transaction.commit();
        }
    }

    public static void saveTransports(Set<Transports> transportsSet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportsSet.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Transports> readTransports() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT l FROM Transports l", Transports.class).getResultList();
        }
    }


    public static List<Transports> transportsSortedByDestination() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT l FROM Transports l " +
                                    "ORDER BY destination ASC", Transports.class)
                    .getResultList();
        }
    }

    public static List<Transports> allPaidOrders() {
       try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
           return session.createQuery(
                           "SELECT l FROM Transports l " +
                                   "WHERE isPaid = :isPaid", Transports.class)
                   .setParameter("isPaid", true)
                   .getResultList();
       }
    }


    public static List<Transports> allOrdersForCompanyForTimePeriod(
            long companyId,
            LocalDate start,
            LocalDate end) {

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "SELECT l FROM Transports l " +
                            "WHERE l.company.id = :id " +
                            "AND isPaid = :isPaid " +
                            "AND eta >= :startDate " +
                            "AND eta <= :endDate", Transports.class)
                    .setParameter("id", companyId)
                    .setParameter("isPaid", true)
                    .setParameter("startDate", start)
                    .setParameter("endDate", end)
                    .getResultList();
        }
    }

}
