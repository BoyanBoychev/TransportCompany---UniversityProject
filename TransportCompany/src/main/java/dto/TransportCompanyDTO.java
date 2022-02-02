package dto;

import java.math.BigDecimal;

public class TransportCompanyDTO {

    private long id;

    private String name;

    private BigDecimal profit;

    public TransportCompanyDTO() {
    }

    public TransportCompanyDTO(long id, String name, BigDecimal profit) {
        this.id = id;
        this.name = name;
        this.profit = profit;
    }

    public TransportCompanyDTO(String name, BigDecimal profit) {
        this.name = name;
        this.profit = profit;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        return "TransportCompanyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profit=" + profit +
                '}';
    }
}
