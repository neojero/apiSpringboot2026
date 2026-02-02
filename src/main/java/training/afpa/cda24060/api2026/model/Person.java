package training.afpa.cda24060.api2026.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // lombok
@Entity // création d'une entite
@Table(name="Person") // lié a la table dans la BDD
public class Person {

    @Id // clé primaire
    @Column(name="id") // lié à la colonne id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // auto-increment
    private Integer id;

    @Column(name="firstname") // lié a la colonne de la table [pas obligatoire]
    private String firstname;

    @Column(name="lastname") // lié a la colonne de la table [pas obligatoire]
    private String lastname;

}
