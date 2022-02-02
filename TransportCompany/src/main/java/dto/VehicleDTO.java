package dto;

import entity.VehicleType;

public class VehicleDTO {

    public long id;

    private String name;

    private String plateNumber;

    private VehicleType type;

    public VehicleDTO() {
    }

    public VehicleDTO(long id, String name, String plateNumber, VehicleType type) {
        this.id = id;
        this.name = name;
        this.plateNumber = plateNumber;
        this.type = type;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", type=" + type +
                '}';
    }
}
