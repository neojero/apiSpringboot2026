package training.afpa.cda24060.api2026.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import training.afpa.cda24060.api2026.model.Person;


@Data
public class PersonDTO {

    private Integer id;
    private String firstname;
    private String lastname;

    @Valid  // Valide aussi les champs de AddressDTO
    @NotNull(message = "L'adresse est obligatoire")
    private AddressDTO address;

    public PersonDTO() {}

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.address = new AddressDTO(person.getAddress());
    }

}
