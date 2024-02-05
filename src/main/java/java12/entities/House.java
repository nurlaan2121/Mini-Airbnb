package java12.entities;

import jakarta.persistence.*;
import java12.GeneratorId;
import java12.HouseType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "houses")
@Data
@ToString(callSuper = true)
@NoArgsConstructor

@SequenceGenerator(name = "generator_id",allocationSize = 1,sequenceName = "house_id")
public class House extends GeneratorId{
    @Column(name = "house_type")
    private HouseType houseType;
    private BigDecimal price;
    private Double rating;
    private String description;
    private int room;
    private boolean furniture;
    @OneToOne(mappedBy = "house",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @ToString.Exclude
    private RentInfo rentInfo;
    //0
    @OneToOne(mappedBy = "house",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @ToString.Exclude
    private Address address;
    @ManyToOne
    @ToString.Exclude
    private Owner owner;

}
