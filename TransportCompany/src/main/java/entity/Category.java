package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "category")
public class Category implements Comparable<Category>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @ManyToMany
    private Set<Employee> drivers;


    public Category() {
    }

    public Category(CategoryType type) {
        this.type = type;
        this.drivers = new TreeSet<>();
    }

    public Category(CategoryType type, Set<Employee> drivers) {
        this.type = type;
        this.drivers = drivers;
    }

    public long getId() {
        return id;
    }

    public CategoryType getType() {
        return type;
    }

    public void addDriver(Employee driver) {
        this.drivers.add(driver);
    }

    @Override
    public int compareTo(Category c) {
        return this.getType().compareTo(c.getType());
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
