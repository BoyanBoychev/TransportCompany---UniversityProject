package dao;

import configuration.SessionFactoryUtil;
import entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class ClientDAO {

    public static void saveClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        }
    }

    public static void saveOrUpdateClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(client);
            transaction.commit();
        }
    }

    public static Client getClient(long id) {
        Client client;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.get(Client.class, id);
            transaction.commit();
        }
        return client;
    }

    public static void deleteClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
        }
    }

    public static void saveClients(Set<Client> clientSet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            clientSet.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Client> readClients() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM entities.Client a", Client.class).getResultList();
        }
    }

}
