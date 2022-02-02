import dao.*;
import entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        TransportCompany company = new TransportCompany("EuroTruckEOOD");
        TransportCompany company2 = new TransportCompany("Speedy");

        Set<TransportCompany> companies = new TreeSet<>();
        companies.add(company);
        companies.add(company2);

        company.setProfit(BigDecimal.valueOf(5000));
        company2.setProfit(BigDecimal.valueOf(3000));

        TransportCompanyDAO.saveCompanies(companies);


        Set<Vehicle> vehicles = new TreeSet<>();
        vehicles.add(new Vehicle(1, "Dacia", VehicleType.VAN, company));
        vehicles.add(new Vehicle(2, "Mercedes",VehicleType.BUS,company));
        vehicles.add(new Vehicle(3, "Scania", VehicleType.TRUCK, company));

        Set<Vehicle> vehicles2 = new TreeSet<>();
        vehicles2.add(new Vehicle(1, "Scoda", VehicleType.VAN, company2));
        vehicles2.add(new Vehicle(2, "Renault", VehicleType.TRUCK, company2));
        vehicles2.add(new Vehicle(3, "Volkswagen", VehicleType.BUS, company2));


        VehicleDAO.saveVehicles(vehicles);
        VehicleDAO.saveVehicles(vehicles2);

        company2.setVehicles(vehicles2);

        /*
        System.out.println(vehicles);
        System.out.println("----------------");
        System.out.println(company.getVehicles());
        System.out.println("----------------");
        System.out.println(company2.getVehicles());
        */

        Category category1 = new Category(CategoryType.B);
        Category category2 = new Category(CategoryType.C);
        Category category3 = new Category(CategoryType.D);

        CategoryDAO.saveOrUpdateCategory(category1);
        CategoryDAO.saveOrUpdateCategory(category2);
        CategoryDAO.saveOrUpdateCategory(category3);

        CategoryDAO.saveCategory(new TreeSet<>(Set.of(category1, category2, category3)));

        Employee driver = new Employee(1, "Hristo", new TreeSet<>(Set.of(category1, category2)),
                company, BigDecimal.valueOf(1500));


        Employee driver2 = new Employee(2, "Dimitar", new TreeSet<>(Set.of(category1)),
                company, BigDecimal.valueOf(1000));


        Employee driver3 = new Employee(3, "Petar", new TreeSet<>(Set.of(category1,category3)),
                company2, BigDecimal.valueOf(2000));

        Employee driver4 = new Employee(4,"Salki",new TreeSet<>(Set.of(category1)),
                company2,BigDecimal.valueOf(1800));

        Employee driver5 = new Employee(5, "Petko", new TreeSet<>(Set.of(category2)),
                company,BigDecimal.valueOf(1400));

        EmployeeDAO.saveDriver(driver);
        EmployeeDAO.saveDriver(driver2);
        EmployeeDAO.saveDriver(driver3);
        EmployeeDAO.saveDriver(driver4);
        EmployeeDAO.saveDriver(driver5);

        driver.addCategories(category1);
        driver.addCategories(category2);

        driver2.addCategories(category1);

        driver3.addCategories(category1);
        driver3.addCategories(category3);

        driver4.addCategories(category1);


        EmployeeDAO.saveOrUpdateDriver(driver);
        EmployeeDAO.saveOrUpdateDriver(driver2);
        EmployeeDAO.saveOrUpdateDriver(driver3);
        EmployeeDAO.saveOrUpdateDriver(driver4);


        company.setDrivers(Set.of(driver, driver2));
        company2.setDrivers(Set.of(driver3, driver4));
        TransportCompanyDAO.saveOrUpdateCompany(company);
        TransportCompanyDAO.saveOrUpdateCompany(company2);


        Client cl1 = new Client("Boyan");

        Client cl2 = new Client("Petya");
        Transports t1 = new Transports(TransportsType.GOODS, company, driver, cl1, "54.65", "Pleven",
                "Varna", LocalDate.of(2022,1,15), LocalDate.of(2022,1,22),
                BigDecimal.valueOf(80), true);

        Transports t2 = new Transports(TransportsType.PEOPLE, company, driver2, cl1, "-", "Sofia",
                "Burgas", LocalDate.of(2022,1,10), LocalDate.of(2022,1,10),
                BigDecimal.valueOf(230), false);

        Transports t3 = new Transports(TransportsType.GOODS, company2, driver3, cl2, "50000", "Sofia",
                "Cluj", LocalDate.of(2022,1,3), LocalDate.of(2022,1,27),
                BigDecimal.valueOf(12000), true);

        Transports t4 = new Transports(TransportsType.GOODS, company2, driver4,cl2,"37.45","Pleven","Haskovo",
                LocalDate.of(2022,1,13),LocalDate.of(2022,1,30),
                BigDecimal.valueOf(250),false);


        ClientDAO.saveClient(cl1);
        ClientDAO.saveClient(cl2);

        cl1.addCompany(company);
        cl1.addOrders(t1);
        cl1.addOrders(t2);
        driver.addPackage(t1);
        driver2.addPackage(t2);

        cl2.addCompany(company2);
        cl2.addOrders(t3);
        cl2.addOrders(t4);
        driver3.addPackage(t3);
        driver4.addPackage(t4);

        TransportsDAO.saveTransports(t1);
        TransportsDAO.saveTransports(t2);
        TransportsDAO.saveTransports(t3);
        TransportsDAO.saveTransports(t4);

        EmployeeDAO.saveOrUpdateDriver(driver);
        EmployeeDAO.saveOrUpdateDriver(driver2);
        EmployeeDAO.saveOrUpdateDriver(driver3);
        EmployeeDAO.saveOrUpdateDriver(driver4);


        ClientDAO.saveOrUpdateClient(cl1);
        ClientDAO.saveOrUpdateClient(cl2);


        System.out.println("<***********************************_Sort_Drivers_***********************************>");
        System.out.println();

        EmployeeDAO.totalCarriagesByEveryDriverInCompany(1);
        System.out.println(EmployeeDAO.driversSortedBySalary(1));
        System.out.println(EmployeeDAO.driversSortedBySalary(2));
        System.out.println(EmployeeDAO.totalCarriagesByEveryDriverInCompany(1));
        System.out.println(EmployeeDAO.totalCarriagesByEveryDriverInCompany(2));
        System.out.println(EmployeeDAO.totalProfitByEveryDriverInCompany(1));
        System.out.println(EmployeeDAO.totalProfitByEveryDriverInCompany(2));


        System.out.println();

        System.out.println("*************************************************************************************");
        System.out.println();

        System.out.println("<******************************_Sort_TransportCompanies_******************************>");
        System.out.println();

        TransportCompanyDAO.companiesSortedByNameAndProfit();
        System.out.println(TransportCompanyDAO.companiesSortedByNameAndProfit());
        System.out.println(TransportCompanyDAO.totalProfitForCompany(1));
        System.out.println(TransportCompanyDAO.totalProfitForCompany(2));
        System.out.println(TransportCompanyDAO.totalProfitForCompanyForTimePeriod(1,
                LocalDate.of(2021,5,20), LocalDate.of(2022,2,2)));


        System.out.println();

        System.out.println("*************************************************************************************");
        System.out.println();

        System.out.println("<**********************************_Sort_Transports_**********************************>");
        System.out.println();

        TransportsDAO.transportsSortedByDestination();
        //System.out.println(TransportsDAO.transportsSortedByDestination());
        System.out.println(TransportsDAO.allPaidOrders());


        System.out.println();

        System.out.println("*************************************************************************************");
        System.out.println();

        System.out.println("<*************************************_SERIALIZE_*************************************>");
        System.out.println();

        ArrayList<Transports> serTransports = new ArrayList<>(List.of(t1, t2, t3));

        serTransports.forEach(Transports::serialize);
        serTransports.forEach(Transports::deserialize);

        System.out.println();
        System.out.println("*************************************************************************************");

    }

}
