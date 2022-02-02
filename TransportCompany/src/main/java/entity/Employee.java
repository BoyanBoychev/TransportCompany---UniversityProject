package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "employees")
public class Employee implements Serializable, Comparable<Employee> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "drivers")
    private Set<Category> categories;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private TransportCompany company;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private Set<Transports> carriages;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    public Employee() {
    }

    public Employee (String name, Set<Category> categories, TransportCompany company, BigDecimal salary) {
        this.name = name;
        this.categories = categories;
        this.company = company;
        this.carriages = new TreeSet<>();
        this.salary = salary;
    }

    public Employee(long id, String name, Set<Category> categories,
                  TransportCompany company, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.categories = categories;
        this.company = company;
        this.salary = salary;
        this.carriages = new TreeSet<>();;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public TransportCompany getCompany() {
        return company;
    }

    public Set<Transports> getCarriages() {
        return carriages;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setCarriage(Set<Transports> carriages) {
        this.carriages = carriages;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategories(Category c) {
        this.categories.add(c);
    }


    public void addPackage(Transports transports) {
        this.carriages.add(transports);
    }


    @Override
    public int compareTo(Employee d) {
        return this.getName().compareTo(d.getName());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", salary=" + salary +
                '}';
    }


}
