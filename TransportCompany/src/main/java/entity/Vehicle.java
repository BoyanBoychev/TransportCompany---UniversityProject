package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Comparable<Vehicle>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VehicleType type;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private TransportCompany company;

    public Vehicle() {
    }

    public Vehicle(long id) {
        this.id = id;
    }

    public Vehicle(long id, String name, VehicleType type, TransportCompany company) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public TransportCompany getCompany() {
        return company;
    }

    public void setCompany(TransportCompany company) {
        this.company = company;
    }

    @Override
    public int compareTo(Vehicle v) {
        return this.getType().compareTo(v.getType());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", company=" + company +
                '}';
    }
}
