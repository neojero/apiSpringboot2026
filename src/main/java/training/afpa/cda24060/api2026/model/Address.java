package training.afpa.cda24060.api2026.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer add_id;
    private String street;
    private String cp;
    private String city;

    // Relation 1:1 inverse (côté "One").
    // indique que Person est le propriétaire de la relation.
    @OneToMany(mappedBy = "address")
    private Set<Person> persons = new HashSet<>();

    public Address(Integer add_id, String street, String cp, String city) {
        this.add_id = add_id;
        this.street = street;
        this.cp = cp;
        this.city = city;
    }

    public Address() {}
}
