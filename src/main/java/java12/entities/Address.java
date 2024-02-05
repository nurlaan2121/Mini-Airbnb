package java12.entities;

import jakarta.persistence.*;
import java12.GeneratorId;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "addresses")
@Data
@ToString(callSuper = true)

@SequenceGenerator(name = "generator_id", allocationSize = 1, sequenceName = "address_id")
public class Address extends GeneratorId{
    private String city;
    private String region;
    private String street;
    @OneToOne
    private House house;
    @OneToOne
    @ToString.Exclude

    private Agency agency;

    @Override
    public int hashCode() {
        // Используйте только поля этого класса для hashCode
        return Objects.hash(agency.getName());
    }

}
