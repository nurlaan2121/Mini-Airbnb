
package java12.entities;

import jakarta.persistence.*;
import java12.Gender;
import java12.GeneratorId;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "owners")
@Data
@ToString(callSuper = true)

@SequenceGenerator(name = "generator_id",allocationSize = 1,sequenceName = "owner_id")
public class Owner extends GeneratorId{
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private Gender gender;

    @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<RentInfo> rentInfoList;
    @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<House> houses;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Agency> agencies;

    @Override
    public String toString() {
        return "Owner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", rentInfoList=" + rentInfoList.size() +
                ", houses=" + houses.size() +
                ", agencies=" + agencies.size() +
                '}';
    }
}
