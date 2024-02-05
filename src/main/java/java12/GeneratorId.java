package java12;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.ToString;

@MappedSuperclass
public class GeneratorId {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generator_id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
