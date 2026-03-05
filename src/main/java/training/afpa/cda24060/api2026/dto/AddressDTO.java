package training.afpa.cda24060.api2026.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import training.afpa.cda24060.api2026.model.Address;
import training.afpa.cda24060.api2026.model.Person;

import java.util.stream.Collectors;

@Data
public class AddressDTO {

    private int add_id;
    @NotBlank(message = "La rue est obligatoire")
    private String street;
    @NotBlank(message = "Le code postal est obligatoire")
    private String cp;
    @NotBlank(message = "La ville est obligatoire")
    private String city;

    public AddressDTO() {}

    public AddressDTO(Address address) {
        this.add_id = address.getAdd_id();
        this.street = address.getStreet();
        this.cp = address.getCp();
        this.city = address.getCity();
    }
}
