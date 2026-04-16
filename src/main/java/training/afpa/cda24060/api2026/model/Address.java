package training.afpa.cda24060.api2026.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // pour swagger
    @Schema(description = "Identifiant unique de l'adresse", example = "1")
    private Integer add_id;
    @Schema(description = "rue de l'adresse", example = "rue du centre")
    private String street;
    @Schema(description = "code postal de l'adresse", example = "54000")
    private String cp;
    @Schema(description = "ville de l'adresse", example = "Nancy")
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
