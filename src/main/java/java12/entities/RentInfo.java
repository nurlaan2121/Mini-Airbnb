package java12.entities;

import jakarta.persistence.*;
import java12.GeneratorId;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "rent_Infoes")
@Data
@ToString(callSuper = true)

@SequenceGenerator(name = "generator_id",allocationSize = 1,sequenceName = "rentInfo_id")
public class RentInfo extends GeneratorId{
    @Column(name = "check_in")
    private Date checkIn;
    @Column(name = "check_out")
    private Date checkOut;
    //0
    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customers;
    @ManyToOne
    private Owner owner;
    @OneToOne
    private House house;
    @ManyToOne
    private Agency agency;

}
