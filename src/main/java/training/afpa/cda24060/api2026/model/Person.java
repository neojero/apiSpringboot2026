package training.afpa.cda24060.api2026.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data // lombok
@Entity // création d'une entite
@Table(name="Person") // lié a la table dans la BDD
public class Person {

    @Id // clé primaire
    @Column(name="id") // lié à la colonne id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // auto-increment
    private Integer id;

    @NotNull(message = "le prénom est obligatoire")
    @Size(min = 2, max = 50, message = "le prénom doit faire entre 2 et 50 caractères")
    @Column(name="firstname") // lié a la colonne de la table [pas obligatoire]
    private String firstname;

    @NotNull(message = "le nom est obligatoire")
    @Size(min = 2, max = 50, message = "le nom doit faire entre 2 et 50 caractères")
    @Column(name="lastname") // lié a la colonne de la table [pas obligatoire]
    private String lastname;

    // cascade : Propage les opérations (save, update, delete) de Person à Address. Si tu sauvegardes une Person, son Address sera aussi sauvegardé.
    // orphanRemoval : Supprime l’Address si elle n’est plus référencée par une Person.
    @ManyToOne()
    // Spécifie le nom de la colonne de jointure.
    @JoinColumn(name = "add_id", nullable = false)
    private Address address;

    // liste des emprunts
    @OneToMany(mappedBy = "person")  //mappedBy : nom de l'attribut dans la classe Loan
    private Set<Loan> loans = new HashSet<>();

}

/*

La vraie règle pour passer de Merise à JPA

👉 On regarde le côté N pour savoir si c’est ManyToOne.

Dans ton modèle :

Person (1,1) —— (0,N) Address

Cela signifie :

Plusieurs Person peuvent référencer la même Address

Donc clé étrangère dans Person

Donc :

@ManyToOne


 */