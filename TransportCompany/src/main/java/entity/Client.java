package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<TransportCompany> companies;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Transports> orders;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
        this.companies = new TreeSet<>();
        this.orders = new TreeSet<>();
    }

    public Client(long id, String name, Set<TransportCompany> companies, Set<Transports> orders) {
        this.id = id;
        this.name = name;
        this.companies = companies;
        this.orders = orders;
    }

    public void addOrders(Transports transports) {
        this.orders.add(transports);
    }

    public void addCompany(TransportCompany company) {
        this.companies.add(company);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
