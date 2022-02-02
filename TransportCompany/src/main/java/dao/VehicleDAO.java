package dao;

import configuration.SessionFactoryUtil;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Set;


public class VehicleDAO {

    public static void saveVehicle(Vehicle vehicle, long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(vehicle);
            transaction.commit();
        }
    }

    public static void saveOrUpdateVehicle(Vehicle vehicle, long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(vehicle);
            transaction.commit();
        }
    }

    public static Vehicle getVehicle(long id) {
        Vehicle vehicle;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.get(Vehicle.class, id);
            transaction.commit();
        }
        return vehicle;
    }

    public static void deleteVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(vehicle);
                transaction.commit();

        }
    }

    public static void saveVehicles(Set<Vehicle> vehicleSet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicleSet.stream().forEach((v) -> session.save(v));
            transaction.commit();
        }
    }

    public static List<Vehicle> readVehicles() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM entities.Vehicle a", Vehicle.class).getResultList();
        }
    }
}
