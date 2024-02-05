package java12.entities;

import jakarta.persistence.*;
import java12.FamilyStatus;
import java12.Gender;
import java12.GeneratorId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@SequenceGenerator(name = "generator_id", allocationSize = 1, sequenceName = "customer_id")
public class Customer extends GeneratorId {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private Gender gender;
    private String national;
    @Column(name = "family_status")
    private FamilyStatus familyStatus;
    @OneToMany(mappedBy = "customers",cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<RentInfo> rentInfo;
}
