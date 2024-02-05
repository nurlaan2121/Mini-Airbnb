package java12.entities;

import jakarta.persistence.*;
import java12.GeneratorId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "agencies")
@Data
@ToString(callSuper = true)

@NoArgsConstructor
@SequenceGenerator(name = "generator_id", allocationSize = 1, sequenceName = "agency_id")
public class Agency extends GeneratorId {
    private String name;
    @Column(length = 13, name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private List<RentInfo> rentInfoList;
    @ToString.Exclude
    @OneToOne(mappedBy = "agency", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Address address;
    @ManyToMany(mappedBy = "agencies",fetch = FetchType.EAGER)
    private List<Owner> owners;

    public Agency(String name, String phoneNumber, List<RentInfo> rentInfoList, Address address, List<Owner> owners) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.rentInfoList = rentInfoList;
        this.address = address;
        this.owners = owners;
    }
    @Override
    public int hashCode() {
        return Objects.hash(address.getCity());
    }
    @Override
    public String toString() {
        return "Agency{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address.getCity() +
                '}';
    }

}
