package java12.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "rent")
@Data
@ToString
@SequenceGenerator(name = "generator_id",allocationSize = 1,sequenceName = "address_id")
public class RentInfo {
}
