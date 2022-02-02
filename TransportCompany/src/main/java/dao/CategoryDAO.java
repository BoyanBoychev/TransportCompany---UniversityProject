package dao;

import configuration.SessionFactoryUtil;

import entity.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class CategoryDAO {

    public static void saveCategory(Category category) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        }
    }

    public static void saveOrUpdateCategory(Category category) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(category);
            transaction.commit();
        }
    }

    public static Category getCategory(long id) {
        Category category;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            category = session.get(Category.class, id);
            transaction.commit();
        }
        return category;
    }

    public static void deleteCategory(Category category) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(category);
            transaction.commit();
        }
    }

    public static void saveCategory(Set<Category> categorieSet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            categorieSet.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Category> readCategory() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Category a", Category.class).getResultList();
        }
    }
}
