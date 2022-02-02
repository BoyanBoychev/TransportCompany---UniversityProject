package dao;

import configuration.SessionFactoryUtil;
import entity.Employee;
import entity.Transports;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.*;

public class EmployeeDAO {

    public static void saveDriver(Employee driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(driver);
            transaction.commit();
        }
    }

    public static void saveOrUpdateDriver(Employee driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(driver);
            transaction.commit();
        }
    }

    public static Employee getDriver(long id) {
        Employee driver;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driver = session.get(Employee.class, id);
            transaction.commit();
        }
        return driver;
    }

    public static void deleteDriver(Employee driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(driver);
            transaction.commit();
            //}
        }
    }

    public static void saveDrivers(Set<Employee> drivers ) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            drivers.stream().forEach((v) -> session.save(v));
            transaction.commit();
        }
    }

    public static List<Employee> readDrivers(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM entities.Employee a", Employee.class).getResultList();
        }
    }

    public static List<Employee> getDriversByCompany(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Employee a WHERE company_id = :companyId", Employee.class)
                    .setParameter("companyId", companyId)
                    .getResultList();
        }
    }

    public static List<Employee> driversSortedBySalary(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT d FROM Employee d " +
                                    "join d.company c " +
                                    "WHERE c.id = :companyId " +
                                    "ORDER BY salary DESC", Employee.class)
                    .setParameter("companyId", companyId)
                    .getResultList();
        }
    }


    /*
    public static List<Employee> driversSortedByCategories(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT d FROM Employee d " +
                                    "join fetch d.categories " +
                                    "join d.company c " +
                                    "WHERE c.id = :companyId " +
                                    "ORDER BY size (d.categories)", Employee.class)
                    .setParameter("companyId", companyId)
                    .getResultList();
        }
    }

     */

    public static Map<Long, Integer> totalCarriagesByEveryDriverInCompany(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            List<Employee> drivers = session.createQuery(
                            "SELECT d FROM Employee d " +
                            "WHERE company_id = :companyId", Employee.class)
                    .setParameter("companyId", companyId)
                    .getResultList();

            Map<Long, Integer> result = new LinkedHashMap<>();

            for(Employee driver : drivers) {
                result.put(driver.getId(), driver.getCarriages().size());
            }
            return result;
        }
    }

    public static Map<Long, BigDecimal> totalProfitByEveryDriverInCompany(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            List<Employee> drivers = session.createQuery(
                            "SELECT d FROM Employee d " +
                                    "WHERE company_id = :companyId", Employee.class)
                    .setParameter("companyId", companyId)
                    .getResultList();

            Map<Long, BigDecimal> result = new LinkedHashMap<>();

            for(Employee driver : drivers) {
                BigDecimal driverProfit = driver.getCarriages()
                        .stream()
                        .map(Transports::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                result.put(driver.getId(), driverProfit);
            }
            return result;
        }
    }

}



