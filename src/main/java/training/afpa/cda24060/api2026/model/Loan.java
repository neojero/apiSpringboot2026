package training.afpa.cda24060.api2026.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_id;

    // Relation N:1 avec Person et Book.
    // Crée des colonnes id et boo_id dans la table loan.
    @ManyToOne
    @JoinColumn(name = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "boo_id")
    private Book book;

    private LocalDate loanDate;
    private LocalDate returnDate;
    private String status; // "ACTIVE", "RETURNED", "OVERDUE"

}
