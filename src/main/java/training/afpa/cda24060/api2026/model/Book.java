package training.afpa.cda24060.api2026.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boo_id;
    private String title;
    private String author;
    private int quantity;

    // Relation 1:N inverse avec Loan.
    @OneToMany(mappedBy = "book")  //mappedBy : nom de l'attribut dans la classe Loan
    private Set<Loan> loans = new HashSet<>();

    @ManyToOne
    private Category category; // clé etrangere ici

}
