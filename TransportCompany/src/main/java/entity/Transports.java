package entity;

import javax.persistence.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transports")
public class Transports implements Serializable, Comparable<Transports>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransportsType type;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee driver;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private TransportCompany company;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "weight", nullable = false)
    private String weight;

    @Column(name = "from_dest", nullable = false)
    private String from;

    @Column(name = "to_dest", nullable = false)
    private String destination;

    @Column(name = "date_sent", nullable = false)
    private LocalDate date;

    @Column(name = "eta", nullable = false)
    private LocalDate eta;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "isPaid", nullable = false)
    private boolean isPaid;


    public Transports() {
    }

    public Transports(long id,TransportsType type, TransportCompany company, Client client, String weight, String from,
                     String to, LocalDate date, LocalDate eta, BigDecimal price, boolean isPaid) {
        this.id = id;
        this.type = type;
        this.company = company;
        this.client = client;
        this.weight = weight;
        this.from = from;
        this.destination = to;
        this.date = date;
        this.eta = eta;
        this.price = price;
        this.isPaid = isPaid;
        this.name = "Transport" + this.getCompany().getPackageID();
        this.company.incrPackageID();
    }

    public Transports(TransportsType type, TransportCompany company, Employee driver, Client client, String weight,
                     String from, String destination, LocalDate date, LocalDate eta, BigDecimal price, boolean isPaid) {
        this.type = type;
        this.company = company;
        this.driver = driver;
        this.client = client;
        this.weight = weight;
        this.from = from;
        this.destination = destination;
        this.date = date;
        this.eta = eta;
        this.price = price;
        this.isPaid = isPaid;
        this.name = "Transport" + this.getCompany().getPackageID();
        this.company.incrPackageID();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TransportsType getType() {
        return type;
    }

    public Employee getEmployee() {
        return driver;
    }

    public TransportCompany getCompany() {
        return company;
    }

    public Client getClient() {
        return client;
    }

    public String getWeight() {
        return weight;
    }

    public String getFrom() {
        return from;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getEta() {
        return eta;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(TransportsType type) {
        this.type = type;
    }

    public void setCompany(TransportCompany company) {
        this.company = company;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setEta(LocalDate eta) {
        this.eta = eta;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void serialize() {
        String name = this.getName() + ".ser";

        File path = new File("./TransportsFiles/" + name);
        try (FileOutputStream fos = new FileOutputStream(path);

             ObjectOutputStream outputStream = new ObjectOutputStream(fos)) {

            outputStream.writeObject(this);
        }
        catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static void deserialize(Transports transports) {
        Transports tmp;
        String name = transports.getName() + ".ser";
        try(FileInputStream file = new FileInputStream("./TransportsFiles/"+name)) {
            ObjectInputStream in = new ObjectInputStream(file);
            tmp = (Transports)in.readObject();
            System.out.println(tmp);
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR");
        }
    }

    @Override
    public int compareTo(Transports t) {
        return Long.compare(this.getId(), t.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Transports{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", driver=" + driver +
                ", company=" + company +
                ", client=" + client +
                ", weight='" + weight + '\'' +
                ", from='" + from + '\'' +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                ", eta=" + eta +
                ", price=" + price +
                ", isPaid=" + isPaid +
                '}';
    }

}
